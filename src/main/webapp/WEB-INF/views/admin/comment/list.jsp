<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>客户留言</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/admin/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/admin/css/main.css"/>
    <script type="text/javascript" src="${ctx}/static/admin/js/libs/modernizr.min.js"></script>
</head>
<body>
<%@ include file="/common/ftop.jsp"%>

<div class="container clearfix">
    <%@ include file="/common/lmenu.jsp"%>
    
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><a class="crumb-name">客户留言</a><span class="crumb-step">&gt;</span><span>留言管理</span></div>
        </div>
        
        <div class="search-wrap">
            <div class="search-content">
                <form id="pageQueryForm" action="${ctx}/question/list" method="post">
					<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber }">
			  		<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
                </form>
            </div>
        </div>
        <div class="result-wrap">
             <div class="result-content">
                 <table class="result-tab" width="100%">
                     <tr>
                         <th width="10%">联系人</th>
				        <th width="10%">联系方式</th>
				        <th width="15%">留言时间</th>
				        <th width="65%" style="text-align:left;padding-left:10px;">留言内容</th>
                     </tr>
                     <c:forEach items="${page.data }" var="ask">
				    	<tr>
					        <td>${ask.xm }</td>
					        <td>${ask.phone }</td>
					        <td>${ask.createTime }</td>
					        <td style="text-align:left;padding-left:10px;">${ask.content }</td>
					    </tr>
				    </c:forEach>
                 </table>
				<%@ include file="/common/page.jsp" %>
             </div>
        </div>
    </div>
    <!--/main-->
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