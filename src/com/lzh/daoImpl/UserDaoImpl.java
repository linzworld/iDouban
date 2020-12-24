
package com.lzh.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.lzh.bean.Msg;
import com.lzh.dao.UserDao;
import com.lzh.entity.User;
import com.lzh.util.MyDataSource;


/**
 * @Description 与用户登录注册相关的操作 
 * @author 林泽鸿
 * @date 2019年4月18日
 */
public class UserDaoImpl implements  UserDao {

private Connection con;
	
private PreparedStatement stmt;	
//新建一个数据库连接池对象
MyDataSource dataSource = new MyDataSource();

/**
 * 给字符串常量定义成一个可注释的常量，易修改
 */
static final String USERNAME="username";
static final String PASSWORD="password";
static final String STATUS="status";

/**
 * @Description 查 用户登录
 * @param user
 * @return 返回msg对象，将信息存储在对象之中进行不同类之间的传输
 */
@Override
	public Msg login(User user)  {
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM user WHERE username = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				//检查数据库中是否存在该用户名和密码
				if(user.getPassword().equals(rs.getString(PASSWORD))&&user.getUsername().equals(rs.getString(USERNAME)))
				{
					//如果数据库中存在该用户，且用户名和密码均相同，则给user对象设置username和password的属性值
					user.setUsername(rs.getString(USERNAME));
					user.setPassword(rs.getString(PASSWORD));
					//判断是否为普通用户
					if(rs.getInt(STATUS)==0)
						{
							return new Msg("用户登录成功",user);
						}
					//判断是否为管理员
					else if(rs.getInt(STATUS)==0)
							{
								return new Msg("管理员登录成功",user);
							}
				}
				if(user.getUsername().equals(rs.getString("username"))) 
					{
						return new Msg("密码错误",null);
					}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("userdao---login--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return new Msg("该用户不存在",null);
	}
	
	/**
	 * @Description 增 用户注册 默认的为普通用户
	 * @param user
	 * @return 返回1表示注册成功
	 * 			     返回0表示注册失败
	 */
@Override
	public int register(User user){
	
		int judge=0;
		
		
		try {
			//获取数据库连接池中的一个连接
			con = dataSource.getConnection();
			String sql="insert into user (username,password,status) values (?,?,0)";
			PreparedStatement ptmt=con.prepareStatement(sql);
			ptmt.setString(1, user.getUsername());
			ptmt.setString(2, user.getPassword());
			int t = ptmt.executeUpdate();
			if(t>0) {
				System.out.println("添加用户成功");
			}
			judge=1;
		} catch (SQLException e) {
			judge=0;
			System.out.println("添加用户失败");
			e.printStackTrace();
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}
	
	/**
	 * @Description 查 查看用户名是否重复
	 * @param user
	 * @return 如果返回值为1表示重复  
	 *               如果返回值为0表示没有重复
	 */
@Override
	public int repetition(User user)
	{
	
		int judge=0;
		
		try {
			con = dataSource.getConnection();
			
			String sql="select * from user where username=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,user.getUsername());
			ResultSet res=stmt.executeQuery();
			while(res.next())
			{
				judge=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("repetition"+"数据库连接失败");
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}

/**
 * @Description 更新user表中的个人信息 传入session中的该用户user对象记录
 * @param user 通过user对象的用户名来更新所要的记录
 * @return 返回1表示更新成功
 * 		     	返回0表示更新失败
 */
@Override
public int personage(User user) {
	int judge=0;
	
	try {
		//数据库连接池
		con = dataSource.getConnection();
		//更改user表的个人信息的字段      portrait=?, 
		String sql="UPDATE user SET signature=?,self_introduction=?,nickname=?,address=? WHERE username = ?";
		PreparedStatement stmt=con.prepareStatement(sql);
		stmt.setString(1,user.getSignature());
		stmt.setString(2,user.getSelfIntroduc());
		stmt.setString(3,user.getNickname());
		stmt.setString(4,user.getAddress());
		//用户名----唯一性
		stmt.setString(5,user.getUsername());
		
		
		//进行更新操作，返回成功更新的条数 
		int ret = stmt.executeUpdate();
		if(ret==1)
		{
			judge=1;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("repetition"+"数据库连接失败");
	}
	finally {
		dataSource.backConnection(con);
		}
	return judge;
	
}
/**
 * @Description 更新数据库中的用户头像的地址
 * @param imageUrl
 * @return 返回1表示更新成功
 * 		     	返回0表示更新失败
 */
@Override
	public int portrait(User user) {
		int judge=0;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//更改user表的个人信息的字段     
			String sql="UPDATE user SET  portrait=? WHERE username = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			//传入图片的地址
			stmt.setString(1,user.getPortrait());
			//用户名----唯一性
			stmt.setString(2,user.getUsername());
			System.out.println("DAO----------protait-----"+user.getPortrait());
			
			//进行更新操作，返回成功更新的条数 
			int ret = stmt.executeUpdate();
			if(ret==1)    
			{
				judge=1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("portrait"+"数据库连接失败");
		}
		finally {
			dataSource.backConnection(con);
			}
		return judge;
	}
	
	/**
	 * @Description  查询该用户的所有信息 ---user表
	 * @param username 用户名
	 * @return user对象
	 */
@Override
	public User userInfo(String username) {
		User user = new User();
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM user WHERE username = ?";
			
			//查询用户信息
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				
				//辨别一条记录的值
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(username);
				//个人账号状态
				user.setPassword(rs.getString("password"));
			    user.setStatus(rs.getInt("reported"));
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
				//找回密码
				user.setCode(rs.getInt("code"));
				user.setOutTime(rs.getTimestamp("out_time"));
			}	
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("userdao---userinfo--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return user;
	}
	
	/**
	 * @Description 通过userId查询该用户的所有信息  
	 * @param userId 
	 * @return user对象
	 */
@Override
	public User userInfoByUserId(int userId) {
		User user = new User();
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM user WHERE user_id = ?";
			
			//查询用户信息
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				
				//辨别一条记录的值
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				//个人账号状态
				user.setPassword(rs.getString("password"));
			    user.setStatus(rs.getInt("reported"));
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
				
			}	
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("userdao---userinfoByUserId--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return user;
	}

	//更新用户表中有关找回密码的字段数据
	@Override
	public boolean userUpdate(User user)
	{
		//返回结果
		boolean judge = false;
		
		try {
			//数据库连接池
			con = dataSource.getConnection();
			//更改user表的字段       
			String sql="UPDATE user SET code = ? , out_time = ?  WHERE  username = ?  ";
			PreparedStatement stmt=con.prepareStatement(sql);
			
			stmt.setInt(1,user.getCode());
			stmt.setTimestamp(2, user.getOutTime());
			stmt.setString(3,user.getUsername());
			
			//进行更新操作，返回成功更新的条数 
			int ret = stmt.executeUpdate();
			if(ret == 1)
			{
				judge = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			  
			System.out.println("userdao---userUpdate--数据库连接异常");
		} finally{
			dataSource.backConnection(con);
		}
		return judge;
	}




}
