<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<%@include file="/common/kindeditor.jsp"%>
<link href="${ctx}/static/admin/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/static/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/global.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/jquery.autotextarea.js"></script>

<script type='text/javascript'>
 
</script>
</head>
<body>
<div id="dcWrap">
	<%@ include file="/common/top.jsp"%>
    <%@ include file="/common/lmenu.jsp"%>
    
    <div id="dcMain">
	   	<!-- 当前位置 -->
		<div id="urHere">分类管理<b>></b><strong>分类列表</strong></div>
		<div class="mainBox" style="height:auto!important;">
			<form id="pageQueryForm" action="${ctx}/catalog/list" method="post">
				<input type="hidden" id="pageNumber" name="pageNumber">
			</form>
			
			<div id="list">
				<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
					<tr>
						<th width="40" align="center">编号</th>
						<th width="100">分类名称</th>
						<th width="100" align="center">操作</th>
					</tr>
					<c:forEach items="${page.data }" var="pp">
						<tr>
							<td align="center">${pp.id }</td>
							<td>${pp.name }</td>
							<td align="center">
								<a href="${ctx}/catalog/edit/${pp.id}">编辑</a> | <a href="${ctx}/catalog/remove/${pp.id}">删除</a>
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
</html>