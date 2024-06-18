package controller.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.EventDao;
import together.dao.ParticipantDao;
import together.dao.SportDao;
import together.vo.Event;
import together.vo.Participant;
import together.vo.Sport;
import together.vo.User;

@WebServlet("/events/*")
public class EventsViewController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("/events/*");
		// int id = Integer.parseInt(request.getParameter("id"));
		try {
//		int id = Integer.parseInt(request.getParameter("id"));
		String uri = request.getRequestURI();
		// System.out.println(uri);
		int id = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));
		// System.out.println(id);
		EventDao eventDao = new EventDao();
		SportDao sportDao = new SportDao();
		ParticipantDao participantDao = new ParticipantDao();
		Event event = eventDao.findById(id);
		request.setAttribute("event", event);
		Sport sport = sportDao.findByNumber(id);
		request.setAttribute("sport", sport);
		
		List<Participant> participants =participantDao.findByEventId(id);
		
		List<String> userIds = new ArrayList<>();
		for (Participant one : participants) {	// 반복문돌면서
			userIds.add(one.getUserid());	// 참가자 아이디만 추출해서 List에 모으고
		}
		
		// 로그인하고 있는 사용자 정보, 세션에서 얻어서
		User authUser = (User)request.getSession().getAttribute("authUser");
		
		// 추출된 참가자아이디목록에 이 로그인 유저의 아이디가 있다면..?
		// 만약 로그인이 안되있으면 authUser.getId() 할때 터질꺼임.. 왜 authUser == null
		if(authUser != null && userIds.contains(authUser.getId())) {
			request.setAttribute("flag", true);
		}else {
			request.setAttribute("flag", false);
		}
		
		String tab = request.getParameter("tab");
		if (tab == null) {
			request.setAttribute("p", participants);
			request.setAttribute("psize", participants.size());
			request.getRequestDispatcher("/WEB-INF/view/events/view-default.jsp").forward(request, response);
		} else if (tab.equals("comments")) {

			request.getRequestDispatcher("/WEB-INF/view/events/view-comments.jsp").forward(request, response);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		request.getRequestDispatcher("/WEB-INF/view/events/error.jsp").forward(request, response);
	}
	}
}

