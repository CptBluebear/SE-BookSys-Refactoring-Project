<%@page import="org.corodiak.booksys.domain.Table"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String date = (String)request.getAttribute("date");
	int [][] bookingInfo = (int[][])request.getAttribute("bookingInfo");
	String [] timeTable = (String[])request.getAttribute("timeTable");
	List<Table> tableList = (List<Table>)request.getAttribute("tableList");
	if(date == null || bookingInfo == null || timeTable == null || tableList == null);
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
                <h1 class="display-4 fw-bolder">예약 세부 선택</h1>
            </div>
        </div>
    </header>
    <!-- Section-->
    <section class="py-3" style="margin-bottom: 10%">
        <div class="container px-3 px-lg-5">
            <h1>MainContent</h1>
            <hr>
            <div class="container">
                <select class="form-select" id="covers" aria-label="Default select example">
                    <option value="1">1명</option>
                    <option value="2">2명</option>
                    <option value="3">3명</option>
                    <option value="4">4명</option>
                </select>
                <br>
                <div class="table-responsive">
                    <table class="table table-bordered text-center">
                        <thead>
                            <tr class="bg-light-gray">
                                <th class="text-uppercase">Time
                                </th>
                                <% for(String time:timeTable) { %>
                                <th class="text-uppercase"><%=time %></th>
								<% } %>


                            </tr>
                        </thead>
                        <tbody>
                        <% for(int i=0;i<tableList.size();i++) { %>
                            <tr>
                                <td class="align-left">Table <%=tableList.get(i).getTableNumber() %>
                                </td>
                                <%
                                	for(int j=0;j<timeTable.length;j++)
                                	{
                                		if(bookingInfo[i][j] == 0)
                                		{
                                %>
                                <td>
                                    <div class="margin-10px-top font-size14" onclick="postinfo('/reservation/checkinfo', <%=tableList.get(i).getTableOid() %>, '<%=timeTable[j] %>')">O
                                    </div>
                                </td>
                                <%
                                		}
                                		else {
                                %>
                                <td>
                                    <div class="margin-10px-top font-size14" onclick="postinfo('/reservation/waitinginfo', <%=tableList.get(i).getTableOid() %>, '<%=timeTable[j] %>')">X
                                    </div>
                                </td>
                                <%
                                		}
                                	}
                                %>
                            </tr>
                        <% } %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
    
    <form id="hiddenform">
    	<input type="hidden" name="date" value="<%=date %>">
    </form>

<%@include file="./static/footer.jsp" %>
</body>
</html>