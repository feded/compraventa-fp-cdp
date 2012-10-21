package py.edu.una.pol.pw.backingBeans;




import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import py.edu.una.pol.pw.beans.RegistroPago;
import py.edu.una.pol.pw.manager.DBManager;
  
@ManagedBean  
public class ListadoPagosBean{  

	private DBManager dbmgr;
	
	private List<RegistroPago> registroPagos = new ArrayList<RegistroPago>();
	 
	public List<RegistroPago> getRegistroPagos() {
		return registroPagos;
	}

	public void setRegistroPagos(List<RegistroPago> registroPagos) {
		this.registroPagos =registroPagos;
	}
  
	
	public ListadoPagosBean() {  
    	dbmgr = new DBManager();
    	registroPagos = dbmgr.getRegistroPagos();
    }  
  
  
 

	
}  
