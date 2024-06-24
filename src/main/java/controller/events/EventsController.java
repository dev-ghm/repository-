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
import together.vo.User;
import together.vo.complex.EventWithDetail;



@WebServlet("/events")
public class EventsController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 파라미터 뽑을 것 있는지..? 세션 뽑을 것 있는지..?
			User authUser = (User)request.getSession().getAttribute("authUser");
			
			SportDao sportDao = new SportDao();
			EventDao eventDao = new EventDao();
			ParticipantDao participantDao = new ParticipantDao();
			
			List<Event> list = eventDao.findAll();
			
			List<EventWithDetail> detailList = new ArrayList<>();
			for(Event e : list) {
				EventWithDetail one = new EventWithDetail();
				one.setEvent(e);
				one.setSport(sportDao.findByNumber(e.getSportid() ));
				if(authUser == null) {
				one.setJoined(false);
				}else {
				List<Participant> participant = participantDao.findByEventId(e.getId());	
				if(Math.random() > 0.5) {
					one.setJoined(true);
				}else {
					one.setJoined(false);
				}
				}
				
				detailList.add(one);
			}
			request.setAttribute("events", detailList);
			request.setAttribute("tagCounts", eventDao.countGroupByTag());
			
			request.getRequestDispatcher("/WEB-INF/view/events/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
