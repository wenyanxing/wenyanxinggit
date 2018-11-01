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
		<div id="urHere">商品管理<b>></b><strong>添加商品</strong></div>
		<div class="mainBox" style="height:auto!important;">
		    <form action="${ctx}/product/save" method="post">
		    	<input type="hidden" name="id" value="${product.id }"/>
		    	<input type="hidden" name="co2" value="${product.recover }"/>
		    	<table style="width:100%;" class="tableBasic">
					<tr>
						<td align="right">商品分类</td>
						<td>
							<select name="catid" class="inpMain">
								<c:forEach items="${cList }" var="ct">
									<option value="${ct.id }">${ct.name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">商品名称</td>
						<td><input type="text" name="name" value="${product.name }" size="40" class="inpMain" /></td>
					</tr>
					<tr>
						<td align="right" valign="top">商品描述</td>
						<td><textarea name="bz" cols="70" rows="3" class="inpMain">${product.bz }</textarea></td>
					</tr>
					<tr>
						<td align="right">商品价格</td>
						<td><input type="text" name="price" value="${product.price }" size="10" class="inpMain" />元/件</td>
					</tr>
					<c:if test="${product.recover != null}">
						<tr>
							<td align="right">当前缩略图</td>
							<td><img src="${product.recover }" width="200" height="200"/></td>
						</tr>
					</c:if>
					<tr>
						<td align="right">缩略图</td>
						<td><input type="text" name="recover" id="url1" value="" /> <input type="button" id="image1" value="选择图片" /></td>
					</tr>
					
					<tr>
						<td align="right" valign="top">详细描述</td>
						<td><textarea id="content" name="content" style="width:80%;height:400px;">${product.content }</textarea></td>
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