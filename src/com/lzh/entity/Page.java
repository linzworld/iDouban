package com.lzh.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 分页的辅助类 ---用于封装分类的属性--List当做装所有的对象集合
 * @author 林泽鸿
 * @time 2019年4月26日 下午2:19:06
 */
public class Page {
	  //总页数
    private int totalPage;         
	 //每页显示的数量--页面大小
    private int pageSize;         
    //总数量--总数据
    private int totalCount;        
    //当前页
    private int currentPage;      

    //当前页的数据集合--objects
    @SuppressWarnings("rawtypes")
	private List  objects = new ArrayList();

	/**
	 * @param totalPage
	 * @param pageSize
	 * @param totalCount
	 * @param currentPage
	 * @param objects
	 */
	@SuppressWarnings("rawtypes")
	public Page(int totalPage, int pageSize, int totalCount, int currentPage, List objects)
	{
		super();
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.objects = objects;
	}

	public int getTotalPage()
	{
		return totalPage;
	}
	//给总页数赋值----程序进行计算
	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}

	public int getPageSize()
	{
		return pageSize;
	}
/**
 * @Description 当调用总数据数的set方法 和 页面大小的set方法之后，计算出总页数 
 * @param pageSize
 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
		//计算公式  --总页数=数据总数%页面大小==0？数据总数 / 页面大小 : 数据总数 / 页面大小 + 1
		this.totalPage =this.totalCount % this.pageSize==0 ? this.totalCount /this.pageSize  :  this.totalCount/this.pageSize +1 ;
	
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	@SuppressWarnings("rawtypes")
	public List  getObjects()
	{
		return objects;
	}

	@SuppressWarnings("rawtypes")
	public void setObjects(List  objects)
	{
		this.objects = objects;
	}

	/**
	 * 
	 */
	public Page()
	{
		super();
		// TODO Auto-generated constructor stub
	}    

}