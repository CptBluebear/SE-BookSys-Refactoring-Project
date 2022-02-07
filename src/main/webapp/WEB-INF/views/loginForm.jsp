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
                <h1 class="display-4 fw-bolder">메뉴</h1>
            </div>
        </div>
    </header>
    <!-- Section-->
    <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <div id="container" class="main_container">
                    <div style="padding: 20px;"></div>
                    <div class="form_container">
                        <form name="login_form" action="./login" method="post">
                            <div class="form_title_div">
                                <p class="form_title_p">로그인</p>
                            </div>
                            <div>
                                <div>
                                    <a class="form_item_name">ID</a>
                                </div>
                                <div>
                                    <input type="text" name="id" placeholder="name" class="form_input" />
                                </div>
                                <div class="form_text_alert_padding">
                                    <div id="alert_username" class="form_text_alert"></div>
                                </div>
                            </div>

                            <div>
                                <div>
                                    <a class="form_item_name">Password</a>
                                </div>
                                <div>
                                    <input type="password" name="password" placeholder="Enter password"
                                        class="form_input" />
                                </div>
                                <div class="form_text_alert_padding">
                                    <div id="alert_password" class="form_text_alert"></div>
                                </div>
                            </div>

                            <div style="height: 10px;"></div>
                            <div>
                                <button type="submit" class="form_submit_button" onclick="login()">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

<%@include file="./static/footer.jsp" %>
</body>
</html>