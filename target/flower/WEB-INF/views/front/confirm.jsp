<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>结算</title>
<link rel="stylesheet" href="${ctx}/static/front/css/style.css">
<link rel="stylesheet" href="${ctx}/static/front/css/orange.css">
<script src="${ctx}/static/front/js/html5.js"></script>

<link rel="stylesheet" href="${ctx}/static/cart/css/common.css" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/static/cart/css/settlement.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/static/cart/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/cart/js/slider.js"></script>
</head>

<body bgcolor="#e0d6df">
  <%@include file="/common/ftop.jsp"%>
  
  <div id="container">
    <div id="content">
      <div class="settlement-message">
        
        <div class="receive-container">
          
          <div class="address-container" style="border:1px solid #bbb;margin:20px auto;padding:40px 20px;">
            <div class="new-address">
              <div class="input-address" style="display:;">
                <form id="suborder" action="${ctx}/cart/payment" method="post">
                  <input type="hidden" name="id"/>
                  <input type="hidden" name="hjje" value="${hjje }"/>
                  <input type="hidden" name="pids" value="${pids }"/>
                  <input type="hidden" name="cid" value="${cid }"/>
                  <input type="hidden" name="state" value="0"/>
                  <ul>
                    <li>
                      <dl>
                        <dt>收件人：</dt>
                        <dd><input type="text" name="sjr"/><span style="color:red;margin-left:10px;">*</span></dd>
                      </dl>
                    </li>
                    <li>
                      <dl>
                        <dt>手机号码：</dt>
                        <dd><input type="text" name="sjrdh"/><span style="color:red;margin-left:10px;">*</span></dd>
                      </dl>
                    </li>
                    <li>
                      <dl>
                        <dt>地址：</dt>
                        <dd>
                          <input type="text" name="address" style="width:500px" class="address-text" /><span style="color:red;margin-left:10px;">*</span>
                        </dd>
                      </dl>
                    </li>
					<li>
                      <dl>
                        <dt>订单备注：</dt>
                        <dd><textarea name="ddbz" cols="80" rows="4"></textarea></dd>
                      </dl>
                    </li>
                  </ul>
                </form>
              </div>
            </div>
          </div>
          
        </div>
        <div class="confirm-form">
          <h2>订单详情确认</h2>
          <ul class="confirm-nav">
            <li class="name">商品</li>
            <li class="price">单价</li>
            <li class="count">数量</li>
            <li class="total">小计</li>
          </ul>
          <c:forEach items="${cartList }" var="cart">
          <ul class="confirm-content">
            <li class="name"><a href="../product/details.html"><img src="${cart.product.recover }" width="80" height="80" alt="${cart.product.name }" /><span>${cart.product.name }</span></a></li>
            <li class="price">${cart.product.price }</li>
            <li class="count">${cart.np }</li>
            <li class="total">${cart.product.price*cart.np }</li>
          </ul>
          </c:forEach>
          <p class="form-price">
            <span>总计：￥${hjje }</span>
          </p>
          <p class="need-pay">实付：<span>￥${hjje }</span></p>
          
          <p class="cart-pay">
            <a href="${ctx}/cart/mycart" class="re-cart">返回购物车</a>
            <a href="#" onclick="$('#suborder').submit();" class="go-pay">去支付</a>
          </p>
        </div>
      </div>
      
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  
</body>
</html>
