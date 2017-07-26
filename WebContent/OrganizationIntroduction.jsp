<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>组织介绍</title>
<link rel="shortcut icon" href="images/timg.ico" type="image/X-icon"/>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- Owl Stylesheets -->
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Studeon Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
<link href='http://fonts.useso.com/css?family=Oswald:400,700,300|Open+Sans:300italic,400italic,600italic,400,300,600,700' rel='stylesheet' type='text/css'>
<!--//webfont-->
<!--Animation-->
<script src="js/wow.min.js"></script>
<link href="css/animate.css" rel='stylesheet' type='text/css' />
<script>
	new WOW().init();
</script>
</head>
<c:if test="${empty requestScope.orgPage.dataList}">
	<jsp:forward page="Org_showAllForIntro"></jsp:forward>
</c:if>
<body>
	<!-- 标题头  -->
	<div class="header box css3-shadow" id="head">
		<div class="container">
			<div class="header-top">
				<div class="logo wow bounceInLeft" data-wow-delay="0.4s">
					<a href="index.jsp"><img src="images/logo.png" alt=""/></a>
				 </div>
			     <div class="h_menu4"><!-- start h_menu4 -->
					<a class="toggleMenu" href="#">Menu</a>
					<ul class="nav">
						<li><a href="index.jsp">首页</a></li>
						<li><a href="register_search">纳新管理</a></li>
						<li><a href="memberManager.jsp">成员管理</a></li>
						<li><a href="memberSearch.jsp">成员查询</a></li>
						<li><a href="activityManager.jsp">活动管理</a></li>
						<li><a href="material_search">物资管理</a></li>
					</ul>
					<div style="position: absolute;top: 22px;right: 50px;">
						<c:if test="${sessionScope.username == null}">
							<a href="login.jsp">登录</a>
						</c:if>
						<a href="exit.jsp" style="align-self: auto;">${sessionScope.name}</a>
					</div>
					<script type="text/javascript" src="js/nav.js"></script>
				</div><!-- end h_menu4 -->
				<div class="social_icons wow bounceInRight" data-wow-delay="0.4s">
						<ul>
							<!-- <li><a href="#"><i class="facebook"></i></a></li>
							<li><a href="#"><i class="twitter"></i></a></li>
							<li><a href="#"><i class="rss"></i></a></li>
							<li><a href="#"><i class="viemo"></i></a></li>
							<li><a href="#"><i class="youtube"></i></a></li> -->
							<%-- <li><a href="exit.jsp" name="exit"  style="top: 100px; color: blue;">你好 ${sessionScope.username}</a></li> --%>
						</ul>
				</div>
	
				<div class="clearfix"> </div>
				
			  </div><!-- end header_main4 -->
		</div>
	</div>
	
	<!-- 内容 -->
	<!-- 以下的内容都是从数据库里面找出来的，针对科协的记录 -->
	<c:forEach items="${requestScope.orgPage.dataList}" var="org">
		<c:if test="${org.organizationId == 1}">	
			<div class="container">
				<div class="content">
					<div class="col-md-9 single-page">
						<!-- 图片和文字介绍部分 -->
						<div class="print-main wow fadeInLeft animated" data-wow-delay="0.4s">
							<h3>南开大学计算机与控制工程学院${org.organizationName}</h3>
							<p class="likes">Posted 02.05.2015 at 18:00h in Web Author By <a href="#">Jhone Smith</a> / <a href="#">60 Likes</a> / <a href="#">2 Comments</a></p>
							<a href="single.html"><img src="images/kx.jpg" class="img-responsive" alt="" /></a>
							<ul class="single-icons">
								<li><a href="#"><i class="penc"></i></a></li>
								<li><i class="date2"></i></li>
							</ul>
							<p class="span"><a href="#">3D Printing, <a href="#">3D Software,</a><a href="#"> Files to Print </a> |  on february 14,2015</p>
							<p class="ptext">微软学生技术俱乐部是微软亚洲研究院与高校合作培养人才的一种探索。俱乐部本着“学习先进技术，开拓创新思维，体验多元文化，成就一流人才”的宗旨，在各高校校团委、相关学院的指导下，通过学术讲座、技术沙龙、兴趣小组、大型比赛、参观访问等活动，活跃学生学术气氛，丰富校园活动。目前，全国共有34个微软学生技术俱乐部。2000年10月第一个微软学生技术俱乐部成立以来，俱乐部成员创新地组织了大量的技术、文化方面的实践活动。同学们自发组成多元化的实践小组，设计明确的实践主题，通过项目研讨和实施过程，达到集团队合作、项目管理和技术、方法学习为一体的目标，从而潜移默化地培养学子们的“个人职业素养”等技巧。</p>
							<p class="ptext">创新是微软的灵魂，也是微软的文化。微软技术俱乐部的各种活动，也都把创新作为自己的理念和追求目标，并通过这种方式，不断推陈出新，取得更大的效果。俱乐部讲座：微软技术俱乐部依托微软亚洲研究院的资源，在所在学校和城市进行了大量形式新颖、反响热烈的讲座和培训。比如东南大学微软技术俱乐部的“七剑下江南”系列活动，南开大学微软技术技术部的“奥运与微软”系列活动，华中科技大学“IT明灯”系列名师大讲坛等，都得到极高的赞扬。</p>
						</div>
						<!-- 下方留言部分 -->
						<div class="single-content">
							<div class="single">
								<!-- 填写留言的区域 -->
								<div class="leave">
									<h4>留言面板</h4>
								</div>
								<!-- 留言的表单 -->
						<form id="commentform" class="wow fadeInRight animated" data-wow-delay="0.4s">
							 <p class="comment-form-author-name"><label for="author">作者</label>
								<input id="author" name="author" type="text" value="" size="30" aria-required="true">
							 </p>
							 <p class="comment-form-email">
								<label for="email">邮箱</label>
								<input id="email" name="email" type="text" value="" size="30" aria-required="true">
							 </p>
							 <p class="comment-form-url">
								<label for="url">标签</label>
								<input id="url" name="url" type="text" value=" " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">
							 </p>
							 <p class="comment-form-comment">
								<label for="comment">评论</label>
								<textarea></textarea>
							 </p>
							 <div class="clearfix"></div>
							 <p class="form-submit">
							 	<input name="submit" type="submit" id="submit" value="提交">
							 </p>
							 <div class="clearfix"></div>
						   </form>
						</div>
						<div class="comments1">
							<h4>留言墙</h4>
							<div class="comments-main wow fadeInLeft animated" data-wow-delay="0.4s">
								<div class="col-md-3 cmts-main-left">
									<img src="images/avatar.jpg" alt="">
								</div>
								<div class="col-md-9 cmts-main-right">
									<a href="#"><h5>龚克</h5></a>
									<p>计控学院科技协会是一个比较轻松的环境，时间大多没有很强的要求。给我们讲座的有负责SQL AZURE、Bing、Academic Search、Windows Embeded等等的研究员。感觉微软投入许多在Bing上，在这个团队的员工占了亚洲研究院不少的份额，而且现在似乎还在继续招人。而那个嵌入式的还Support了许多M8的工作，然后说WM上的程序都可以在M8上跑，只是分辨率的问题，只能占屏幕一小部分，主要是开发者没有按照微软的Suggestion来进行开发。然而现在M9却要拥抱Andriod了，不知道微软会不会很失落呢。 </p>
									<div class="cmts">
										<div class="col-md-8 cmnts-left">
											<p>On April 14, 2014, 18:01</p>
										</div>
										<div class="col-md-4 cmnts-right">
											<a href="#">回复</a>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
				<!-- 右方导航部分 -->
				<!--start-right-content-->
				<div class="col-md-3 right-content">
				  	<h5>Blog Search</h5>
					<div class="search">
						<form>
							<input type="text" value="Enter to search..." onfocus="this.value='';" onblur="if (this.value == '') {this.value ='';}">
							<input type="submit" value="">
						</form>
					</div>
					<!-- 立即报名按钮，没有用div美化 -->
					
					<a href="register.jsp"><input type="button" value="立即报名" style="font-size: 40px;background-color: orange;border-bottom-style: solid;"></a>
					
					
					<div class="categories">
						<h4>下属部门</h4>
						<ul class="category">
					
					
								<!-- 找到科技协会的记录，以下列出他的下辖部门-->
								<c:forEach items="${org.departments}" var="deps">
									<li><a href="#">${deps.departmentName}</a></li>
								</c:forEach>
							</c:if>
						</c:forEach>
						</ul>
					</div>
					<div class="sidebar-bottom">
						<h5>近期活动</h5>
						<ul>
							<li><a href="#">February 2015</a></li>
							<li><a href="#">January 2015</a></li>
							<li><a href="#">December 2015</a></li>
							<li><a href="#">November 2015</a></li>
							<li><a href="#">February 2015</a></li>
							<li><a href="#">December 2015</a></li>
							</ul>
					</div>
					<div class="featured">
						<h4>部门风采</h4>
						<ul>
							<li><a href="single.html"><img src="images/s1.jpg" alt=""/></a></li>
							<li><a href="single.html"><img src="images/s2.jpg" alt=""/></a></li>
							<li><a href="single.html"><img src="images/s3.jpg" alt=""/></a></li>
							<li><a href="single.html"><img src="images/s4.jpg" alt=""/></a></li>
							<li><a href="single.html"><img src="images/s5.jpg" alt=""/></a></li>
							<li><a href="single.html"><img src="images/s6.jpg" alt=""/></a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>				
			</div>
		</div>
<!--尾注部分--><!--/start-copyright-section-->
				<div class="copyright">
					<div class="container">
					<div class="logo2  wow bounceInLeft" data-wow-delay="0.4s"><a href="index.html"><img src="images/logo2.png" alt=""/ title="logo" /></a></div>
						<p class="write  wow bounceInRight" data-wow-delay="0.4s">Copyright &copy; 2015<a href="http://nankai.edu.cn" target="_blank" title="南开大学">南开大学社团联合会</a></p>
					</div>
					<div class="clearfix"></div>
				</div>
</body>
</html>