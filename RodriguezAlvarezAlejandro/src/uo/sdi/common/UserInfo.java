package uo.sdi.common;

import uo.sdi.model.User;

public class UserInfo {
	
	protected User user;
	protected int numViajes;
	
	
	protected String login;
	protected String name;
	protected String surname;
	protected String email;
	
	public UserInfo (User user , int numViajes){
		this.user = user;
		this.numViajes = numViajes;
		inicializar();
	}

	private void inicializar() {
		this.setEmail(user.getEmail());
		this.setLogin(user.getLogin());
		this.setName(user.getName());
		this.setSurname(user.getSurname());
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNumViajes() {
		return numViajes;
	}

	public void setNumViajes(int numViajes) {
		this.numViajes = numViajes;
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
