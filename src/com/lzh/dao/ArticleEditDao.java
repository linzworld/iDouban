package com.lzh.dao;

import com.lzh.entity.Article;

/**
 * @Description 存储文章内容到数据库 
 * @author 林泽鸿
 * @time 2019年5月2日 下午10:10:00
 */
public interface ArticleEditDao
{
	
	
 /**
  * @Description  增加一篇文章记录到数据库---新增文章
  * @param article article表所对应的实体类
  * @return 刚插入数据库中的文章的id---失败则返回-1
  */
	public int articleInsert(Article article);
	 /**
	  * @Description  查找编辑过的文章--查找文章
	  * @param article article表所对应的实体类
	  * @return 刚插入数据库中的文章的id---失败则返回-1
	  */
	 public int articleQuery(Article article);
			
	 /**
	  * @Description  更新一篇文章记录--a_article表--修改文章
	  * @param article article表所对应的实体类
	  * @return 成功与否
	  */
	public boolean articleUpdate(Article article);
	/**
	 * @Description 查询a_tag表中是否含有该记录 
	 * @param tagName 用户输入的标签名
	 * @return 成功返回该记录的主键 ---失败返回-1
	 */
	public int tagExist(String tagName);
	
	/**
	 * @Description 新增一条记录到a_tag表中 
	 * @param tagName 用户输入的标签名
	 * @return 成功返回该记录的主键 ---失败返回-1
	 */
	public int tagInsert(String tagName);
	
	
/**
 * @Description 在article_to_tag表中插入一条新纪录 
 * @param articleId 文章id
 * @param tagId 文章分类的id
 * @return 成功与否
 */
	public boolean middleInsert(int articleId , int tagId);
	
	/**
	 * @Description 删除---在article_to_tag表中（中间表）删除所有关于articleId的所有纪录 
	 * @param articleId 文章id
	 * @return 成功与否
	 */
	public boolean middleDelete ( int articleId ) ;

	/**
	 * @Description 删除---在article表中删除所有关于文章id的一条纪录 
	 * @param articleId 文章id
	 * @return 成功与否
	 */
	public boolean articleDelete ( int articleId ) ;
}
