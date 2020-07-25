package com.lzh.dao;

import java.util.List;

import com.lzh.entity.Article;
import com.lzh.entity.ArticleComment;
import com.lzh.entity.ArticleReply;

/**
 * @Description 与显示文章相关的数据库操作 
 * @author 林泽鸿
 * @time 2019年5月10日 上午1:28:32
 */
public interface ArticleShowDao
{

	/**
	 * @Description  通过articleId查询该用户的所有信息  
	 * @param articleId
	 * @return articleId对象
	 */
	public Article articleByArticlerId(int articleId) ;
	
	

	/**
	 * @Description  INSERT 增加一条评论的消息 
	 * @param articleComment articleComment对象
	 * @return 成功与否
	 */
	public boolean commentInsert(ArticleComment articleComment );

	/**
	 * @Description 通过文章id找到评论的信息 
	 * @param articleId 文章的Id
	 * @return articleComment数据对象
	 */
	public ArticleComment getCommentInfo(int articleId);

	/**
	 * @Description 查询列表的记录总数--a_comment
	 * @param articleId 文章id
	 * @return 记录总数
	 */
	public int getCommentTotalCount( int  articleId );

	/**
	 * @Description 通过用户定义的页面数和页面大小，进行分页查询
	 * @param currentPage 当前页
	 * @param pageSize    页面大小
	 * @param articleId     文章id
	 * @return articleComments (只含有articleComment)数据集合
	 */
	public List<ArticleComment> queryCommentByPage( int currentPage, int pageSize ,int articleId );
	/**
	 * @Description  得到接收评论的用户Id--给a_reply表数据的增加提供条件
	 * @param articleReply  对象
	 * @return 接收评论的用户Id
	 */
	public int getUserReplyToId ( ArticleReply articleReply ) ;
	/**
	 * @Description  INSERT 增加一条回复的消息 
	 * @param articleReply articleReply对象
	 * @return 成功与否
	 */
	public boolean replyInsert ( ArticleReply articleReply ) ;

	/**
	 * @Description SELECT--查找a_reply中符合条件的articleReply对象
	 * @param articleComment 评论对象
	 * @return  ArticleReply
	 */
	public ArticleReply getReplyInfo  (  ArticleComment articleComment );

	/**
	 * @Description 通过articleComment中的comment_id进行筛选 
	 * @param articleComment 评论对象
	 * @return ArticleReply对象集合
	 */
	public List<ArticleReply> queryReplyByComment ( ArticleComment  articleComment) ;
	
}
