<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>与${sessionScope.nickname}的豆邮</title>
<link rel="icon" href="http://47.102.212.18/iDouBan/icon/iDouBan_favicon.ico" type="image/x-icon">

<!-- 和别人的豆邮 -->
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
		    color: black;
		    background-color: orange;
		    border-radius: 5px;
		    padding: 3px;
		}

/*************************************************/
/*具体功能的界面设计*/
/*文章列表每一行的标头*/
.header{
    margin-top: 5px;
}
/*文章的主体*/
#main_content-left-top{
    width: 675px;
}
/*作者的头像*/
.author-img{
	margin-top: 10px;
border-radius: 4px;
}

/*作者的昵称*/
#author-nickname{
	
}
/*文章标题*/
.title{
    height: 20px;
    margin-bottom: 5px;
    margin-top: 5px; 
}
/*中间部分*/
.middle{
	height: auto;
}
/*文章主题部分*/
.article-main-content{
    height: auto;
}
/*文章主题---充当要被撑开的父盒子*/
.content{
    width: 675px;
	height: auto;
}

/*文章内部具体修改*/
/*文章的标头*/
/*作者的头像*/

/*文章发表时间*/
#author-publishedTime{
    margin: 5px 0px 1px 26px;
    color: black;
    font-size: 14px;
}

/*按钮的布局*/
.bottom_inner_div{
    height: 50px;
}
/*用户可点击的按钮--用户互动*/
.user_operation_btn{
    color: grey;
    float: right;
    margin: 23px 9px;
}

/*按钮组*/
.bottom{
    margin-left: 270px;
    margin-top: 60px;
    width: 400px;
    margin-bottom: 20px;
}

/*对话区的布局*/
#main_content-left-centre{
    height: auto;
    width: 675px;
}
/*对话者头像的布局*/
#pic{
    margin: 13px 6px;
    width: 70px;
    float: left;
}
/*对话的内容布局*/
#doumail-content{
    float: left;
    height: 90px;
    width: 590px;
    margin: 11px 0px;
}
/*对话的文字内容*/
.doumail-msg{
    margin: 8px;
}

/*文本框*/
textarea{
    width: 98%;
    height: 140px;
}
/*对话区分页的选项*/
#paging{
    width: auto;
    border: 1px solid #80808029;
    border-radius: 35px;
    float: right;
    margin-right: 0px;
    margin-bottom: 25px;
    margin-top: 57px;
    padding: 8px 14px;
    background-color: #fdfcf9;
}

/*提交按钮*/
#submit_btn{
    border-top-width: 0px;
    border-right-width: 0px;
    border-bottom-width: 0px;
    border-left-width: 0px;
    height: 27px;
    color: #000000;
    width: 52px;
    border-radius: 3px;
    background-color: #f0e2e26b;

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
					<%--文章的显示 --%>
					<div id="main_content-left-top">
						<div >
							<h1   style="margin-bottom: 20px;">与${sessionScope.nickname}的豆邮</h1>
						</div>
						
					</div>

					</div>
					
					<!-- 对话区 -->
					<div id="main_content-left-centre">
						<hr>
						<div>
						
						</div>
						<!-- 豆邮的对话区 --分页列表--->
				        <!-- 豆邮的每一项 --> 
				         <div  id="doumail-code">
				         
				         </div>
				          <!-- 设置锚点 -->
					           <a href="#" id="submit-confirm"></a>
				         <!-- 对话编辑区 -->
				         <h2>
						        你的回应
						            &nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
						 </h2>
				         <div class="doumail-form-div" >
				         
							
							
							<%--对话--隐藏表单 --%>	
					        <form action="/iDouBan/DoumailServlet?pre_method=doumail_content&method=doumail_show" id="onSubmit" name="doumail_form" method="post" >
					        	<!-- 发表对话的文本框 -->
					          <div class="item">
					              <textarea name="doumail-place" required  id="doumail-textarea" rows="4" cols="64"  onKeyUp="checkMaxInput(this)" onKeyDown="checkMaxInput(this)"></textarea>
					              <br>
					          </div>
					          	<%--隐藏表单--用来传输文本框的内容--content --%>
					            <input type="hidden" name="content" id="content" value="">
								<%--隐藏表单，设置当前被回复的用户的Id --%>					            
					           <input type="hidden" name="to_user_id" id="to_user_id" value="">
					        </form>
					        <%--隐藏表单--提交 --%>
					        	<div class="submit-confirm">
					          		<span>
					                	<button onclick="formSubmit()" id="submit_btn">确定</button>
					                </span>
					           </div>
					     </div>
					     <!-- 分页选择链接    /iDouBan/ArticleListServlet?method=article_list -->
						 <div id="paging">
						 		<span id="page_number">
								当前第 <strong > <span id ="currentPage_num"> </span> </strong> 页，总共 <strong > <span id="totalPage_num"> </span> </strong> 页             
								</span>		
								<a href="#" id="first_page" class="page_contrller">首页</a>
								<!-- 利用el表达式的三目运算符进行判断 -->
								<a href="#" id="previous_page"  class="page_contrller">上一页</a>
								<a href="#" id="next_page"  class="page_contrller">下一页</a>
								<a href="#"  id="last_page" class="page_contrller">尾页</a>					
						 </div>
					</div>
				</div>
				
				<div id="main_content-right">
				</div>
				
				
			

	<script type="text/javascript">
	//定义一个全局变量，用来存放对话当前的页码
	var currentPage;
	//对话的总页数
	var totalPage;
	//用于限制文本框的输入长度
	function checkMaxInput(txt)

	{ 
	       if (txt.value.length > 666)
	    	   {  
	    	   txt.value = txt.value.substring(0, 666);
	    	   alert("最大输入长度为666个字节!!!");
	    	   }
	       
	}
	
	
	
	//进行隐藏表单的提交(onSubmit)--提交对话或者回复的提交的字符串内容
	function formSubmit(){
		//用户对话区的文本框输入的值--对话或者回复的内容
		var y = document.getElementById("doumail-textarea");

		//过滤掉script标签
		if( y.value !== filterScript(y.value) )
			{
				alert("你的输入中含有不合法的字符，请重新输入！");
			}
		var lastContent ;
		//将其代码进行转义，为了显示在对话中
		lastContent =  htmlEscape(y.value);
		//如果填入的数据为空
		if( isContentEmpty( lastContent ) )
			{
				//退出函数
				return ;
			}
		//用户填入的文本框内容---转义后存入数据库
		document.getElementById("content").value = lastContent ;
		//被对话的用户的id
		document.getElementById("to_user_id").value = ${requestScope.toUserId};
		//提交对话的表单
			document.getElementById("onSubmit").submit();
			return ;
		}
	
/***************************************************/
	//表单验证
	//判空处理
	function isContentEmpty(yValue){
		//先进行非空判断
		v=$.trim(yValue);
        if(v==''||v==null)
        	{
        		alert("对话或者回复的内容不能为空");
        		//将空格清空
        		document.getElementById("doumail-textarea").value="";
        		return true;
        	}
		return false;
	}
	
	//过滤script标签
	function filterScript(htmlStr){
			//匹配script标签
		    var reg=new RegExp("<.*?script[^>]*?>.*?(<\/.*?script.*?>)*","ig");
		    if(reg.test(htmlStr)){
		       return htmlStr.replace(/<.*?script[^>]*?>.*?(<\/.*?script.*?>)*/ig,'');
		    }
		return htmlStr ;
	}
	
	//HTML代码进行转义---用户填写的位置
	function htmlEscape(htmlStr) {
		 return htmlStr.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
	}

	
/***********************************************/
	//初始化的对话区
	$(document).ready(function(){
		var currentPage = 1 ;
		$("#totalPage_num").innerText = totalPage ;
		toAjax(currentPage);
	});
	
	
	
	//分页对话区的切换
	//首页
	$(document).ready(function(){
		$("#first_page").click(function(event){
			if(currentPage === 1){
				alert("已经是对话的首页了！");
				return ;
				}
		
			currentPage = 1;
			toAjax(currentPage);
		});
	});
	
	
	//上一页的点击事件，对话区的切换
	$(document).ready(function(){
		$("#previous_page").click(function(event){
			currentPage=currentPage==1?1:currentPage-1;
			toAjax(currentPage);
		});
	});

	//下一页的点击事件，对话区的切换
	$(document).ready(function(){
		$("#next_page").click(function(event){
			currentPage=currentPage==totalPage?totalPage:currentPage+1;
			toAjax(currentPage);

		});
	});
	
	//尾页的点击事件，对话区的切换
	$(document).ready(function(){
		$("#last_page").click(function(event){
			if(currentPage === totalPage){
				alert("已经是对话的尾页了！");
				return ;
				}
			if(totalPage===0){
				return ;
			}
			else{
				toAjax(totalPage);		
			}
		
		});
	});
	
	
	//展示页面数的地方
	function showNum( ){
		document.getElementById("currentPage_num").innerHTML = currentPage;
		document.getElementById("totalPage_num").innerHTML = totalPage;
		
		if( totalPage == 0)
			{	
			document.getElementById("currentPage_num").innerHTML = 1;
			document.getElementById("totalPage_num").innerHTML = 1;
			}
	
	}
	
	//页面先加载完文章主题部分--然后通过ajax加载对话部分
	//原生JS实现AJAX 
	//封装要进行发送AJAX请求的函数---对话区专用
	function toAjax(c){
		
		//清空上一次的对话内容---用empty清空原来的对话区
		$("#doumail-code").empty();
		
		/* 异步方式 */
		xmlHttpRequest = new XMLHttpRequest();
		/* 设置xmlHttpRequest对象的回调函数 */
		xmlHttpRequest.onreadystatechange = callback ;
		// 异步方式为false 同步处理---为了拿到值
		xmlHttpRequest.open("post","http://47.102.212.18/iDouBan/DoumailServlet",true);
		//设置post方式的头信息--文件上传application/x-www-form-urlencoded  multipart/form-data --文件上传不能加这个标头设置
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttpRequest.send("method=doumail_show_details&to_user_id="+${requestScope.toUserId}+"&currentPage="+c); 
	}
	
	
	
	
	//定义回调函数
	function callback(){	
		if(xmlHttpRequest.readyState == 4 & xmlHttpRequest.status == 200 )
		{
			//接收服务端返回的数据
			//设置服务器返回值的类型是String格式--JSON字符串---JSON.parse将字符串转化为对象
			 obj = JSON.parse(xmlHttpRequest.responseText);  
				 for (var i = 0; i<obj.objects.length;i++ ) {
						 $doumail = 
	            	 	'						<!-- 对话的每一项 -->'+
	            		
						'						<div class=\"doumail-item\" id=\"'+obj.objects[i].doumailId+'\" style=\"    display: flex;box-shadow: 0 0 black; \" >'+
						'						    <!-- 对话人的头像 -->'+
						'						    <div class=\"pic\" style=\"margin: 13px 6px;width: 70px;float: left;\">'+
						'						        	<img width=\"52\" height=\"52\" class="" src=\"'+obj.objects[i].fromUserImg+'\" alt=\"用户头像\" style=\"margin: 2px 0px;    border-radius: 4px;\">'+
						'						    </div>'+
						'						    <div class="content report-doumail" style="float: left;height: auto;width: 590px;margin: 11px 0px;">'+
						'						        <div class="author">'+
						'						        	<!-- 对话时间 -->'+
						'						            <span class="">'+obj.objects[i].chatTime+'</span>'+
						'                           		<!--对话人的昵称-->'	+												
						'						            <a href="#" class="" style="color:black;margin: 0px 11px;">'+obj.objects[i].fromUserNick+'</a>'+
						'						        </div>'+
						'						'+
						'						        <!-- 对话的内容-->'+
						'						        <p id=\"doumail-msg\" style="margin: 10px 2px;height: auto;    line-height: 1.6;font-size: 13px;    width: 575px;word-wrap: break-word;overflow: hidden;">'+obj.objects[i].chatMsg+
						'						</p>'+
						'						    </div>'+
						'<input type=\"hidden\"   value = \"'+obj.objects[i].doumailId+'\">'+
						'						</div>'+
						'';
							    $("#doumail-code").prepend($doumail);
	         }
					

				//拿到分页对应的当前页
				currentPage = obj.currentPage;
				//拿到总页数
				totalPage = obj.totalPage ;
				//设置当前页数和总页数
				if(totalPage != null )
				{	
					if( totalPage == 0)
					{
					currentPage = 1;
					totalPage = 0 ;
					}
				}
				
				//调用显示页数的函数
				showNum( );
		}
	}
	 

</script>
</body>
</html>