package com.lzh.service;

import com.lzh.dao.ArticleInteractionDao;
import com.lzh.daoImpl.ArticleInteractionDaoImpl;

public class ArticleInteractionService
{
	ArticleInteractionDao articleInteractionDao = new ArticleInteractionDaoImpl() ;
	/**
	 * @Description  得到是否存在该点赞记录
	 * @param typeId 类型的主键id
	 * @param type 类型
	 * @param userId 用户id
	 * @return 成功则返回1，失败则返回0
	 */
	public int starExist ( int typeId ,int type , int userId ) {
		if ( articleInteractionDao.starExist(typeId, type, userId ) )
			{
				return 1 ;
			}
		else 
			{
			   return 0;
			}
	} 
	

	/**
	 * @Description  新增一条点赞记录
	 * @param typeId 类型的主键id
	 * @param type 类型
	 * @param userId 用户id
	 * @return 成功与否
	 */
	public boolean starInsert( int typeId ,int type , int userId  ) {
		//如果已存在该点赞的记录，则返回结果，不执行下一步
		if(articleInteractionDao.starExist(typeId, type, userId) )
		{
			return true ;
		}
		//先在点赞表中加入一条点赞
		articleInteractionDao.starInsert(typeId, type, userId) ;
		//然后再记录点赞表中符合该对象所在数据库表的主键id的点赞总数
		int starNum = articleInteractionDao.starNumQuery(typeId,type);
		//将给记录总数更新到相对应的类型的记录上
		switch(type)
		{
		    case 1://点赞类型为文章
			    {
			    	return articleInteractionDao.starNumUpdate(starNum, typeId);
			    }
		    case 2://点赞类型为评论
			    {
			    	return articleInteractionDao.comStarNumUpdate(starNum, typeId);
			    }
		    case 3://点赞类型为回复
			    {
			    	return articleInteractionDao.replyStarNumUpdate(starNum, typeId);
			    }
		}
		return false;
	}
	
	
	/**
	 * @Description  删除一条点赞记录
	 * @param typeId 类型的主键id
	 * @param type 类型
	 * @param userId 用户id
	 * @return 成功与否
	 */
	public boolean starDelete( int typeId ,int type , int userId  ) {
		//先在点赞表中删除一条点赞
		articleInteractionDao.starDelete(typeId, type, userId) ;
		//然后再记录点赞表中该对象所在数据库表的主键id的点赞总数
		int starNum = articleInteractionDao.starNumQuery(typeId,type);
		
		//将给记录总数更新到相对应的类型的记录上
		switch(type)
		{
		    case 1://点赞类型为文章
			    {
			    	return articleInteractionDao.starNumUpdate(starNum, typeId);
			    }
		    case 2://点赞类型为评论
			    {
			    	return articleInteractionDao.comStarNumUpdate(starNum, typeId);
			    }
		    case 3://点赞类型为回复
			    {
			    	return articleInteractionDao.replyStarNumUpdate(starNum, typeId);
			    }
		}
		return false;
	}

	/**
	 * @Description  得到是否存在该收藏记录
	 * @param articleId  文章的Id
	 * @param userId 用户id
	 * @return 成功与否
	 */
	public boolean collectionExist ( int articleId ,int userId ) {
		return articleInteractionDao.collectionExist(articleId, userId);
	} 

	/**
	 * @Description   新增一条收藏记录
	 * @param articleId 文章id
	 * @param userId 进行收藏操作的用户id
	 * @return 成功与否
	 */
	public boolean collectionInsert  ( int articleId ,int userId ) {
		//如果已存在该收藏的记录，则返回结果，不执行下一步
		if(articleInteractionDao.collectionExist(articleId, userId) ){
			return true;
		}
		//先在收藏表中加入一条收藏
		articleInteractionDao.collectionInsert(articleId, userId) ;
		//然后再记录点赞表中符合该对象所在数据库表的主键id的点赞总数
		int collectionNum = articleInteractionDao.collectionNumQuery(articleId) ;
			//将给记录总数更新到文章表的收藏数的记录上
			 return articleInteractionDao.collectionNumUpdate(collectionNum, articleId);
	}
	
	/**
	 * @Description   删除一条收藏记录
	 * @param articleId 文章id
	 * @param userId 进行收藏操作的用户id
	 * @return 成功与否
	 */
	public boolean collectionDelete  ( int articleId ,int userId ) {
		
		//先在收藏表中删除一条收藏
		articleInteractionDao.collectionDelete(articleId, userId) ;
		//然后再记录点赞表中符合该对象所在数据库表的主键id的点赞总数
		int collectionNum = articleInteractionDao.collectionNumQuery(articleId) ;
			//将给记录总数更新到文章表的收藏数的记录上
			 return articleInteractionDao.collectionNumUpdate(collectionNum, articleId);
	}

	
	/**
	 * @Description  得到是否存在该转发记录
	 * @param articleId  文章的Id
	 * @param userId 用户id
	 * @return 成功与否
	 */
	public boolean shareExist ( int articleId ,int userId ) {
		return articleInteractionDao.shareExist(articleId, userId);
	} 

	/**
	 * @Description   新增一条转发记录
	 * @param articleId 文章id
	 * @param userId 进行转发操作的用户id
	 * @return 成功与否
	 */
	public boolean shareInsert  ( int articleId ,int userId ) {
		//如果已存在该转发的记录，则返回结果，不执行下一步
		if(articleInteractionDao.shareExist(articleId, userId) ){
			return true;
		}
		//先在转发表中加入一条转发
		articleInteractionDao.shareInsert(articleId, userId) ;
		//然后再记录点赞表中符合该对象所在数据库表的主键id的点赞总数
		int shareNum = articleInteractionDao.shareNumQuery(articleId) ;
			//将给记录总数更新到文章表的转发数的记录上
			 return articleInteractionDao.shareNumUpdate(shareNum, articleId);
	}
	
	/**
	 * @Description   删除一条转发记录
	 * @param articleId 文章id
	 * @param userId 进行转发操作的用户id
	 * @return 成功与否
	 */
	public boolean shareDelete  ( int articleId ,int userId ) {
		
		//先在转发表中删除一条转发
		articleInteractionDao.shareDelete(articleId, userId) ;
		//然后再记录点赞表中符合该对象所在数据库表的主键id的点赞总数
		int shareNum = articleInteractionDao.shareNumQuery(articleId) ;
			//将给记录总数更新到文章表的转发数的记录上
			 return articleInteractionDao.shareNumUpdate(shareNum, articleId);
	}
	
}
