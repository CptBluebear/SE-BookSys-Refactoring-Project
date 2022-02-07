<%@page import="org.corodiak.booksys.domain.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Order> orderList = (List<Order>)request.getAttribute("orderList");
	int bookingOid = (int)request.getAttribute("bookingOid");
	int totalPrice = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영수증</title>
</head>
<body>
영수증</br>
번호 : <%=bookingOid %></br>
=============================</br>
<%
	for(Order order:orderList)
	{
		int price = order.getOrderQuantity() * order.getOrderMenu().getMenuPrice();
		totalPrice += price;
%>
<%=order.getOrderMenu().getMenuName() %> <%=order.getOrderQuantity() %> <%=price %></br>
<%
	}
%>
=============================</br>
합계 : <%=totalPrice %></br>
</body>
</html>