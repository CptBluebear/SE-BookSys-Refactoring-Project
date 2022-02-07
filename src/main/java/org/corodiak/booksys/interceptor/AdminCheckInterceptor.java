package org.corodiak.booksys.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.booksys.util.JWTUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 관리자 여부 체크 인터셉터
 */
public class AdminCheckInterceptor extends HandlerInterceptorAdapter
{
	/*
	 * 컨트롤러 진입 전, 관리자 여부인지 체크함
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		Cookie [] cookie = request.getCookies();
		if(cookie == null)
		{
			request.getRequestDispatcher(request.getContextPath() + "/error/403").forward(request, response);
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
			if((boolean)claims.get("customerIsAdmin"))
			{
				return true;
			}
			else
			{
				request.getRequestDispatcher(request.getContextPath() + "/error/403").forward(request, response);
				return false;
			}
		}
	}
}
