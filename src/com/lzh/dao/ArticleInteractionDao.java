package com.lzh.dao;

/**
 * @Description 文章的用户互动--点赞，收藏，转发 评论数，回复数，
 * @author 林泽鸿
 * @time 2019年5月10日 下午8:32:37
 */
public interface ArticleInteractionDao
{

	/**
	 * @Description 在a_comment表中对符合articleId的记录数进行搜索
	 * @param comNum 该文章的评论数
	 * @param articleId 文章id
	 * @return 文章的评论总数
	 */
	public int comNumQuery (   int articleId ) ;
	
	/**
	 * @Description 在增加评论的同时，执行该方法，更新a_article表中的评论数 
	 * @param articleId
	 * @return 成功与否
	 */
	public boolean comNumUpdate( int comNum ,  int articleId );

	/**
	 * @Description 查询a_star表中是否含有该记录 
	 * @param typeId 类型相对应的主键id
	 * @param type 类型
	 * @param userId 用户id
	 * @return 成功与否
	 */
	public boolean starExist(int typeId ,int type ,int userId );
	
	/**
	 * @Description 增加点赞的记录-----------------文章，评论，回复共用 
	 * @param typeId 各自对应的主键id
	 * @param type 类型的分类
	 * @param userId 进行点赞的用户的id
	 * @return 成功与否
	 */
	public boolean starInsert( int typeId ,int type ,int userId ); 
	
	
	/**
	 * @Description 删除点赞的记录---文章，评论，回复------------------共用 
	 * @param typeId 各自对应的主键id
	 * @param type 类型的分类
	 * @param userId 进行点赞的用户的id
	 * @return 成功与否
	 */
	public boolean starDelete( int typeId ,int type ,int userId ); 
	/**
	 * @Description 在a_article表中对符合articleId的记录数进行搜索----共用
	 * @param starNum 该文章的点赞数
	 * @param articleId 文章id
	 * @return 文章的点赞总数
	 */
	public int starNumQuery (   int articleId , int type ) ;	
	
	/**
	 * @Description 在增加点赞的同时，执行该方法，更新a_article表中的点赞数 
	 * @param starNum 文章点赞数
	 * @param articleId 文章id
	 * @return 成功与否
	 */
	public boolean starNumUpdate( int starNum ,  int articleId );

	/**
	 * @Description 在增加点赞的同时，执行该方法，更新a_comment表中的点赞数 
	 * @param starNum 评论点赞数
	 * @param commentId 评论id
	 * @return 成功与否
	 */
	public boolean comStarNumUpdate( int starNum ,  int commentId );
	
	/**
	 * @Description 在增加点赞的同时，执行该方法，更新a_reply表中的点赞数 
	 * @param starNum 回复点赞数
	 * @param replyId 回复id
	 * @return 成功与否
	 */
	public boolean replyStarNumUpdate( int starNum ,  int replyId );
	

	
	/**
	 * @Description  查询a_collection表中是否含有该记录 
	 * @param articleId 文章的id
	 * @param userId 进行收藏操作的用户id
	 * @return 成功与否
	 */
	public boolean collectionExist(int articleId , int userId );
	
	/**
	 * @Description  增加收藏文章的记录 
	 * @param articleId 对应文章的主键id 
	 * @param userId 进行收藏的用户的Id
	 * @return 成功与否
	 */
	public boolean collectionInsert( int articleId ,int userId ); 
	
	/**
	 * @Description  删除收藏文章的记录 
	 * @param articleId 对应文章的主键id 
	 * @param userId 进行收藏的用户的Id
	 * @return 成功与否
	 */
	public boolean collectionDelete( int articleId ,int userId ); 

	/**
	 * @Description 在a_collection表中查找符合articleId的记录总数
	 * @param articleId 文章的Id
	 * @return 文章的收藏总数
	 */
	public int collectionNumQuery (   int articleId   ) ;	
	/**
	 * @Description  在进行文章的收藏（收藏或者取消收藏）的同时，执行该方法，更新a_article表中的收藏数
	 * @param collectionNum 文章的收藏数
	 * @param articleId 文章的id
	 * @return 成功与否
	 */
	public boolean collectionNumUpdate( int collectionNum ,  int articleId );
	
	
	
	
	/**
	 * @Description  查询a_share表中是否含有该记录 
	 * @param articleId 文章的id
	 * @param userId 进行收藏操作的用户id
	 * @return 成功与否
	 */
	public boolean shareExist(int articleId , int userId );
	
	/**
	 * @Description  增加收藏文章的记录 
	 * @param articleId 对应文章的主键id 
	 * @param userId 进行收藏的用户的Id
	 * @return 成功与否
	 */
	public boolean shareInsert( int articleId ,int userId ); 
	
	/**
	 * @Description  删除收藏文章的记录 
	 * @param articleId 对应文章的主键id 
	 * @param userId 进行收藏的用户的Id
	 * @return 成功与否
	 */
	public boolean shareDelete( int articleId ,int userId ); 

	/**
	 * @Description 在a_share表中查找符合articleId的记录总数
	 * @param articleId 文章的Id
	 * @return 文章的收藏总数
	 */
	public int shareNumQuery (   int articleId   ) ;	
	/**
	 * @Description  在进行文章的收藏（收藏或者取消收藏）的同时，执行该方法，更新a_article表中的收藏数
	 * @param shareNum 文章的收藏数
	 * @param articleId 文章的id
	 * @return 成功与否
	 */
	public boolean shareNumUpdate( int shareNum ,  int articleId );
	

}
