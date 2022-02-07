<%@page import="org.corodiak.booksys.domain.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.corodiak.booksys.domain.WalkIn"%>
<%@page import="org.corodiak.booksys.domain.Reservation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Reservation> reservationList = (List<Reservation>)request.getAttribute("reservationList");
	List<WalkIn> walkInList = (List<WalkIn>)request.getAttribute("walkInList");
	List<Menu> menuList = (List<Menu>)request.getAttribute("menuList"); 
	List<Integer> bookingOidList = new ArrayList<Integer>();
	for(Reservation reservation:reservationList)
		bookingOidList.add(reservation.getBookingOid());
	for(WalkIn walkIn:walkInList)
		bookingOidList.add(walkIn.getBookingOid());
	
%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords"
        content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
    <meta name="description"
        content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
    <meta name="robots" content="noindex,nofollow">
    <title>SE 프로젝트</title>
    <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <!-- Custom CSS -->
    <link href="/resources/admin/plugins/bower_components/chartist/dist/chartist.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/admin/plugins/bower_components/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.css">
    <!-- Custom CSS -->
    <link href="/resources/admin/css/admin/style.min.css" rel="stylesheet">
</head>

<body>
    <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
        data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
        <header class="topbar" data-navbarbg="skin5">
            <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                <div class="navbar-header" data-logobg="skin6">
                    <a class="nav-toggler waves-effect waves-light text-dark d-block d-md-none"
                        href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                </div>
                <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                    <ul class="navbar-nav ms-auto d-flex align-items-center">

                        <li>
                            <a class="profile-pic">
                                <span class="text-white font-medium">Logined As Admin</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <aside class="left-sidebar" data-sidebarbg="skin6">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <!-- User Profile-->
                        <li class="sidebar-item pt-2">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/staff"
                                aria-expanded="false">
                                <i class="far fa-clock" aria-hidden="true"></i>
                                <span class="hide-menu">관리자 메인</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/staff/addmenu"
                                aria-expanded="false">
                                <i class="fa fa-table" aria-hidden="true"></i>
                                <span class="hide-menu">메뉴 목록 추가</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/staff/walkin/selectdate"
                                aria-expanded="false">
                                <i class="fa fa-font" aria-hidden="true"></i>
                                <span class="hide-menu">현장 예약 추가</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/staff/sales"
                                aria-expanded="false">
                                <i class="fa fa-font" aria-hidden="true"></i>
                                <span class="hide-menu">매출 이력</span>
                            </a>
                        </li>
                    </ul>

                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </aside>

        <div class="page-wrapper">

            <div class="page-breadcrumb bg-white">
                <div class="row align-items-center">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">대시보드</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <div class="d-md-flex">
                            <ol class="breadcrumb ms-auto">
                                <li><a href="/home" class="fw-normal">메인페이지</a></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid">

                <!-- RECENT SALES -->
                <div class="row">
                    <div class="col-md-12 col-lg-12 col-sm-12">
                    <!-- ############################################################################## -->
                        <div class="white-box">
                            <div class="d-md-flex mb-3">
                                <h3 class="box-title mb-0">예약 목록</h3>
                                <div class="col-md-3 col-sm-4 col-xs-6 ms-auto">
                                	<form action="./staff" method="get">
                                    	<input type="date" name="date">
                                    	<button class="btn btn-primary" type="submit">확인</button>
                                    </form>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table no-wrap">
                                    <thead>
                                        <tr>
                                            <th class="border-top-0">#</th>
                                            <th class="border-top-0">Time</th>
                                            <th class="border-top-0">Table</th>
                                            <th class="border-top-0">Covers</th>
                                            <th class="border-top-0">Auth</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                    	for(Reservation reservation:reservationList)
                                    	{
                                    %>
                                        <tr>
                                            <td><%=reservation.getBookingOid() %></td>
                                            <td><%=reservation.getBookingTime().toString() %></td>
                                            <td><%=reservation.getReservationTable().getTableNumber() %></td>
                                            <td><%=reservation.getBookingCovers() %></td>
                                            <td>
                                            <%
                                            	if(reservation.getReservationArrivalTime() == null)
                                            	{
                                            %>
                                            <form action="/staff/authpass/<%=reservation.getReservationOid() %>" method="post">
                                            	<input type="text" name="authpass" size="4">
                                            	<button class="btn btn-primary">확인</button>
                                            </form>
                                            <%
                                            	}
                                            	else
                                            	{
                                            %><%=reservation.getReservationArrivalTime().toString() %><% } %>
                                            </td>
                                        </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="d-md-flex mb-3">
                                <h3 class="box-title mb-0">현장 예약 목록</h3>
                                <div class="col-md-3 col-sm-4 col-xs-6 ms-auto">
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table no-wrap">
                                    <thead>
                                        <tr>
                                            <th class="border-top-0">#</th>
                                            <th class="border-top-0">Time</th>
                                            <th class="border-top-0">Table</th>
                                            <th class="border-top-0">Covers</th>
                                            <th class="border-top-0">Auth</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                    	for(WalkIn walkIn:walkInList)
                                    	{
                                    %>
                                        <tr>
                                            <td><%=walkIn.getBookingOid() %></td>
                                            <td><%=walkIn.getBookingTime().toString() %></td>
                                            <td><%=walkIn.getWalkInTable().getTableNumber() %></td>
                                            <td><%=walkIn.getBookingCovers() %></td>
                                            <td>
                                            </td>
                                        </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-lg-12 col-sm-12">
                        <div class="white-box">
                            <div class="d-md-flex mb-3">
                                <h3 class="box-title mb-0">주문 추가</h3>
                                <div class="col-md-3 col-sm-4 col-xs-6 ms-auto">
                                </div>
                            </div>
                            <form action="/staff/addorder" method="post">
								<div class="form-group">
									<label>고유 ID</label>
									<select class="form-select shadow-none row border-top" name="bookginOid">
									<%
										for(int oid:bookingOidList)
										{
									%>
                                        <option value="<%=oid %>"><%=oid %></option>
                                    <% } %>
                                    </select>
									<label>주문</label>
									<select class="form-select shadow-none row border-top" name="menuOid">
									<%
										for(Menu menu:menuList)
										{
									%>
                                        <option value="<%=menu.getMenuOid() %>"><%=menu.getMenuName() %></option>
                                    <% } %>
                                    </select>
									<label>수량</label>
									<input type="text" class="form-control" name="quantity"><br>
									<button class="btn btn-primary " type="submit">확인</button>
								</div>
							</form>
                        </div>
                    </div>
                </div>
                
            </div>

            <!-- footer -->

        </div>

    </div>

    <script src="/resources/admin/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="/resources/admin/bootstrap/dist/js/admin/bootstrap.bundle.min.js"></script>
    <script src="/resources/admin/js/admin/app-style-switcher.js"></script>
    <script src="/resources/admin/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js"></script>
    <!--Wave Effects -->
    <script src="/resources/admin/js/admin/waves.js"></script>
    <!--Menu sidebar -->
    <script src="/resources/admin/js/admin/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="/resources/admin/js/admin/custom.js"></script>
    <!--This page JavaScript -->
    <!--chartis chart-->
    <script src="/resources/admin/plugins/bower_components/chartist/dist/chartist.min.js"></script>
    <script src="/resources/admin/plugins/bower_components/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="/resources/admin/js/pages/dashboards/dashboard1.js"></script>
</body>
</html>