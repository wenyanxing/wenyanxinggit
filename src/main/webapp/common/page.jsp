<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <style>
   	.pagea{
		padding:0 5px;
		border:1px solid #6CA6CD;
	}
	.pagea:HOVER {
		background:#D1EEEE;
	}
   </style>
    <div style="text-align: center;padding-top: 5px">
		<c:if test="${page.totalpage>1}">
			第${page.pageNumber}页/共${page.totalpage}页&nbsp;&nbsp;
			<a href="javascript:submitForm('1');">首页</a>&nbsp;&nbsp;
			<c:if test="${page.pageNumber-1>0}">
				<a href="javascript:submitForm('${page.pageNumber-1==0?1:page.pageNumber-1}');">上一页</a>&nbsp;&nbsp;
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="num">
				<a href="javascript:submitForm('${num }');" class="pagea" <c:if test="${page.pageNumber==num}">style="background:#6CA6CD;color:#fff;"</c:if>>${num }</a>
			</c:forEach>
			&nbsp;&nbsp;
			<c:if test="${page.pageNumber-page.totalpage<0}">
				<a href="javascript:submitForm('${page.pageNumber>page.totalpage?page.totalpage:page.pageNumber+1}');">下一页</a>&nbsp;&nbsp;
			</c:if>
			<a href="javascript:submitForm('${page.totalpage}');">尾页</a>&nbsp;&nbsp;
			<select id="s1">
				<c:forEach begin="1" end="${page.totalpage}" var="num">
					<option value="${num}" ${page.pageNumber==num?'selected="selected"':''}>${num}</option>
				</c:forEach>
			</select>
			<a href="javascript:jump()">跳转</a>&nbsp;&nbsp;
		</c:if>
		共${page.rows}条记录
	</div>
	<script type="text/javascript">
		function jump(){
			var num = document.getElementById("s1").value;
			submitForm(num);
		}
	</script>
