package org.corodiak.booksys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 에러 페이지 컨트롤러
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController
{
	
	@RequestMapping(value = "/400")
	public String error400(HttpServletRequest request, HttpServletResponse response)
	{
		return "error/error400";
	}
	
	@RequestMapping(value = "/403")
	public String error403(HttpServletRequest request, HttpServletResponse response)
	{
		return "error/error403";
	}
	
	
	@RequestMapping(value = "/404")
	public String error404(HttpServletRequest request, HttpServletResponse response)
	{
		return "error/error404";
	}
	
	@RequestMapping(value = "/500")
	public String error500(HttpServletRequest request, HttpServletResponse response)
	{
		return "error/error500";
	}
}