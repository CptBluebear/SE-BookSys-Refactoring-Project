<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@include file="./static/head.jsp" %>

<body>
<%@include file="./static/nav.jsp" %>

    <!-- Header-->
    <header class="bg-dark py-1">
        <div class="container px-4 px-lg-5 my-3">
            <div class="text-center text-white">
                <h1 class="display-4 fw-bolder">SE Restruant</h1>
            </div>
        </div>
    </header>
    <!-- Section-->
    <section class="py-1" style="margin-bottom: 15%">
        <div class="mainpage">
            <center>
                <div class="mainText">
                    <h1>Team 7 Restaurant</h1>
                </div>
                <img src="/resources/img/main.jpg" height="600" width="900" alt="main_page">
            </center>
        </div>
    </section>

<%@include file="./static/footer.jsp" %>
</body>
</html>