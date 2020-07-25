package com.lzh.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @Description article_list.jsp中列表中每行所需元素 预览文章信息的列表 相当于浏览发现
 * @author 林泽鸿
 * @time 2019年5月5日 下午3:43:37
 */
public class ArticleList
{
	/**
	 * 文章的id
	 */
	private int articleId ;
	/**
	 * 文章的标题
	 */
	private String title;
	/**
	 * 发表时间
	 */
	private java.sql.Timestamp publishedTime ;
	/**
	 * 文章的分类名
	 */
	private String tagName ;
	/**
	 * 文章作者的id
	 */
	private int authorId ;
	/**
	 * 文章作者的头像
	 */
	private String authorImg ;
	/**
	 * 文章作者的昵称
	 */
	private String authorNick ;
	
	/**
	 * 转发量
	 */
	private  int shareNum ;
	/**
	 * 点赞数
	 */
	private  int starNum;
	/**
	 * 评论数
	 */
	private  int commentNum;
	/**
	 * 收藏数
	 */
	private int collectionNum;
	/**
	 * 预览文字--整份文章内容
	 */
	private String content; 
	/**
	 * 文章的第一张图片
	 */
	private String firstImg;
	/**
	 *是否置顶
	 */
	private int stick;
	/**
	 * 浏览量
	 */
	private int pageView;
			/**
			 * @param articleId
			 * @param title
			 * @param publishedTime
			 * @param tagName
			 * @param authorId
			 * @param authorImg
			 * @param authorNick
			 * @param shareNum
			 * @param starNum
			 * @param commentNum
			 * @param collectionNum
			 * @param content
			 * @param firstImg
			 * @param stick
			 * @param pageView
			 */
			public ArticleList(int articleId, String title, Timestamp publishedTime, String tagName, int authorId,
					String authorImg, String authorNick, int shareNum, int starNum, int commentNum, int collectionNum,
					String content, String firstImg, int stick, int pageView)
			{
				super();
				this.articleId = articleId;
				this.title = title;
				this.publishedTime = publishedTime;
				this.tagName = tagName;
				this.authorId = authorId;
				this.authorImg = authorImg;
				this.authorNick = authorNick;
				this.shareNum = shareNum;
				this.starNum = starNum;
				this.commentNum = commentNum;
				this.collectionNum = collectionNum;
				this.content = content;
				this.firstImg = firstImg;
				this.stick = stick;
				this.pageView = pageView;
			}
			/**
			 * 
			 */
			public ArticleList()
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
			public String getAuthorImg()
			{
				return authorImg;
			}
			public void setAuthorImg(String authorImg)
			{
				this.authorImg = authorImg;
			}
			public String getAuthorNick()
			{
				return authorNick;
			}
			public void setAuthorNick(String authorNick)
			{
				this.authorNick = authorNick;
			}
			public int getShareNum()
			{
				return shareNum;
			}
			public void setShareNum(int shareNum)
			{
				this.shareNum = shareNum;
			}
			public int getStarNum()
			{
				return starNum;
			}
			public void setStarNum(int starNum)
			{
				this.starNum = starNum;
			}
			public int getCommentNum()
			{
				return commentNum;
			}
			public void setCommentNum(int commentNum)
			{
				this.commentNum = commentNum;
			}
			public int getCollectionNum()
			{
				return collectionNum;
			}
			public void setCollectionNum(int collectionNum)
			{
				this.collectionNum = collectionNum;
			}
			public String getContent()
			{
				return content;
			}
			public void setContent( String content)
			{
				this.content = content;
			}
			public String getFirstImg()
			{
				return firstImg;
			}
			public void setFirstImg(String firstImg)
			{
				this.firstImg = firstImg;
			}
			public int getStick()
			{
				return stick;
			}
			public void setStick(int stick)
			{
				this.stick = stick;
			}
			public int getPageView()
			{
				return pageView;
			}
			public void setPageView(int pageView)
			{
				this.pageView = pageView;
			}


}