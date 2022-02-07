package org.corodiak.booksys.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.booksys.util.JWTUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 컨트롤러 진입 전, 로그인 정보를 미리 request에 처리하여 넣어주는 인터셉터
 */
public class LoginInfoInterceptor extends HandlerInterceptorAdapter
{
	/*
	 * jwt토큰 기반으로 로그인 정보 불러와서 request에 미리 삽입 처리
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		//System.out.println("InfoPreHandler");
		Cookie [] cookie = request.getCookies();
		if(cookie == null)
		{
			return true;
		}
		String jwt = null;
		
		for(Cookie c:cookie)
		{
			if(c.getName().equals("booksysSid"))
			{
				jwt = c.getValue();
				break;
			}
		}
		
		Map<String, Object> claims = JWTUtil.validateToken(jwt);
		
		if(claims == null)
		{
			return true;
		}
		else
		{
			request.setAttribute("InterceptorCustomerOid", claims.get("customerOid"));
			request.setAttribute("InterceptorCustomerIsAdmin", claims.get("customerIsAdmin"));
			return true;
		}
	}
	/*
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception
	{
		//System.out.println("InfoPostHandler");
		//System.out.println(request.getAttribute("InterceptorCustomerOid"));
		//System.out.println(request.getAttribute("InterceptorCustomerIsAdmin"));
		mav.addObject("customerOid", request.getAttribute("InterceptorCustomerOid"));
		mav.addObject("customerIsAdmin", request.getAttribute("InterceptorCustomerIsAdmin"));
	}
	*/
}
