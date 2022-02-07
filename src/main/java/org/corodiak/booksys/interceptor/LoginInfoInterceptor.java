package org.corodiak.booksys.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.booksys.util.JWTUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * ��Ʈ�ѷ� ���� ��, �α��� ������ �̸� request�� ó���Ͽ� �־��ִ� ���ͼ���
 */
public class LoginInfoInterceptor extends HandlerInterceptorAdapter
{
	/*
	 * jwt��ū ������� �α��� ���� �ҷ��ͼ� request�� �̸� ���� ó��
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
