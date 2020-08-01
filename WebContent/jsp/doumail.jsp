<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的豆邮</title>
<link rel="icon" href="http:/img.linzworld.cn/img/20200801142359.png" type="image/x-icon">

<!-- 豆邮的消息列表 -->
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
		<style type="text/css">
		/*样式初始化*/
		*{
			margin:0;
			padding:0;/*清除内外边距*/
		}
		ul{
			list-style:none;/*去掉列表中的样式的小点*/
		}
		.clearfix:before,.clear:after{/*清除浮动*/
			display:table;
			content:"";
		}
		.clearfix:after{
			clear:both;
		}
		.clearfix{
			*zoom:1;
		}
		a{
			color:#FFFFFF;
			text-decoration: none;/*取消下划线*/
		}        
		input{
			border:0;/*将input的边框改为0*/
			box-sizing:border-box;/*CSS3盒子模型 border和padding都包含到width中*/
			
		}
		body{
			background-color: #FFFFFF;/*整个页面的背景色*/
		}
		
		/*顶部的导航栏*/
		#first_menu{
			
			height:22px;
			background-color: #545652;
			border-top:3px solid #545652;/* 上边框 */
			border-bottom: 3px solid #545652;/* 下边框 */
		}
		#first_menu a{
		    float:right;/*右对齐*/ 
			font-family: 5px;/*字体*/
			color:#d5d5d5;
			text-decoration:none;/*取消下划线*/
			/* width:100px; */
			padding:8px 12px;/*上下是0，左右是15*/ 
			height:5px;
			line-height:5px;/*行高等于高度可以文字垂直居中*/
		    
			display:inline-block;/*显示成盒子大小*/
		}
		#first_menu a:hover{/*鼠标经过a链接*/
			background-color: #001D26;
		}
		/*带有图标的第二个导航栏*/
		#second{
			height:74px;
			/* background-color:pink; */
			overflow:hidden;/*防止外边距合并的问题*/
				background-color: #edf4ed;/*设置背景颜色*/
				margin-bottom:50px;/*下方的空白处*/
		}
		
		#second_menu{
			width: 1040px;
			height:58px;
			/* background-color: blue; */
			margin:8px auto;
		}
		/*让图标左浮动*/
		.logo{
			float:left;
			margin-right:50px;/*与右边的选项框相距50个像素*/
		}
		/*导航栏的选项*/
		.navbar{
			float:left;
			height:30px;
			line-height:30px;/*行高会继承 父类*/
			margin: 15px 0px 0px 0px;/*设置外边距*/
			
		}
		/*导航栏中的具体选项*/
		.navbar li{
			float:left;/*使排列成一行*/
		}
		.navbar li a{
			font-family: 33px;/*字体*/
			color:#072;
			text-decoration:none;/*取消下划线*/
			/* width:100px; */
		
			padding:0 15px;/*上下是0，左右是15*/ 
			height:30px;
			
			line-height:30px;/*行高等于高度可以文字垂直居中*/
			display:inline-block;/*显示成盒子大小*/
		}
		.navbar li a:hover{/*鼠标经过a链接*/
			background-color: #6dbc65;/*显示的颜色*/
		}
		
		/*搜索框外框部分--用来使搜索框排列在一行*/
		.search{
			float:right;
			height:30px;
			line-height:30px;/*行高会继承 父类*/
			margin-left:50px;/*与左边的logo相距50个像素*/
			margin-top:15px;/*与顶部边相距15个像素*/	
			border:1px solid #E1E9E1;/*设置边框边界的大小和颜色*/
		
		}
		/*搜索栏*/
    	.search input[type=text]{/*选择type属性为text的文本框,无搜索键*/
			/* background-color: blue; */
			width:249px;
			height:30px;
			padding-left:15px;/*搜索框中的文字与左保持20px距离*/
			
				float:left; 
				
		} 
		.search input[type=submit]{/*搜索的按钮*/
			width:31px;
			height:30px;
			/* background-color: pink; */
				float:left;
				/*搜素按钮的图片*/
				background: #FFFFFF url(http://47.102.212.18/iDouBan/image/01.png) center center no-repeat;
				background-size:15px 15px;
		}
		/*放置主要的页面内容*/
		#main_content{
			width: 1040px;
			height:1400px;
			/* background-color: blue; */
			margin: auto 239.600px;
			background-color: #ffffff;	
		}
		/*我关注的人的信息*/		
		.users_info_list{
			width: 1040px;
			height:80px;
			background-color: #ffffff;
			margin-left:0px;
			padding-buttom:5px;
			border:1px solid yellow;/*设置边框边界的大小和颜色*/
			
		}
		/*其他人的头像*/
		#everyone_list_portrait{
		margin-top:10px;
		margin-buttom:10px;
		margin-left:5px;
		}
		/*使得两个div在同一行*/
		#everyone_list_img{
		float:left;
		}
		/*我关注的人的基本信息*/
		#everyone_list_info{
		float:left;
		}
		#everyone_list_operation{
		margin-right:30px;
		margin-top:10px;
		margin-buttom:10px;
		float:right;
		}
		/*--昵称和个性签名*/
		#everyone_list_nickname{
		margin-top:10px;
		margin-buttom:10px;
		margin-left:10px;
		font-family: 33px;/*字体*/
		color:#072;
		
		}
		#everyone_list_signature{
		margin-top:10px;
		margin-buttom:10px;
		margin-left:10px;
		font-family: 33px;/*字体*/
		color:#072;
		}
		/*用户操作超链接*/
		.operation{
		color:black;
		}
		/*分页选项的布局*/
		#paging{
		float:right;
		margin-top:10px;
		margin-buttom:10px;
		margin-right:10px;
		}
		/*分页的文字说明*/
		#page_number{
		margin-right:20px;
		}
		/*分页的超链接*/
		.page_contrller{
		color:black;
		background-color:orange;
		}

/*************************************************/
/*具体功能的界面设计*/
/*整个整体布局的右半部分*/
#main_content-left{
	width:780px;
}

/*文章列表每一行的标头*/
.header{
   height: 70px;
}
/*每一项的最大布局*/
.item{
	height: 80px;
}
/*发送者的图片*/
.fromUserImg{
  height: 50.8;
  width: 62px;
  height: 62px;
  display: block;
   float: left;
}
/*发送者的头像的a*/
#img_a{
	width: 52px;
	margin: 5px;
	float: left;
	height: 48px;
}
/*展示发送者的头像*/
#fromUserImg_img{
	width: 52px;
	height: 52px;
	margin: 3px;
	border-radius: 2px;
	float: left;
}
/*右半部分*/
.right_part{
   	width: 700px;
    float: left;
    height: 80px;
  	margin: 5px;
}
/*右半上部分*/
.right_up_part{
    width: 675px;
    height: 30px;
}
/*发送者的昵称*/
#from_user_nickname{
	margin: 5px 0px 5px 5px;
	color:black;
	width: auto;
	display:block;
	float: left;
	height: 20px;
}
/*右下部分*/
.right_down_part{
    width: 790px;
    float: left;
    height: 25px;
    margin: 5px;

}
/*发送的时间*/
#chat_time{
    color: black;
    width: auto;
    display: block;
    float: right;
    font-size: 14px;
    height: 20px;
}
</style>



	</head>
		<!-- 个人信息的主页 -->
		<body>
			<!-- 顶部的菜单栏 -->
			<nav id="first">
				<div id="first_menu">
					<!-- 跳到servlet，对用户的cookie进行注销 -->
				    <a  href="http://47.102.212.18/iDouBan/ClearLoginServlet">退出登录</a>
				    <!-- 相对于端口号的相对路径 -->
					<a  href="http://47.102.212.18/iDouBan/jsp/alter.jsp">账号管理</a>
					<a  href="http://47.102.212.18/iDouBan/jsp/my_page.jsp">个人主页</a>
					
					<a href="/iDouBan/DoumailServlet?method=my_doumail_list">豆邮</a>
					
					<a href="/iDouBan/FriendListServlet?method=blacklist_list">黑名单</a>
					<a href="/iDouBan/FriendListServlet?method=attention_list">我的关注</a>
					<a href="/iDouBan/FriendListServlet?method=friend_list">我的好友</a>
					<!-- 先跳转servlet 查询第一页的所有人信息 -->
					<a href="/iDouBan/EveryoneListServlet?method=everyone_list">所有人</a>
					<!-- 编辑文章 -->
					<a href="http://47.102.212.18/iDouBan/jsp/article_edit.jsp" target="_blank">写文章</a>
					<a href="/iDouBan/ArticleListServlet?method=article_list">所有文章</a>
					<a href="/iDouBan/ArticleListServlet?method=my_article_list" >我的文章</a>
					<a href="/iDouBan/ArticleListServlet?method=my_collection_list">我的收藏</a>
				</div>
			</nav>
			<!-- 第二个导航栏 -->
			<header id="second">
					<nav  id="second_menu">
						<!-- logo部分 -->
							<div class="logo">
							 	<img alt="豆瓣logo" src="http://47.102.212.18/iDouBan/image/豆瓣首页logo.jpg" width=175px height=58px >
							</div>
						<!-- 导航栏部分_可选择部分 -->
							<div class="navbar">
								<ul>
									<li><a href="#">首页</a></li>
									<li><a href="http://47.102.212.18/iDouBan/jsp/my_page.jsp">个人主页</a></li>
									<li><a href="/iDouBan/ArticleListServlet?method=article_list">浏览发现</a></li>
								</ul>	
							</div>
							<!-- 搜索框部分 -->
							<div class="search">
									<form action="ArticleListServlet?method=search_article_list&currentPage=1" id="search_action" method="post">
										<!-- placeholder占位符 -->
										<input type="text" name="search_content" id="search_content" placeholder="搜索你感兴趣的内容和人">
										<!-- placeholder--默认显示值 -->
										<input  type="submit" value="">
									</form>
							</div>
					</nav>
			</header>
			<!-- 显示主要内容的地方 -->
			<div id="main_content">
				<div id="main_content-left">
					<%--豆邮的信息列表 --%>
					<div id="main_content-left-top">
					<!-- 豆邮的标题 -->
					 <div >
							<h1  style="margin-bottom: 20px;">我的豆邮</h1>
					</div>
					
						<div >
						 	<c:forEach  items="${requestScope.p.objects}" var="a" varStatus="status">
								<!-- 豆邮每一项的内容 -->
							    <div class="item">
									<!--豆邮发送者的头像和昵称 -->
									<div class="header" >
										<!--发送者的头像---左半部分 -->
										 <div class="author-img" >
											<a href="javascript:void(0);" target="_blank" id="img_a">
												<img src="${a.fromUserImg}" id = "fromUserImg_img">
											</a>
										</div>
										<!--每一项的右半部分 -->						   
										<div class="right_part">	
								        	<!--每一项的右半上部分 -->		
								            <div class="right_up_part">
												<!--发送者的昵称-->
												<a href="javascript:void(0);" target="_blank" id="from_user_nickname" >
													${a.fromUserNick}
												</a>
								                <!--发送的时间-->
									             <a href="#" target="_blank" id="chat_time"  >
													${a.chatTime}
												 </a>
								               
								    		</div>
								    		<!--每一项的右半下部分 -->		
								    		<!--豆邮内容预览-->
								    		<div  class="right_down_part">	
												<!-- 跳转到豆邮的详情---所有有关双方的豆邮 -->
												<a href="/iDouBan/DoumailServlet?method=doumail_show&to_user_id=${a.fromUserId}&nickname=${a.fromUserNick}" target="_blank" style="color:black">
												    ${a.chatMsg}
												</a>
											</div>
										</div>
									</div>	
								</div>
								 <br>	
								 <hr>					
						 	</c:forEach>
						</div>
					</div>
						 <!-- 分页选择链接    /iDouBan/ArticleListServlet?method=article_list -->
						 <div id="paging">
						 		<span id="page_number">
								当前第 ${requestScope.p.currentPage} 页，总共 ${requestScope.p.totalPage} 页             
								</span>		
								<a href="DoumailServlet?method=${requestScope.method}&currentPage=1" id="first_page" class="page_contrller">首页</a>
								<!-- 利用el表达式的三目运算符进行判断 -->
								<a href="DoumailServlet?method=${requestScope.method}&currentPage=${(requestScope.p.currentPage==1)?1:requestScope.p.currentPage-1}" id="pervious_page"  class="page_contrller">上一页</a>
								<a href="DoumailServlet?method=${requestScope.method}&currentPage=${(requestScope.p.currentPage==requestScope.p.totalPage)?requestScope.p.totalPage:requestScope.p.currentPage+1}" id="next_page"  class="page_contrller">下一页</a>
								<a href="DoumailServlet?method=${requestScope.method}&currentPage=${requestScope.p.totalPage}" id="last_page"  class="page_contrller">尾页</a>					
											
						 </div>
						 
						 
					</div>
					<div id="main_content-left-centre">

						
					</div>
					<div id="main_content-right">
					</div>
				</div>
</body>
</html>