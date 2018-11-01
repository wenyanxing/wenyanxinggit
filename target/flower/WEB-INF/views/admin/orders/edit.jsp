<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<link href="${ctx}/static/admin/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/static/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/global.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/jquery.autotextarea.js"></script>

</head>
<body>
<div id="dcWrap">
	<%@ include file="/common/top.jsp"%>
    <%@ include file="/common/lmenu.jsp"%>
    
    <div id="dcMain">
	   	<!-- 当前位置 -->
		<div id="urHere">订单管理<b>></b><strong>订单详情</strong></div>
		<div class="mainBox" style="height:auto!important;">
		    <form action="${ctx}/orders/save" method="post">
		    	<input type="hidden" name="id" value="${orders.id }"/>
		    	<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
					<tr>
						<td align="right">订单号</td>
						<td>${orders.ddno }&nbsp;&nbsp;&nbsp;&nbsp;
							下单时间：<fmt:formatDate value="${orders.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
							<c:if test="${orders.state == 0}"><b style="color:red;">待支付</b></c:if>
				        	<c:if test="${orders.state == 1}"><b style="color:green;">已支付</b></c:if>
				        	<c:if test="${orders.state == 2}"><b style="color:yellow;">已发货</b></c:if>
				        	<c:if test="${orders.state == 3}"><b style="color:blue;">已收货</b></c:if>
					    </td>
					</tr>
					<tr>
						<td align="right" valign="top">收件人</td>
						<td>${orders.sjr }&nbsp;&nbsp;&nbsp;&nbsp;
							电话：${orders.sjrdh }&nbsp;&nbsp;&nbsp;&nbsp;
							地址：${orders.address }</td>
					</tr>
					<tr>
						<td align="right" valign="top">订单备注</td>
						<td>${orders.ddbz }</td>
					</tr>
					<tr>
						<td colspan="2">
							<table width="100%">
								<tr>
									<td colspan="4" align="center">商品列表</td>
								</tr>
								<tr>
									<td>商品</td>
									<td>单价</td>
									<td>数量</td>
									<td>小计</td>
								</tr>
								<c:forEach items="${orders.pList }" var="pp">
									<tr>
										<td><a href="../product/details.html">
											<img src="${pp.product.recover }" width="80" height="80"/>
											<span style="line-height:80px;">${pp.product.name }</span></a>
										</td>
										<td>${pp.product.price }</td>
										<td>${pp.num }</td>
										<td>${pp.product.price*pp.num }</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">物流信息</td>
						<td>公司：<input type="text" name="wlxx" value="${orders.wlxx }" size="50" class="inpMain" />
						物流单号：<input type="text" name="wlno"  value="${orders.wlno }" size="60" class="inpMain" /></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<c:if test="${orders.state == 1 }">
								<input name="submit" class="btn" type="submit" value="确认发货" />
							</c:if>
							<input name="back" onclick="window.location.href='${ctx}/orders/list'" class="btn" type="button" value="返回" />
						</td>
					</tr>
		     	</table>
		    </form>
	 	</div>
 	</div>
 	<div class="clear"></div>
</div>
</body>
</html>