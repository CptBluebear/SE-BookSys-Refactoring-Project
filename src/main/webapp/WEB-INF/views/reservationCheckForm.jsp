<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String date = (String)request.getAttribute("date");
	int tableOid = (int)request.getAttribute("tableOid");
	String time = (String)request.getAttribute("time");
	int covers = (int)request.getAttribute("covers");
	int tableNumber = (int)request.getAttribute("tableNumber");
%>
<!DOCTYPE html>
<html>

<%@include file="./static/head.jsp" %>

<body>
<%@include file="./static/nav.jsp" %>

<!-- Header-->
    <header class="bg-dark py-1">
        <div class="container px-4 px-lg-5 my-3">
            <div class="text-center text-white">
                <h1 class="display-4 fw-bolder">예약 정보</h1>
            </div>
        </div>
    </header>
    <!-- Section-->
    <section class="py-5">
        <div class="text-center">
            <h1>최종 예약정보</h1>
        </div>
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <table id="example" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                        	<th>날짜</th>
                            <th>시간</th>
                            <th>인원수</th>
                            <th>테이블 번호</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        	<td><%=date %></td>
                            <td><%=time %></td>
                            <td><%=covers %> 명</td>
                            <td>Table <%=tableNumber %></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <br />
        <div class="col text-center">
            <h4>입력하신 정보가 맞다면 확인을 눌러주십시오</h4>
            <form action="/reservation/confirm" method="post">
            	<input type="hidden" name="date" value="<%=date %>" />
            	<input type="hidden" name="tableOid" value="<%=tableOid %>" />
            	<input type="hidden" name="time" value="<%=time %>" />
            	<input type="hidden" name="covers" value="<%=covers %>" />
            	<button class="btn btn-primary" type="submit">확인</button>
            </form>
        </div>
    </section>

<%@include file="./static/footer.jsp" %>
</body>
</html>