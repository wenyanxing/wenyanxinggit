<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>公告管理</title>
<%@include file="/common/kindeditor.jsp"%>
<link href="${ctx}/static/admin/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/static/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/global.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/js/jquery.autotextarea.js"></script>

<script type='text/javascript'>
KindEditor.ready(function(K) {
	var editor = K.create('textarea[name="content"]', {
		cssPath : '${ctx}/static/kindeditor/plugins/code/prettify.css',
		uploadJson : '${ctx}/static/kindeditor/jsp/upload_json.jsp',
		fileManagerJson : '${ctx}/static/kindeditor/jsp/file_manager_json.jsp',
		allowFileManager : true,
		allowImageUpload : true
	});
	
	K('#image1').click(function() {
		editor.loadPlugin('image', function() {
			editor.plugin.imageDialog({
				imageUrl : K('#url1').val(),
				clickFn : function(url, title, width, height, border, align) {
					K('#url1').val(url);
					editor.hideDialog();
				}
			});
		});
	});
});
</script>
</head>
<body>
<div id="dcWrap">
	<%@ include file="/common/top.jsp"%>
    <%@ include file="/common/lmenu.jsp"%>
    
    <div id="dcMain">
	   	<!-- 当前位置 -->
		<div id="urHere">公告管理<b>></b><strong>添加公告</strong></div>
		<div class="mainBox" style="height:auto!important;">
		    <form action="${ctx}/article/save" method="post">
		    	<input type="hidden" name="id" value="${article.id }"/>
		    	<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
					<tr>
						<td align="right">标题</td>
						<td><input type="text" name="title" value="${article.title }" size="60" class="inpMain" /></td>
					</tr>
					<tr>
						<td align="right" valign="top">概要</td>
						<td><textarea name="outline" cols="70" rows="3" class="inpMain">${article.outline }</textarea></td>
					</tr>
					<tr>
						<td align="right" valign="top">详细描述</td>
						<td><textarea id="content" name="content" style="width:80%;height:400px;">${article.content }</textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><input name="submit" class="btn" type="submit" value="提交" /></td>
					</tr>
		     	</table>
		    </form>
	 	</div>
 	</div>
 	<div class="clear"></div>
</div>
</body>
</html>