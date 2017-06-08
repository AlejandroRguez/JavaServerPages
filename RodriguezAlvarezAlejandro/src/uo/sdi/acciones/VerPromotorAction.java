package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.common.UserInfo;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;

public class VerPromotorAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String resultado = "EXITO";
		Long id = Long.parseLong(request.getParameter("id"));
		TripDao tdao = PersistenceFactory.newTripDao();
		UserDao udao = PersistenceFactory.newUserDao();
		Integer contador = 0;
		User usuario = udao.findById(tdao.findById(id).getPromoterId());
		for(Trip t : tdao.findAll()){
			if(t.getPromoterId().equals(usuario.getId())){
				contador = contador ++;
			}
		}
		UserInfo userInfo = new UserInfo(usuario, contador);
		request.setAttribute("info" , userInfo);
		return resultado;
	}

}
