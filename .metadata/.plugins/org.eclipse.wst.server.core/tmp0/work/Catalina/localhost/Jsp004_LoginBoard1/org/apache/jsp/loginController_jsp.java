/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.46
 * Generated at: 2022-02-07 09:16:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import com.login.biz.MYMemberBiz;
import com.login.dto.MYMemberDto;

public final class loginController_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.io.PrintWriter");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.login.biz.MYMemberBiz");
    _jspx_imports_classes.add("com.login.dto.MYMemberDto");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 request.setCharacterEncoding("UTF-8"); 
      out.write('\r');
      out.write('\n');
 response.setContentType("text/html; charset=UTF-8"); 
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");

	//1. 보내준 값이 있으면 받는다.
	//2. db에 전달할 값이 있으면 전달하고,없으면 없는대로 호출해서 리턴받는다.
	//3. 화면에 전달할 값이 있으면, request 객체에 담아준다.
	//4. 보낸다.
	String command = request.getParameter("command");
	System.out.println("["+command+"]");
	MYMemberBiz biz = new MYMemberBiz();
	if (command.equals("login")){
		
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		
		
		MYMemberDto dto = biz.login(myid, mypw);
		if (dto != null){
			//세션에 로그인정보 담아주자 
			session.setAttribute("dto", dto);
			session.setMaxInactiveInterval(10*60);
			
			if (dto.getMyrole().equals("ADMIN")){	//등급이 관리자일땐 관리자 페이지로
				response.sendRedirect("adminMain.jsp");
			}else {	//등급이 유저일땐 유저 페이지로
				request.setAttribute("myid", myid);//잘담겼다~~
				pageContext.forward("userMain.jsp");
			}
		}else {
			System.out.println("로그인 실패");
			
		}
		
	}else if (command.equals("logout")){
		session.invalidate();
		response.sendRedirect("index.html");
	}else if (command.equals("userSelectAll")){
		//유저ㅓ 전부다 select 
		List<MYMemberDto> list = (List<MYMemberDto>)biz.selectUserListAll();
	
		
		request.setAttribute("user", list);
		
		pageContext.forward("userListAll.jsp");
	}else if(command.equals("userSelectEn")){
		List<MYMemberDto> list =biz.selectUserListEn();
		request.setAttribute("list", list);
		pageContext.forward("userListEn.jsp");
	}else if (command.equals("updateRole")){
		//유저전부 select해서 role update 가능하도록 ? 
		//받아야할건 그냥 ....
		//페이지 이동해서 화면에 일단 개인 정보 한줄로뿌려준다음
		int myno = Integer.parseInt(request.getParameter("myno"));
		MYMemberDto dto = biz.selectUser(myno);
		request.setAttribute("dto", dto);
		pageContext.forward("updateRole.jsp");
	}else if (command.equals("updateform")){
		int myno =Integer.parseInt(request.getParameter("myno"));
		String role = request.getParameter("role");
		
		MYMemberDto dto = new MYMemberDto();
		dto.setMyno(myno);
		dto.setMyrole(role);
		System.out.println(dto.getMyno()+dto.getMyrole()+"컨트롤러에서 받은거");
		int res = biz.updateUserRole(myno, role);
		System.out.println(res+"res");
		if (res>0){//성공

      out.write("\r\n");
      out.write("	<script type=\"text/javascript\">\r\n");
      out.write("	alert (\"등급변경 성공\");\r\n");
      out.write("	location.href = \"loginController.jsp?command=userSelectEn\";\r\n");
      out.write("	</script>\r\n");
			
		}else{	//실패

      out.write("\r\n");
      out.write("	<script type=\"text/javascript\">\r\n");
      out.write("	alert (\"등급변경 실패\");\r\n");
      out.write("	location.href = \"loginController.jsp?command=userSelectEn\";\r\n");
      out.write("	</script>\r\n");
		
			
		}
		
	}else if (command.equals("selectMypage")){
		
		String myid = request.getParameter("myid");
		MYMemberDto dto = biz.selectUser2(myid);
		request.setAttribute("dto", dto);
		pageContext.forward("mypage.jsp");
		
	}else if (command.equals("idDrop")){
		int myno =Integer.parseInt(request.getParameter("myno"));
		int res =biz.idDrop(myno);
		if (res>0){	//성공
			System.out.println("성공");
			response.sendRedirect("index.html");
		}else{//실패
			System.out.println("실패");
			response.sendRedirect("index.html");
		}
				
	}else if (command.equals("mypageUpdate")){
		//여긴그냥 정보만 받아서 화면에 selectMypage 뿌려주고 input박스에 담아주자 
		
		String myid = request.getParameter("myid");
		MYMemberDto dto = biz.selectUser2(myid);
		request.setAttribute("dto", dto);
		pageContext.forward("mypageUpdate.jsp");
		
	}else if (command.equals("mypageUpdateForm")){
	
		String mypw =request.getParameter("mypw");
		String myaddr =request.getParameter("myaddr");
		String myphone =request.getParameter("myphone");
		String myemail =request.getParameter("myemail");
		int myno =Integer.parseInt(request.getParameter("myno"));
		
		MYMemberDto dto = new MYMemberDto();
		
		dto.setMypw(mypw);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		dto.setMyno(myno);
		
		int res=biz.mypageUpdate(mypw, myaddr, myphone, myemail, myno);
		request.setAttribute("myno", myno);
		if (res>0){	//성공
			
			pageContext.forward("loginController.jsp?command=selectMypage2");
		}else{//실패
			
			pageContext.forward("loginController.jsp?command=selectMypage2");
		}
		
	}else if (command.equals("selectMypage2")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		MYMemberDto dto = biz.selectUser(myno);
		request.setAttribute("dto", dto);
		pageContext.forward("mypage.jsp");
	}else if(command.equals("regist")){
		
		response.sendRedirect("regist.jsp");
		
	}else if (command.equals("idchk")){
		String myid = request.getParameter("myid");
		//다오만들고 호출 
		MYMemberDto dto = biz.idchk();
		//
		System.out.println("컨트롤러로 리턴된 dao" +dto.getMyid());
		boolean idnotused=true;
		if (myid != null){	//얘 myid 말고 그걸로 바꿔야하는거아님 ??? 디티오안에 있는게 없으면 ?? 뭐지 얘이상함 흐름 다시보자 
			idnotused=false;
		}
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
	}else if(command.equals("registForm")){
		//여기서 input내용 받고 다오 호출해서 성공 실패 조회 하고 로그인 화면으로 가서 로그인 할수있도록
		String myid =request.getParameter("myid");
		String mypw =request.getParameter("mypw");
		String myname=request.getParameter("myname");
		String myaddr=request.getParameter("myaddr");
		String myphone=request.getParameter("myphone");
		String myemail=request.getParameter("myemail");
		
		MYMemberDto dto = new MYMemberDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res= biz.regist(dto);
		if (res>0){//성공
			response.sendRedirect("index.html");
		}else{//실패
			response.sendRedirect("index.html");
		}
	}
	

      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
