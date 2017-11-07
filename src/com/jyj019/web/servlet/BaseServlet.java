package com.jyj019.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/servlet/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	Class<? extends BaseServlet> clazz = this.getClass();
	String meth = req.getParameter("method");
	
	if (meth==null) {
		meth="index";
	}
	Method method;
	try {
		method = clazz.getMethod(meth, HttpServletRequest.class,HttpServletResponse.class);
		String path=(String) method.invoke(this, req,resp);
		if (path!=null) {
			req.getRequestDispatcher(path).forward(req, resp);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
}
  public String  index(HttpServletRequest req, HttpServletResponse resp) {
	
	  
	  return "/index.jsp";
		
		
	}
  

}
