package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.common.StatusInfo;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class AceptarSolicitudAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado = "EXITO";
		
		Long userId = Long.parseLong(request.getParameter("userId"));
		Long tripId = Long.parseLong(request.getParameter("tripId"));
		Long [] ids = {userId, tripId};
		SeatDao sdao = PersistenceFactory.newSeatDao();
		ApplicationDao adao =  PersistenceFactory.newApplicationDao();
		TripDao tdao = PersistenceFactory.newTripDao();
		Trip t = tdao.findById(tripId);
		Seat s = sdao.findById(ids);
		Application a = adao.findById(ids);
		if(s != null){
			if(t.getAvailablePax() > 0){
				s.setStatus(SeatStatus.ACCEPTED);
				sdao.update(s);
				t.setAvailablePax(t.getAvailablePax() - 1);
				tdao.update(t);
				Log.info("Se ha aceptado la solicitud correctamente");
				request.setAttribute("status", new StatusInfo() {{
					value = "La solicitud ha sido aceptada";
				}});
			}else {
				resultado = "FRACASO";
				Log.info("El viaje no tiene plazas disponibles");
				request.setAttribute("status", new StatusInfo() {{
					value = "El viaje no tiene plazas disponibles";
				}});
			}
		}else if (a != null){
			if(t.getAvailablePax() > 0){
				Seat seat = new Seat();
				seat.setTripId(a.getTripId());
				seat.setUserId(a.getUserId());
				seat.setStatus(SeatStatus.ACCEPTED);
				adao.delete(ids);
				sdao.save(seat);
				t.setAvailablePax(t.getAvailablePax() - 1);
				tdao.update(t);
				Log.info("Se ha aceptado la solicitud correctamente");
				request.setAttribute("status", new StatusInfo() {{
					value = "La solicitud ha sido aceptada";
				}});
			}else {
				resultado = "FRACASO";
				Log.info("El viaje no tiene plazas disponibles");
				request.setAttribute("status", new StatusInfo() {{
					value = "El viaje no tiene plazas disponibles";
				}});
			}
		}
		
		return resultado;
	}

}
