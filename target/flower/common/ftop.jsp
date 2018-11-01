<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Header Block-->
<div class="header-wrapper">
	<header class="container">
		
		<div class="head-right">
			<ul class="top-nav">
				<c:if test="${sessionScope.fuser != null }">
					<li class=""><a href="${ctx}/percenter/index">我的账户</a></li>
					<li class="my-wishlist"><a href="${ctx}/cart/mycart">购物车</a></li>
					<li class="checkout"><a href="${ctx}/customer/logout">注销</a></li>
				</c:if>
				<c:if test="${sessionScope.fuser == null }">
					<li class="checkout"><a href="${ctx}/customer/login">注册</a></li>
					<li class="log-in"><a href="${ctx}/customer/login">登录</a></li>
				</c:if>
				
			</ul>
		</div>

		<nav id="smoothmenu1" class="ddsmoothmenu mainMenu">
			<ul id="nav">
				<li class="active"><a href="${ctx}/index">首页</a></li>
				<li><a href="${ctx}/product/index">全部商品</a></li>
			</ul>
		</nav>
		
	</header>
</div>