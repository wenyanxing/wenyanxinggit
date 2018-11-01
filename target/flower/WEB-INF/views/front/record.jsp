<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的账户</title>
<link rel="stylesheet" href="${ctx}/static/front/css/style.css">
<link rel="stylesheet" href="${ctx}/static/front/css/orange.css">
<script src="${ctx}/static/front/js/html5.js"></script>

<link rel="stylesheet" href="${ctx}/static/cart/css/common.css" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/static/cart/css/record.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/static/cart/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/cart/js/common.js"></script>
<script type="text/javascript" src="${ctx}/static/cart/js/slider.js"></script>
</head>

<body bgcolor="#e0d6df">
  <%@include file="/common/ftop.jsp"%>
  
  <div id="container">
    
    <div id="content" style="width:1000px;margin:0 auto;padding-right:10px;">
      <h1 class="title">购买记录</h1>
      <div class="record-con">
        <ul class="control-tab">
          <li <c:if test="${state == 9 }">class="active"</c:if> onclick="window.location.href='${ctx}/percenter/index?state=9'">所有订单</li>
          <li <c:if test="${state == 0 }">class="active"</c:if> onclick="window.location.href='${ctx}/percenter/index?state=0'">待付款</li>
          <li <c:if test="${state == 1 }">class="active"</c:if> onclick="window.location.href='${ctx}/percenter/index?state=1'">待发货</li>
          <li <c:if test="${state == 2 }">class="active"</c:if> onclick="window.location.href='${ctx}/percenter/index?state=2'">待收货</li>
        </ul>
        <style>
        	.olist{border: 1px solid #bbb;}
        	.olist tr td{border: 1px solid #bbb;text-align:center;}
        	.ptab tr td{border:none;border-top: 1px solid #bbb;}
        </style>
        <table width="100%" class="olist">
	        <tr style="height:30px;background: #FF7F50;">
	            <td width="30%" align="center">商品</td>
	            <td width="10%" align="center">单价（元）</td>
	            <td width="10%" align="center">数量</td>
	            <td width="15%" align="center">状态</td>
	            <td width="15%" align="center">总计</td>
	            <td width="20%" align="center">操作</td>
	        </tr>
	     </table>
	     
	        <c:forEach items="${oList }" var="orders">
	        <table width="100%">
	        <tr>
        		<td colspan="6" style="text-align:left;padding:10px 0;border:none;">
        			<span style="margin-left: 10px;">下单时间：<fmt:formatDate value="${orders.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
        			<span class="record-num" style="margin-left: 10px;">订单号：${orders.ddno}</span></td>
        			<c:if test="${orders.state == 0 }">
        				<a style="float:right;" href="${ctx}/percenter/deleteOrder?id=${orders.id}">删除订单</a>
        			</c:if>
        		</td>
	        </tr>
	        </table>
	        <table width="100%" class="olist">
        	<tr>
	        	<td colspan="3" width="50%">
	        		<table width="100%" border="0" class="ptab">
	        			<c:forEach items="${orders.pList }" var="pp">
		        			<tr>
		        				<td width="30%" style="padding: 5px 0;"><img src="${pp.product.recover }" width="75" height="75" style="border:1px solid #666;"/></td>
		        				<td width="30%">${pp.product.name }</td>
		        				<td width="20%">
		        					<c:if test="${pp.product.state == 2 }">${pp.product.yhprice }</c:if>
									<c:if test="${pp.product.state != 2 }">${pp.product.price }</c:if>
								</td>
		        				<td width="20%">${pp.num }</td>
		        			</tr>
	        			</c:forEach>
	        		</table>
	        	</td>
	        	<td width="15%">
	        		<c:if test="${orders.state == 0}"><b style="color:red;">待支付</b></c:if>
		        	<c:if test="${orders.state == 1}"><b style="color:green;">已支付</b></c:if>
		        	<c:if test="${orders.state == 2}"><b style="color:#FF8247;">已发货</b></c:if>
		        	<c:if test="${orders.state == 3}"><b style="color:blue;">已收货</b></c:if>
	        	</td>
	        	<td width="15%" style="vertical-align: center;">${orders.hjje}</td>
	        	
	        	<td width="20%">
	        		<c:if test="${orders.state == 0}"><a href="${ctx}/percenter/confirmOrder?id=${orders.id}">立即付款</a></c:if>
	        		<c:if test="${orders.state == 2}"><a href="#" onclick="updateState(${orders.id},3);">确认收货</a></c:if>
				</td>
	   
	        </tr>
	        </c:forEach>
        </table>
      </div>
      
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
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
				window.location.href = "${ctx}/percenter/index";
			}
		});
	}
	</script>
</body>
</html>
