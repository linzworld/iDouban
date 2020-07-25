package com.lzh.dao;

import com.lzh.bean.Msg;
import com.lzh.entity.User;
/**
 * @Description 包含和用户登录相关的操作 
 * @author 林泽鸿
 * @date 2019年4月18日
 */
public interface UserDao
{
	/**
	 * @Description 用户登录
	 * @param user
	 * @return 返回msg对象，将信息存储在对象之中进行不同类之间的传输
	 */
	public Msg login(User user);


	/**
	 * @Description 用户注册 默认的为普通用户
	 * @param user
	 * @return 返回1表示注册成功
	 * 			     返回0表示注册失败
	 */
	public int register(User user);
	
	
	/**
	 * @Description 查看用户名是否重复
	 * @param user
	 * @return 如果返回值为1表示重复  
	 *               如果返回值为0表示没有重复
	 */
		public int repetition(User user);
	
		/**
		 * @Description 更新user表中的个人信息
		 * @param user 通过user对象的用户名来更新所要的记录
		 * @return 返回1表示更新成功
		 * 		     	返回0表示更新失败
		 */
		public int personage(User user);
		
	/**
	 * @Description 更新数据库中的用户头像的地址
	 * @param user
	 * @return 返回1表示更新成功
	 * 		     	返回0表示更新失败
	 */
		public int portrait(User user);


		/**
		 * @Description  查询该用户的所有信息  
		 * @param username 用户名
		 * @return user对象
		 */
		public User userInfo(String username) ;
			

		/**
		 * @Description 通过userId查询该用户的所有信息  
		 * @param userId 
		 * @return user对象
		 */
		public User userInfoByUserId(int userId) ;
	
		/**
		 * @Description 通过用户名更新code和outTime 
		 * @param user 当前用户
		 * @return 
		 */
		public boolean userUpdate(  User user  ) ;


		
}
