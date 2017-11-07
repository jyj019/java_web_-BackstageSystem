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
	
	//ajaxע�᣺��̬��ȡ�˺ŵĳ����Ƿ���ȷ
	public void zhuce(HttpServletRequest request, HttpServletResponse response) {		
		String value = request.getParameter("data");		
		// 3. ҵ���߼����� ����һ����� 1 �ɹ� 0 ʧ��
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
	
	/*ע�᣺�ɹ������伤�����active����
	 * 
	 * ��ȡ�û��������롢�����ʼ���
	 * �������Ϊ��huxia@huxia.com
	 * �ж��û����Ƿ���ڣ�Ҫ��ѯ�����û����ڱ����Աȣ�
	 * 			�����ڣ����ж�������ȷ���� �û���������ĳ��̶Բ���
	 * 					����ȷ������uid��password��code��ȫ��ʽ
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
					request.setAttribute("msg", "<font color='red' >ע��ɹ���ȥ���伤��</font>");
					
				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("msg", "<font color='red' >ע�����</font>");
					
				}
			}else {
				request.setAttribute("msg", "<font color='red' >���䲻��ȷ�����������˻����볤�Ȳ���</font>");
			}
		}else {
				request.setAttribute("msg","<font color='red' >�˺��Ѵ���,������ע��</font>");		
		}
		return "/jsp/zhuce.jsp";	
	}
	//�жϼ����Ƿ�ɹ����ɹ�����¼���棬���ɹ���ע��������
	public String active(HttpServletRequest request, HttpServletResponse response){
		
		String code = request.getParameter("code");
		String path=null;
		int flag = new UserServiceImpl().active(code);
		if(flag==1) {
			request.setAttribute("msg", "<font color='red' >����ɹ������¼����</font>");
			path="/jsp/Login.jsp";
			System.out.println("ative22");
		}else {
			request.setAttribute("msg", "<font color='red' >����ʧ�ܣ���</font>");
			path="/jsp/zhuce.jsp";
		}
		System.out.println("ative2");
		return path;
	}
	
	//��ȡ��֤�룬����ʾ����Ļ��
	public String yanzm(HttpServletRequest request,HttpServletResponse response) {
			ValidateCode code = new ValidateCode(80, 30, 4, 10);
			// ����������֤����ڹ���������
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
	 * ��½������֤����жϵ�¼
	 * ��ȡ��֤�룬���ж�
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
				// �û���������֤�ɹ�   �����ж��Ƿ񼤻�
				if(user.getState()==1) {
					
					request.getSession().setAttribute("user", user);
					
					return "/jsp/main.jsp";
				}else {
					request.setAttribute("msg1", "�˺�δ�������");
					return "/jsp/Login.jsp";
				}
				
			}else {
				
				request.setAttribute("msg1", "�û�����������֤���󣡣�����");
				return "/jsp/Login.jsp";
			}
		} else {
			if(username!=null) {
				request.setAttribute("msg", "��֤�����");
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
					// �û���������֤�ɹ�   �����ж��Ƿ񼤻�
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
						request.setAttribute("msg1", "�˺�δ�������");
						return "/jsp/Login.jsp";
					}
					
				}else {
					
					request.setAttribute("msg1", "�û�����������֤���󣡣�����");
					return "/jsp/Login.jsp";
				}
			} else {
				if(username!=null) {
					request.setAttribute("msg", "��֤�����");
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
				request.setAttribute("msg", "�˺��������");
				path="/jsp/Login.jsp";
			}*/		
	}
	//��ҳ
	public String  findPage(HttpServletRequest request,HttpServletResponse response) {
		//��ȡ��ǰҳ��
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// ÿһҳ��ʾ����������
		int pageSize=5; 
		PageBean<User> pageBean = new UserServiceImpl().findPage(currentPage,pageSize);
		request.getSession().setAttribute("pageBean", pageBean);	
		return "/jsp/user_list.jsp";
	}
	//����û�(add.jsp)
	public String add(HttpServletRequest request,HttpServletResponse response) {
			User user=new User();
			try {
				BeanUtils.populate(user, request.getParameterMap());
				user.setUid(UUIDUtils.getId());
				user.setCode(UUIDUtils.getId());
				user.setPassword(MD5Utils.md5(user.getPassword()));				
				int result = new UserServiceImpl().insert(user);
				if(result>0) {
					request.getSession().setAttribute("msg", "���û���ӳɹ�������");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.getSession().setAttribute("msg", "���ʧ�ܣ�����");
			} 
		return "/jsp/msg.jsp";
	}
	//�޸��û���Ϣ��ʹ��update.jsp)
	public String update(HttpServletRequest request,HttpServletResponse response) {
			User user=new User();
			try {
				BeanUtils.populate(user, request.getParameterMap());
				int result = new UserServiceImpl().update(user);
				if(result>0) {
					request.getSession().setAttribute("msg", "�޸ĳɹ�������");
				}else {
					request.getSession().setAttribute("msg", "�޸�ʧ�ܣ�����");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		return "/jsp/msg.jsp";
	}
	//ͨ��id��ѯһ������ѯ���˽����û�������������(Ϊ���޸ĵ�ʱ��鵽�û����޸�)
	public String findOne1(HttpServletRequest request,HttpServletResponse response) {
		String uid = request.getParameter("uid");
		System.out.println(uid);
		User user = new UserServiceImpl().findOne1(uid);
		String path=null;
		if(user!=null) {
			request.getSession().setAttribute("user1", user);
			path="/jsp/update.jsp";
		}else {
			request.getSession().setAttribute("msg", "û�и��û�������");
			path="/jsp/msg.jsp";
		}
	return path;
	}
	//ͨ��idɾ��һ��
	public String delOne(HttpServletRequest request,HttpServletResponse response) {
		String uid = request.getParameter("uid");
		int result = new UserServiceImpl().delById(uid);
		if(result>0) {
			request.getSession().setAttribute("msg", "ɾ���ɹ�");
		}else {
			request.getSession().setAttribute("msg", "ɾ��ʧ�ܣ�����ԭ�򣡣���");
		}
		return "/jsp/msg.jsp";
	}
	//ͨ��idȫ��ɾ��
	public String delAll(HttpServletRequest request,HttpServletResponse response) {
		String[] uids = request.getParameterValues("uid");
		int result=0;
		for (String uid : uids) {
			result= new UserServiceImpl().delById(uid);
		}
		if(result>0) {
			request.getSession().setAttribute("msg", "����ɾ���ɹ�����");
		}else {
			request.getSession().setAttribute("msg", "����ɾ��ʧ�ܣ���");
		}		
		return "/jsp/msg.jsp";
	
	}
}



