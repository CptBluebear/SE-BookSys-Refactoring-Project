<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@include file="./static/head.jsp"%>

<body>
	<%@include file="./static/nav.jsp"%>

	<!-- Header-->
	<header class="bg-dark py-1">
		<div class="container px-4 px-lg-5 my-3">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">회원가입</h1>
			</div>
		</div>
	</header>
	<!-- Section-->
	<section class="py-4">
		<div class="container px-2 px-lg-2 mt-2">
			<div
				class="row gx-2 gx-lg-2 row-cols-2 row-cols-md-8 row-cols-xl-4 justify-content-center">
				<section>
					<form name="register" action="" method="post">
						<div class="form-group">
							<label>아이디</label> <input type="text" name="id"
								placeholder="아이디 입력" class="form-control"> <label>암호</label>
							<input type="password" name="password" class="form-control">
							<label>이름</label> <input type="text" name="name" value=""
								class="form-control"> <label>생년월일</label> <input
								type="date" name="birthday" class="form-control"> <label>전화번호</label>
							<input type="text" name="phoneNumber" placeholder="010-****-****"
								class="form-control"> </br>
							<button type="submit" class="btn btn-primary">회원가입</button>
						</div>
					</form>
				</section>
			</div>
		</div>
	</section>

	<%@include file="./static/footer.jsp"%>
</body>
</html>