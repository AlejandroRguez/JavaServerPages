package uo.sdi.acciones;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.common.StatusInfo;
import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.PersistenceFactory;

public class CrearViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado = "EXITO";
		Trip trip = new Trip();
		Date fechaLlegada = null;
		Date fechaSalida = null;
		Date fechaCierre = null;
		Double latSalida = 0.0;
		Double latLlegada = 0.0;
		Double lonSalida = 0.0;
		Double lonLlegada = 0.0;
		Double coste = 0.0;
		int plazasTotales = 0;
		int plazasDisponibles = 0;
		String direccionSalida = request.getParameter("direccionSalida");
		String ciudadSalida = request.getParameter("ciudadSalida");
		String provinciaSalida = request.getParameter("provinciaSalida");
		String paisSalida = request.getParameter("paisSalida");
		String cpSalida = request.getParameter("cpSalida");
		String direccionLlegada = request.getParameter("direccionLlegada");
		String ciudadLlegada = request.getParameter("ciudadLlegada");
		String provinciaLlegada = request.getParameter("provinciaLlegada");
		String paisLlegada = request.getParameter("paisLlegada");
		String cpLlegada = request.getParameter("cpLlegada");
		
		if(direccionSalida.equals("") || ciudadSalida.equals("") || provinciaSalida.equals("")
				|| paisSalida.equals("") || cpSalida.equals("") || direccionLlegada.equals("")
				|| ciudadLlegada.equals("") || provinciaLlegada.equals("") || paisLlegada.equals("") 
				|| cpLlegada.equals("") ) {
			Log.info("Algunos campos obligatorios están vacíos");
			request.setAttribute("status", new StatusInfo() {{
				value = "Algunos campos obligatorios están vacíos";
			}});
			return "FRACASO";
		}
		
		try {
		 fechaLlegada = new SimpleDateFormat("dd/MM/yyyy HH:mm")
		.parse(request.getParameter("fechaLlegada"));
		 fechaSalida = new SimpleDateFormat("dd/MM/yyyy HH:mm")
		.parse(request.getParameter("fechaSalida"));
		 fechaCierre = new SimpleDateFormat("dd/MM/yyyy HH:mm")
		.parse(request.getParameter("fechaCierre"));
		 coste = Double.parseDouble(request.getParameter("coste"));
		 plazasTotales = Integer.parseInt(request.getParameter("plazasTotales"));
		 plazasDisponibles =Integer.parseInt(request.getParameter("plazasDisponibles"));
		
		}catch (Exception e){
			Log.info("El formato de alguno de los datos es inválido");
			request.setAttribute("status", new StatusInfo() {{
				value = "El formato de algunos datos es incorrecto, por favor vuelva a intentarlo";
			}});
			return "FRACASO";
			
		}
		 
		if(!checkFechas(fechaCierre, fechaLlegada, fechaSalida)){
			Log.info("El formato de alguno de los datos es inválido");
			request.setAttribute("status", new StatusInfo() {{
				value = "Las fechas son incoherentes, por favor vuelva a intentarlo";
			}});
			return "FRACASO";
		}
		
		if(!checkPlazas(plazasDisponibles, plazasTotales)){
			Log.info("El formato de alguno de los datos es inválido");
			request.setAttribute("status", new StatusInfo() {{
				value = "Las plazas son incoherentes, por favor vuelva a intentarlo";
			}});
			return "FRACASO";
		}
		
		Waypoint dep = new Waypoint(latSalida, lonSalida);
		Waypoint arr = new Waypoint (latLlegada, lonLlegada);
		AddressPoint departure = new AddressPoint(direccionSalida,
				ciudadSalida, provinciaSalida, paisSalida, cpSalida, dep);
		AddressPoint arrival = new AddressPoint(direccionLlegada,
				ciudadLlegada, provinciaLlegada, paisLlegada, cpLlegada, arr);
		trip.setArrivalDate(fechaLlegada);
		trip.setAvailablePax(plazasDisponibles);
		trip.setClosingDate(fechaCierre);
		trip.setComments("");
		trip.setDepartureDate(fechaSalida);
		trip.setEstimatedCost(coste);
		trip.setMaxPax(plazasTotales);
		trip.setPromoterId(((User) request.getSession().getAttribute("user")).getId());
		trip.setStatus(TripStatus.OPEN);
		trip.setDeparture(departure);
		trip.setDestination(arrival);
		PersistenceFactory.newTripDao().save(trip);
		request.setAttribute("status", new StatusInfo() {{
			value = "El viaje ha sido creado correctamente";
		}});
		
		return resultado;
	}
	
	private boolean checkPlazas (int disponibles, int totales){
		boolean resultado = true;
		if(disponibles > totales){
			resultado = false;
		}if(disponibles == 0){
			resultado = false;
		}	return resultado;
	}
	
	private boolean checkFechas (Date cierre, Date llegada, Date salida){
		boolean resultado = false;
		Date hoy = Calendar.getInstance().getTime();
		if(cierre.after(hoy) && salida.after(cierre) && llegada.after(salida)){
			resultado = true;
		}
		return resultado;
	}

}
