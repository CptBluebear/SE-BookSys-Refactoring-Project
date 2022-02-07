package org.corodiak.booksys.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.booksys.util.JWTUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 로그인 여부 체크 인터셉터
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter
{
	/*
	 * 컨트롤러 진입 전, 로그인 여부 체크
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		//System.out.println("CheckPreHandler");
		Cookie [] cookie = request.getCookies();
		if(cookie == null)
		{
			request.getRequestDispatcher(request.getContextPath() + "/error/403").forward(request, response);
			//response.setStatus(403);
			return false;
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
			request.getRequestDispatcher(request.getContextPath() + "/error/403").forward(request, response);
			return false;
		}
		else
		{
			//request.setAttribute("InterceptorCustomerOid", claims.get("customerOid"));
			//request.setAttribute("InterceptorCustomerIsAdmin", claims.get("customerIsAdmin"));
			return true;
		}
	}
	
	/*
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	{
		
	}
	*/
}
