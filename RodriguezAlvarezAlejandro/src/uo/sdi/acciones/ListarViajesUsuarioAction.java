package uo.sdi.acciones;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class ListarViajesUsuarioAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String resultado = "EXITO";
		Long id = ((User) request.getSession().getAttribute("user")).getId();
		TripDao t = PersistenceFactory.newTripDao();
		List<Trip> viajes = t.findAll();
		Map<Trip, String> viajesPromovidosSinHacer = new HashMap<Trip, String>();
		Map<Trip, String> viajesPromovidosHechos = new HashMap<Trip, String>();
		Map<Trip, String> viajesHechosNoPromovidos = new HashMap<Trip, String>();
		Map<Trip, String> viajesSolicitados = new HashMap<Trip, String>();
		Date date = Calendar.getInstance().getTime();
		SeatDao seat = PersistenceFactory.newSeatDao();
		ApplicationDao app = PersistenceFactory.newApplicationDao();

		// Obtención de los viajes en los que el usuario es promotor y aún no se
		// han realizado
		for (Trip trip : viajes) {
			if (trip.getClosingDate().after(date)
					&& trip.getPromoterId().equals(id)) {
				viajesPromovidosSinHacer.put(trip, "PROMOTOR");
				Log.debug(
						"Obtenida lista de viajes promovidos sin hacer conteniendo [%d] viajes",
						viajesPromovidosSinHacer.size());
			} else if (trip.getClosingDate().before(date)
					&& trip.getPromoterId().equals(id)) {
				viajesPromovidosHechos.put(trip, "PROMOTOR");
				Log.debug(
						"Obtenida lista de viajes promovidos hechos conteniendo [%d] viajes",
						viajesPromovidosHechos.size());
			} else if (trip.getClosingDate().before(date)
					&& seat.findByUserAndTrip(id, trip.getId()) != null) {
				viajesHechosNoPromovidos.put(trip, "PARTICIPANTE");
				Log.debug(
						"Obtenida lista de viajes  no promovidos hechos conteniendo [%d] viajes",
						viajesHechosNoPromovidos.size());
			} else {
				if (trip.getClosingDate().after(date)) {
					Long[] ids = { id, trip.getId() };
					if (app.findById(ids) != null) {
						if (trip.getAvailablePax() > 0) {
							viajesSolicitados.put(trip, "PENDIENTE");
						} else {
							viajesSolicitados.put(trip, "SIN_PLAZA");
						}
					}
					if (seat.findByUserAndTrip(id, trip.getId()) != null) {
						Seat s = seat.findByUserAndTrip(id, trip.getId());
						String estado = s.getStatus().name();
						viajesSolicitados.put(trip, estado);
						Log.debug(
								"Obtenida lista de viajes  en los que ha solicitado plaza conteniendo [%d] viajes",
								viajesSolicitados.size());

					}
				}
			}

		}
		request.setAttribute("listaViajes1", viajesPromovidosSinHacer);
		request.setAttribute("listaViajes2", viajesPromovidosHechos);
		request.setAttribute("listaViajes3", viajesHechosNoPromovidos);
		request.setAttribute("listaViajes4", viajesSolicitados);

		return resultado;
	}

}
