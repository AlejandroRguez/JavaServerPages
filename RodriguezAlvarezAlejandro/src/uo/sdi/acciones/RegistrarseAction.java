package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.common.StatusInfo;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.PersistenceFactory;

public class RegistrarseAction  implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String login = request.getParameter("login");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		String resultado = "EXITO";
		
		if(login.equals("") || nombre.equals("") || apellidos.equals("") 
									   || email.equals("") || password.equals("") || password2.equals("")){
			Log.info("Algunos campos obligatorios están vacíos");
			request.setAttribute("status", new StatusInfo() {{
				value = "Algunos campos obligatorios están vacíos";
			}});
			return "FRACASO";
		}
		
		if(PersistenceFactory.newUserDao().findByLogin(login) != null){
			request.setAttribute("status", new StatusInfo() {{
				value = "Nombre de usuario repetido";
			}});
			resultado = "FRACASO";
			Log.info("El usuario [%s] ya existe",login);
		}
		else  if(email.startsWith("@")|| !email.contains("@") || email.endsWith("@")){
			request.setAttribute("status", new StatusInfo() {{
				value = "Formato de email incorrecto";
			}});
			resultado = "FRACASO";
			Log.info("Formato de email incorrecto");
		}
		else if(!(password.equals(password2))){
			request.setAttribute("status", new StatusInfo() {{
				value = "Las contraseñas no coinciden";
			}});
			resultado = "FRACASO";
			Log.info("Contraseñas incorrectas");
		}
		else {
			User user = new User();
			user.setLogin(login);
			user.setName(nombre);
			user.setSurname(apellidos);
			user.setEmail(email);
			user.setPassword(password);
			user.setStatus(UserStatus.ACTIVE);
			PersistenceFactory.newUserDao().save(user);
			Log.info("Se ha creado al usuario [%s]", login);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		}
		
		return resultado;
	}

}
