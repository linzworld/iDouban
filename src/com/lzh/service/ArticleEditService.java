package com.lzh.service;
/**
 * @Description 实现文章的储存和展示的逻辑处理 
 * @author 林泽鸿
 * @time 2019年5月2日 下午11:22:20
 */

import com.lzh.dao.ArticleEditDao;
import com.lzh.daoImpl.ArticleEditDaoImpl;
import com.lzh.entity.Article;

public class ArticleEditService
{
	/**
	 * DAO层
	 */
	ArticleEditDao articleDao = new ArticleEditDaoImpl();
	
/**
 * @Description 新增文章--新增一条记录到friend表
 * @param article 文章的信息
 * @return 成功与否
 */
	public int articleInsert(Article article) {
		return articleDao.articleInsert(article);
	}


	/**
	 * @Description 新增一条记录到a_tag（判断），最后新增一条记录到中间表中（article_to_tag）
	 * @param tagName 用户输入的文章分组标签名
	 * @param articleId 文章id
	 * @return 成功与否
	 */
	  public boolean articleNewTag(String tagName  ,int articleId)  {
		 boolean judge = false ;
		  int tagId = -1;
		  tagId = articleDao.tagExist(tagName);
		  			//如果a_tag表中找无该标签名对应的记录，则新建一条新纪录到a_tag表中
			  		if( tagId ==-1 )
					  	{
			  				//新建一条新纪录到a_tag表中
			  				tagId = articleDao.tagInsert(tagName);
					  	}
			  		else	{       	}
				  		//最后将上面得到的结果放到文章和标签的中间表中
				  		judge = articleDao.middleInsert(articleId, tagId);
		  		return judge ;
		  }
	 
	  /**
	   * @Description 修改文章--1. 更新文章表记录---2.删除中间表记录
	   * @param article 文章的信息
	   * @return 成功与否
	   */
	  public boolean articleModify (Article article) {
		//返回结果  
		boolean judge = false ;
				judge = articleDao.articleUpdate(article);
				if( judge==true )
					{
						judge = articleDao.middleDelete ( article.getArticleId() ) ;
					}
				//文章更新出错
				else
					{
					
					}
				return judge;
	  }
	  
	  /**
	   * @Description 删除文章 
	   * @param articleId 文章的Id
	   * @return 成功与否
	   */
	  public boolean articleDelete (int articleId) {
		  
		 return articleDao.articleDelete(articleId) ;
	  }
}
