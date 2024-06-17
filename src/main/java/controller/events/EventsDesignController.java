package controller.events;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.SportDao;

@WebServlet("/events/design")
public class EventsDesignController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			SportDao sportDao = new SportDao();
			List<String> types = sportDao.findDistinctType();
			System.out.println(types.size());
			request.setAttribute("types", types);
			request.getRequestDispatcher("/WEB-INF/view/events/design.jsp").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

}
