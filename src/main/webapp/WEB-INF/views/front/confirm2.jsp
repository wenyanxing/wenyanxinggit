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
        <h1 class="title">购买商品</h1>
        <div class="receive-container">
          <h2>收货人</h2>
          <div class="address-container">
            <div class="new-address">
              <div class="input-address" style="display:;">
                <form id="suborder" action="${ctx}/percenter/payOrder" method="post">
                  <input type="hidden" name="id" value="${orders.id }"/>
                  <input type="hidden" name="hjje" value="${orders.hjje }"/>
                  <input type="hidden" name="pids" value="${orders.pids }"/>
                  <input type="hidden" name="cid" value="${orders.customer.id }"/>
                  <input type="hidden" name="state" value="0"/>
                  <ul>
                    <li>
                      <dl>
                        <dt><span class="require">*</span>收件人：</dt>
                        <dd><input type="text" name="sjr" value="${orders.sjr }"/></dd>
                      </dl>
                    </li>
                    <li>
                      <dl>
                        <dt><span class="require">*</span>手机号码：</dt>
                        <dd><input type="text" name="sjrdh" value="${orders.sjrdh }"/></dd>
                      </dl>
                    </li>
                    <li>
                      <dl>
                        <dt><span class="require">*</span>地址：</dt>
                        <dd>
                          <input type="text" name="address"  value="${orders.address }" style="width:500px" class="address-text" />
                        </dd>
                      </dl>
                    </li>
					<li>
                      <dl>
                        <dt>订单备注：</dt>
                        <dd><textarea name="ddbz" cols="80" rows="4">${orders.ddbz }</textarea></dd>
                      </dl>
                    </li>
                  </ul>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="confirm-form">
          <h2>确认订单信息</h2>
          <ul class="confirm-nav">
            <li class="name">商品</li>
            <li class="price">单价</li>
            <li class="count">数量</li>
            <li class="total">小计</li>
          </ul>
          <c:forEach items="${orders.pList }" var="pp">
          <ul class="confirm-content">
            <li class="name"><a href="../product/details.html"><img src="${pp.product.recover }" width="80" height="80" alt="${pp.product.name }" /><span>${pp.product.name }</span></a></li>
            <li class="price">${pp.product.price }</li>
            <li class="count">${pp.num }</li>
            <li class="total">${pp.product.price*pp.num }</li>
          </ul>
          </c:forEach>
          <p class="form-price">
            <span>总计：￥${orders.hjje }</span>
          </p>
          <p class="need-pay">实付：<span>￥${orders.hjje }</span></p>
          
          <p class="cart-pay">
            <a href="${ctx}/percenter/index" class="re-cart">返回我的订单</a>
            <a href="#" onclick="$('#suborder').submit();" class="go-pay">去支付</a>
          </p>
        </div>
      </div>
      
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  
</body>
</html>
