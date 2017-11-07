package com.jyj019.web.filter;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyj019.beans.User;
import com.jyj019.service.UserService;
import com.jyj019.service.impl.UserServiceImpl;
import com.jyj019.utils.MD5Utils;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2=(HttpServletRequest) request;
		HttpServletResponse response2=(HttpServletResponse) response;	
		Cookie[] cookies=request2.getCookies();
		String username=null;
		String password=null;	
		UserService uService=new UserServiceImpl();
		User user=null;
		int flag=0;
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				String name=cookie.getName();
				if ("user2".equals(name)) {
					String value=cookie.getValue();
					String[] split=value.split("&");
					username=split[0];
					password=split[1];
					
					System.out.println(password);
					user=uService.findOne(username, password);
					if (user!=null) {
						flag=1;
					}
				} 
			}
		} 
		System.out.println("11111111111111111111");
		if (flag==1) {
			request2.getSession().setAttribute("user", user);
			response2.sendRedirect(request2.getContextPath()+"/jsp/main.jsp");
		} else {
			chain.doFilter(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
