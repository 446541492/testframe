package com.yunshan.testframe.base;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.yunshan.testframe.util.base.DateEditor;


/**
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @version 1.0.0, 2016/6/3. 16:26
 */
public class BaseController {
	@Autowired
	protected HttpSession session;

	public static String out(String msg, HttpServletResponse res) {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(msg);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 
	 * @Description: 输出Excel
	 * @param res
	 * @param book
	 * @param fileName
	 * @param @return
	 * @return String
	 * @throws
	 * @author <a href="http://wanglay.com">Lei Wang</a>
	 * @date 2016年6月22日
	 */
	public static String outExcel(HttpServletResponse res, HSSFWorkbook book,
			String fileName) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			book.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		res.reset();
		res.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			res.setHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		ServletOutputStream out = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			out = res.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
