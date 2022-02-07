<%@page import="org.corodiak.booksys.domain.Waiting"%>
<%@page import="org.corodiak.booksys.domain.Reservation"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Reservation> reservationList = (List<Reservation>)request.getAttribute("reservationList");
	List<Waiting> waitingList = (List<Waiting>)request.getAttribute("waitingList");
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
                <h1 class="display-4 fw-bolder">마이페이지</h1>
            </div>
        </div>
    </header>
    <!-- Section-->
    <section class="py-5" style="margin-bottom: 20%">
        <div class="container px-4 px-lg-5 mt-5">
			<h1>예약 내역</h1>
            <hr>
            <div class="container">
                <div class="table-responsive">
                    <table class="table table-bordered text-center">
                        <thead>
                            <tr class="bg-light-gray">
                                <th class="text-uppercase">날짜</th>
                                <th class="text-uppercase">시간</th>
                                <th class="text-uppercase">인원</th>
                                <th class="text-uppercase">테이블</th>
                                <th class="text-uppercase">옵션</th>
                            </tr>
                        </thead>
						<tbody>
						<%
							for(Reservation reservation:reservationList)
							{
						%>
							<tr>
								<td>
									<div class="margin-10px-top font-size14"><%=reservation.getBookingDate().toString() %></div>
								</td>
								<td>
									<div class="margin-10px-top font-size14"><%=reservation.getBookingTime().toString() %></div>
								</td>
								<td>
									<div class="margin-10px-top font-size14"><%=reservation.getBookingCovers() %>명</div>
								</td>
								<td>
									<div class="margin-10px-top font-size14"><%=reservation.getReservationTable().getTableNumber() %>번</div>
								</td>
								<td>
									<div class="margin-10px-top font-size14">인증 : <%=reservation.getReservationAuthPassword() %> <button class="btn btn-danger" onclick="location.href='/reservation/reservationcancel/<%=reservation.getReservationOid() %>'">취소</button></div>
								</td>
							</tr>
						<% } %>
						</tbody>
					</table>
				</div>
			</div>
        </div>
		<div class="container px-4 px-lg-5 mt-5">
			<h1>예약 대기 내역</h1>
            <hr>
            <div class="container">
                <div class="table-responsive">
                    <table class="table table-bordered text-center">
                        <thead>
                            <tr class="bg-light-gray">
                                <th class="text-uppercase">날짜</th>
                                <th class="text-uppercase">시간</th>
                                <th class="text-uppercase">인원</th>
                                <th class="text-uppercase">옵션</th>
                            </tr>
                        </thead>
						<tbody>
						<%
							for(Waiting waiting:waitingList)
							{
						%>
						<tr>
							<td>
								<div class="margin-10px-top font-size14"><%=waiting.getBookingDate().toString() %></div>
							</td>
							<td>
								<div class="margin-10px-top font-size14"><%=waiting.getBookingTime().toString() %></div>
							</td>
							<td>
								<div class="margin-10px-top font-size14"><%=waiting.getBookingCovers() %></div>
							</td>
							<td>
								<div class="margin-10px-top font-size14"><button class="btn btn-danger" onclick="location.href='/reservation/waitingcancel/<%=waiting.getWaitingOid() %>'">취소</button></div>
							</td>
						</tr>
						<% } %>
						</tbody>
					</table>
				</div>
			</div>
        </div>
    </section>

<%@include file="./static/footer.jsp" %>
</body>
</html>