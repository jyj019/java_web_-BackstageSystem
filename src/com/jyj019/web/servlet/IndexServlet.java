package com.qianfeng.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/servlet/IndexServlet")
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public String  getData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String path=new UserServlet().login2(request, response);
			
			return path;	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
