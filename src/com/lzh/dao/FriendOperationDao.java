package com.lzh.dao;

import java.util.List;

/**
 * @Description 用户更改好友关系的DAO操作 
 * @author 林泽鸿
 * @time 2019年4月30日 下午5:38:35
 */
public interface FriendOperationDao
{

	
	/**
	 * @Description  UPDATE-将好友关系更改为 当前用户选择的好友关系
	 * @param fromUserId  friend表中记录的发出者
	 * @param toUserId 用户选择的好友
	 * @param status 更改之后的好友状态
	 * @return 成功与否
	 */
	public boolean friendUpdate(int fromUserId, int toUserId ,int status );
	
	/**
	 * @Description DELETE-将好友关系为无状态---即在数据库没有存在该记录
	 * @param fromUserId 记录发出者
	 * @param toUserId 记录接收者
	 * @return 成功与否
	 */
	public boolean friendDelete(int fromUserId, int toUserId);
	
	/**
	 * @Description INSERT 增加一条好友关系----从双方关系为无状态到一方关注另一方
	 * @param fromUserId 记录发出者
	 * @param toUserId 记录接收者
	 * @return 成功与否
	 */
	public boolean friendInsert(int fromUserId, int toUserId);

	/**
	 * @Description 查询当前friend表中一条记录中双方的好友关系 
	 * @param fromUserId 记录发出者
	 * @param toUserId 记录接收者
	 * @return status--当前这条记录中双方的关系-----设置0为无状态
	 */
	public int friendQuery(int fromUserId, int toUserId);
	
	
	/**
	 * @Description 查询在好友分组表中是否存在符合条件的记录
	 * @param groupName 分组名
	 * @return 成功与否
	 */
		public boolean friendGroupExist(String groupName);
	/**
	 * @Description 在好友分组表中新增一条分组记录 
	 * @param groupName 分组名
	 * @return 成功与否
	 */
	public boolean friendGroupInsert( String groupName ) ;
	
	/**
	 * @Description 在group表中进行好友的分组的id查询 
	 * @param groupName 分组名
	 * @return group_id 分组的id
	 */
	public int getGroupIdBygroupName (String groupName ) ;
	/**
	 * @Description 更新好友分组--friend表 
	 * @param fromUserId 好友关系发出者
	 * @param toUserId 好友关系接收者
	 * @param status 好友关系的状态
	 * @param groupId 分组的id
	 * @return 成功与否
	 */
	public boolean friendGroupUpdate(int fromUserId, int toUserId ,int status , int groupId) ;
	/**
	 * @Description 通过当前用户的id得到这个用户的好友分组名的数据集合 
	 * @param userId 用户id
	 * @return  这个用户的好友分组名的数据集合 
	 */
	public List<String> friendGroupNameQuery( int userId  );
	

}
