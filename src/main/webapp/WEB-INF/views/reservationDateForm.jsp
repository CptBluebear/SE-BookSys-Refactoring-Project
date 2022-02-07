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
                <h1 class="display-4 fw-bolder">예약 날짜 선택</h1>
            </div>
        </div>
    </header>
    <!-- Section-->
    <section class="py-3">
        <div class="container px-3 px-lg-5">
            <h1>MainContent</h1>
            <hr>
            <section class="ftco-section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-14">
                            <div class="elegant-calencar d-md-flex">
                                <div class="wrap-header d-flex align-items-center">
                                    <p id="reset">reset</p>
                                    <div id="header" class="p-5">
                                        <div class="pre-button d-flex align-items-center justify-content-center"><i
                                                class="fa fa-chevron-left"></i></div>
                                        <div class="head-info">
                                            <div class="head-day"></div>
                                            <div class="head-month"></div>
                                        </div>
                                        <div class="next-button d-flex align-items-center justify-content-center"><i
                                                class="fa fa-chevron-right"></i></div>
                                    </div>
                                </div>
                                <div class="calendar-wrap">
                                    <table id="calendar">
                                        <thead>
                                            <tr>
                                                <th>Sun</th>
                                                <th>Mon</th>
                                                <th>Tue</th>
                                                <th>Wed</th>
                                                <th>Thu</th>
                                                <th>Fri</th>
                                                <th>Sat</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
            </section>
            <div class="col text-center">
                <button class="btn btn-primary" type="button" onclick="javascript:postreservationdate()">다음</button>
            </div>
        </div>
    </section>

	<%@include file="./static/footer.jsp"%>
	<script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/popper.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/main.js"></script>
</body>
</html>