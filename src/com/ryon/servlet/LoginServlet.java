package com.ryon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ryon.db.MemberDAO;
import com.ryon.dto.MemberDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/lg_ok")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("chkid");
		String pw = request.getParameter("chkpw");
		// 만약 db에 있는 아이디고, pw가 맞다면
		// SELECT * FROM member where id=chkid;
		// 로그인 성공
		// 아니면 실패
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		// db에 id pw있는지 물어봄
		MemberDTO dto = MemberDAO.select(id, pw);
		if(dto == null) {
			response.getWriter().append("아이디가 없거나 패스워드 틀림");
		} else {
			response.getWriter().append("로그인 성공");
			session.setAttribute("name", dto.getName());
			response.sendRedirect("main.jsp");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
