package py.edu.una.pol.pw.backingBeans;




import java.util.List;

import javax.faces.bean.ManagedBean;

import py.edu.una.pol.pw.manager.DBManager;
import entity.beans.Compra;
  
@ManagedBean  
public class ListadoComprasBean{  

	private DBManager dbmgr;
	
	private List<Compra> compras;
	 
	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
  
	
	public ListadoComprasBean() {  
    	dbmgr = new DBManager();
    	compras = dbmgr.getCompras();
    }  
  
  
 

	
}  
