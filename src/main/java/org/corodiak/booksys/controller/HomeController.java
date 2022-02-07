package org.corodiak.booksys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.booksys.domain.Menu;
import org.corodiak.booksys.domain.Reservation;
import org.corodiak.booksys.domain.Table;
import org.corodiak.booksys.domain.Waiting;
import org.corodiak.booksys.service.BookingService;
import org.corodiak.booksys.service.BookingServiceImpl;
import org.corodiak.booksys.service.CustomerService;
import org.corodiak.booksys.service.MenuService;
import org.corodiak.booksys.service.ReservationService;
import org.corodiak.booksys.service.TableService;
import org.corodiak.booksys.service.WaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/*
 * 관리자 권한의 요청을 제외한 컨트롤러
 */
@Controller
public class HomeController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	BookingService bookginService;
	
	@Autowired
	TableService tableService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	WaitingService waitingService;
	
	// index 페이지, 메인페이지
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model)
	{
		return "index";
	}
	
	//로그인 폼
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model)
	{
		return "loginForm";
	}
	
	//로그인 처리
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public String loginProcess(
			@RequestParam("id")String id,
			@RequestParam("password")String password,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		String jwt = null;
		try {
			jwt = customerService.login(id, password);
			if(jwt == null)
			{
				response.sendRedirect(request.getHeader("referer"));
				return "notmake";
			}
			else
			{
				Cookie jwtCookie = new Cookie("booksysSid", jwt);
				response.addCookie(jwtCookie);
				return "simple/loginSuccess";
			}
		} catch(Exception e)
		{
			return "error/error403";
		}
	}
	
	//회원가입 폼
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String registerPage(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model)
	{
		return "registerForm";
	}
	
	//회원가입 처리
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public String registerProcess(
			@RequestParam("id")String id,
			@RequestParam("password")String password,
			@RequestParam("name")String name,
			@RequestParam("phoneNumber")String phoneNumber,
			@RequestParam("birthday")String birthday,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			) throws Exception
	{
		if(customerService.register(id, password, name, phoneNumber, birthday))
		{
			//System.out.println("Success");
			//response.setStatus(200);
			//response.sendRedirect("/home");
			
			return "registerSuccess";
		}
		else
		{
			//System.out.println("Fail");
			return "notmake";
		}
	}
	
	//테스트 페이지
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model)
	{	
		waitingService.checkWaiting();
		return "notmake";
	}
	
	//메뉴 리스트
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String showMenuList(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model)
	{
		List<Menu> menuList = menuService.getMenuList();
		
		model.addAttribute("menuList", menuList);
		
		return "menuList";
	}
	
	//예약 페이지, 날짜선택으로 자동 리다이렉트
	@RequestMapping(value = "/reservation")
	public String reservationSelectDateRedirect(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model) throws Exception
	{
		response.sendRedirect("/reservation/selectdate");
		return "notmake";
	}
	
	//예약 날짜 선택 페이지
	@RequestMapping(value = "/reservation/selectdate")
	public String reservationSelectDate(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model)
	{
		return "reservationDateForm";
	}
	
	//예약 날짜선택에 따른 테이블, 시간, 인원 선택 페이지
	@RequestMapping(value = "/reservation/selectinfo", method = RequestMethod.POST)
	public String reservationSelectInfo(
			@RequestParam("date")String date,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		int [][] bookingInfo = ((BookingServiceImpl)bookginService).bookingArrayByDate(date);
		String [] timeTable = ((BookingServiceImpl)bookginService).generateTimeTable();
		List<Table> tableList = tableService.getTableList();
		
		model.addAttribute("date", date);
		model.addAttribute("bookingInfo", bookingInfo);
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("tableList", tableList);
		
		return "reservationInfoForm";
	}
	
	
	//예약 최종 확인 페이지
	@RequestMapping(value = "/reservation/checkinfo", method = RequestMethod.POST)
	public String reservationCheckInfo(
			@RequestParam("date")String date,
			@RequestParam("tableOid")int tableOid,
			@RequestParam("time")String time,
			@RequestParam("covers")int covers,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		List<Table> tableList = tableService.getTableList();
		for(Table table:tableList)
		{
			if(table.getTableOid() == tableOid)
				model.addAttribute("tableNumber", table.getTableNumber());
		}
		
		model.addAttribute("date", date);
		model.addAttribute("tableOid", tableOid);
		model.addAttribute("time", time);
		model.addAttribute("covers", covers);
		
		return "reservationCheckForm";
	}
	
	//예약 처리 후 마이페이지로 리다이렉트
	@RequestMapping(value = "/reservation/confirm", method = RequestMethod.POST)
	public String reservationConfirm(
			@RequestParam("date")String date,
			@RequestParam("tableOid")int tableOid,
			@RequestParam("time")String time,
			@RequestParam("covers")int covers,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			) throws ServletException, IOException
	{	
		time = time + ":00";
		int customerOid = (int)request.getAttribute("InterceptorCustomerOid");
		
		boolean result = reservationService.createReservation(covers, date, time, customerOid, tableOid);
		if(!result)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/error/400");
			rd.forward(request, response);
		}
		
		return "reservationSuccess";
	}
	
	//마이페이지
	@RequestMapping(value = "/mypage")
	public String mypage(
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		int customerOid = (int)request.getAttribute("InterceptorCustomerOid");
		
		List<Reservation> reservationList = reservationService.readReservationByCustomerOid(customerOid);
		List<Waiting> waitingList = waitingService.readWaitingByCustomerOid(customerOid);
		
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("waitingList", waitingList);
		
		return "mypage";
	}
	
	//예약 대기열 페이지
	@RequestMapping(value = "/reservation/waitinginfo", method = RequestMethod.POST)
	public String waitingCheckInfo(
			@RequestParam("date")String date,
			@RequestParam("tableOid")int tableOid,
			@RequestParam("time")String time,
			@RequestParam("covers")int covers,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		model.addAttribute("date", date);
		model.addAttribute("time", time);
		model.addAttribute("covers", covers);
		
		return "waitingCheckForm";
	}
	
	//예약 대기 처리 후 메인페이지로 리다이렉트
	@RequestMapping(value = "/reservation/waitingconfirm", method = RequestMethod.POST)
	public String waitingConfirm(
			@RequestParam("date")String date,
			@RequestParam("time")String time,
			@RequestParam("covers")int covers,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			) throws ServletException, IOException
	{
		
		time = time + ":00";
		int customerOid = (int)request.getAttribute("InterceptorCustomerOid");
		
		boolean result = waitingService.createWaiting(covers, date, time, customerOid);
		
		if(!result)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/error/400");
			rd.forward(request, response);
		}
		
		waitingService.checkWaiting();
		
		return "waitingSuccess";
	}
	
	//예약 취소
	@RequestMapping(value = "/reservation/reservationcancel/{reservationOid}")
	public String calcelReservation(
			@PathVariable("reservationOid")int reservationOid,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		reservationService.removeReservation(reservationOid);
		waitingService.checkWaiting();
		return "reservationRemoveSuccess";
	}
	
	//예약 대기 취소
	@RequestMapping(value = "/reservation/waitingcancel/{waitingOid}")
	public String calcelWaiting(
			@PathVariable("waitingOid")int waitingOid,
			HttpServletRequest request, HttpServletResponse response, Locale locale, Model model
			)
	{
		waitingService.removeWaiting(waitingOid);
		waitingService.checkWaiting();
		return "reservationRemoveSuccess";
	}
}