package com.jyj019.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import com.jyj019.beans.PageBean;
import com.jyj019.beans.User;
import com.jyj019.service.UserService;
import com.jyj019.service.impl.UserServiceImpl;
import com.jyj019.utils.MD5Utils;
import com.jyj019.utils.UUIDUtils;

import cn.dsna.util.images.ValidateCode;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/servlet/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//ajax注册：动态获取账号的长度是否正确
	public void zhuce(HttpServletRequest request, HttpServletResponse response) {		
		String value = request.getParameter("data");		
		// 3. 业务逻辑处理 创建一个标记 1 成功 0 失败
		String flag="";		
		if(value.length()>5 && value.length()<15) {
			flag="1";
		}else {
			flag="0";
		}				
		try {
			response.getWriter().write(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*注册：成功到邮箱激活，进入active方法
	 * 
	 * 获取用户名、密码、电子邮件；
	 * 邮箱必须为：huxia@huxia.com
	 * 判断用户名是否存在（要查询所有用户，在遍历对比）
	 * 			不存在，在判断邮箱正确不和 用户名，密码的长短对不对
	 * 					都正确就设置uid，password，code安全格式
	 **/
	public String register(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String email1="huxia@huxia.com";
		List<User> list=new ArrayList<>();
		UserService uService=new UserServiceImpl();
		list=uService.findAll();
		int num=0;
		for (User user : list) {
			if (user.getUsername().equals(username)) {
				num=1;
			}
		}
		if (num==0) {
			if (email.equals(email1)&&username.length()>5&&password.length()>5&&username.length()<16&&password.length()<16) {
				User user =new User();
				try {
					BeanUtils.populate(user,request.getParameterMap());

					user.setUid(UUIDUtils.getId());
					user.setPassword(MD5Utils.md5(user.getPassword()));
					user.setCode(UUIDUtils.getId());
					new UserServiceImpl().regist(user);
					request.setAttribute("msg", "<font color='red' >注册成功，去邮箱激活</font>");
					
				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("msg", "<font color='red' >注册出错</font>");
					
				}
			}else {
				request.setAttribute("msg", "<font color='red' >邮箱不正确或者密码与账户密码长度不对</font>");
			}
		}else {
				request.setAttribute("msg","<font color='red' >账号已存在,请重新注册</font>");		
		}
		return "/jsp/zhuce.jsp";	
	}
	//判断激活是否成功；成功到登录界面，不成功在注册界面继续
	public String active(HttpServletRequest request, HttpServletResponse response){
		
		String code = request.getParameter("code");
		String path=null;
		int flag = new UserServiceImpl().active(code);
		if(flag==1) {
			request.setAttribute("msg", "<font color='red' >激活成功！请登录！！</font>");
			path="/jsp/Login.jsp";
			System.out.println("ative22");
		}else {
			request.setAttribute("msg", "<font color='red' >激活失败！！</font>");
			path="/jsp/zhuce.jsp";
		}
		System.out.println("ative2");
		return path;
	}
	
	//获取验证码，并显示在屏幕上
	public String yanzm(HttpServletRequest request,HttpServletResponse response) {
			ValidateCode code = new ValidateCode(80, 30, 4, 10);
			// 将产生的验证码放在共享域里面
			String code1 = code.getCode();
			System.out.println(code1);
			request.getSession().setAttribute("code1", code1);
			System.out.println(code1);
			try {
				code.write(response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "/jsp/Login.jsp";
		}
	/*
	 * 登陆：有验证码的判断登录
	 * 获取验证码，先判断
	 * */
	
	/*public String  login1(HttpServletRequest request,HttpServletResponse response) {
		
		String username=request.getParameter("username");
		String password1=request.getParameter("password");
		String yzm = request.getParameter("yzm");
		//String auto=request.getParameter("auto");
		//String checked = request.getParameter("checked");
		String password=MD5Utils.md5(password1);
		System.out.println(yzm);
		
		String code1 = request.getSession().getAttribute("code1") + "";
		System.out.println(code1);
		UserService uService=new UserServiceImpl();
		
		User user=uService.findOne(username, password);
		
		if (yzm.equalsIgnoreCase(code1)) {
			if(user!=null) {
				// 用户名密码验证成功   还得判断是否激活
				if(user.getState()==1) {
					
					request.getSession().setAttribute("user", user);
					
					return "/jsp/main.jsp";
				}else {
					request.setAttribute("msg1", "账号未激活！！！");
					return "/jsp/Login.jsp";
				}
				
			}else {
				
				request.setAttribute("msg1", "用户名或密码验证错误！！！！");
				return "/jsp/Login.jsp";
			}
		} else {
			if(username!=null) {
				request.setAttribute("msg", "验证码错误");
			}
			return "/jsp/Login.jsp";
		}
		
		
	} */
	
	public String  login2(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
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
		if (flag==1) {
			for (Cookie cookie : cookies) {
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			request.getSession().setAttribute("user", user);
			try {
				response.sendRedirect(request.getContextPath()+"/jsp/main.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return "/jsp/Login.jsp";
	}
public String login(HttpServletRequest request,HttpServletResponse response) {
		
			String username=null;
			String password=null;
			
			UserService uService=new UserServiceImpl();
			User user=null;
			String code1 = request.getSession().getAttribute("code1") + "";
			username=request.getParameter("username");
			String password1=request.getParameter("password");
			String yzm = request.getParameter("yzm");	
			String auto=request.getParameter("auto");		
			password=MD5Utils.md5(password1);
			user=uService.findOne(username, password);
			if (yzm.equalsIgnoreCase(code1)) {
				if(user!=null) {
					// 用户名密码验证成功   还得判断是否激活
					if(user.getState()==1) {
						
						Cookie cookie=new Cookie("user2", username+"&"+password);
						
						cookie.setPath(request.getContextPath());
					
						if (auto!=null) {
							cookie.setMaxAge(60*60);
						} else {
							cookie.setMaxAge(0);
						}
					
						response.addCookie(cookie);
						request.getSession().setAttribute("user", user);		
						return "/jsp/main.jsp";
					}else {
						request.setAttribute("msg1", "账号未激活！！！");
						return "/jsp/Login.jsp";
					}
					
				}else {
					
					request.setAttribute("msg1", "用户名或密码验证错误！！！！");
					return "/jsp/Login.jsp";
				}
			} else {
				if(username!=null) {
					request.setAttribute("msg", "验证码错误");
				}
				return "/jsp/Login.jsp";
			}
			/*if (user!=null) {
				Cookie cookie=new Cookie("user2", username+"&"+password);
			
				cookie.setPath(request.getContextPath());
			
				if (auto!=null) {
					cookie.setMaxAge(60*60);
				} else {
					cookie.setMaxAge(0);
				}
			
				response.addCookie(cookie);
				request.getSession().setAttribute("user", user);
			
				path="/jsp/main.jsp";
			} else {
				request.setAttribute("msg", "账号密码错误");
				path="/jsp/Login.jsp";
			}*/		
	}
	//分页
	public String  findPage(HttpServletRequest request,HttpServletResponse response) {
		//获取当前页数
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 每一页显示多少条数据
		int pageSize=5; 
		PageBean<User> pageBean = new UserServiceImpl().findPage(currentPage,pageSize);
		request.getSession().setAttribute("pageBean", pageBean);	
		return "/jsp/user_list.jsp";
	}
	//添加用户(add.jsp)
	public String add(HttpServletRequest request,HttpServletResponse response) {
			User user=new User();
			try {
				BeanUtils.populate(user, request.getParameterMap());
				user.setUid(UUIDUtils.getId());
				user.setCode(UUIDUtils.getId());
				user.setPassword(MD5Utils.md5(user.getPassword()));				
				int result = new UserServiceImpl().insert(user);
				if(result>0) {
					request.getSession().setAttribute("msg", "新用户添加成功！！！");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.getSession().setAttribute("msg", "添加失败！！！");
			} 
		return "/jsp/msg.jsp";
	}
	//修改用户信息（使用update.jsp)
	public String update(HttpServletRequest request,HttpServletResponse response) {
			User user=new User();
			try {
				BeanUtils.populate(user, request.getParameterMap());
				int result = new UserServiceImpl().update(user);
				if(result>0) {
					request.getSession().setAttribute("msg", "修改成功！！！");
				}else {
					request.getSession().setAttribute("msg", "修改失败！！！");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		return "/jsp/msg.jsp";
	}
	//通过id查询一个：查询到了将该用户共享到共享域中(为了修改的时候查到用户好修改)
	public String findOne1(HttpServletRequest request,HttpServletResponse response) {
		String uid = request.getParameter("uid");
		System.out.println(uid);
		User user = new UserServiceImpl().findOne1(uid);
		String path=null;
		if(user!=null) {
			request.getSession().setAttribute("user1", user);
			path="/jsp/update.jsp";
		}else {
			request.getSession().setAttribute("msg", "没有该用户！！！");
			path="/jsp/msg.jsp";
		}
	return path;
	}
	//通过id删除一个
	public String delOne(HttpServletRequest request,HttpServletResponse response) {
		String uid = request.getParameter("uid");
		int result = new UserServiceImpl().delById(uid);
		if(result>0) {
			request.getSession().setAttribute("msg", "删除成功");
		}else {
			request.getSession().setAttribute("msg", "删除失败，请找原因！！！");
		}
		return "/jsp/msg.jsp";
	}
	//通过id全部删除
	public String delAll(HttpServletRequest request,HttpServletResponse response) {
		String[] uids = request.getParameterValues("uid");
		int result=0;
		for (String uid : uids) {
			result= new UserServiceImpl().delById(uid);
		}
		if(result>0) {
			request.getSession().setAttribute("msg", "批量删除成功！！");
		}else {
			request.getSession().setAttribute("msg", "批量删除失败！！");
		}		
		return "/jsp/msg.jsp";
	
	}
}



