package com.lzh.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.FriendDao;
import com.lzh.entity.User;
import com.lzh.util.MyDataSource;
/**
 * @Description 用于处理和用户好友相关的数据库操作 
 * @author 林泽鸿
 * @time 2019年4月27日 下午9:55:31
 */
public class FriendDaoImpl implements FriendDao
{
	
	private Connection con;
	
	private PreparedStatement stmt;	
	
	
	//新建一个数据库连接池对象
	MyDataSource dataSource = new MyDataSource();
	private UserDaoImpl userDao = new UserDaoImpl();

	
	/**
	 * @Description 查询列表的记录总数  user表
	 * @return 记录总数
	 */
	@Override
	public int getTotalCount() {
		//总数据量
		int count = -1;
		try {
			//获取连接池连接
			con = dataSource.getConnection();
			//查询数据库中记录的总数量
			String sql = "SELECT  count(1) FROM user";
			
			stmt = con.prepareStatement(sql);
		
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) //若查询结果不为空
			{
				//总数据量
			count = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("FriendDao---count--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return count;
	}


	
	
	/**
	 * @Description 通过用户定义的页面数和页面大小，进行分页查询  user表
	 * @param currentPage 当前页
	 * @param pageSize 页面大小(每页的数据容量)
	 * @return users--当前页的所有符合条件的对象--放在list集合中
	 */
	@Override
	public List<User> queryEveryoneByPage(int currentPage,int pageSize){
		List<User> users = null;
		users = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = "SELECT * FROM user   limit ? offset ?";
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, pageSize);
			stmt.setInt(2, begin);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){//拿到数据集合
				User user = new User();
				//辨别一条记录的值
				user.setUserId(rs.getInt("user_id"));
				user.setUsername("username");
				//个人账号状态
				user.setPassword(rs.getString("password"));
			    user.setStatus(rs.getInt("status"));
				user.setReported(rs.getInt("reported"));
				//被封号的截止时间
				user.setTitleTime(rs.getTimestamp("title_time"));	
				//个人信息
				user.setPortrait(rs.getString("portrait"));
				user.setSignature(rs.getString("signature"));
				user.setSelfIntroduc(rs.getString("self_introduction"));
				user.setNickname(rs.getString("nickname"));
				user.setAddress(rs.getString("address"));
				//用户注册的时间
				user.setTime(rs.getTimestamp("time"));
			  //放到集合中
				users.add(user);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FriendDao---queryEveryoneByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return users;
	}
	
	/**
	 * @Description  传入要模糊搜索的内容 ---找出其中符合条件的记录数 user表
	 * @param searchContent 要搜索的字段内容
	 * @return 记录数
	 */
	@Override
	public int getSearchCount( String searchContent ) {
		//总数据量
				int count = -1;
				try {
					//获取连接池连接
					con = dataSource.getConnection();
					//查询数据库中记录的总数量
					String sql = "SELECT count(1) FROM user WHERE nickname LIKE  ? "  ;
					stmt = con.prepareStatement(sql);
					stmt.setString(1,"%"+searchContent+"%");
					
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) //若查询结果不为空
					{
						//总数据量
					count = rs.getInt(1);
					}	
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("FriendDao---searchCount--数据库连接异常");
				} finally{
					dataSource.backConnection(con);
				}
				return count;
		
	}
	

	/**
	 * @Description 模糊查询 通过用户定义的页面数和页面大小，进行分页查询  user表
	 * @param currentPage 当前页
	 * @param pageSize 页面大小(每页的数据容量)
	 * @param searchContent 搜索内容
	 * @return users--当前页的所有符合搜索条件的对象--放在list集合中
	 */
	@Override
	public List<User> queryEveryoneByPage(int currentPage,int pageSize,String searchContent){
		List<User> users = null;
		users = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = "SELECT * FROM  ( SELECT * FROM user WHERE nickname LIKE  ? )  as a  LIMIT ? OFFSET ?";
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);
			stmt.setString(1,"%"+searchContent+"%");
			stmt.setInt(2, pageSize);
			stmt.setInt(3, begin);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){//拿到数据集合
				User user = new User();
				//辨别一条记录的值
				user.setUserId(rs.getInt("user_id"));
				user.setUsername("username");
				//个人账号状态
				user.setPassword(rs.getString("password"));
			    user.setStatus(rs.getInt("status"));
				user.setReported(rs.getInt("reported"));
				//被封号的截止时间
				user.setTitleTime(rs.getTimestamp("title_time"));	
				//个人信息
				user.setPortrait(rs.getString("portrait"));
				user.setSignature(rs.getString("signature"));
				user.setSelfIntroduc(rs.getString("self_introduction"));
				user.setNickname(rs.getString("nickname"));
				user.setAddress(rs.getString("address"));
				//用户注册的时间
				user.setTime(rs.getTimestamp("time"));
			  //放到集合中
				users.add(user);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FriendDao---模糊搜索---queryEveryoneByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return users;
	}

	/**
	 *  我关注的人或者好友或者黑名单 的记录总数--friend表
	 */
	@Override
	public int getFriendTotalCount ( int userId,int status ) {

		//总数据量
				int count = -1;
				try {
					//获取连接池连接
					con = dataSource.getConnection();
					//查询数据库中记录的总数量
					//friend表--查询当前用户关注的好友信息
					String sql = "SELECT  count(1) FROM friend WHERE from_user_id=? AND status=?";
					
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, userId);
					stmt.setInt(2, status);
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) //若查询结果不为空
					{
						//总数据量
					count = rs.getInt(1);
					}	
				} catch (SQLException e) {
					e.printStackTrace();
					  
					System.out.println("FriendDao--friendcount--数据库连接异常");
				} finally{
					dataSource.backConnection(con);
				}
				return count;
			}
	
	
	/**
	 * @Description   我关注的人或者好友或者黑名单--分页查询--对象集合 friend表-->user表
	 * @param userId 当前用户的id值
	 * @param status  查询条件 0--黑名单--1--关注  2--好友
	 * @param currentPage 当前页
	 * @param pageSize 页面大小
	 * @return users--当前页的对象集合
	 */
	@Override
	public List<User> queryFriendByPage( int userId,int status,int currentPage,int pageSize){
		List<User> users = null;
		users = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = "SELECT * FROM friend WHERE from_user_id = ? AND status = ?  limit ? offset ?";
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, userId);
			stmt.setInt(2, status);
			stmt.setInt(3, pageSize);
			stmt.setInt(4, begin);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){//拿到数据集合
				User user = null;
				user = new User();
				user = userDao.userInfoByUserId(rs.getInt("to_user_id"));
			  //放到集合中
				users.add(user);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FriendDao---queryFriendByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return users;
	}

	/**
	 * @Description 我关注的人或者好友或者黑名单- 找出其中符合条件的记录数 user表
	 * @param searchContent 要模糊搜索的字段内容
	 * @param userId      当前用户的id值
	 * @param status  查询条件 0--黑名单--1--关注  2--好友
	 * @return 记录数
	 */
	@Override
	public int getFriendSearchCount(String searchContent,int userId,int status) {
	
		//总数据量
				int count = -1;
				try {
					//获取连接池连接
					con = dataSource.getConnection();
					//查询数据库中记录的总数量---多表查询-- user+friend
					String sql = "SELECT  count(1)   FROM friend , user WHERE friend.from_user_id=? AND friend.status=? AND user.nickname LIKE  ? AND friend.to_user_id=user.user_id";
					stmt = con.prepareStatement(sql);
					
					stmt.setInt(1, userId);
					stmt.setInt(2, status);
					stmt.setString(3,"%"+searchContent+"%");
					
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) //若查询结果不为空
					{
						//总数据量
					count = rs.getInt(1);
					}	
				}
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("FriendDao---getFriendSearchCount--数据库连接异常");
				} finally{
					dataSource.backConnection(con);
				}
				return count;
		
	}
	/**
	 * @Description  我关注的人或者好友或者黑名单--分页查询--对象集合
	 * @param searchContent 模糊搜索内容
	 * @param userId      当前用户的id值
     * @param status 查询条件 0--黑名单--1--关注  2--好友-- 
	 * @param currentPage  当前页
	 * @param pageSize    页面大小
	 * @return  users--当前页的对象集合
	 */
	@Override
	public List<User> queryFriendByPage ( String searchContent,int userId, int status,int currentPage, int pageSize ) {
		List<User> users = null;
		users = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = " SELECT  *   FROM friend , user WHERE friend.from_user_id=? AND friend.status=? AND user.nickname LIKE  ? AND friend.to_user_id=user.user_id LIMIT ? OFFSET ? "; 
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, userId);
			stmt.setInt(2, status);
			stmt.setString(3, "%"+searchContent+"%");
			stmt.setInt(4, pageSize);
			stmt.setInt(5, begin);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){//拿到数据集合
				User user = null;
				user = new User();
				user = userDao.userInfoByUserId(rs.getInt("to_user_id"));
			  //放到集合中
				users.add(user);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FriendDao---模糊搜索---queryFriendByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return users;
		
	}




	/**
	 * SELECT--联表查询--得到满足条件的记录数
	 */
	@Override
	public int getFriendGroupCount(String searchContent, int userId, int status)
	{
	//总数据量
			int count = -1;
			try {
				//获取连接池连接
				con = dataSource.getConnection();
				//查询数据库中记录的总数量---多表查询-- friend + friend_group
				String sql = "SELECT  count(1)   FROM friend , friend_group WHERE friend.from_user_id = ?  AND friend.status = ?  AND friend_group.group_name_to =  ?  AND friend.group_id=friend_group.group_id";
				stmt = con.prepareStatement(sql);
				
				stmt.setInt(1, userId);
				stmt.setInt(2, status);
				stmt.setString(3,searchContent);
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) //若查询结果不为空
				{
					//总数据量
				count = rs.getInt(1);
				}	
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("FriendDao---getFriendGroupCount--数据库连接异常");
			} finally{
				dataSource.backConnection(con);
			}
			return count;
	
}



	@Override
	public List<User> queryFriendGroupByPage(String searchContent, int userId, int status, int currentPage,int pageSize)
	{
		List<User> users = null;
		users = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			//分页查询的sql语句
			String sql = " SELECT  *  FROM friend , friend_group WHERE friend.from_user_id = ?  AND friend.status = ?  AND friend_group.group_name_to =  ?  AND friend.group_id=friend_group.group_id  LIMIT ? OFFSET ? "; 
			//占位符代表的参数
			//limit 查询的数目====pageSize
			//offset对应的记录开始数
			int begin = ( currentPage-1 )*pageSize;
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, userId);
			stmt.setInt(2, status);
			stmt.setString(3, searchContent);
			stmt.setInt(4, pageSize);
			stmt.setInt(5, begin);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){//拿到数据集合
				User user = null;
				user = new User();
				user = userDao.userInfoByUserId(rs.getInt("to_user_id"));
			  //放到集合中
				users.add(user);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FriendDao-----queryFriendGroupByPage--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return users;
		
	}

	
}
