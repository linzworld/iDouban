package com.lzh.entity;
/**
 * @Description 好友关系表对应的实体类 
 * @author 林泽鸿
 * @time 2019年5月15日 上午6:04:50
 */
public class Friend
{
	/**
	 * 好友关系分组的id
	 */
	private int friendId;
/**
 * 进行好友关系操作的用户
 */
 private int fromUserId;

 /**
  * 好友关系的对象的用户Id
  */
private int toUserId ;

/**
 * 好友关系的状态
 */
private int status ;

/**
 * 好友分组的id
 */
private int groupId;

		/**
		 * @param friendId
		 * @param fromUserId
		 * @param toUserId
		 * @param status
		 * @param groupId
		 */
		public Friend(int friendId, int fromUserId, int toUserId, int status, int groupId)
		{
			super();
			this.friendId = friendId;
			this.fromUserId = fromUserId;
			this.toUserId = toUserId;
			this.status = status;
			this.groupId = groupId;
		}
		
		/**
		 * 
		 */
		public Friend()
		{
			super();
			// TODO Auto-generated constructor stub
		}
		
		public int getFriendId()
		{
			return friendId;
		}
		
		public void setFriendId(int friendId)
		{
			this.friendId = friendId;
		}
		
		public int getFromUserId()
		{
			return fromUserId;
		}
		
		public void setFromUserId(int fromUserId)
		{
			this.fromUserId = fromUserId;
		}
		
		public int getToUserId()
		{
			return toUserId;
		}
		
		public void setToUserId(int toUserId)
		{
			this.toUserId = toUserId;
		}
		
		public int getStatus()
		{
			return status;
		}
		
		public void setStatus(int status)
		{
			this.status = status;
		}
		
		public int getGroupId()
		{
			return groupId;
		}
		
		public void setGroupId(int groupId)
		{
			this.groupId = groupId;
		}



}
