<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
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
		<div id="urHere">用户管理<b>></b><strong>用户列表</strong></div>
		<div class="mainBox" style="height:auto!important;">
			<form id="pageQueryForm" action="${ctx}/customer/list" method="post">
				<input type="hidden" id="pageNumber" name="pageNumber">
			</form>
			
			<div id="list">
				<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
					<tr>
                         <th width="20%">用户名</th>
                         <th width="20%">电话</th>
                         <th width="20%">邮箱</th>
                         <th width="20%">注册时间</th>
                         <th width="20%">操作</th>
                     </tr>
					<c:forEach items="${page.data }" var="customer">
				    	<tr>
					        <td>${customer.username }</td>
					        <td>${customer.phone }</td>
					        <td>${customer.email }</td>
					        <td>${customer.createdate }</td>
					        <td>
					        	<a href="${ctx}/customer/remove/${customer.id}">删除</a>
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