<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link href="${ctx}/static/admin/css/public.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/static/admin/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/admin/js/global.js"></script>
</head>
<body>
<!-- 顶部  -->
<%@ include file="/common/top.jsp"%>

<div id="dcWrap">
    <%@ include file="/common/lmenu.jsp"%>
    
    <div id="dcMain"> <!-- 当前位置 -->
		<div id="urHere">网上鲜花订购平台后台管理系统</div>
		<img src="${ctx}/static/login/images/grass2.jpg" style="width:auto; height:405px;"/>
	</div>
</div>
</body>
</html>