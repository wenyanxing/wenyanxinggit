<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品信息</title>
<link rel="stylesheet" href="${ctx}/static/front/css/style.css">
<link rel="stylesheet" href="${ctx}/static/front/css/orange.css">
<script src="${ctx}/static/front/js/html5.js"></script>

<link rel="stylesheet" href="${ctx}/static/cart/css/common.css" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/static/cart/css/product.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/static/cart/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/cart/js/slider.js"></script>

</head>
<body bgcolor="#e0d6df">
<%@include file="/common/ftop.jsp"%>

<div id="container">
  <div id="nav-side">
      <div class="pro-kinds">
        <h1 class="kinds-tit">产品分类</h1>
        <div class="kinds-cont">
          <div class="kinds-det">
            <h2 class="det-tit">挑一挑总有你喜欢的</h2>
            <div class="det-content">
              <c:forEach items="${cList }" var="cc">
              <a href="${ctx}/product/index?cid=${cc.id}">${cc.name }</a>
             </c:forEach> 
              <div style="clear: both;"></div>
            </div>
          </div>
          
        </div>
      </div>
      <div class="hot-sale">
        <h1 class="hot-tit">热卖商品</h1>
        <div class="hot-cont">
          <ul class="hot-list">
            <c:forEach items="${pList1 }" var="pp">
            <li>
              <p><a href="${ctx}/product/productdetail?id=${pp.id}"><img src="${pp.recover }" width="136" height="112" alt="${pp.name }" /></a></p>
              <p class="hot-name"><a href="${ctx}/product/productdetail?id=${pp.id}">${pp.name }  <span>RMB:${pp.price }</span></a></p>
              <p class="hot-name">${fn:substring(pp.bz,0,9) }</p>
            </li>
           </c:forEach>
          </ul>
        </div>
      </div>
    </div>
  <div id="content">
    <div class="pro-detail">
      <div class="pro-img">
      <div id="tsShopContainer">
                    <div id="tsImgS">
                        <a href="#" title="Images" class="MagicZoom" id="MagicZoom"><img width="300" height="300" src="${product.recover}" /></a>
                    </div>
                    <div id="tsPicContainer">
                        <div id="tsImgSArrL" onclick="tsScrollArrLeft()"></div>
                        <div id="tsImgSCon">
                            <ul>
                                <!-- <li onclick="showPic(0)" rel="MagicZoom" class="tsSelectImg"><img height="42" width="42" src="image/01.jpg" tsImgS="image/01.jpg" /></li>
                                <li onclick="showPic(1)" rel="MagicZoom"><img height="42" width="42" src="image/02.png" tsImgS="image/02.png" /></li>
                                <li onclick="showPic(2)" rel="MagicZoom"><img height="42" width="42" src="image/03.jpg" tsImgS="image/03.jpg" /></li>
                                <li onclick="showPic(3)" rel="MagicZoom"><img height="42" width="42" src="image/04.jpg" tsImgS="image/04.jpg" /></li>
                                <li onclick="showPic(4)" rel="MagicZoom"><img height="42" width="42" src="image/05.jpg" tsImgS="image/05.jpg" /></li>
                                <li onclick="showPic(5)" rel="MagicZoom"><img height="42" width="42" src="image/06.jpg" tsImgS="image/06.jpg" /></li>
                                <li onclick="showPic(6)" rel="MagicZoom"><img height="42" width="42" src="image/07.jpg" tsImgS="image/07.jpg" /></li>
                                <li onclick="showPic(7)" rel="MagicZoom"><img height="42" width="42" src="image/08.jpg" tsImgS="image/08.jpg" /></li>
                                <li onclick="showPic(8)" rel="MagicZoom"><img height="42" width="42" src="image/09.jpg" tsImgS="image/09.jpg" /></li>
                                <li onclick="showPic(9)" rel="MagicZoom"><img height="42" width="42" src="image/10.gif" tsImgS="image/10.gif" /></li>
                                <li onclick="showPic(10)" rel="MagicZoom"><img height="42" width="42" src="image/11.gif" tsImgS="image/11.gif" /></li>
                                <li onclick="showPic(10)" rel="MagicZoom"><img height="42" width="42" src="image/11.gif" tsImgS="image/11.gif" /></li>
                                <li onclick="showPic(10)" rel="MagicZoom"><img height="42" width="42" src="image/11.gif" tsImgS="image/11.gif" /></li> -->
                            </ul>
                        </div>
                        <div id="tsImgSArrR" onclick="tsScrollArrRight()"></div>
                    </div>
                </div>
                <script src="${ctx}/static/front/product/js/ShopShow.js"></script>
        
      </div>
      
      <div class="text-container">
        <h1>${product.name }</h1>
        <p class="pro-text">${product.bz }</p>
        <div class="pro-price">
         <dl>
            <dt>价格</dt>
            <dd><b style="font-size:18px;color:red;">￥${product.price }</b><span>元/500g</span></dd>
          </dl>
        </div>
        <script>
		  $(".fre-target").click(function(){
			$(this).find("ul").toggle();
			$(this).find("li").click(function(){
			  $(".fre-target > a").text($(this).text());
			});
		  })
        </script>
        <dl class="pro-stock">
          <dt>数量</dt>
          <dd>
            <input class="pro-number" type="text" id="np" value="1" />
            <span class="cho-number"> <span class="pro-add">
            <img src="${ctx}/static/front/images/ico-add-number.gif" width="19" height="13" alt="" /></span> 
            <span class="pro-red"><img src="${ctx}/static/front/images/ico-red-number.gif" width="19" height="13" alt="" /></span> </span> 件
            <span class="stock-total"><c:if test="${product.kczt == 0 }"><b style="color:blue;">库存充足<b/></c:if>
            <c:if test="${product.kczt == 1 }"><b style="color:red;">库存不足</b></c:if></span>
		</dd>
        </dl>
        <script>
		    $(document).ready(function(){
			  $(".pro-add").click(function(){
				var getVal = parseInt($(this).parent().prev().val());
				$(this).parent().prev().val(getVal + 1);
			  });
			  $(".pro-red").click(function(){
				var getVal = $(this).parent().prev().val();
				if(getVal > 1){
			      $(this).parent().prev().val(getVal - 1);
				}
			  });
			})
          </script>
        <div class="buy-cart">
        	<a class="cart" href="#" onclick="javascript:addCart(${product.id});">加入购物车</a> 
        </div>
      </div>
      <div style="clear: both;"></div>
    </div>
    <div class="goods-evaluation">
      <h1><a href=".goods-details" class="tab-detail active">商品详情</a><!-- <a href=".total-evaluation" class="tab-evaluation">顾客评价</a> --></h1>
      <script>
	      $(".goods-evaluation h1 a").click(function(){
		    $(this).addClass("active").siblings().removeClass("active");
			$(this).parent().siblings().css("display","none");
			var getTarget = $(this).attr("href");
			$(getTarget).css("display","block");
			return false;
	   	  })
        </script>
      <div class="goods-details">${product.content }</div>
      <div class="total-evaluation">
        <ul>
        
          <li>
            <p class="eval-content">帮同事买的，非常好，包装也很好</p>
            <p class="eval-time">今天</p>
            <p class="eval-people">金***s</p>
          </li>
          
        </ul>
      </div>
    </div>
    
  </div>
</div>
<input type="hidden" name="cid" id="cid" value="${sessionScope.fuser.id }"/>
<%@include file="/common/footer.jsp"%>
<script>
function addCart(pid){
	var cid = $("#cid").val();
	var np = $("#np").val();
	if(cid == null){
		alert('请先登录！');
	}else{
		$.ajax({
			data : "cid=" +cid+"&pid="+pid+"&np="+np,
			type : "POST",
			dataType : 'json',
			url : "${ctx}/cart/addCart",
			error : function(data) {
				alert(data.msg);
			},
			success : function(data) {
				alert(data.msg);
			}
		});
	}
}
</script>
</body>
</html>
