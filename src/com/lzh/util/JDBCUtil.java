package com.lzh.util;
import java.io.IOException;
/*
 /* 连接数据库的具体操作
 * 相当于一个连接和关闭数据库的工具
 * 为了减少代码冗杂
 * 存放一些数据库的配置文件
 * */
import java.sql.*;
import java.util.Properties;
/**
 * 
 * @Description 数据库的连接和关闭工具
 * @author 林泽鸿
 * @date 2019年4月18日
 */
public  class JDBCUtil {
	
	/**
	 * 获取数据库连接
	 * 数据库的配置在配置文件中(db.properties)
	 * @return
	 * @throws Exception
	 */
	//读取和处理资源文件中的信息
	static Properties pros = null;
	//加载JDBCUtil类的时候调用，static代码块只用一次
	static 
	{
		pros = new Properties();
		try 
			{
				//输入流读取资源文件
				pros.load(JDBCUtil.class.getResourceAsStream("/db.properties"));
			}
		catch (IOException e) 
			{
				e.printStackTrace();
				System.out.println("util------加载properties失败");
			}
	}

	/**
	 * @Description 得到数据库连接 
	 * @return con
	 */
	  public static Connection getCon(){ 
	  try
		  {
		  		Class.forName(pros.getProperty("jdbcName"));
		  } 
	  catch (ClassNotFoundException e) 
		 { 
			  e.printStackTrace();
			  System.out.println("util-----jdbcName寻找数据库连接失败"); 
		  } 
	  Connection con; 
	  try 
	  	{
		  	con = DriverManager.getConnection(pros.getProperty("db_URL"),pros.getProperty("db_User"), pros.getProperty("db_Password")); 
		  	return con;
		  }
	  catch (SQLException e)
	  	{
		  	e.printStackTrace(); 
		  	System.out.println("util--------加载JDBCUtil失败"); 
		  	return null; 
		 }
	  }
	 

	  /**
	   * @Description   关闭三个开关
	   * @param rs
	   * @param st
	   * @param con
	   */
	public static void close(ResultSet rs,PreparedStatement st,Connection con) {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}


/**
 * @Description  关闭连接，关两个开关
 * @param st
 * @param con
 */
	public static void close(PreparedStatement st,Connection con){
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description 关闭一个连接的开关
	 * @param con
	 */
	public static void close( Connection con){
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

