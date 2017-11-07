package com.jyj019.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class FileUpServlet
 */
@WebServlet("/servlet/FileUpServlet")
public class FileUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean mul = ServletFileUpload.isMultipartContent(request);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list = null;
		try {
			list = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		for (FileItem fileItem : list) {
			if (fileItem.isFormField()) {
				String fieldName = fileItem.getFieldName();
				String value = fileItem.getString();
				System.out.println(fieldName +value);	
			} else {
				String filename = fileItem.getName();
				request.getSession().setAttribute("img", filename);
				InputStream Stream = fileItem.getInputStream();
				String path = getServletContext().getRealPath("/image/");
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File file = new File(dir, filename);
				try {
					FileOutputStream oStream = new FileOutputStream(file);
					byte[] buff = new byte[8*1024];
					int len=0;
					while((len=Stream.read(buff))!=-1) {
						oStream.write(buff, 0, len);
					}
					Stream.close();
					oStream.close();
					request.setAttribute("msg","<font color='red' >上传成功</font>");
					response.setHeader("refresh", "0;url=/work1/jsp/myinfo.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
