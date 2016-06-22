package com.yunshan.testframe.controller.upload;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.yunshan.testframe.CommonConfig;
import com.yunshan.testframe.base.BaseController;
import com.yunshan.testframe.beans.Muser;
import com.yunshan.testframe.util.WebUtil;
import com.yunshan.testframe.util.base.StringUtils;


/**
 * 图片上传处理类
 * 
 * @author wanglei
 */
@Controller
@RequestMapping(value="/upload")
public class UploadImgController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(UploadImgController.class);

	/**
	 * 图片上传，需先登录
	 * 
	 * @param res
	 * @param session
	 * @param classify
	 *            图片路径分类子路径文件夹
	 */
	@RequestMapping("/image")
	public void up1(String classify, String imgWH, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException
	{

		Muser user = (Muser) WebUtil.getSessionUser(session);
		MultipartHttpServletRequest multipartRequest = ((MultipartHttpServletRequest) req);
		Iterator<String> it = multipartRequest.getFileNames();
		// 最大文件大小
		long maxSize = 1000000;
		String upFileName = "";
		while (it.hasNext())
		{
			upFileName = (String) it.next();
			break;
		}
		MultipartFile file = multipartRequest.getFile(upFileName);
		if (file != null && !file.isEmpty() && file.getSize() > 0
		// &&file.getContentType().matches("^image/.*")//flash上传格式一样，所有文件父属性
		)
		{// 图片文件
			JSONObject msg = new JSONObject();
			// 获得用户ID
			int uid = user.getId();
			long size = file.getSize();
			if (size > maxSize)
			{
				msg.put("status", 0);
				msg.put("msg", "图片不能超出" + maxSize);
			} else
			{
				String name = file.getOriginalFilename();
				String hz = gethz(name);
				logger.debug("*******************size=" + size + ",   getFieldName=" + name + ",   后缀=" + hz);
				String uuid = UUID.randomUUID().toString().replaceAll("-", "").toString();
				// 获得项目根路径
				String realPath = session.getServletContext().getRealPath("");
				String imageDir = "/uploadImg/";
				if (classify != null && classify.length() > 0)
				{
					imageDir += classify + '/';
				}
				imageDir += uid + "/";
				realPath = realPath + imageDir;
				String fileName = uuid + hz;
				logger.debug("realPath=" + realPath + "  ,imageDir=" + imageDir + "   ,fileName=" + fileName);
				try
				{
					outFile(file.getInputStream(), realPath + "/", fileName);
					if (imgWH != null && "1".equals(imgWH))
					{
						BufferedImage bufferedImg = ImageIO.read(file.getInputStream());
						int width = bufferedImg.getWidth();
						int height = bufferedImg.getHeight();
						msg.put("width", width);
						msg.put("height", height);
					}
					msg.put("status", 1);
					msg.put("imagePath", imageDir + fileName);
				} catch (Exception e)
				{
					logger.error("上传异常Upload", e);
					msg.put("status", 0);
					msg.put("msg", "上传异常Upload");
				}
			}
			out(msg.toJSONString(), res);
		} else
		{
			logger.debug("Upload error no file !没有上传图片文件");
		}
	}

	/**
	 * 参数 type 上传图片的模块分类 根据用户ID和模块保存图片 用于图文编辑器
	 */
	@RequestMapping("/uploadJson")
	public void uploadJson(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// User user = (User)request.getSession().getAttribute("loginUser");
		// 如果有配置目标位置，就放目标文件夹下，如果没有就放本项目发布WEBROOT下
		// String realPath =
		// !"".equalsIgnoreCase(session.getAttribute("imgPath")
		// .toString()) ? session.getAttribute("imgPath").toString() : session
		// .getServletContext().getRealPath("");
		String realPath = session.getServletContext().getRealPath("");
		String type = (String) request.getAttribute("type");
		String savePath = realPath + "/uploadImg/attached/";

		// 文件保存目录URL
		// String saveUrl =
		// (!"".equalsIgnoreCase(session.getAttribute("imgDomain")
		// .toString()) ? session.getAttribute("imgDomain").toString() :
		// request.getContextPath()) + "/uploadImg/attached/";
		String saveUrl = CommonConfig.fileServer + "/uploadImg/attached/";
		if (!StringUtils.isNullString(type))
		{
			savePath += type;
			saveUrl += type;
		}
		Writer out = response.getWriter();
		// 文件保存目录路径

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 1000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request))
		{
			out.write(getError("请选择文件。"));
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory())
		{
			uploadDir.mkdirs();
		}
		// 检查目录写权限
		if (!uploadDir.canWrite())
		{
			out.write(getError("上传目录没有写权限。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null)
		{
			dirName = "image";
		}
		if (!extMap.containsKey(dirName))
		{
			out.write(getError("目录名不正确。"));
			return;
		}
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists())
		{
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists())
		{
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = null;
		try
		{
			items = upload.parseRequest(request);
		} catch (FileUploadException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Iterator itr = items.iterator();
		while (itr.hasNext())
		{
			FileItem item = (FileItem) itr.next();

			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField())
			{
				// 宽高
				// BufferedImage sourceImg =ImageIO.read(
				// item.getInputStream());
				// Float pw=(float) sourceImg.getWidth();
				// Float ph = (float) sourceImg.getHeight();
				// Float w=Float.parseFloat(request.getParameter("width"));
				// Float h=Float.parseFloat(request.getParameter("height"));
				// if((pw/ph)!=(w/h)){
				// out.write(getError("上传文件不符合要求。"));
				// return;
				// }
				// 检查文件大小
				if (item.getSize() > maxSize)
				{
					out.write(getError("上传文件大小超过限制。"));
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt))
				{
					out.write(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				try
				{
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				} catch (Exception e)
				{
					out.write(getError("上传文件失败。"));
					return;
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				out.write(obj.toJSONString());
			}
		}
	}

	private String getError(String message)
	{
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	/**
	 * @param ioput
	 *            文件输入流
	 * @param path
	 *            文件存放路径 注 ： dsasda/dsf/ 最后斜杠结尾
	 * @param name
	 *            文件包括名称 注 sdfasdfasd.jpg不带前斜杠
	 * @return
	 */
	private Boolean outFile(InputStream ioput, String path, String name)
	{// /uploadImg/xsh/111/aaaa
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		File realFile = new File(path);
		if (!realFile.exists())
			realFile.mkdirs();
		try
		{
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(ioput);
			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(path + name));
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1)
			{
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} catch (FileNotFoundException a)
		{
			a.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			// 关闭流
			try
			{
				if (inBuff != null)
					inBuff.close();
				if (outBuff != null)
					outBuff.close();
			} catch (IOException c)
			{
				c.printStackTrace();
			}
		}
		return true;
	}

	/*
	 * 拿到图片的后缀
	 */
	private String gethz(String str)
	{
		int a = str.lastIndexOf(".");
		if (a == -1)
			return "";
		else
		{
			return (str.trim()).substring(a, str.length());
		}
	}

	/**
	 * 图片切割后名字
	 * 
	 * @param src
	 * @param destname
	 * @return
	 */
	private String getdest(String src, String destname)
	{
		int a = src.lastIndexOf(".");
		if (a == -1)
			return "";
		else
		{
			String hz = (src.trim()).substring(a, src.length());
			String qd = (src.trim()).substring(0, a);
			return qd + destname + hz;
		}
	}

}
