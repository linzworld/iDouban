package com.lzh.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @Description 实体类 对应数据库中的a_reply表 
 * @author 林泽鸿
 * @time 2019年5月10日 上午3:00:54
 */
public class ArticleReply
{
	/**
	 * 回复表的id
	 */
	private int replyId;
	/**
	 * 评论表的id
	 */
	private int commentId ;
	/**
	 * 回复的消息
	 */
	private String replyMsg;
	/**
	 * 进行回复的用户id
	 */
	private int userReplyFromId;
	/**
	 * 被回复的用户id
	 */
	private int userReplyToId;
	/**
	 * 进行回复的用户的头像
	 */
	private String userReplyImg ;
	/**
	 * 被回复的用户的昵称
	 */
	private String userReplyToNick ;
	/**
	 * 进行回复的用户的昵称
	 */
	private String userReplyFromNick;
	/**
	 * 回复的点赞数
	 */
	private int replyStar;
	/**
	 * 进行回复的时间
	 */
	private java.sql.Timestamp replyTime ;
	
	
	/**
	 * @param replyId
	 * @param commentId
	 * @param replyMsg
	 * @param userReplyFromId
	 * @param userReplyToId
	 * @param userReplyImg
	 * @param userReplyToNick
	 * @param userReplyFromNick
	 * @param replyStar
	 * @param replyTime
	 */
	public ArticleReply(int replyId, int commentId, String replyMsg, int userReplyFromId, int userReplyToId,
			String userReplyImg, String userReplyToNick, String userReplyFromNick, int replyStar, Timestamp replyTime)
	{
		super();
		this.replyId = replyId;
		this.commentId = commentId;
		this.replyMsg = replyMsg;
		this.userReplyFromId = userReplyFromId;
		this.userReplyToId = userReplyToId;
		this.userReplyImg = userReplyImg;
		this.userReplyToNick = userReplyToNick;
		this.userReplyFromNick = userReplyFromNick;
		this.replyStar = replyStar;
		this.replyTime = replyTime;
	}
	/**
	 * 
	 */
	public ArticleReply()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public int getReplyId()
	{
		return replyId;
	}
	public void setReplyId(int replyId)
	{
		this.replyId = replyId;
	}
	public int getCommentId()
	{
		return commentId;
	}
	public void setCommentId(int commentId)
	{
		this.commentId = commentId;
	}
	public String getReplyMsg()
	{
		return replyMsg;
	}
	public void setReplyMsg(String replyMsg)
	{
		this.replyMsg = replyMsg;
	}
	public int getUserReplyFromId()
	{
		return userReplyFromId;
	}
	public void setUserReplyFromId(int userReplyFromId)
	{
		this.userReplyFromId = userReplyFromId;
	}
	public int getUserReplyToId()
	{
		return userReplyToId;
	}
	public void setUserReplyToId(int userReplyToId)
	{
		this.userReplyToId = userReplyToId;
	}
	public String getUserReplyImg()
	{
		return userReplyImg;
	}
	public void setUserReplyImg(String userReplyImg)
	{
		this.userReplyImg = userReplyImg;
	}
	public String getUserReplyToNick()
	{
		return userReplyToNick;
	}
	public void setUserReplyToNick(String userReplyToNick)
	{
		this.userReplyToNick = userReplyToNick;
	}
	public String getUserReplyFromNick()
	{
		return userReplyFromNick;
	}
	public void setUserReplyFromNick(String userReplyFromNick)
	{
		this.userReplyFromNick = userReplyFromNick;
	}
	public int getReplyStar()
	{
		return replyStar;
	}
	public void setReplyStar(int replyStar)
	{
		this.replyStar = replyStar;
	}

	public String getReplyTime()
	{
		//更改其时间格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return  df.format(replyTime);
	}
	public void setReplyTime(java.sql.Timestamp replyTime)
	{
		this.replyTime = replyTime;
	}
	

		
		

			
	 		
}
