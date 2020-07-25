package com.lzh.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @Description 
 * @author 林泽鸿
 * @time 2019年5月14日 上午4:34:33
 */
public class  Doumail
{
	
	/**
	 * 豆邮表的id
	 */
	private  int doumailId ;
	
	/**
	 * 发出豆邮的人的用户id
	 */
	private int fromUserId;
	/**
	 * 发出豆邮的人的头像
	 */
	private String fromUserImg;
	/**
	 * 发送豆邮的人的昵称
	 */
	private String fromUserNick;
	
	/**
	 * 接受豆邮的人的用户id
	 */
	private int toUserId  ;
	/**
	 * 接收豆邮的人的头像
	 */
	private String toUserImg ;
	/**
	 * 接收豆邮的人的昵称
	 */
	private String toUserNick ;
	
	/**
	 * 豆邮的内容
	 */
	private String chatMsg;

	/**
	 * 豆邮的发送时间
	 */
	private java.sql.Timestamp chatTime ;
	/**
	 * 记录当前记录的删除情况---
	 * ----0为无状态，
	 * ----1为发送豆邮的人进行了删除，
	 * ----2为接收豆邮的人进行了删除
	 */
	private int status ;
	/**
	 * 记录当前的豆邮是否已被阅读
	 */
	private int read ;
			/**
			 * @param doumailId
			 * @param fromUserId
			 * @param fromUserImg
			 * @param fromUserNick
			 * @param toUserId
			 * @param toUserImg
			 * @param toUserNick
			 * @param chatMsg
			 * @param chatTime
			 * @param status
			 * @param read
			 */
			public Doumail(int doumailId, int fromUserId, String fromUserImg, String fromUserNick, int toUserId,
					String toUserImg, String toUserNick, String chatMsg, Timestamp chatTime, int status, int read)
			{
				super();
				this.doumailId = doumailId;
				this.fromUserId = fromUserId;
				this.fromUserImg = fromUserImg;
				this.fromUserNick = fromUserNick;
				this.toUserId = toUserId;
				this.toUserImg = toUserImg;
				this.toUserNick = toUserNick;
				this.chatMsg = chatMsg;
				this.chatTime = chatTime;
				this.status = status;
				this.read = read;
			}
			/**
			 * 
			 */
			public Doumail()
			{
				super();
				// TODO Auto-generated constructor stub
			}
			public int getDoumailId()
			{
				return doumailId;
			}
			public void setDoumailId(int doumailId)
			{
				this.doumailId = doumailId;
			}
			public int getFromUserId()
			{
				return fromUserId;
			}
			public void setFromUserId(int fromUserId)
			{
				this.fromUserId = fromUserId;
			}
			public String getFromUserImg()
			{
				return fromUserImg;
			}
			public void setFromUserImg(String fromUserImg)
			{
				this.fromUserImg = fromUserImg;
			}
			public String getFromUserNick()
			{
				return fromUserNick;
			}
			public void setFromUserNick(String fromUserNick)
			{
				this.fromUserNick = fromUserNick;
			}
			public int getToUserId()
			{
				return toUserId;
			}
			public void setToUserId(int toUserId)
			{
				this.toUserId = toUserId;
			}
			public String getToUserImg()
			{
				return toUserImg;
			}
			public void setToUserImg(String toUserImg)
			{
				this.toUserImg = toUserImg;
			}
			public String getToUserNick()
			{
				return toUserNick;
			}
			public void setToUserNick(String toUserNick)
			{
				this.toUserNick = toUserNick;
			}
			public String getChatMsg()
			{
				return chatMsg;
			}
			public void setChatMsg(String chatMsg)
			{
				this.chatMsg = chatMsg;
			}
			public String getChatTime()
			{
				//更改其时间格式
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				return  df.format(chatTime);
			}
			public void setChatTime(java.sql.Timestamp chatTime)
			{
				this.chatTime = chatTime;
			}
			public int getStatus()
			{
				return status;
			}
			public void setStatus(int status)
			{
				this.status = status;
			}
			public int getRead()
			{
				return read;
			}
			public void setRead(int read)
			{
				this.read = read;
			}
	
	
	
}
