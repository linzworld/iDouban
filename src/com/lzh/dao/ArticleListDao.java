package com.lzh.dao;

import java.util.List;

import com.lzh.entity.ArticleList;

/**
 * @Description 文章列表相关的数据库操作--分页-模糊
 * @author 林泽鸿
 * @time 2019年5月8日 下午11:11:08
 */
public interface ArticleListDao
{
	/**
	 * @Description 查询列表的记录总数--article
	 * @return 记录总数
	 */
	public int getTotalCount();



	/**
	 * @Description 通过用户定义的页面数和页面大小，进行分页查询
	 * @param currentPage 当前页
	 * @param pageSize    页面大小
	 * @return articleLists (只含有article)数据集合
	 */
	public List<ArticleList> queryArticleListByPage( int currentPage, int pageSize );
	
	
	
	
	/**
	 * @Description 查询列表的记录总数--article--我的文章列表
	 * @return 记录总数
	 */
	public int getMyTotalCount(int userId);



	/**
	 * @Description 通过用户定义的页面数和页面大小，进行分页查询---我的文章列表
	 * @param currentPage 当前页
	 * @param pageSize    页面大小
	 * @return articleLists (只含有article)数据集合
	 */
	public List<ArticleList> queryMyArticleListByPage(int currentPage, int pageSize, int userId);


	

		/**
		 * @Description 找出其中符合条件的记录数----a_article表
		 * @param searchContent  要模糊搜索的字段内容
		 * @return 记录数
		 */
		public int getArticleSearchCount ( String searchContent ) ;
		

		/**
		 * @Description  分页查询----找出符合条件的对象集合
		 * @param searchContent 模糊搜索内容
		 * @param currentPage  当前页
		 * @param pageSize    页面大小
		 * @return ArticleList对象集合
		 */
		public List<ArticleList> queryArticleByPage ( String searchContent ,int currentPage, int pageSize ) ;




		

		/**
		 * @Description 找出其中符合条件的记录数----a_article表
		 * @param searchContent  要模糊搜索的字段内容
		 * @param authorId 作者的id
		 * @return 记录数
		 */
		public int getMyArticleSearchCount ( String searchContent ,int authorId ) ;
		

		/**
		 * @Description  分页查询----找出符合条件的对象集合
		 * @param searchContent 模糊搜索内容
		 * @param currentPage  当前页
		 * @param pageSize    页面大小
		 * @param authorId 作者的id
		 * @return ArticleList对象集合
		 */
		public List<ArticleList> queryMyArticleByPage ( String searchContent ,int currentPage, int pageSize ,int authorId) ;




		
		/**
		 * @Description 查询列表的记录总数--article--我的收藏列表
		 * @return 记录总数
		 */
		public int getMyCollectionTotalCount(int userId);



		/**
		 * @Description 通过用户定义的页面数和页面大小，进行分页查询---我的收藏列表
		 * @param currentPage 当前页
		 * @param pageSize    页面大小
		 * @return articleLists (只含有article)数据集合
		 */
		public List<ArticleList> queryMyCollectionByPage(int currentPage, int pageSize, int userId);


		
	

}
