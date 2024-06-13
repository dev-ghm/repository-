package controller;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse responese) throws ServletException, IOException {
		
		
		// View 를 처리할 JSP 로 보내고 싶다면
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, responese);
		
	}
}