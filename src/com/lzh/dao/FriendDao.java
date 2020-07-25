package com.lzh.dao;

import java.util.List;

import com.lzh.entity.User;

/**
 * @Description 处理和用户好友相关的功能
 * @author 林泽鸿
 * @time 2019年4月27日 下午9:54:25
 */
public interface FriendDao
{

	/**
	 * @Description 查询列表的记录总数
	 * @return 记录总数
	 */
	public int getTotalCount();

	/**
	 * @Description 通过用户定义的页面数和页面大小，进行分页查询
	 * @param currentPage 当前页
	 * @param pageSize    页面大小
	 * @return users--当前页的对象集合
	 */

	public List<User> queryEveryoneByPage( int currentPage, int pageSize );

	/**
	 * @Description 传入要模糊搜索的内容 ---找出其中符合条件的记录数
	 * @param searchContent 要搜索的字段内容
	 * @return 记录数
	 */
	public int getSearchCount(String searchContent);

	/**
	 * @Description 模糊搜索---通过用户定义的页面数和页面大小，进行分页查询
	 * @param currentPage   当前页
	 * @param pageSize      页面大小
	 * @param searchContent
	 * @return users--当前页的对象集合
	 */
	public List<User> queryEveryoneByPage(int currentPage, int pageSize, String searchContent);

	/**
	 * @Description 我关注的人或者好友或者黑名单的记录总数--friend表
	 * @param userId 当前用户的id值
	 * @param status 查询条件  1--关注  2--好友 3-黑名单
	 * @return 记录总数
	 */
	public int getFriendTotalCount(int userId, int status);


	/**
	 * @Description  我关注的人或者好友或者黑名单--分页查询--对象集合
	 * @param userId      当前用户的id值
     * @param status 查询条件  1--关注  2--好友 3-黑名单
	 * @param currentPage  当前页
	 * @param pageSize    页面大小
	 * @return  users--当前页的对象集合
	 */
	public List<User> queryFriendByPage(int userId, int status,int currentPage, int pageSize);


	/**
	 * @Description 我关注的人或者好友或者黑名单-找出其中符合条件的记录数 user表
	 * @param searchContent 要模糊搜索的字段内容
	 * @param userId      当前用户的id值
	 * @param status 查询条件
	 * @return 记录数
	 */
	public int getFriendSearchCount(String searchContent,int userId,int status) ;
	
	/**
	 * @Description  我关注的人或者好友或者黑名单--分页查询--对象集合
	 * @param searchContent 模糊搜索内容
	 * @param userId   当前用户的id值
     * @param status 查询条件  1--关注  2--好友 3-黑名单
	 * @param currentPage  当前页
	 * @param pageSize    页面大小
	 * @return  users--当前页的对象集合
	 */
	public List<User> queryFriendByPage ( String searchContent,int userId, int status,int currentPage, int pageSize ) ;
	
	/**
	 * @Description 找出其中符合条件的记录数 friend_group表和friend表
	 * @param searchContent 要搜索的字段内容
	 * @param userId      当前用户的id值
	 * @param status 查询条件
	 * @return 记录数
	 */
	public int getFriendGroupCount(String searchContent,int userId,int status) ;
	
	/**
	 * @Description 分页查询--对象集合
	 * @param searchContent 搜索内容
	 * @param userId   当前用户的id值
     * @param status 查询条件  1--关注  2--好友 3-黑名单
	 * @param currentPage  当前页
	 * @param pageSize    页面大小
	 * @return  users--当前页的对象集合
	 */
	public List<User> queryFriendGroupByPage ( String searchContent,int userId, int status,int currentPage, int pageSize ) ;



}
