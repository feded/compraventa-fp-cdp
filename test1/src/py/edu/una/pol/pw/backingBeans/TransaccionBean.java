package py.edu.una.pol.pw.backingBeans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class TransaccionBean {
	
	public static Integer cantidad;

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		TransaccionBean.cantidad = cantidad;
	}
	
	
	

}
