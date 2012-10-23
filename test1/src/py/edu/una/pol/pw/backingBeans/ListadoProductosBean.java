package py.edu.una.pol.pw.backingBeans;




import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import py.edu.una.pol.pw.manager.DBManager;
import entity.beans.Producto;
  
@ManagedBean  
public class ListadoProductosBean{  

	private DBManager dbmgr;
	
	private List<Producto> productos;
	 
	
  
    public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	
	public ListadoProductosBean() {  
    	dbmgr = new DBManager();
    	productos = dbmgr.getProductos();
    }  
  
  
    public void onEdit(RowEditEvent event) {  
    	dbmgr = new DBManager();
    	dbmgr.guardarProducto((Producto) event.getObject());
    	FacesMessage msg = new FacesMessage("Producto Editado", ((Producto) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {
	       eliminarProducto((Producto) event.getObject());
    }
    
    public String eliminarProducto(Producto producto){
    	dbmgr = new DBManager();
    	if (dbmgr.eliminarProducto(producto)){
    		productos.remove(producto);
    		FacesMessage msg = new FacesMessage("Eliminado con Éxito", (producto).getNombre());  
    	    FacesContext.getCurrentInstance().addMessage(null, msg);  
    	}else{
    		FacesMessage msg = new FacesMessage("No se puede eliminar el Producto", (producto).getNombre());  
    	    FacesContext.getCurrentInstance().addMessage(null, msg);  
    	}
    	return "/productos/listado_prouctos.xhtml";
    	
    }

	
}  
