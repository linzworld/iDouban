package com.lzh.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @Description 对应数据库中的a_comment表
 * @author 林泽鸿
 * @time 2019年5月8日 下午5:28:20
 */
public class ArticleComment
{
	/**
	 * 评论表的id
	 */
	private int commentId;
	/**
	 * 被评论的文章的id
	 */
	private int articleId;
	/**
	 * 评论的消息
	 */
	private String comMsg;
	/**
	 * 进行评论的用户id
	 */
	private int userComId;
	/**
	 * 进行评论的用户的头像
	 */
	private String userComImg ;
	
	/**
	 * 进行评论的用户的昵称
	 */
	private String userComNick;
	/**
	 * 评论的点赞数
	 */
	private int comStar;
	/**
	 * 进行评论的时间
	 */
	private java.sql.Timestamp comTime ;
	/**
	 * 记录当前用户对应该评论是否为点赞状态--0为无点赞，1为点赞
	 */
	private int starStatus = 0;
		/**
		 * @param commentId
		 * @param articleId
		 * @param comMsg
		 * @param userComId
		 * @param userComImg
		 * @param userComNick
		 * @param comStar
		 * @param comTime
		 * @param starStatus
		 */
		public ArticleComment(int commentId, int articleId, String comMsg, int userComId, String userComImg,
				String userComNick, int comStar, Timestamp comTime, int starStatus)
		{
			super();
			this.commentId = commentId;
			this.articleId = articleId;
			this.comMsg = comMsg;
			this.userComId = userComId;
			this.userComImg = userComImg;
			this.userComNick = userComNick;
			this.comStar = comStar;
			this.comTime = comTime;
			this.starStatus = starStatus;
		}
		/**
		 * 
		 */
		public ArticleComment()
		{
			super();
			// TODO Auto-generated constructor stub
		}
		public int getCommentId()
		{
			return commentId;
		}
		public void setCommentId(int commentId)
		{
			this.commentId = commentId;
		}
		public int getArticleId()
		{
			return articleId;
		}
		public void setArticleId(int articleId)
		{
			this.articleId = articleId;
		}
		public String getComMsg()
		{
			return comMsg;
		}
		public void setComMsg(String comMsg)
		{
			this.comMsg = comMsg;
		}
		public int getUserComId()
		{
			return userComId;
		}
		public void setUserComId(int userComId)
		{
			this.userComId = userComId;
		}
		public String getUserComImg()
		{
			return userComImg;
		}
		public void setUserComImg(String userComImg)
		{
			this.userComImg = userComImg;
		}
		public String getUserComNick()
		{
			return userComNick;
		}
		public void setUserComNick(String userComNick)
		{
			this.userComNick = userComNick;
		}
		public int getComStar()
		{
			return comStar;
		}
		public void setComStar(int comStar)
		{
			this.comStar = comStar;
		}
		public String getComTime()
		{
			//更改其时间格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			return  df.format(comTime);
		}
		public void setComTime(java.sql.Timestamp comTime)
		{
			this.comTime = comTime;
		}
		public int getStarStatus()
		{
			return starStatus;
		}
		public void setStarStatus(int starStatus)
		{
			this.starStatus = starStatus;
		}
	
}