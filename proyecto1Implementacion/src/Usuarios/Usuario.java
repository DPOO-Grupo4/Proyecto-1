package Usuarios;

public class Usuario {
	private String login;
	protected String password;
	private String correo;
	
	public Usuario(String login, String password, String correo) {
		this.login = login;
		this.password = password;
		this.correo = correo;
	}
	public String getLogin() {
		return this.login;
	}
	public String getPassword() 
	{
		return this.password;
	}
	public String getCorreo()
	{
		return this.correo;
	}
}
