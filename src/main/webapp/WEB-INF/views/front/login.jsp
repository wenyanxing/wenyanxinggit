<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>如意花店-登录</title>

<!--js-->
<script src="${ctx}/static/front/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/static/front/js/common.js"></script>
<script src="${ctx}/static/front/js/jquery.easing.1.3.js"></script>
<script src="${ctx}/static/front/js/ddsmoothmenu.js"></script>
<script src="${ctx}/static/front/js/jquery.flexslider.js"></script>
<script src="${ctx}/static/front/js/jquery.elastislide.js"></script>
<script src="${ctx}/static/front/js/jquery.jcarousel.min.js"></script>
<script src="${ctx}/static/front/js/jquery.accordion.js"></script>
<script src="${ctx}/static/front/js/light_box.js"></script>
<script type="text/javascript">$(document).ready(function(){$(".inline").colorbox({inline:true, width:"50%"});});</script>
<!--end js-->

<!-- Mobile Specific Metas ================================================== -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS ================================================== -->

<link rel="stylesheet" href="${ctx}/static/front/css/style.css">
<link rel="stylesheet" href="${ctx}/static/front/css/orange.css">
<link rel="stylesheet" href="${ctx}/static/front/css/skeleton.css">
<link rel="stylesheet" href="${ctx}/static/front/css/layout.css">
<link rel="stylesheet" href="${ctx}/static/front/css/ddsmoothmenu.css"/>
<link rel="stylesheet" href="${ctx}/static/front/css/elastislide.css"/>
<link rel="stylesheet" href="${ctx}/static/front/css/home_flexslider.css"/>

<link rel="stylesheet" href="${ctx}/static/front/css/light_box.css"/>
<script src="${ctx}/static/front/js/html5.js"></script>


</head>
<body>
	<div class="mainContainer sixteen container">
            <!--Header Block-->
            <div class="header-wrapper">
              
            </div>
<!--Content Block-->
<section class="content-wrapper">
	<div class="content-container container">
		<div class="main">
			<h1 class="page-title">注册|登录</h1>
			<div class="account-login">
				<div class="col-1 new-users">
					<form id="fregist" action="${ctx}/customer/sregist" method="post">
					<input type="hidden" name="id"/>
					<div class="content">
						<h2>新用户</h2>
						<p>通过与我们的商店创建一个帐户。</p>
						
						<ul class="form-list">
							<li>
								<label class="required" for="email">用户名<em>*</em></label>
								<div class="input-box">
									<input type="text" class="input-text required-entry" name="username" />
								</div>
								<div class="clear"></div>
							</li>
							<li>
								<label class="required" for="pass">密码<em>*</em></label>
								<div class="input-box">
									<input type="password" class="input-text required-entry" />
								</div>
								<div class="clear"></div>
							</li>
							<li>
								<label class="required" for="pass">确认密码<em>*</em></label>
								<div class="input-box">
									<input type="password"class="input-text required-entry" name="pwd"/>
								</div>
								<div class="clear"></div>
							</li>
							<li>
								<label class="required" for="email">电话<em>*</em></label>
								<div class="input-box">
									<input type="text" class="input-text required-entry" name="phone" />
								</div>
								<div class="clear"></div>
							</li>
							<li>
								<label class="required" for="email">邮箱<em>*</em></label>
								<div class="input-box">
									<input type="text" class="input-text required-entry" name="email" />
								</div>
								<div class="clear"></div>
							</li>
						</ul>
					</div>
					<div class="buttons-set">
						<a class="orange-btn" onclick="$('#fregist').submit();"><span><span>注册</span></span></a>
						<div class="clear"></div>
					</div>
					</form>
				</div>
				
				<div class="col-2 registered-users">
					<form id="flogin" action="${ctx}/customer/flogin" method="post">
					<div class="content">
						<h2>客户登录</h2>
						<p>已经注册过？请直接登录</p>
						<ul class="form-list">
							<li>
								<label class="required" for="email">用户名<em>*</em></label>
								<div class="input-box">
									<input type="text" class="input-text required-entry" name="username"/>
								</div>
								<div class="clear"></div>
							</li>
							<li>
								<label class="required" for="pass">密码<em>*</em></label>
								<div class="input-box">
									<input type="password" class="input-text required-entry" name="pwd"/>
								</div>
								<div class="clear"></div>
							</li>
						</ul>
					</div>
					<div class="buttons-set">
						<a class="f-left" href="#"></a>
						<a class="orange-btn" onclick="$('#flogin').submit();"><span><span>登录</span></span></a>
						<div class="clear"></div>
					</div>
					</form>
            	</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="clearfix"></div>
		
	</div>
</section>
</div>

</body>
</html>

