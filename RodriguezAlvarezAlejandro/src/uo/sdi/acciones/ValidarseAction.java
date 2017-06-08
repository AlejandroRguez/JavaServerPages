package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.common.StatusInfo;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class ValidarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		int contadorLogin = 0;
		String resultado="EXITO";
		String login=request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session=request.getSession();
		String pass = "";
		if (session.getAttribute("user")==null) {
			UserDao dao = PersistenceFactory.newUserDao();
			User userByLogin = dao.findByLogin(login);
			if(userByLogin != null)
				 pass = userByLogin.getPassword();
			if (userByLogin==null  || ! pass.equals(password)) {
				request.setAttribute("status", new StatusInfo() {{
					value = "Nombre de usuario y/o contraseña incorrectos";
				}});
				session.invalidate();
				Log.info("El usuario [%s] no está registrado",login);
				resultado="FRACASO";
				
			}
			else {
				session.setAttribute("user", userByLogin);
				int contador=Integer.parseInt((String)request.getServletContext().getAttribute("contador"));
				request.getServletContext().setAttribute("contador", String.valueOf(contador+1));
				Log.info("El usuario [%s] ha iniciado sesión",login);
			}
		}
		else if (!login.equals(((User) session.getAttribute("user"))
				.getLogin())) {
			Log.info(
					"Se ha intentado iniciar sesión como [%s] teniendo la sesión iniciada como [%s]",
					login, ((User) session.getAttribute("user")).getLogin());
			session.invalidate();
			resultado = "FRACASO";
		}
		
		if(resultado.equals("FRACASO")){
			contadorLogin =Integer.parseInt((String)request.getServletContext().getAttribute("contadorLogin"));
			request.getServletContext().setAttribute("contadorLogin", String.valueOf(contadorLogin+1));
		}
		contadorLogin =Integer.parseInt((String)request.getServletContext().getAttribute("contadorLogin"));
		if(contadorLogin > 0 && resultado.equals("EXITO")){
			request.getServletContext().setAttribute("contadorLogin", String.valueOf(0));
			return "PRUEBA";
		}
		
		return resultado;
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
