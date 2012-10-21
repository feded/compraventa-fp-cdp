package py.edu.una.pol.pw.backingBeans;




import java.util.List;

import javax.faces.bean.ManagedBean;
import py.edu.una.pol.pw.beans.Venta;
import py.edu.una.pol.pw.manager.DBManager;
  
@ManagedBean  
public class ListadoVentasBean{  

	private DBManager dbmgr;
	
	private List<Venta> ventas;
	 
	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
  
	
	public ListadoVentasBean() {  
    	dbmgr = new DBManager();
    	ventas = dbmgr.getVentas();
    }  
  
  
 

	
}  
