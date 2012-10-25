package py.edu.una.pol.pw.backingBeans;




import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import session.beans.ListadosLocal;
import entity.beans.Compra;
  
@ManagedBean  
public class ListadoComprasBean{  


	private List<Compra> compras;
	 
	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
  
	
	public ListadoComprasBean() {  
		Context context;
		  try {
		   context = new InitialContext();
		   ListadosLocal listadosBean = (ListadosLocal) context
		     .lookup("java:app/test1/Listados!session.beans.ListadosLocal");
		   compras = listadosBean.getCompras();
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
    }  
  
  
 

	
}  
