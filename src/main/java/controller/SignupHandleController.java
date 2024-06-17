package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.UserDao;
import together.vo.User;

@WebServlet("/signup-handle")
public class SignupHandleController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		int birth= Integer.parseInt(request.getParameter("birth"));
		String gender = request.getParameter("gender");
		String interest = request.getParameter("interest");
		
		UserDao userDao = new UserDao();
		boolean result = false;
		try {
			User exist = userDao.findById(id);
			if(exist == null) {
				User one = new User(id,password, name,gender, birth, email, String.join(",", interest));
				result = userDao.save(one);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		if(result) {
			response.sendRedirect(request.getContextPath()+"/index");
		}else {
		response.sendRedirect(request.getContextPath()+"/signup");
		}

		
	}

}
