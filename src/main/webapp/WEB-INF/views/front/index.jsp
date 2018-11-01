<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>首页</title> <script
		src="${ctx}/static/front/js/jquery-1.8.2.min.js"></script>
	<script src="${ctx}/static/front/js/common.js"></script>
	<script src="${ctx}/static/front/js/jquery.easing.1.3.js"></script>
	<script src="${ctx}/static/front/js/ddsmoothmenu.js"></script>
	<script src="${ctx}/static/front/js/jquery.flexslider.js"></script>
	<script src="${ctx}/static/front/js/jquery.elastislide.js"></script>
	<script src="${ctx}/static/front/js/jquery.jcarousel.min.js"></script>
	<script src="${ctx}/static/front/js/jquery.accordion.js"></script>
	<script src="${ctx}/static/front/js/light_box.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".inline").colorbox({
				inline : true,
				width : "50%"
			});
		});
	</script>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="${ctx}/static/front/css/style.css">
	<link rel="stylesheet" href="${ctx}/static/front/css/orange.css">
	<link rel="stylesheet" href="${ctx}/static/front/css/skeleton.css">
	<link rel="stylesheet" href="${ctx}/static/front/css/layout.css">
	<link rel="stylesheet" href="${ctx}/static/front/css/ddsmoothmenu.css" />
	<link rel="stylesheet" href="${ctx}/static/front/css/elastislide.css" />
	<link rel="stylesheet" href="${ctx}/static/front/css/home_flexslider.css" />
	<link rel="stylesheet" href="${ctx}/static/front/css/light_box.css" />
	<script src="${ctx}/static/front/js/html5.js"></script>
	</head>
<body>
	<div class="mainContainer big container">
		<!--主要内容区-->
		<%@include file="/common/ftop.jsp"%>

		<section class="banner-wrapper">
		<div class="banner-block container">
			<div class="flexslider">
				<!--调用框架接口        促销商品-->
				<ul class="slides">
					<li><img title="Banner" alt="Banner"
						src="${ctx}/static/front/images/banner1.png" /></li>
					<li><img title="Banner" alt="Banner"
						src="${ctx}/static/front/images/banner2.png" /></li>
					<li><img title="Banner" alt="Banner"
						src="${ctx}/static/front/images/banner3.png" /></li>
					<li><img title="Banner" alt="Banner"
						src="${ctx}/static/front/images/banner4.png" /></li>
				</ul>
			</div>
			<ul class="banner-add">
				<li
					style="padding: 5px 10px; line-height: 30px; background: #FF8C00;"><span
					style="color: #fff;">通知公告</span></li>
				<c:forEach items="${aList }" var="art">
					<li style="padding: 5px 10px; border-top: none; line-height: 30px;"><a
						href="${ctx}/article/newsdetail?id=${art.id}">${fn:substring(art.title,0,16) }</a><span style="float: right;">[<fmt:formatDate value="${art.pubTime}" pattern="yyyy-MM-dd"/> ]</span></li>
				</c:forEach>
			</ul>
		</div>
		</section>

		<!--Content Block-->
		<!--内容区-->
		<section class="content-wrapper">
		<div class="content-container container">
			<div class="heading-block">
				<h1>热卖商品</h1>
				<ul class="pagination">
					<li class="grid"><a href="#" title="网格"></a></li>
					<!--网格状翻转按钮-->
				</ul>
			</div>

			<!--feature：特色，特价-->
			<div class="feature-block">
				<ul id="mix" class="product-grid">
					<c:forEach items="${rmList }" var="pp">
						<li>
							<!--第一个-->
							<div class="pro-img">
								<img title="Freature Product" alt="Freature Product"
									src="${pp.recover}" />
							</div>
							<div class="pro-hover-block">
								<!--“热卖商品”和“新品上架”区别：鼠标经过-->
								<h4 class="pro-name">${pp.name }</h4>
								<div class="link-block">
									<a href="${ctx}/product/productdetail?id=${pp.id}" target="_blank" class="quickproLink"
										title="商品详情">商品详情</a> <a href="#" class="quickproLink"
										onclick="javascript:addCart(${pp.id});"
										title="加入购物车">加入购物车</a>
								</div>
								<div class="pro-price">￥${pp.price }</div>
							</div>
						</li>
				    </c:forEach>
				</ul>
			</div>

			<div class="heading-block">
				<h1>新品上架</h1>
			</div>
			<style>
				#pgrid li{float:left;margin:5px;}
			</style>
			<div class="new-product-block">
				<ul class="product-grid" id="pgrid">
					<c:forEach items="${xpList }" var="pp">
						<li>
							<div class="pro-img">
								<img title="Freature Product" alt="Freature Product"
									src="${pp.recover }" />
							</div>
							<div class="pro-content">
								<p>${pp.name }</p>
							</div>
							<div class="pro-price">￥${pp.price }</div>
							<div class="pro-btn-block">
								<a class="add-cart left" href="#" onclick="javascript:addCart(${pp.id});">加入购物车</a> <a
									class="add-cart right"
									href="${ctx}/product/productdetail?id=${pp.id}" target="_blank" title="商品详情">商品详情</a>
							</div>
							<div class="pro-link-block"></div>
						</li>
					</c:forEach>
				</ul>
				
			</div>

		</div>
		</section>
	</div>

	<!--Footer Block-->
	<%@include file="/common/footer.jsp"%>
	<input type="hidden" name="cid" id="cid" value="${sessionScope.fuser.id }"/>
	<script>
		function addCart(pid){
			var cid = $("#cid").val();
			var np = 1;
			if(cid == null){
				alert('请先登录！');
			}else{
				$.ajax({
					data : "cid=" +cid+"&pid="+pid+"&np="+np,
					type : "POST",
					dataType : 'json',
					url : "${ctx}/cart/addCart",
					error : function(data) {
						alert(data.msg);
					},
					success : function(data) {
						alert(data.msg);
					}
				});
			}
		}
	</script>
</body>
</html>

