package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.core.ApplicationContext;

/**
 * Servlet implementation class calc
 */
@WebServlet("/calc2")
public class calc2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 지금이 클래스 자체가 에러남

		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");

		int v = 0;
		if (!v_.equals("")) {
			v = Integer.parseInt(v_);
		}

		// 계산
		if (op.equals("=")) {
			// int x= (Integer)application.getAttribute("value");
			// int x= (Integer)session.getAttribute("value");
			int x = 0;
			for (Cookie c:cookies) {
				if (c.getName().equals("value")) {
					x=Integer.parseInt(c.getValue());
					break;
				}
			}
			

			int y = v;
			String operator="";
			// String operator = (String)application.getAttribute("op");
			//String operator = (String) session.getAttribute("op");
			for (Cookie c:cookies) {
				if (c.getName().equals("op")) {
					operator=c.getValue();
					break;
				}
			}
			int result = 0;
			if (operator.equals("+")) {
				result = x + y;
				response.getWriter().printf("result 는 %d", result);
			} else {
				result = x - y;
				response.getWriter().printf("result 는 %d", result);
			}

			// 저장
		} else {
			// application.setAttribute("value", v);
			// application.setAttribute("op", op);

			// session.setAttribute("value", v);
			// session.setAttribute("op", op);

			Cookie valueCookie = new Cookie("value",String.valueOf(v));
			Cookie opCookie = new Cookie("op",op);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(24*60*60);
			opCookie.setPath("/calc2");
			response .addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");
		}

	}

}
