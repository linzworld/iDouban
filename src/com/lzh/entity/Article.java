package com.lzh.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @Description article表的实体类 ---对应文章编辑界面的文章主体部分--以及文章主体的显示功能
 * @author 林泽鸿
 * @time 2019年5月2日 下午9:41:43
 */
		public class Article
		{
			/**
			 * 文章id
			 */
		private int articleId;
		/**
		 * 文章标题
		 */
		private String title;
		
		/**
		 * 文章的分类名
		 */
		private String tagName ;
		/**
		 * 文章作者的id
		 */
		private int authorId ;
		/**
		 * 作者的昵称
		 */
		private String authorNick ;
		
		/**
		 * 文章作者的头像
		 */
		private String authorImg ;
		
		/**
		 * 发表时间
		 */
		private java.sql.Timestamp publishedTime ;
		/**
		 * 分类id
		 */
		private int tagId;
		
		/**
		 * 主要的文章内容HTML代码
		 */
		private String content; 
		
		/**
		 * 点赞数
		 */
		private int starNum;
		/**
		 * 收藏数
		 */
		private int collectionNum;
		/**
		 * 评论数
		 */
		private int commentNum;
		/**
		 * 转发数
		 */
		private int shareNum ;
		/**
		 * 浏览量
		 */
		private int pageView;
		/**
		 *是否置顶
		 */
		private int stick;
		/**
		 * @param articleId
		 * @param title
		 * @param tagName
		 * @param authorId
		 * @param authorNick
		 * @param authorImg
		 * @param publishedTime
		 * @param tagId
		 * @param content
		 * @param starNum
		 * @param collectionNum
		 * @param commentNum
		 * @param shareNum
		 * @param pageView
		 * @param stick
		 */
		public Article(int articleId, String title, String tagName, int authorId, String authorNick, String authorImg,
				Timestamp publishedTime, int tagId, String content, int starNum, int collectionNum, int commentNum,
				int shareNum, int pageView, int stick)
		{
			super();
			this.articleId = articleId;
			this.title = title;
			this.tagName = tagName;
			this.authorId = authorId;
			this.authorNick = authorNick;
			this.authorImg = authorImg;
			this.publishedTime = publishedTime;
			this.tagId = tagId;
			this.content = content;
			this.starNum = starNum;
			this.collectionNum = collectionNum;
			this.commentNum = commentNum;
			this.shareNum = shareNum;
			this.pageView = pageView;
			this.stick = stick;
		}
		/**
		 * 
		 */
		public Article()
		{
			super();
			// TODO Auto-generated constructor stub
		}
		public int getArticleId()
		{
			return articleId;
		}
		public void setArticleId(int articleId)
		{
			this.articleId = articleId;
		}
		public String getTitle()
		{
			return title;
		}
		public void setTitle(String title)
		{
			this.title = title;
		}
		public String getTagName()
		{
			return tagName;
		}
		public void setTagName(String tagName)
		{
			this.tagName = tagName;
		}
		public int getAuthorId()
		{
			return authorId;
		}
		public void setAuthorId(int authorId)
		{
			this.authorId = authorId;
		}
		public String getAuthorNick()
		{
			return authorNick;
		}
		public void setAuthorNick(String authorNick)
		{
			this.authorNick = authorNick;
		}
		public String getAuthorImg()
		{
			return authorImg;
		}
		public void setAuthorImg(String authorImg)
		{
			this.authorImg = authorImg;
		}
		public String getPublishedTime()
		{
			//更改其时间格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			return  df.format(publishedTime);
		}
		public void setPublishedTime(java.sql.Timestamp publishedTime)
		{
			this.publishedTime = publishedTime;
		}
		public int getTagId()
		{
			return tagId;
		}
		public void setTagId(int tagId)
		{
			this.tagId = tagId;
		}
		public String getContent()
		{
			return content;
		}
		public void setContent(String content)
		{
			this.content = content;
		}
		public int getStarNum()
		{
			return starNum;
		}
		public void setStarNum(int starNum)
		{
			this.starNum = starNum;
		}
		public int getCollectionNum()
		{
			return collectionNum;
		}
		public void setCollectionNum(int collectionNum)
		{
			this.collectionNum = collectionNum;
		}
		public int getCommentNum()
		{
			return commentNum;
		}
		public void setCommentNum(int commentNum)
		{
			this.commentNum = commentNum;
		}
		public int getShareNum()
		{
			return shareNum;
		}
		public void setShareNum(int shareNum)
		{
			this.shareNum = shareNum;
		}
		public int getPageView()
		{
			return pageView;
		}
		public void setPageView(int pageView)
		{
			this.pageView = pageView;
		}
		public int getStick()
		{
			return stick;
		}
		public void setStick(int stick)
		{
			this.stick = stick;
		}
}		