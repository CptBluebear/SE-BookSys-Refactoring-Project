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
                    <h1 class="display-4 fw-bolder">회원가입 완료</h1>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
			<center>
				<h1>회원가입이 완료되었습니다.</h1>
				<h1>생성하신 계정으로 로그인 하시길 바랍니다.</h1>
			</center>
        </section>

<%@include file="./static/footer.jsp" %>
</body>
</html>