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
		<div id="urHere">商品管理<b>></b><strong>商品列表</strong></div>
		<div class="mainBox" style="height:auto!important;">
			<div class="filter">
				<form id="pageQueryForm" action="${ctx}/product/list" method="post">
					<input type="hidden" id="pageNumber" name="pageNumber">
					<select name="catid">
						<option value="0">全部</option>
						<c:forEach items="${cList }" var="ct">
							<option value="${ct.id }">${ct.name }</option>
						</c:forEach>
					</select>
					<input name="submit" class="btnGray" type="submit" value="筛选" />
				</form>
				<span>&nbsp;</span>
			</div>
			
			<div id="list">
				<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
					<tr>
						<th width="40" align="center">编号</th>
						<th width="200">商品名称</th>
						<th align="left">规格等备注</th>
						<th width="80">价格</th>
						<th width="100" align="center">商品分类</th>
						<th width="100" align="center">热卖品</th>
						<th width="150" align="center">操作</th>
					</tr>
					<c:forEach items="${page.data }" var="pp">
						<tr>
							<td align="center">${pp.id }</td>
							<td>${pp.name }</td>
							<td>${pp.bz }</td>
							<td>${pp.price }</td>
							<td align="center">${pp.catalog.name }</td>
							<td align="center"><c:if test="${pp.state == 0 }">无</c:if>
								<c:if test="${pp.state == 1 }"><b style="color:#FF8247;">热卖中</b></c:if></td>
							<td align="center">
								<c:if test="${pp.state == 0 }"><a href="${ctx}/product/updateState/${pp.id}/1">设置热卖</a></c:if>
								<c:if test="${pp.state == 1 }"><a href="${ctx}/product/updateState/${pp.id}/0">取消热卖</a></c:if> | 
								<a href="${ctx}/product/edit/${pp.id}">编辑</a> | <a href="${ctx}/product/remove/${pp.id}">删除</a>
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