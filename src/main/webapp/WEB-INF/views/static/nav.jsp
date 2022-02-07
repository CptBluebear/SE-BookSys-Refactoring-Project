<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int customerOid = 0;
	boolean isAdmin = false;
	
	try{ 
		isAdmin = (boolean)request.getAttribute("InterceptorCustomerIsAdmin");
	}
	catch(Exception e)
	{
		isAdmin = false;
	}
	
	try{ 
		customerOid = (int)request.getAttribute("InterceptorCustomerOid");
	}
	catch(Exception e)
	{
		customerOid = 0;
	}
%>
<!DOCTYPE html>
 <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="/home">Restaurant</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link" href="/menu">메뉴</a></li>
                        <li class="nav-item"><a class="nav-link" href="/reservation/selectdate">예약</a></li>
						<!--
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
						-->
                    </ul>
					<ul class="navbar-nav">
						<% if(isAdmin) { %><li class="nav-item"><a class="nav-link" href="/staff">관리자</a></li> <% } %>
						<% if(customerOid != 0) { %><li class="nav-item"><a class="nav-link" href="/mypage">마이페이지</a></li> <% } %>
						<% if(customerOid != 0) { %><li class="nav-item"><a class="nav-link" href="javascript:logout();">로그아웃</a></li> <% } %>
                        <% if(customerOid == 0) { %><li class="nav-item"><a class="nav-link" href="/register">회원가입</a></li> <% } %>
                        <% if(customerOid == 0) { %><li class="nav-item"><a class="nav-link" href="/login">로그인</a></li> <% } %>
					</ul>
					<!--
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </button>
                    </form>
					-->
                </div>
            </div>
        </nav>