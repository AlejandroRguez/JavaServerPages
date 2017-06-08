package uo.sdi.acciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;

public class VerSolicitudesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado = "EXITO";
		Long id =Long.parseLong(request.getParameter("id"));
		List <Seat> seats = PersistenceFactory.newSeatDao().findAll();
		List<Application> apps = PersistenceFactory.newApplicationDao().findAll();
		UserDao dao = PersistenceFactory.newUserDao();
		Map<User, Seat> solicitudes = new HashMap <User, Seat>();
		Map<User, Application> applications = new HashMap <User, Application>();
		for(Seat s: seats){
			if (s.getTripId().equals(id)){
				User user =dao .findById(s.getUserId());
				solicitudes.put(user, s);
			}
		}
		
		for(Application a : apps){
			if(a.getTripId().equals(id)){
				User user =dao .findById(a.getUserId());
				applications.put(user, a);
			}
		}
		request.setAttribute("seats", solicitudes);
		request.setAttribute("apps", applications);
		return resultado;
	}

}
