package com.lzh.service;

import java.util.ArrayList;
import java.util.List;

import com.lzh.dao.ArticleListDao;
import com.lzh.dao.UserDao;
import com.lzh.daoImpl.ArticleListDaoImpl;
import com.lzh.daoImpl.UserDaoImpl;
import com.lzh.entity.ArticleList;
import com.lzh.entity.User;

/**
 * @Description 对文章列表进行展示的业务逻辑处理 
 * @author 林泽鸿
 * @time 2019年5月5日 下午4:03:15
 */
public class ArticleListService
{
	
		ArticleList articleList = new ArticleList();
		
		ArticleListDao articleListDao = new ArticleListDaoImpl();
		
		UserDao userDao =new  UserDaoImpl() ;
		
		/**
		 * @Description 查询数据的总条数
		 * @return 总条数
		 */
		public int getTotalCount() {
			return articleListDao.getTotalCount();
			}
		
		
		/**
		 * @Description  分页查询所有文章
		 * @param currentPage 当前页面 
		 * @param pageSize 页面大小
		 * @return 数据集合
		 */
	public List<ArticleList> queryArticleListByPage( int currentPage ,int pageSize){
		List<ArticleList> articleLists = new ArrayList<ArticleList>() ;
		List<ArticleList> articleLists1 = new ArrayList<ArticleList>() ;
		
		User user = new User();
		//先拿到文章表的信息
		articleLists1 = articleListDao.queryArticleListByPage(currentPage, pageSize);
		//进行数据的拼接
		for( ArticleList articleList : articleLists1 )
		{ 
				user = null ; 
				//拿到作者的基本信息
				user = userDao.userInfoByUserId(articleList.getAuthorId());
				
				//赋给articleList
				articleList.setAuthorImg( user.getPortrait() );
				articleList.setAuthorNick( user.getNickname() );
				
				
				//放到数据集合中
				articleLists.add(articleList);
		}
		
		return articleLists;
	}
	
	
	/**
	 * @Description 查询数据的总条数--我的文章列表
	 * @return 总条数
	 */
	public int getMyTotalCount(int userId ) {
		return articleListDao.getMyTotalCount( userId );
		}
	
	
	/**
	 * @Description  分页查询所有文章
	 * @param currentPage 当前页面 
	 * @param pageSize 页面大小
	 * @return 数据集合
	 */
		public List<ArticleList> queryMyArticleListByPage( int currentPage ,int pageSize ,int userId){
			List<ArticleList> articleLists = new ArrayList<ArticleList>() ;
			List<ArticleList> articleLists1 = new ArrayList<ArticleList>() ;
			
			User user = new User();
			//先拿到文章表的信息--我的文章信息
			articleLists1 = articleListDao.queryMyArticleListByPage(currentPage, pageSize  , userId);
			//进行数据的拼接
			for( ArticleList articleList : articleLists1 )
			{ 
					user = null ; 
					//拿到作者的基本信息
					user = userDao.userInfoByUserId(articleList.getAuthorId());
		
					//赋给articleList
					articleList.setAuthorImg( user.getPortrait() );
					articleList.setAuthorNick( user.getNickname() );
					
					
					//放到数据集合中
					articleLists.add(articleList);
			}
			
			return articleLists;
		}
			

	/**
	 * @Description  查询符合条件的数据的总条数
	 * @param searchContent 搜索框中的内容
	 * @return 符合条件的总条数
	 */
	public int getArticleSearchCount ( String searchContent ) {
		return articleListDao.getArticleSearchCount(searchContent) ;
		
	}



	/**
	 * @Description 
	 * @param searchContent 搜索框中的内容
	 * @param currentPage 当前页
	 * @param pageSize 页面大小
	 * @return  数据集合( 对象集合 )
	 */
	public List<ArticleList> queryArticleByPage( String searchContent , int currentPage ,int pageSize){
		List<ArticleList> articleLists = new ArrayList<ArticleList>() ;
		List<ArticleList> articleLists1 = new ArrayList<ArticleList>() ;
		
		User user = new User();
		//先拿到文章表的信息--我的文章信息
		articleLists1 = articleListDao.queryArticleByPage(searchContent, currentPage, pageSize);
			//进行数据的拼接
			for( ArticleList articleList : articleLists1 )
			{ 
					user = null ; 
					//拿到作者的基本信息
					user = userDao.userInfoByUserId(articleList.getAuthorId());
					//赋给articleList
					articleList.setAuthorImg( user.getPortrait() );
					articleList.setAuthorNick( user.getNickname() );
					//放到数据集合中
					articleLists.add(articleList);
			}
			return articleLists;
		}


	/**
	 * @Description  查询符合条件的数据的总条数
	 * @param searchContent 搜索框中的内容
	 * @param  authorId 作者的id
	 * @return 符合条件的总条数
	 */
	public int getMyArticleSearchCount ( String searchContent  , int authorId ) {
		return articleListDao.getArticleSearchCount(searchContent) ;
		
	}



	/**
	 * @Description 
	 * @param searchContent 搜索框中的内容
	 * @param currentPage 当前页
	 * @param pageSize 页面大小
	 * @param authorId 作者的id
	 * @return  数据集合( 对象集合 )
	 */
	public List<ArticleList> queryMyArticleByPage( String searchContent , int currentPage ,int pageSize , int authorId){
		List<ArticleList> articleLists = new ArrayList<ArticleList>() ;
		List<ArticleList> articleLists1 = new ArrayList<ArticleList>() ;
		
		User user = new User();
		//先拿到文章表的信息--我的文章信息
		articleLists1 = articleListDao.queryMyArticleByPage(searchContent, currentPage, pageSize ,authorId);
			//进行数据的拼接
			for( ArticleList articleList : articleLists1 )
			{ 
					user = null ; 
					//拿到作者的基本信息
					user = userDao.userInfoByUserId(articleList.getAuthorId());
					//赋给articleList
					articleList.setAuthorImg( user.getPortrait() );
					articleList.setAuthorNick( user.getNickname() );
					//放到数据集合中
					articleLists.add(articleList);
			}
			return articleLists;
		}

	/**
	 * @Description 查询数据的总条数--我的收藏中的文章集合
	 * @return 总条数
	 */
	public int getMyCollectionTotalCount(int userId ) {
		return articleListDao.getMyCollectionTotalCount( userId );
		}
	
	
	/**
	 * @Description  分页查询我的收藏中的文章集合
	 * @param currentPage 当前页面 
	 * @param pageSize 页面大小
	 * @return 数据集合
	 */
		public List<ArticleList> queryMyCollectionByPage( int currentPage ,int pageSize ,int userId){
			List<ArticleList> articleLists = new ArrayList<ArticleList>() ;
			List<ArticleList> articleLists1 = new ArrayList<ArticleList>() ;
			
			User user = new User();
			//先拿到文章表的信息--我的文章信息
			articleLists1 = articleListDao.queryMyCollectionByPage(currentPage, pageSize  , userId);
			//进行数据的拼接
			for( ArticleList articleList : articleLists1 )
			{ 
					user = null ; 
					//拿到作者的基本信息
					user = userDao.userInfoByUserId(articleList.getAuthorId());
		
					//赋给articleList
					articleList.setAuthorImg( user.getPortrait() );
					articleList.setAuthorNick( user.getNickname() );
					
					
					//放到数据集合中
					articleLists.add(articleList);
			}
			
			return articleLists;
		}

	
	
}
