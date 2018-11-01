<script type="text/javascript" src="${ctx}/static/jquery/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery/easyui/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery/easyui/themes/bootstrap/easyui.css">
<script type="text/javascript">
	function pleaseWait(mes) {
		$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:$(document).width(),height:$(document).height()}).appendTo("body");
		$("<div class=\"datagrid-mask-msg\"></div>").html(mes).appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
	}
	function dispalyLoad() {
	    $(".datagrid-mask").remove();
	    $(".datagrid-mask-msg").remove();
	}
</script>
