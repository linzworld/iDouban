package com.lzh.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;
/**
 * @Description 数据库连接池 
 * @author 林泽鸿
 * @date 2019年4月19日
 */
public class MyDataSource implements DataSource
{

	/**
	 * 数据库连接池的大小
	 */
	private final static int POOL_SIZE=5;
	/**
	 * 1.创建一个容器，用于储存connection连接对象
	 * 
	 */
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	/**
	 * 2.创建5个连接对象到容器中
	 */
	static {
		for(int i = 0;i< POOL_SIZE; i++)
		{
			Connection conn = JDBCUtil.getCon();
			pool.add(conn);
		}

		
	}
	/**
	 * 重写，获取连接的方法
	 */
	@Override
	public Connection getConnection() throws SQLException
	{
		Connection conn = null; 
		/**3.使用前先判断
		数据库池无连接对象，若已满，则再次创建对象
		*/
		if(pool.size()==0) {
			for(int i = 0;i<POOL_SIZE; i++)
			{
			conn = JDBCUtil.getCon();
			pool.add(conn);
			}
		}
		//5。从连接池中获取一个连接对象connection，拿到一个连接对象
		conn = pool.remove(0);
		//从连接池中移除，即让conn拿到一个对象
		return conn;
	}
	/**
	 * 归还连接对象到连接池
	 */
	public void backConnection(Connection conn) {
		pool.add(conn);
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Connection getConnection(String username, String password) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
