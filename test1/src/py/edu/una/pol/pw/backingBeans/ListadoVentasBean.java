package py.edu.una.pol.pw.backingBeans;




import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import session.beans.ListadosLocal;
import entity.beans.Venta;
  
@ManagedBean  
public class ListadoVentasBean{  

	
	
	private List<Venta> ventas;
	 
	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
  
	
	public ListadoVentasBean() {  
		Context context;
		  try {
		   context = new InitialContext();
		   ListadosLocal listadosBean = (ListadosLocal) context
		     .lookup("java:app/test1/Listados!session.beans.ListadosLocal");
		   ventas = listadosBean.getVentas();
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
    }  
  
  
 

	
}  
