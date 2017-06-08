package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.common.StatusInfo;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class CancelarSolicitudAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado = "EXITO";
		Long id =Long.parseLong(request.getParameter("id"));
		User user = (User) request.getSession().getAttribute("user");
		Long [] ids = {user.getId(), id};
		ApplicationDao app = PersistenceFactory.newApplicationDao();
		SeatDao seat = PersistenceFactory.newSeatDao();
		TripDao tdao = PersistenceFactory.newTripDao();
		Trip t = tdao.findById(id);
		if (app.findById(ids) != null){
			app.delete(ids);
			Log.info("Borrada la solicitud del usuario [%s]", user.getLogin());
			request.setAttribute("status", new StatusInfo() {{
				value = "Su solicitud ha sido eliminada";
			}});
		}
		if (seat.findByUserAndTrip(user.getId(), id) != null){ 
			seat.delete(ids);
			t.setAvailablePax(t.getAvailablePax() + 1);
			tdao.update(t);
			Log.info("Borrada la solicitud del usuario [%s]", user.getLogin());
			request.setAttribute("status", new StatusInfo() {{
				value = "Su solicitud ha sido eliminada";
			}});
		}

		return resultado;
	}

}
