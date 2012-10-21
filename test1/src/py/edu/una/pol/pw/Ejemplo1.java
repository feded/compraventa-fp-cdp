package py.edu.una.pol.pw;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Ejemplo1 {
	String msj = "Mensaje de prueba";
	
	public String getMensaje() {
		return this.msj;
	}
}
