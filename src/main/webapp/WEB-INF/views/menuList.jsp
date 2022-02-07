<%@page import="org.corodiak.booksys.domain.Menu"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Menu> menuList = (List<Menu>)request.getAttribute("menuList");
%>
<!DOCTYPE html>
<html>

<%@include file="./static/head.jsp"%>

<body>
	<%@include file="./static/nav.jsp"%>

	<!-- Header-->
	<header class="bg-dark py-1">
		<div class="container px-4 px-lg-5 my-3">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">메뉴</h1>
			</div>
		</div>
	</header>

	<!-- Section-->
	<section class="py-5" style="margin-bottom: 10%">
		<div class="container px-4 px-lg-5">
			<!-- Carousel wrapper -->
			<!-- Controls -->
			<div class="d-flex justify-content-center mb-4"></div>
			<!-- Inner -->
			<!-- Single item -->
			<div class="container">
				<div class="row">
					<% for(Menu menu:menuList) { %>
					<div class="col-lg-4">
						<div class="card">
							<img src="/resources/attach/<%=menu.getMenuImagePath() %>"
								class="card-img-top" alt="..." />
							<div class="card-body">
								<h5 class="card-title"><%=menu.getMenuName() %></h5>
								<p class="card-text">
									<%=menu.getMenuDescription() %>
								<p>
									가격 : <%=menu.getMenuPrice() %></p>
								</p>
							</div>
						</div>
					</div>
					<% } %>
				</div>

			</div>
		</div>

		<!-- Carousel wrapper -->
	</section>

	<%@include file="./static/footer.jsp"%>
</body>
</html>