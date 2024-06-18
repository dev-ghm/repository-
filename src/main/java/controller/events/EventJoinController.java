package controller.events;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import together.dao.EventDao;
import together.dao.ParticipantDao;
import together.vo.Event;
import together.vo.Participant;
import together.vo.User;

@WebServlet("/events/join/*")
public class EventJoinController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// path 로 eventId 를 받는 다는 조건하에
		EventDao eventDao = new EventDao();
		ParticipantDao participantDao = new ParticipantDao();
		String[] items = request.getRequestURI().split("/");
		int eventId = Integer.parseInt(items[items.length-1]);
		// 파라미터로 받게되면
		// int eventId = Integer.parseInt(request.getParameter("eventId") );
		
		User authUser = (User)request.getSession().getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/login?url=/events/"+eventId);
			return;
		}
		String authUserId = authUser.getId();
		Date joinAt = new Date(System.currentTimeMillis());
		
		List<Participant> participants = participantDao.findByEventId(eventId);
		List<String> userIds = new ArrayList<>();
		for(Participant one : participants) {
			userIds.add(one.getUserid());
		}
		Event event = eventDao.findById(eventId);
		
		if(!userIds.contains(authUserId) && event.getAttendee() < event.getCapacity()) {
		
		Participant p = new Participant(-1, authUserId, eventId, joinAt);
		
		participantDao.save(p);
		
		eventDao.increaseAttendeeById(eventId);
		}
		response.sendRedirect(request.getContextPath()+"/events/"+eventId);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}