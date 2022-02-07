package org.corodiak.booksys.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.booksys.util.JWTUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * ������ ���� üũ ���ͼ���
 */
public class AdminCheckInterceptor extends HandlerInterceptorAdapter
{
	/*
	 * ��Ʈ�ѷ� ���� ��, ������ �������� üũ��
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
