<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>如意花店-全部商品</title> <!--js-->
	<script src="${ctx}/static/front/js/jquery-1.8.2.min.js"></script>
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
	<!--end js-->

	<!-- Mobile Specific Metas ================================================== -->
	<meta name="viewport"
		content="width=device-width, initial-scale=1, maximum-scale=1">

		<!-- CSS ================================================== -->

		<link rel="stylesheet" href="${ctx}/static/front/css/style.css">
			<link rel="stylesheet" href="${ctx}/static/front/css/orange.css">
				<link rel="stylesheet" href="${ctx}/static/front/css/skeleton.css">
					<link rel="stylesheet" href="${ctx}/static/front/css/layout.css">
						<link rel="stylesheet"
							href="${ctx}/static/front/css/ddsmoothmenu.css" />
						<link rel="stylesheet"
							href="${ctx}/static/front/css/elastislide.css" />
						<link rel="stylesheet"
							href="${ctx}/static/front/css/home_flexslider.css" />

						<link rel="stylesheet"
							href="${ctx}/static/front/css/light_box.css" />
						<script src="${ctx}/static/front/js/html5.js"></script>
</head>
<body>
	<div class="mainContainer sixteen container">

		<%@include file="/common/ftop.jsp"%>
		<form id="pageQueryForm" action="${ctx}/product/index" method="post">
			<input type="hidden" id="pageNumber" name="pageNumber" />
        </form>
		<section class="content-wrapper">
		<div class="content-container container">
			<div class="breadcrum-container">
				<ul>
					<li><a href="#"></a></li>
					<li></li>
				</ul>
			</div>
			<div class="col-left">
				<div class="block man-block" style="height:auto;">
					<div class="block-title">鲜花分类</div>
					<ul>
						<li><a href="${ctx}/product/index">全部鲜花</a></li>
						<c:forEach items="${cList }" var="cc">
							<li><a href="${ctx}/product/index?cid=${cc.id}">${cc.name }</a></li>
						</c:forEach>
					</ul>
				</div>
				
			</div>
			<style>
				#pgrid li{float:left;margin:5px;}
			</style>
			<div class="col-main">
				
				<div class="new-product-block">
					<ul class="product-grid" id="pgrid">
						<c:forEach items="${ppList }" var="pp">
							<li>
								<div class="pro-img">
									<img title="鲜花图" alt="鲜花图" src="${pp.recover}" />
								</div>
								<div class="pro-content">
									<p>${pp.name}</p>
								</div>
								<div class="pro-price">￥${pp.price }</div>
								<div class="pro-btn-block">
									<a class="add-cart left" href="#" onclick="javascript:addCart(${pp.id});">加入购物车</a> <a
										class="add-cart right"
										href="${ctx}/product/productdetail?id=${pp.id}" target="_blank" title="快速预览">快速预览</a>
								</div>
								<div class="pro-link-block">
									
								</div>
							</li>
						</c:forEach>
					</ul>
					
				</div>
				<div class="pager-container">
					<%@ include file="/common/page.jsp"%>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		</section>
	</div>
	<!--Quick view Block-->
	<script type="text/javascript">
		jQuery(function() {
			var tabContainers = jQuery('div.tabs > div');
			tabContainers.hide().filter(':first').show();
			jQuery('div.tabs ul.tabNavigation a').click(function() {
				tabContainers.hide();
				tabContainers.filter(this.hash).show();
				jQuery('div.tabs ul.tabNavigation a').removeClass('selected');
				jQuery(this).addClass('selected');
				return false;
			}).filter(':first').click();
		});
	</script>
	<article style="display:none;"> <section
		id="quick-view-container" class="quick-view-wrapper">
	<div class="quick-view-container">
		<div class="quick-view-left">
			<h2>百合花</h2>
			<div class="product-img-box">
				<p class="product-image">
					<img src="${ctx}/static/front/images/sale_icon_img.png" title="Sale" alt="Sale"
						class="sale-img" /> <a href="view.html" title="Image"><img
						src="${ctx}/static/front/images/quick_view_img1.png" title="Image" alt="Image" /></a>
				</p>
				<ul class="thum-img">
					<li><img src="${ctx}/static/front/images/quick_thum_img1.png" title="" alt="" /></li>
					<li><img src="${ctx}/static/front/images/quick_thum_img1.png" title="" alt="" /></li>
				</ul>
			</div>
		</div>
		<div class="quick-view-right tabs">
			<ul class="tab-block tabNavigation">
				<li><a class="selected" title="Overview" href="#tabDetail">概览</a></li>
				<li><a title="细节展示" href="#tabDes">细节展示</a></li>
			</ul>
			<div id="tabDetail" class="tabDetail">
				<div class="first-review">此商品为鲜活易腐类，不支持7天无理由退货</div>
				<div class="price-box">
					<span class="price">￥69.00</span>
				</div>
				<div class="availability">有库存</div>
				<div class="color-size-block">
					<div class="label-row">
						<label><em>*</em> 颜色</label> <span class="required">* 必填项</span>
					</div>
					<div class="select-row">
						<select><option>红色</option>
							<option>蓝色</option>
							<option>粉色</option>
							<option>紫色</option>

						</select>
					</div>
					<div class="label-row">
						<label><em>*</em>支付方式</label>
					</div>
					<div class="select-row">
						<select><option>快捷支付</option>
							<option>余额宝支付</option>
							<option>集分宝</option>


						</select>
					</div>
				</div>
				<div class="add-to-cart-box">
					<span class="qty-box"> <label for="qty">数量:</label> <a
						class="prev" title="" href="#"><img alt="" title=""
							src="${ctx}/static/front/images/qty_prev.png"></a> <input type="text" name="qty"
						class="input-text qty" id="qty" maxlength="12" value="1">
							<a class="next" title="" href="#"><img alt="" title=""
								src="${ctx}/static/front/images/qty_next.png"></a></span>
					<button title="加入购物车" class="form-button">
						<span>加入购物车</span>
					</button>
				</div>
			</div>
			<div id="tabDes" class="tabDes">
				<div>如意花店适用场景: 爱意表达 生日 祝福 婚礼 探望 其他 求婚 友情 周年纪念</div>
				<div>鲜花主花材: 百合适用对象: 爱人 老师 客户 领导/长辈 朋友/同事 病人鲜花朵数: 11朵</div>
				<div>适用节日: 情人节 圣诞节 春节 3.8妇女节 母亲节 感恩节 教师节 中秋节 七夕 白色情人节</div>
				<div>鲜花绿植工艺: 鲜花(鲜切花)鲜花规格(直径X高): 35*55花束辅材: 黄莺/满天星/勿忘我</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	</section> </article>
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

		function submitForm(pageNo) {
			$("#pageNumber").val(pageNo);
			$("#pageQueryForm").submit();
		}
	</script>
</body>
</html>

