package com.lzh.service;

import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.FriendOperationDao;
import com.lzh.daoImpl.FriendOperationDaoImpl;

/**
 * @Description 好友关系的业务逻辑层 
 * @author 林泽鸿
 * @time 2019年4月30日 下午7:13:00
 */
public class FriendOperationService
{
	/**
	 * 引入DAO层
	 */
	FriendOperationDao friendOperationDao = new FriendOperationDaoImpl();
	
	/**
	 * @Description 将好友从黑名单移除--等同于删除A->B的记录 
	 * @param fromUserId 当前用户的ID
	 * @param toUserId 被选中的用户ID
	 * @return 成功与否
	 */
	public boolean friendDelete(int fromUserId, int toUserId) {
	
		boolean judge = false;
		
			if( friendOperationDao.friendDelete(fromUserId, toUserId) )
				{
					judge = true;
				}
			else
				{
					
				}
		return judge;
	
	}
	/**
	 * @Description  好友关系状态的更新
	 * @param fromUserId 当前用户的ID
	 * @param toUserId 被选中的用户ID
	 * @param status 更新后的状态
	 * @return 成功与否
	 */
	public boolean friendUpdate(int fromUserId, int toUserId,int status) {
		
		boolean judge = false;
		
			if( friendOperationDao.friendUpdate(fromUserId, toUserId,status) )
				{
					judge = true;
				}
			else
				{
					
				}
					
		return judge;
	
	}
	
	/**
	 * @Description 好友关系状态的查询
	 * @param fromUserId 当前用户的ID
	 * @param toUserId 被选中的用户ID
	 * @return status 当前friend表中的好友关系
	 */
	public int friendQuery(int fromUserId, int toUserId) {
		
		
		//查询是否有此记录--并且查出其状态
		return friendOperationDao.friendQuery(fromUserId, toUserId) ;
	
	}
	/**
	 * @Description  关注---增加一条记录---status默认为1
	 * @param fromUserId 当前用户的ID
	 * @param toUserId 被选中的用户ID
	 * @groupName 好友分组名
	 * @return 成功与否
	 */
	public boolean friendInsert(int fromUserId, int toUserId ,String groupName) {
		
		boolean judge = false;
			//新增一条好友关系记录到friend表中
			if( friendOperationDao.friendInsert(fromUserId, toUserId))
				{
						//判断在分组表中是否存在该分组名
						//不存在的情况
				      if(friendOperationDao.friendGroupExist(groupName)!=true )
					      {
				    	  	//新增一条好友分组记录到好友分组表中
					    	  friendOperationDao.friendGroupInsert(groupName);
					      }
				      else
				      	{
				    	  
				      	}
				      			//无论分组名，双方公用的函数
				      		int groupId = friendOperationDao.getGroupIdBygroupName(groupName);
				      		//进行friend表的更新
				      		friendOperationDao.friendGroupUpdate(fromUserId, toUserId, 1, groupId);
				      		//得到该好友关系对应的结果
				      		judge = true ;
				}
			else
				{
					
				}
		return judge;
	}
	
	/**
	 * @Description 得到该用户对应的好友分组名
	 * @param userId 当前用户id
	 * @return 分组名集合
	 */
	public List<String> friendGroupNameQuery( int userId  )
		{
		List<String> groupNames = new ArrayList<String>() ;
		//拿到返回的数据
		groupNames = friendOperationDao.friendGroupNameQuery(userId);
		
		return groupNames;
		}
	
	/**
	 * @Description  关注---增加一条记录---status默认为1
	 * @param fromUserId 当前用户的ID
	 * @param toUserId 被选中的用户ID
	 * @groupName 好友分组名
	 * @return 成功与否
	 */
	public boolean friendGroupAlter(int fromUserId, int toUserId ,String groupName) {
			//返回的结果
			boolean judge = false;
						//判断在分组表中是否存在该分组名
						//不存在的情况
				      if(friendOperationDao.friendGroupExist(groupName)!=true )
					      {
				    	  	//新增一条好友分组记录到好友分组表中
					    	  friendOperationDao.friendGroupInsert(groupName);
					      }
				      else
				      	{
				    	  
				      	}
				      		//无论分组名，双方公用的函数
				      		int groupId = friendOperationDao.getGroupIdBygroupName(groupName);
				      		//进行friend表的更新
				      		friendOperationDao.friendGroupUpdate(fromUserId, toUserId, 1, groupId);
				      		//得到该好友关系对应的结果
				      		judge = true ;
		return judge;
	}
	
}
