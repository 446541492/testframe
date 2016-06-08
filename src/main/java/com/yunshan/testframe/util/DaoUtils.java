package com.yunshan.testframe.util;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *@author wangl
 *实体类生成工具
 */
public class DaoUtils {
	static	final String  	databaseName="dbtest";
	static  final String    username="testuser1";
	static	final String 	pass="test#2015TU";
	static	final String 	packageName="testframe";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			getConnection();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("ok");
		createEntity("users");

//		createDAO("User");
//		insert();
//		getnames();

//		String path = DaoUtils.class.getResource("/").getPath().replace("/target/classes/", "");
//		String daoPath = path.substring(1, path.length())+"/src/main/java/com/job36/dao/";
//		String mapperPath = path.substring(1, path.length())+"/src/main/resources/mapper";
//		String servicePath = path.substring(1, path.length())+"/src/main/java/com/job36/service/";
//		String serviceImplPath = path.substring(1, path.length())+"/src/main/java/com/job36/service/impl";
//		

	}
	
	
	/**
	 * 创建 Dao\Service\ServiceImpl\Controller\Mapper
	 * 如果存在，则不创建，避免覆盖，需要覆盖请自行调整
	 * @param clazzName
	 */
	private static void createDAO(String clazzName) {
		String path = DaoUtils.class.getResource("/").getPath().replace("/WebRoot/WEB-INF/classes/", "");
		String daoPath = path.substring(1, path.length())+"/src/com/yunshan/dao/";
		String mapperPath = path.substring(1, path.length())+"/src/mapper";
		String servicePath = path.substring(1, path.length())+"/src/com/yunshan/service/";
		String implPath = path.substring(1, path.length())+"/src/com/yunshan/service/impl";
		
		clazzName = clazzName.replace("_", "");
		clazzName =clazzName.substring(0, 1).toUpperCase() + clazzName.substring(1);
//		String conPath = "com\\yunshan\\controller\\";
		
		String mapperName = clazzName+"Mapper";
		String daoName = clazzName+"Dao";
		String serviceName = clazzName+"Service";
		String implName = clazzName+"ServiceImpl";
//		String controllerName = clazzName+"Controller";
		
		File mapperFile = new File(mapperPath,mapperName+".xml");
//		File conFile = new File(base+conPath+controllerName+".java");
		File daoFile = new File(daoPath,daoName+".java");
		File serFile = new File(servicePath,serviceName+".java");
		File implFile = new File(implPath,implName+".java");
		
		if(daoFile.exists()) {
			System.out.println("DaoUtils.createDAO() 已经存在:"+daoName);
			return;
		}
		
//		StringBuffer con = new StringBuffer();
//		con.append("package com.job36.controller;\n");
//		con.append("\n");
//		con.append("\n");
//		con.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
//		con.append("import org.springframework.beans.factory.annotation.Autowired;\n");
//		con.append("import org.springframework.stereotype.Controller;\n");
//		con.append("import com.job36.service."+clazzName+"Service;\n");
//		con.append("\n");
//		con.append("\n");
//		con.append("@Controller\n");
//		con.append("@RequestMapping(\"/"+clazzName.substring(0,1).toLowerCase()+clazzName.substring(1)+"\")\n");
//		con.append("public class "+controllerName+" extends BaseController {\n");
//		con.append("\n");
//		con.append("\t@Autowired\n");
//		con.append("\tprivate "+clazzName+"Service "+clazzName.substring(0,1).toLowerCase()+clazzName.substring(1)+"Service;");
//		con.append("\n");
//		con.append("\n");
//		con.append("\t@RequestMapping\n");
//		con.append("\tpublic String execute() {\n");
//		con.append("\n");
//		con.append("\t\treturn null;\n");
//		con.append("\t}\n");
//		con.append("\n");
//		con.append("\n");
//		con.append("\n");
//		con.append("}");
//		
//		try {
//			FileUtils.writeStringToFile(conFile, con.toString(), "utf-8");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		StringBuffer dao = new StringBuffer();
		dao.append("package com.yunshan."+packageName+".dao;\n\n");
		dao.append("import org.springframework.stereotype.Repository;\n");
		dao.append("import com.yunshan."+packageName+".beans."+clazzName+";\n");
		dao.append("import com.yunshan."+packageName+".base.GenericDao;\n\n");
		dao.append("@Repository\n");
		dao.append("public interface "+daoName+" extends GenericDao<"+clazzName+", Integer>  {\n");
		dao.append("\n");
		dao.append("}");
		
		try {
			FileUtils.writeStringToFile(daoFile, dao.toString(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuffer ser = new StringBuffer();
		ser.append("package com.yunshan."+packageName+".service;\n\n");
		ser.append("import com.yunshan."+packageName+".base.BaseService;\n");
		ser.append("import com.yunshan."+packageName+".beans."+clazzName+";\n\n");
		ser.append("public interface "+serviceName+"  extends BaseService<"+clazzName+", Integer>{\n");
		ser.append("\n");
		ser.append("}");
		
		try {
			FileUtils.writeStringToFile(serFile,ser.toString(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuffer impl = new StringBuffer();
		impl.append("package com.yunshan."+packageName+".service.impl;\n\n");
		impl.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		impl.append("import org.apache.commons.logging.Log;\n");
		impl.append("import org.apache.commons.logging.LogFactory;\n");
		impl.append("import org.springframework.stereotype.Service;\n");
		impl.append("import com.yunshan."+packageName+".base.GenericDao;\n");
		impl.append("import com.yunshan."+packageName+".base.GenericService;\n");
		impl.append("import com.yunshan."+packageName+".beans."+clazzName+";\n\n");
		impl.append("import com.yunshan."+packageName+".dao."+daoName+";\n");
		impl.append("import com.yunshan."+packageName+".service."+serviceName+";\n\n");
		impl.append("@Service(\""+serviceName.substring(0,1).toLowerCase()+serviceName.substring(1)+"\")\n");
		impl.append("public class "+implName+" extends "+"GenericService<"+clazzName+", Integer> "+" implements "+serviceName+" {\n");
		impl.append("\n");
		impl.append("\tprivate static Log logger = LogFactory.getLog("+implName+".class);\n");
		impl.append("\t@Autowired\n");
		impl.append("\tprivate "+daoName+" "+daoName.substring(0,1).toLowerCase()+daoName.substring(1)+";\n");
		impl.append("\t@Override\n");
		impl.append("\tpublic GenericDao<"+clazzName+", Integer> getDao() {\n");
		impl.append("\t	return "+daoName.substring(0,1).toLowerCase()+daoName.substring(1)+";\n");
		impl.append("\t}\n");
		impl.append("\n");
		impl.append("\n");
		impl.append("}");
		
		try {
			FileUtils.writeStringToFile(implFile, impl.toString(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuffer mapper = new StringBuffer();
		mapper.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		mapper.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n");
		mapper.append("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
		mapper.append("<mapper namespace=\"com.yunshan."+packageName+".dao." + daoName + "\">\n");
		mapper.append("\n");
		mapper.append("\n");
		mapper.append("\n");
		mapper.append("</mapper>\n");
		
		try {
			FileUtils.writeStringToFile(mapperFile, mapper.toString(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("DaoUtils.createDAO() ok");
	}
	
	
	/**
	 * 创建实体类
	 * 如果存在，则不创建，避免覆盖，需要覆盖请自行调整
	 * @param tableName
	 */
	private static void createEntity(String tableName) {
		String sql = "select * from "+tableName;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSetMetaData psd = null;
		String className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
		className = className.replace("_", "");
		if(tableName.startsWith("[")){
			className = tableName.substring(1,tableName.length()-1);
		}
		try {
			
			Map<String, String> map = getRemarks(tableName);
			
			conn = getConnection();
			ps = conn.prepareStatement(sql);  
            psd = ps.getMetaData();  
            int size = psd.getColumnCount();//共有多少列  
            
            StringBuffer sb = new StringBuffer();
            StringBuffer p = new StringBuffer();
            StringBuffer g = new StringBuffer();
            sb.append("package com.yunshan."+packageName+".beans;");
            sb.append("\n\n");
            sb.append("import java.io.Serializable;\n");
            sb.append("#import#");
            sb.append("\n");
            sb.append("/**\n");
            sb.append("*@author wangl\n");
            sb.append("*/\n");
            sb.append("@SuppressWarnings(\"serial\")\n");
            sb.append("public class "+className+" implements Serializable{ \n\n");
            
            String imp = "";
            
            for(int i=1;i<=size;i++) {
            	String name = psd.getColumnName(i).substring(0,1).toUpperCase()+ psd.getColumnName(i).substring(1);
            	String type = psd.getColumnTypeName(i);
            	
            	String preName = name.substring(0,1).toLowerCase();
            	String javaName =  preName + name.substring(1);
            	String javaType = "int".equalsIgnoreCase(type)||"INT UNSIGNED".equalsIgnoreCase(type)?"Integer":"varchar".equalsIgnoreCase(type)?"String":"datetime".equalsIgnoreCase(type)?"Date":"String";
            	
            	if("Date".equals(javaType)) {
            		if("".equals(imp)) {
            			imp = "import java.util.Date;";
            		}
            	}
            	
            	String remark = map.get(javaName);
            	if(null != remark){
            		p.append("/** " +remark+ " */\n");
            	}
            	p.append("\tprivate "+javaType+" "+javaName+";\n");
            	
            	/** setter */
            	g.append("\tpublic void set"+name+"("+javaType+" "+javaName+") {\n");
            	g.append("\t\tthis."+javaName+"="+javaName+";\n");
            	g.append("\t}\n");
            	/** getter */
            	g.append("\tpublic "+javaType+" get"+name+"() {\n");
            	g.append("\t\treturn this."+javaName+";\n");
            	g.append("\t}\n");
            	
            }
            sb.append(p);
            sb.append("\n");
            sb.append(g);
            sb.append("}");
            
            String data = sb.toString().replace("#import#", imp);
            String path = DaoUtils.class.getResource("/").getPath().replace("/webapp/WEB-INF/classes/", "").replace("/target/classes/", "");
    		path = path.substring(1, path.length())+"/src/main/java/com/yunshan/"+packageName+"/beans/";
    		
            FileUtils.writeStringToFile(new File(path,className+".java"), data, "utf-8");
            System.out.println("DaoUtils.createEntity() ok");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(null != conn){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private static Connection getConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.10.245:3306/"+databaseName+"?useUnicode=true&characterEncoding=UTF-8";
		Connection con = null;
		Class.forName(driver);
		con = DriverManager.getConnection(url, username, pass);
		return con;
	}
	private static void insert(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer(); 
		sql.append(" insert into aaa(name) values('王磊')");
//		sql.append(" left join syscolumns b on  a.major_id=b.id and a.minor_id=b.colid ");
//		sql.append(" where a.major_id = (select id from sysobjects where name= '"+tableName+"' )");
//		sql.append(" order by b.colid ");
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			int n = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
	}
private static void getnames() {
		
		Map<String, String> map = new HashMap<String, String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer(); 
		sql.append(" select name from aaa");
//		sql.append(" left join syscolumns b on  a.major_id=b.id and a.minor_id=b.colid ");
//		sql.append(" where a.major_id = (select id from sysobjects where name= '"+tableName+"' )");
//		sql.append(" order by b.colid ");
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if(null != rs) {
				while(rs.next()) {
					String columnName = rs.getString("name");
					System.out.println(columnName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
	}
	private static Map<String, String> getRemarks(String tableName) {
		
		Map<String, String> map = new HashMap<String, String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer(); 
		sql.append(" select COLUMN_NAME as columnName ,COLUMN_COMMENT as remarks  from INFORMATION_SCHEMA.Columns where table_name='"+tableName+"' and table_schema='"+databaseName+"'");
//		sql.append(" left join syscolumns b on  a.major_id=b.id and a.minor_id=b.colid ");
//		sql.append(" where a.major_id = (select id from sysobjects where name= '"+tableName+"' )");
//		sql.append(" order by b.colid ");
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if(null != rs) {
				while(rs.next()) {
					String columnName = rs.getString("columnName");
					String remarks = rs.getString("remarks");
					if(null == columnName || "".equals(columnName)) {
						columnName = tableName;
					}
					map.put(columnName, remarks);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return map;
	}
	
	
	/**
	 * 关闭数据库连接
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void close(ResultSet rs ,Statement st, Connection conn) {
		
		if(null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if(null != st) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st = null;
			}
		}
		if(null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}
