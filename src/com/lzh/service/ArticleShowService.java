package com.lzh.service;

import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.ArticleInteractionDao;
import com.lzh.dao.ArticleShowDao;
import com.lzh.dao.UserDao;
import com.lzh.daoImpl.ArticleInteractionDaoImpl;
import com.lzh.daoImpl.ArticleShowDaoImpl;
import com.lzh.daoImpl.UserDaoImpl;
import com.lzh.entity.Article;
import com.lzh.entity.ArticleComment;
import com.lzh.entity.ArticleReply;
import com.lzh.entity.User;

/**
 * @Description 文章显示功能的业务逻辑处理和组装数据库的结果 
 * @author 林泽鸿
 * @time 2019年5月10日 上午2:12:07
 */
public class ArticleShowService
{
	ArticleShowDao articleShowDao = new ArticleShowDaoImpl();

	ArticleInteractionDao articleInteractionDao = new ArticleInteractionDaoImpl() ;
	
	UserDao userDao = new UserDaoImpl();
	
	Article article = new Article() ;
	
	ArticleInteractionService articleInteractionService = new ArticleInteractionService ();
	
		/**
		 * @Description 获取文章主体的数据 
		 * @param articleId
		 * @return articleId数据对象
		 */
		public Article getArticleInfo( int articleId){
			//承载给页面的文章的信息
			Article articleInfo = new Article() ;
			
			User user = new User();
			//先拿到文章表的信息
			articleInfo = articleShowDao.articleByArticlerId(articleId) ;
					
					//进行数据的拼接
					user = null ; 
					//拿到作者的基本信息
					user = userDao.userInfoByUserId(articleInfo.getAuthorId());
					
					//赋给articleInfo
					articleInfo.setAuthorImg( user.getPortrait() );
					articleInfo.setAuthorNick( user.getNickname() );
					
					
			
			return articleInfo;
		}
		/**
		 * @Description  进行评论的增加
		 * @param articleComment 
		 * @return 成功与否
		 */
		public boolean commentInsert(ArticleComment articleComment ) {
			//先在评论表中加入一条评论
			articleShowDao.commentInsert(articleComment);
			//然后再记录评论中符合该文章id的评论总数
			int comNum = articleInteractionDao.comNumQuery ( articleComment.getArticleId() ) ;
			
			//将给记录总数更新到相对应的文章记录上
			return 	articleInteractionDao.comNumUpdate( comNum, articleComment.getArticleId() ) ;
		
		}
		
	
		/**
		 * @Description   查询评论数据的总条数
		 * @param articleId 文章id
		 * @return  符合条件的总条数
		 */
		public int getCommentTotalCount( int articleId) {
			return articleShowDao.getCommentTotalCount(articleId);
			}
		
	
			/**
			 * @Description  分页查询所有文章
			 * @param currentPage 当前页面 
			 * @param pageSize 页面大小
			 * @return 数据集合
			 */
		public List<ArticleComment> queryCommentByPage( int currentPage ,int pageSize , int articleId , int userId){
			List<ArticleComment> articleComments = new ArrayList<ArticleComment>() ;
			List<ArticleComment> articleComments1 = new ArrayList<ArticleComment>() ;
			
			User user = new User();
			
			//先拿到文章表的信息
			articleComments1 = articleShowDao.queryCommentByPage(currentPage, pageSize ,articleId);
			//进行数据的拼接
			for( ArticleComment articleComment : articleComments1 )
			{ 
					user = null ; 
					//拿到进行评论的人的基本信息
					user = userDao.userInfoByUserId(articleComment.getUserComId() );
					
					//赋给articleComment
					articleComment.setUserComImg( user.getPortrait() );
					articleComment.setUserComNick( user.getNickname() );
					//记录点赞情况--当前用户对应的评论
					articleComment.setStarStatus(articleInteractionService.starExist(articleComment.getCommentId(), 2 , userId ) );
					
					//放到数据集合中
					articleComments.add(articleComment);
			}
			
			return articleComments;
		}
		
		/**
		 * @Description  进行回复的增加
		 * @param articleReply 
		 * @return 成功与否
		 */
		public boolean replyInsert(ArticleReply  articleReply) {
			//先在评论表中找到评论id相对应的用户Id
			 articleReply.setUserReplyToId( articleShowDao.getUserReplyToId(articleReply)   )  ;
			//将组装后的对象作为参数传到增加的DAO中
			return articleShowDao.replyInsert ( articleReply ) ;
		}
		
		/**
		 * @Description  进行回复的回复的增加
		 * @param articleReply 
		 * @return 成功与否
		 */
		public boolean replyInsertReply(ArticleReply  articleReply) {
			//将组装后的对象作为参数传到增加的DAO中
			return articleShowDao.replyInsert ( articleReply ) ;
		}
		

		/**
		 * @Description 组装返回回复对象的数据集合 
		 * @param currentPage 当前页
		 * @param pageSize 页面大小
		 * @param articleId 文章id
		 * @return articleReplys---数据集合
		 */
		public List<ArticleReply> queryReplyByComment( int currentPage ,int pageSize , int articleId){
			List<ArticleComment> articleComments = new ArrayList<ArticleComment>() ;
			//查询数据库的返回结果	
			List <ArticleReply> articleReplysBack = new ArrayList<ArticleReply>() ;
			
			//拿到回复的数据集合
			List <ArticleReply> articleReplys = new ArrayList<ArticleReply>() ;
			//通过评论的id进行数据集合的筛选--过滤到只剩符合评论id的回复对象的数据集合
			//先拿到评论对象的信息
			articleComments = articleShowDao.queryCommentByPage(currentPage, pageSize ,articleId);
				//进行数据的拼接--两个for循环--先在articleComments集合中进行循环
				for( ArticleComment articleComment : articleComments )
					{ 
							//在回复表中找到符合条件的评论id--返回数据库中的查询结果
							articleReplysBack = null;
							articleReplysBack = articleShowDao.queryReplyByComment(articleComment) ;
							if ( articleReplysBack != null ) 
								{
									//拿到相对应的用户昵称
									for( ArticleReply articleReply : articleReplysBack )
										{
														//拿到一个过滤之后的装有对应的comment_id的articleComment对象集合
														if( articleReply.getCommentId()==articleComment.getCommentId() )
															{
																//先通过articleReply的user_id找到相对应的用户对象，再通过对象.属性得到值，最后放到articleReply对象中
																articleReply.setUserReplyFromNick( userDao.userInfoByUserId( articleReply.getUserReplyFromId() ).getNickname()  ) ;
																articleReply.setUserReplyToNick( userDao.userInfoByUserId( articleReply.getUserReplyToId()).getNickname() );
																//得到发出回复的人的头像
																articleReply.setUserReplyImg( userDao.userInfoByUserId( articleReply.getUserReplyFromId() ).getPortrait() );
																//将符合条件的数据放在articleReplys中
																articleReplys.add( articleReply ) ;
															}
												}
										}
							//在a_reply中没有相对应的记录
							else 
							{
							
							}
				}
			return articleReplys;
		}

		
}
