package py.edu.una.pol.pw.backingBeans;




import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import py.edu.una.pol.pw.manager.DBManager;
import session.beans.ListadosLocal;
import entity.beans.RegistroPago;
  
@ManagedBean  
public class ListadoPagosBean{  

	
	
	private List<RegistroPago> registroPagos = new ArrayList<RegistroPago>();
	 
	public List<RegistroPago> getRegistroPagos() {
		return registroPagos;
	}

	public void setRegistroPagos(List<RegistroPago> registroPagos) {
		this.registroPagos =registroPagos;
	}
  
	
	public ListadoPagosBean() {  
    	//dbmgr = new DBManager();
    	//registroPagos = dbmgr.getRegistroPagos();
		Context context;
		  try {
		   context = new InitialContext();
		   ListadosLocal listadosBean = (ListadosLocal) context
		     .lookup("java:app/test1/Listados!session.beans.ListadosLocal");
		   registroPagos = listadosBean.getRegistroPagos();
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
    }  

}  
