<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>User Account - Bootstrap Admin</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />    
    
    <link href="./css/bootstrap.min.css" rel="stylesheet" />
    <link href="./css/bootstrap-responsive.min.css" rel="stylesheet" />
    
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet" />
    <link href="./css/font-awesome.css" rel="stylesheet" />
    
    <link href="./css/adminia.css" rel="stylesheet" /> 
    <link href="./css/adminia-responsive.css" rel="stylesheet" /> 
    
    
    <link href="./css/pages/plans.css" rel="stylesheet" /> 

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body>
	
<div class="navbar navbar-fixed-top">
	
	<div class="navbar-inner">
		
		<div class="container">
			
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>
			
			<a class="brand" href="./">Adminia Admin</a>
			
			<div class="nav-collapse">
			
				<ul class="nav pull-right">
					<li class="divider-vertical"></li>
					
					<li class="dropdown">
						
						<a data-toggle="dropdown" class="dropdown-toggle " href="#">
							${sessionemp.ename} <b class="caret"></b>							
						</a>
						
						<ul class="dropdown-menu">
							
							<li>
								<a href="${pageContext.request.contextPath}/changepassword.jsp"><i class="icon-lock"></i>修改密码</a>
							</li>
							
							<li class="divider"></li>
							
							<li>
								<a href="logout"><i class="icon-off"></i> 注销</a>
							</li>
						</ul>
					</li>
				</ul>
				
			</div> <!-- /nav-collapse -->
			
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->




<div id="content">
	
	<div class="container">
		
		<div class="row">
			
			<div class="span3">
				
				<div class="account-container">
				
					<div class="account-avatar">
						<img src="./img/headshot.png" alt="" class="thumbnail" />
					</div> <!-- /account-avatar -->
				
					<div class="account-details">
						
						<span class="account-name">${sessionemp.ename}</span>
						
						<span class="account-role"><c:choose><c:when test="${isadmin}">管理员</c:when><c:otherwise>普通员工</c:otherwise></c:choose></span>
						
						<span class="account-actions">
							<a href="${pageContext.request.contextPath}/tosingeinfo?empid=${sessionemp.empid}">我的资料</a> 
						</span>
					
					</div> <!-- /account-details -->
				
				</div> <!-- /account-container -->
				
				<hr />
				
				<ul id="main-nav" class="nav nav-tabs nav-stacked">
					
					<li>
						<a href="${pageContext.request.contextPath}/queryallemp">
							<i class="icon-home"></i>
							员工管理		
						</a>
					</li>
					
					<li>
						<a href="${pageContext.request.contextPath}/loadAllDeptByPage">
							<i class="icon-th-list"></i>
							部门管理		
						</a>
					</li>
					
					<li>
						<a href="${pageContext.request.contextPath}/showAllJob">
							<i class="icon-th-large"></i>
							岗位管理
						</a>
					</li>
					
					<li>
						<a href="${pageContext.request.contextPath}/getAllTestInfoByPage">
							<i class="icon-signal"></i>
							试用管理
						</a>
					</li>
					
					<li class="active">
						<a href="${pageContext.request.contextPath}/tohumanlist">
							<i class="icon-user"></i>
							人才库
						</a>
					</li>
					
					<li>
						<a href="${pageContext.request.contextPath}/getalladjustinfo">
							<i class="icon-pushpin"></i>
							调动信息
						</a>
					</li>
					
				</ul>	
				
				
				
				<hr />
				
				<div class="sidebar-extra">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.</p>
				</div> <!-- .sidebar-extra -->
				
				<br />
		
			</div> <!-- /span3 -->
			
			
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-th-large"></i>
					人才库					
				</h1>
				
				
				<div class="row">
					
					<div class="span9">
				
						<div class="widget">
							
							<div class="widget-header">
								<i class="icon-th-list"></i>
								<h3>基本信息</h3>
							</div> <!-- /widget-header -->
									
							<div class="widget-content">
								
								
								
								<div class="tabbable">
							<div class="tab-content">
								<div class="tab-pane active" id="1">
								<form class="form-horizontal" action="tohireemp" method="post" />
									<fieldset>
										<input type="hidden" name="resid" value="<s:property value="humres.resid"/>"/>
										<div class="control-group">
											<label class="control-label">姓名</label>
											<div class="controls">
												<input type="text" class="input-medium disabled"  value="<s:property value="humres.ename"/>" disabled="" />
											</div> <!-- /controls -->
										</div>
										
										<div class="control-group">
											<label class="control-label">性别</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.sex"/>" disabled="" />
											</div> <!-- /controls -->				
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">生日</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.birthdate"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">身份证</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.identnum"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">政治面貌</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.polstatus"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">民族</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.nation"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">籍贯</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.navplace"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">联系电话</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.tel"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">电子邮件</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.email"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">身高</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.height"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">血型</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.booldtype"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">婚姻情况</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.marry"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">最高学历</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.recschool"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">毕业学校</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.school"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">所学专业</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.major"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										
										<div class="control-group">
											<label class="control-label">毕业日期</label>
											<div class="controls">
												<input type="text" class="input-medium disabled" value="<s:property value="humres.graddate"/>" disabled="" />
											</div> <!-- /controls -->
										</div> <!-- /control-group -->
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">雇用</button> 
										</div> <!-- /form-actions -->
									</fieldset>
								</form>
								</div>
								
								
								
							</div>
						  
						  
						</div>
								
								
								
								
								
								
								
								
							</div> <!-- /widget-content -->
							
						</div> <!-- /widget -->
						
					</div> <!-- /span9 -->
					
				</div> <!-- /row -->
				
				
				
				
				
				
				
				
				
			</div> <!-- /span9 -->
			
			
		</div> <!-- /row -->
		
	</div> <!-- /container -->
	
</div> <!-- /content -->
					
	
<div id="footer">
	
	<div class="container">				
		<hr />
		<p>&copy; 2012 Go Ideate.</p>
	</div> <!-- /container -->
	
</div> <!-- /footer -->


    

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/jquery-1.7.2.min.js"></script>


<script src="./js/bootstrap.js"></script>

  </body>
</html>