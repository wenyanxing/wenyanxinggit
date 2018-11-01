<%@tag pageEncoding="UTF-8"%>
<%@tag import="com.bishe.core.utils.StringUtil"%>
<%@ attribute name="intDm" type="java.lang.Integer" required="true"%>
<%@ attribute name="length" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String dm =  StringUtil.zeroPadding(intDm, length);
%>
<%= dm%>