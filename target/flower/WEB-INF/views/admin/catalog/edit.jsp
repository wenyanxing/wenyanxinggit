<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>果品分类管理</title>
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
		<div id="urHere">分类管理<b>></b><strong>添加分类</strong></div>
		<div class="mainBox" style="height:auto!important;">
		    <form action="${ctx}/catalog/save" method="post">
		    	<input type="hidden" name="id" value="${catalog.id }"/>
		    	<table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
					<tr>
						<td align="right">分类名称</td>
						<td>
							<input type="text" name="name" value="${catalog.name }" size="40" class="inpMain" />
						</td>
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