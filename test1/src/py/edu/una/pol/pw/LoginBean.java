package py.edu.una.pol.pw;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class LoginBean {
	private String usuario , contrasenha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}
	
	public String login() {
		if (usuario != null && usuario.equals(contrasenha))
			return "paginaPrincipal";
		else {
			usuario ="";
			FacesContext.getCurrentInstance().addMessage("una cadena", new FacesMessage("Error de autenticacion", "El usuario y la contrasenha deben ser iguales"));
			return "Login";
		}
	}
}
