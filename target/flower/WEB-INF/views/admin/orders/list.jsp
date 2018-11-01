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
		<div id="urHere">订单管理<b>></b><strong>订单列表</strong></div>
		<div class="mainBox" style="height:auto!important;">
			<div class="filter">
				<form id="pageQueryForm" action="${ctx}/product/list" method="post">
					<input type="hidden" id="pageNumber" name="pageNumber">
                    <table>
                        <tr>
                            <th width="70">订单号:</th>
                            <td><input placeholder="订单号" name="ddno" value="${ddno }" type="text" class="inpMain"></td>
                            <td><input name="submit" class="btnGray" type="submit" value="筛选" /></td>
                        </tr>
                    </table>
					
				</form>
				<span>&nbsp;</span>
			</div>
			
			<div id="list">
				<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
					<tr>
                        <th width="10%">订单号</th>
                        <th width="20%">地址</th>
                        <th width="10%">收件人</th>
                        <th width="10%">电话</th>
                        <th width="10%">下单时间</th>
                        <th width="10%">订单状态</th>
                        <th width="10%">物流公司</th>
                        <th width="10%">运单号</th>
                        <th width="10%">操作</th>
                    </tr>
					<c:forEach items="${page.data }" var="orders">
				    	<tr>
					        <td>${orders.ddno }</td>
					        <td>${orders.address }</td>
					        <td>${orders.sjr }</td>
					        <td>${orders.sjrdh }</td>
					        <td><fmt:formatDate value="${orders.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					        <td>
					        	<c:if test="${orders.state == 0}"><b style="color:red;">待支付</b></c:if>
					        	<c:if test="${orders.state == 1}"><b style="color:green;">已支付</b></c:if>
					        	<c:if test="${orders.state == 2}"><b style="color:#FF8247;">已发货</b></c:if>
					        	<c:if test="${orders.state == 3}"><b style="color:blue;">已收货</b></c:if>
					        </td>
					        <td>${orders.wlxx }</td>
					        <td>${orders.wlno }</td>
					        <td>
					        	<a href="${ctx}/orders/edit/${orders.id}">订单详情</a>
					        </td>
					    </tr>
				    </c:forEach>
				</table>
    		</div>
    		<div class="clear"></div>
    		
    		<div class="pager">
    			<%@ include file="/common/page.jsp"%>
    		</div>
		</div>
	</div>
	<div class="clear"></div>
	
</div>

<script>
	function submitForm(pageNo) {
		$("#pageNumber").val(pageNo);
		$("#pageQueryForm").submit();
	}
</script>	
</body>
</body>
</html>