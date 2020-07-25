package com.lzh.service;

import com.lzh.bean.Msg;
import com.lzh.daoImpl.UserDaoImpl;
import com.lzh.entity.User;
import com.lzh.util.ValidateUtil;

/**
 * @Description 对传过来的要登陆的用户名和密码，进行调用方法的处理
 * @author 林泽鸿
 * @date 2019年4月18日
 */
public class UserService
{

	private UserDaoImpl userDao = new UserDaoImpl();

	/**
	 * @Description 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public Msg login(String username, String password)
	{

		User user = new User();
		/**
		 * 调用判空的方法
		 */
		if (ValidateUtil.isInvalidUserName(username) == true)
		{

			// 用一个对象存储返回值
			return new Msg("用户名为空", null);
		}
		/**
		 * 调用DAO层，检查是否存在该用户和 密码是否正确 以及确认其管理权限
		 */
		user.setUsername(username);
		user.setPassword(password);
		// 调用了dao层
		Msg msg = userDao.login(user);
		return msg;
	}
/**
 * @Description 用户注册 
 * @param userName
 * @param password
 * @return
 */
	public Msg register(String username, String password)
	{
		int judge = 0;
		User user = new User();
		/**
		 * 调用判空的方法
		 */
		if (ValidateUtil.isInvalidUserName(username) == true)
		{

			// 用一个对象存储返回值
			return new Msg("用户名为空", null);
		}
		else if (ValidateUtil.isInvalidPassword(password) == true)
				{
					return new Msg("密码为空", null);
				}
		/**
		 * 调用DAO层，检查是否存在该用户和 密码是否正确 以及确认其管理权限
		 */
		user.setUsername(username);
		user.setPassword(password);

		/**
		 * 重复时
		 */
		if (userDao.repetition(user) == 1)
		{
			return new Msg("用户名重复", null);
		}
		else
		{
		judge = userDao.register(user);
				if(judge==1) 
				{
					return new Msg("注册成功", user);
					}
				else 
				{
					return new Msg("注册失败", user);
				}
		}
	}

/**
 * @Description  更新当前用户的个人信息 
 * @param user 当前用户的user对象（装有数据）
 * @return 	true-->成功
 *  				false-->失败
 */
	public boolean personage(User user) {
		int judge = 0;
		//成功为1 失败为0
		judge = userDao.personage(user);
		if(judge==1) 
			{
				return true;
			}
		else
			{
				return false;
			}
		}
	/**
	 * @Description 将头像的地址更新到数据库中
	 * @param user
	 * @return 布尔值
	 */
	public boolean portrait(User user) {
		int judge = 0;
		//成功为1 失败为0
		judge = userDao.portrait(user);
		if(judge==1) 
			{
				return true;
			}
		else
			{
				return false;
			}
		}
	/**
	 * @Description 查询该用户的所有信息 
	 * @param username 用户名
	 * @return user对象 --装有查询结果的信息
	 */
	public User UserInfo(String username)
	{
		User user = new User();
		user = userDao.userInfo(username);
		  if(user!=null) {
			  return user;
		  }
		  else {
			  return null;
		  }
	}
	
	/**
	 * 	@Description 通过用户id得到用户对象数据 
	 * @param userId 用户id
	 * @return user对象
	 */
	public User userInfoByUserId( int userId ){
		User user = new User();
		user = userDao.userInfoByUserId(userId);
		  if(user!=null) {
			  return user;
		  }
		  else {
			  return null;
		  }
	}
	
	/**
	 * @Description  通过用户名进行逻辑的判断，和记录的增加
	 * @param username 用户名==邮箱号
	 * @return 成功与否
	 */
	public  boolean findBackUser( String username  ) {
		User user = new User () ;
		user.setUsername(username);
			//查询数据库，若不存在该用户名，则返回
		    //存在该记录
			if( userDao.repetition(user) == 1 )
				{
					//将该用户对应的记录中的找回密码相关的字段，更新为最新情况

				}
			else 
				{
					return false ;
				}
			return false;
	}
	
		
}