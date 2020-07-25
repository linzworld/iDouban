package com.lzh.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.DoumailDao;
import com.lzh.entity.Doumail;
import com.lzh.util.MyDataSource;
/**
 * @Description 关于豆邮的数据库操作 
 * @author 林泽鸿
 * @time 2019年5月14日 上午5:07:25
 */
public class DoumailDaoImpl implements DoumailDao
{
	
	private Connection con;
	
	private PreparedStatement stmt;	
	
	
	//新建一个数据库连接池对象
	MyDataSource dataSource = new MyDataSource();
	
	
	/**
	 *SELECT--- 在doumail表中查找当前用户id收到的豆邮记录，得到其记录总数
	 */
	@Override
	public int getTotalCount( int userId )
	{
		//总数据量
		int count = -1;
		try {
			//获取连接池连接
			con = dataSource.getConnection();
			//查询数据库中记录的总数量---满足的条件---当前用户是豆邮的接收者
			String sql = "SELECT  count(1) FROM doumail WHERE  to_user_id = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) //若查询结果不为空
			{
				//总数据量
			count = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("DoumailDaoImpl---getTotalCount--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return count;
	}

	/**
	 * SELECT--查询当前用户的收到的豆邮的数据集合
	 */
	@Override
	public List<Doumail> queryDoumailByPage( int currentPage, int pageSize ,int userId )
	{
		List<Doumail> doumails = null;
		doumails = new ArrayList<Doumail>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = "SELECT * FROM doumail WHERE to_user_id = ?  ORDER BY chat_time DESC  limit ? offset ?";
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			
			stmt.setInt(2, pageSize);
			stmt.setInt(3, begin);
			ResultSet rs = stmt.executeQuery();
			//拿到数据集合
			while(rs.next()){
				Doumail doumail = new Doumail();
				//辨别一条记录的值---拿到doumail表中的数据
				//豆邮的基本信息
				doumail.setDoumailId(rs.getInt("doumail_id"));
				doumail.setFromUserId(rs.getInt("from_user_id"));
				doumail.setToUserId(rs.getInt("to_user_id"));
				//豆邮的详情
				doumail.setChatMsg(rs.getString("chat_msg"));
				doumail.setChatTime(rs.getTimestamp("chat_time"));
				//当前豆邮的状态
				doumail.setStatus(rs.getInt("status"));
				doumail.setRead(rs.getInt("read"));
			
				//放到集合中
				doumails.add(doumail);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DoumailDaoImpl---queryDoumailListByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return doumails;
	}

/**
 * SELECT---得到符合当前用户和被选择的用户的豆邮记录总数
 */
	@Override
	public int getShowTotalCount(int userId ,int toUserId)
	{
		//总数据量
		int count = -1;
		try {
			//获取连接池连接
			con = dataSource.getConnection();
			//查询数据库中记录的总数量---满足的条件---当前用户是豆邮的接收者或者是发送者
			String sql = "SELECT  count(1) FROM doumail WHERE    from_user_id = ? AND to_user_id = ?    OR    to_user_id = ? AND from_user_id = ?   ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, toUserId);
			stmt.setInt(3, userId);
			stmt.setInt(4, toUserId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) //若查询结果不为空
			{
				//总数据量
			count = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("DoumailDaoImpl---getShowTotalCount--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return count;
	}


	/**
	 * SELECT---在doumail表中查找有关当前用户id和当前用户选择的用户的豆邮记录--数据集合
	 */
	@Override
	public List<Doumail> queryShowByPage(int currentPage, int pageSize, int userId  , int  toUserId) 
	{
		List<Doumail> doumails = null;
		doumails = new ArrayList<Doumail>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = "SELECT * FROM doumail   WHERE    from_user_id = ? AND to_user_id = ?    OR    to_user_id = ? AND from_user_id  = ? ORDER BY chat_time DESC  limit ? offset ?";
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, toUserId);
			stmt.setInt(3, userId);
			stmt.setInt(4, toUserId);
			
			stmt.setInt(5, pageSize);
			stmt.setInt(6, begin);
			ResultSet rs = stmt.executeQuery();
			//拿到数据集合
			while(rs.next()){
				Doumail doumail = new Doumail();
				//辨别一条记录的值---拿到doumail表中的数据
				//豆邮的基本信息
				doumail.setDoumailId(rs.getInt("doumail_id"));
				doumail.setFromUserId(rs.getInt("from_user_id"));
				doumail.setToUserId(rs.getInt("to_user_id"));
				//豆邮的详情
				doumail.setChatMsg(rs.getString("chat_msg"));
				doumail.setChatTime(rs.getTimestamp("chat_time"));
				//当前豆邮的状态
				doumail.setStatus(rs.getInt("status"));
				doumail.setRead(rs.getInt("read"));
			
				//放到集合中
				doumails.add(doumail);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DoumailDaoImpl---queryShowByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return doumails;
	}

	/**
	 * INSERT---增加一条记录到doumial表中
	 */
	@Override
	public boolean doumailInsert(Doumail doumail)
	{
		boolean judge = false;
		try {
			//获取数据库连接池中的一个连接
			con = dataSource.getConnection();
			//增加一条记录到doumail表中
			String sql="insert into doumail  ( from_user_id , to_user_id , chat_msg ) values (?,?,?)";
			PreparedStatement stmt=con.prepareStatement(sql) ;
			stmt.setInt ( 1, doumail.getFromUserId() );
			stmt.setInt ( 2, doumail.getToUserId());
			//用户发送豆邮的消息
			stmt.setString(3, doumail.getChatMsg());
			
			int t = stmt.executeUpdate();
			if(t>0) 
			{
				judge = true;
			}
		} catch (SQLException e) {
			judge=false;
			System.out.println("DoumailDaoImpl---doumailInsert--数据库连接异常");
			e.printStackTrace();
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}
}
