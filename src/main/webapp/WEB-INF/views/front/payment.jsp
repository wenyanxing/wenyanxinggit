<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模拟支付</title>
<link rel="stylesheet" href="${ctx}/static/front/css/style.css">
<link rel="stylesheet" href="${ctx}/static/front/css/orange.css">

<link rel="stylesheet" href="${ctx}/static/cart/css/common.css" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/static/cart/css/pay.css" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/static/cart/css/settlement.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/static/cart/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/cart/js/slider.js"></script>
</head>

<body bgcolor="#e0d6df">
  
  <div id="container">
    <div id="content">
      <div class="pay-method">
        <h1 class="title">支付方式</h1>
        <div class="form-message">
          <p>订单号：<span>${orders.ddno }</span></p>
          <p>订单总价：<span>${orders.hjje }元</span></p>
        </div>
        <div class="online-method">
          <p class="online-title"><img src="${ctx}/static/cart/img/pay/logo-online.gif" width="139" height="55" alt="在线支付" />选择支付方式：</p>
          <div class="online-container">
            <img src="${ctx}/static/cart/img/pay/zffs.jpg" style="width:875px;height:auto;margin:0 auto;"/>
          </div>
          <div class="form-message">
	          <p>说明：实现在线支付需要银联、银行或第三方平台接口，故此处模拟支付流程</p>
	        </div>
          <p class="cart-pay" style="margin-top:20px;">
          	<a href="${ctx}/index" class="re-cart">返回首页</a>
            <a href="#" onclick="updateState(${orders.id},1);" class="go-pay">确认支付</a>
          </p>
          
        </div>
      </div>
    </div>
  </div>
  <script>
	function updateState(oid,state){
		$.ajax({
			data : "id=" +oid+"&state="+state,
			type : "POST",
			dataType : 'json',
			url : "${ctx}/orders/updateState",
			error : function(data) {
				alert(data.msg);
			},
			success : function(data) {
				alert(data.msg);
			}
		});
	}
	</script>
</body>
</html>
