package controller.feeds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.SportDao;
import together.vo.User;

@WebServlet("/feeds/write")
public class writeController extends HttpServlet {
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		List<User> user = new ArrayList<>();
		request.setAttribute("user", user);
		User authUser = (User) request.getSession().getAttribute("authUser");
		SportDao sportDao = new SportDao();
		List<String> types = sportDao.findDistinctType();
		System.out.println(types.size());
		request.setAttribute("types", types);
		if(authUser != null) {
			request.getRequestDispatcher("/WEB-INF/view/feeds/write.jsp").forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+ "/login");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
