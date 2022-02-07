package org.corodiak.booksys.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.booksys.domain.Menu;
import org.corodiak.booksys.domain.Order;
import org.corodiak.booksys.domain.Reservation;
import org.corodiak.booksys.domain.Table;
import org.corodiak.booksys.domain.WalkIn;
import org.corodiak.booksys.service.BookingService;
import org.corodiak.booksys.service.BookingServiceImpl;
import org.corodiak.booksys.service.MenuService;
import org.corodiak.booksys.service.OrderService;
import org.corodiak.booksys.service.ReservationService;
import org.corodiak.booksys.service.TableService;
import org.corodiak.booksys.service.WalkInService;
import org.corodiak.booksys.util.FileHandleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/*
 * 관리자 권한 필요한 동작을 하는 컨트롤러
 */
@Controller
@RequestMapping(value = "/staff")
public class StaffController
{
	@Autowired
	MenuService menuService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	TableService tableService;
	
	@Autowired
	WalkInService walkInService;
	
	@Autowired
	OrderService orderService;
	
	//관리자 메인페이지, 지정날짜 예약 목록 및 현장예약 목록 출력, 주문 추가 기능 존재
	//입력되는 date가 없다면 오늘 날짜로 조회
	@RequestMapping
	public String staffindex(
			@RequestParam(value = "date", required = false)String date,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		if(date == null)
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
			date = LocalDate.now().format(dtf);
		}
		List<Reservation> reservationList = reservationService.readReservationByDate(date);
		List<WalkIn> walkInList = walkInService.readWalkInByDate(date);
		List<Menu> menuList = menuService.getMenuList();
		
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("walkInList", walkInList);
		model.addAttribute("menuList", menuList);
		
		return "staff/staffindex";
	}
	
	//메뉴 목록 추가 폼
	@RequestMapping(value = {"/addmenu"}, method = RequestMethod.GET)
	public String addMenuPage(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model)
	{
		return "staff/addMenuForm";
	}
	
	//메뉴 목록 추가 처리
	@RequestMapping(value = {"/addmenu"}, method = RequestMethod.POST)
	public String addMenuProcess(
			@RequestParam("name")String name,
			@RequestParam("price")int price,
			@RequestParam("description")String description,
			@RequestParam("image")MultipartFile image,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			) throws Exception
	{
		String filepath = FileHandleUtil.saveFile(image, request.getServletContext().getRealPath("resources/attach/"));

		if(filepath == null) return "error/error500";
		
		if(menuService.addMenu(name, price, description, filepath))
		{
			response.sendRedirect(request.getHeader("referer"));
			return "notmake";
		}
		else return "error/error400";
	}
	
	//예약에 대한 AuthPassword 입력 처리
	@RequestMapping(value = "/authpass/{reservationOid}")
	public String authReservation(
			@RequestParam("authpass")String authpass,
			@PathVariable("reservationOid")int reservationOid,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			) throws IOException
	{
		Reservation reservation = reservationService.readReservationByOid(reservationOid);
		if(reservation.getReservationAuthPassword().equals(authpass))
		{
			reservationService.updateArrivalTimeByOid(reservationOid);
		}
		response.sendRedirect(request.getHeader("referer"));
		return "notmake";
	}
	
	//현장예약, 날짜 선택으로 리다이렉트
	@RequestMapping(value = "/walkin")
	public String walkinSelectDateRedirect(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model) throws IOException
	{
		response.sendRedirect("/staff/walkin/selectdate");
		return "notmake";
	}
	
	//현장예약, 날짜 선택 폼
	@RequestMapping(value = "/walkin/selectdate")
	public String reservationSelectDate(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model)
	{
		return "staff/walkinDateForm";
	}
	
	//현장 예약, 날짜 선택 정보에 따른 테이블, 시간, 인원 선택
	@RequestMapping(value = "/walkin/selectinfo", method = RequestMethod.POST)
	public String reservationSelectInfo(
			@RequestParam("date")String date,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		int [][] bookingInfo = ((BookingServiceImpl)bookingService).bookingArrayByDate(date);
		String [] timeTable = ((BookingServiceImpl)bookingService).generateTimeTable();
		List<Table> tableList = tableService.getTableList();
		
		model.addAttribute("date", date);
		model.addAttribute("bookingInfo", bookingInfo);
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("tableList", tableList);
		
		return "staff/walkInInfoForm";
	}
	
	//현장예약 처리
	@RequestMapping(value = "/walkin/confirm", method = RequestMethod.POST)
	public String walkinCheckInfo(
			@RequestParam("date")String date,
			@RequestParam("tableOid")int tableOid,
			@RequestParam("time")String time,
			@RequestParam("covers")int covers,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			) throws ServletException, IOException
	{
		time = time + ":00";
		boolean result = walkInService.createWalkIn(covers, date, time, tableOid);
		if(!result)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/error/400");
			rd.forward(request, response);
		}
	
		
		return "staff/walkInSuccess";
	}
	
	//음식 주문 추가
	@RequestMapping(value = "/addorder")
	public String addOrder(
			@RequestParam("bookginOid")int bookingOid,
			@RequestParam("menuOid")int menuOid,
			@RequestParam("quantity")int quantity,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			) throws ServletException, IOException
	{
		boolean result = orderService.addOrder(menuOid, bookingOid, quantity);
		if(!result)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/error/400");
			rd.forward(request, response);
		}
		else
		{
			response.sendRedirect(request.getHeader("referer"));
		}
		return "notmake";
	}
	
	//지정 날짜에 따른 매출 내역 출력 및 영수증 확인 기능, 날짜 지정 없으면 오늘 날짜로 처리
	@RequestMapping(value = "/sales")
	public String sales(
			@RequestParam(value = "date", required = false)String date,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		if(date == null)
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
			date = LocalDate.now().format(dtf);
		}
		
		List<Order> orderList = orderService.readOrderByDate(date);
		Map<Integer, Integer> salePrice = new HashMap<Integer, Integer>();
		int totalSales = 0;
		for(Order order:orderList)
		{
			totalSales += order.getOrderQuantity() * order.getOrderMenu().getMenuPrice();
			int currentPrice = salePrice.get(order.getOrderBookingOid()) == null ? 0 : salePrice.get(order.getOrderBookingOid());
			salePrice.put(order.getOrderBookingOid(), currentPrice + (order.getOrderQuantity() * order.getOrderMenu().getMenuPrice()) );
		}
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("salePrice", salePrice);
		model.addAttribute("totalSales", totalSales);
		
		return "staff/staffsales";
	}
	
	//영수증 페이지
	@RequestMapping(value = "/receipt/{bookingOid}")
	public String receipt(
			@PathVariable("bookingOid")int bookingOid,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		List<Order> orderList = orderService.readOrderByBookingOid(bookingOid); 
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("bookingOid", bookingOid);
		
		return "staff/receipt";
	}
	
}
