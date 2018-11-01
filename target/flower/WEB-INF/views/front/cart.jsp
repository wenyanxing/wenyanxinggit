<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>购物车</title>
<link rel="stylesheet" href="${ctx}/static/front/css/style.css">
<link rel="stylesheet" href="${ctx}/static/front/css/orange.css">
<script src="${ctx}/static/front/js/html5.js"></script>

<link rel="stylesheet" href="${ctx}/static/cart/css/common.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/cart/css/modal.css" media="all" />
<link rel="stylesheet" href="${ctx}/static/cart/css/cart.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/static/cart/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/cart/js/slider.js"></script>
<script type="text/javascript" src="${ctx}/static/cart/js/modal.js"></script>
</head>

<body bgcolor="#e0d6df">
 
 <%@include file="/common/ftop.jsp"%>
  
  <div id="container">
    <div id="content">
      <div class="cart-message">
        <h1 class="title">购物车</h1>
        <ul class="cart-nav">
          <li class="checked">选择</li>
          <li class="name">商品</li>
          <li class="message">商品信息</li>
          <li class="price">单价</li>
          <li class="count">数量</li>
          <li class="operation">操作</li>
        </ul>
        <c:forEach items="${cartList }" var="cart">
	        <ul class="shopping-message">
	          <li class="checked">
	          	<span><input type="checkbox" onclick="updateZje();" id="p${cart.product.id }" name="pprice" value="${cart.product.price }"/></span>
	          	<input type="hidden" id="pp${cart.product.id }" value="${cart.product.id }"/>
	          	<img src="${cart.product.recover }" width="72" height="72" alt="${cart.product.name }" />
	          </li>
	          <li class="name"><a href="${ctx}/product/productdetail?id=${cart.product.id}">${cart.product.name }</a></li>
	          <li class="message">${cart.product.bz }</li>
	          <li class="price">${cart.product.price }</li>
	          <li class="count">
	            <span class="count-number"><input type="text" id="np${cart.product.id }" value="${cart.np }" onblur="updateZje();"/></span>
	          </li>
	          <li class="operation"><a href="${ctx}/cart/removeProduct?pid=${cart.product.id}" class="del-operation" data-title="删除">删除</a></li>
	        </ul>
        </c:forEach>
        
        <div class="cart-all">
          <span>总计：￥</span>
          <span class="cash-total" id="hjje">0.00</span>
          <input type="hidden" id="fhjje"/>
        </div>
        <div class="contiue-pay">
          <a href="${ctx}/index" class="contiue">继续购物</a>
          <a href="#" onclick="subOrder();" class="pay" style="background: #FF7F50;">结 算</a>
        </div>
      </div>
      
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  <script>
	//更新总金额
	function updateZje(){
		var zje = 0;
		$("input[name=pprice]").each(function(element) {
			var ppid = this.id;
			if($("#"+ppid).is(':checked')){
				zje += Number(this.value)*Number($('#n'+ppid).val());
			}
		});
		$("#hjje").html(zje);
		$("#fhjje").val(zje);
	}

	function subOrder(){
		var argJson = "[";
		$("input[name=pprice]").each(function(element) {
			var ppid = this.id;
			if($("#"+ppid).is(':checked')){
				argJson += "{pid:\""+$('#p'+ppid).val()+"\",np:\""+$('#n'+ppid).val()+"\"}";
			}
		});
		argJson += "]";
		var hjje = $("#fhjje").val();
		window.location.href = "${ctx}/cart/confirm?argJson="+argJson+"&hjje="+hjje;
	}
  </script>
</body>
</html>
