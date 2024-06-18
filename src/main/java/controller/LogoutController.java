package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.UserDao;
import together.vo.User;


public class LogoutController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id = request.getParameter("id");
	UserDao userDao = new UserDao();
	User user = userDao.deleteByUserId(id);
	request.getSession().setAttribute("auth", false);
	request.getSession().removeAttribute("authuserid");
//	session.invalidate();	// 이 사용자의 세션을 강제 종료
	
	response.sendRedirect(request.getContextPath()+"/index.jsp");

	}
}
