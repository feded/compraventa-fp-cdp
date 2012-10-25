package py.edu.una.pol.pw.backingBeans;




import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.event.RowEditEvent;

import session.beans.ListadosLocal;
import session.beans.ProductoLocal;
import entity.beans.Producto;
  
@ManagedBean  
public class ListadoProductosBean{  

	private List<Producto> productos;

    public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	
	public ListadoProductosBean() {  
		Context context;
		  try {
		   context = new InitialContext();
		   ListadosLocal listadosBean = (ListadosLocal) context
		     .lookup("java:app/test1/Listados!session.beans.ListadosLocal");
		   productos = listadosBean.getProductos();
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
		//dbmgr = new DBManager();
    	//productos = dbmgr.getProductos();
    }  
  
  
    public void onEdit(RowEditEvent event) {  
    	//dbmgr = new DBManager();
    	//dbmgr.guardarProducto((Producto) event.getObject());
    	Context context;
		  try {
		   context = new InitialContext();
		   ProductoLocal productoBean = (ProductoLocal) context
		     .lookup("java:app/test1/Producto!session.beans.ProductoLocal");
		     productoBean.guardarProducto((Producto) event.getObject());
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
    	FacesMessage msg = new FacesMessage("Producto Editado", ((Producto) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {
	       eliminarProducto((Producto) event.getObject());
    }
    
    public String eliminarProducto(Producto producto){
    	
    	//dbmgr = new DBManager();
    	Context context;
		  try {
		   context = new InitialContext();
		   ProductoLocal productoBean = (ProductoLocal) context
		     .lookup("java:app/test1/Producto!session.beans.ProductoLocal");
		     productoBean.eliminarProducto(producto);
		     FacesMessage msg = new FacesMessage("Eliminado con Éxito", (producto).getNombre());  
	    	 FacesContext.getCurrentInstance().addMessage(null, msg);  
		  } catch (NamingException e) {
		   e.printStackTrace();
		   FacesMessage msg = new FacesMessage("No se puede eliminar el Producto", (producto).getNombre());  
		   FacesContext.getCurrentInstance().addMessage(null, msg); 
		  }
    	/*if (dbmgr.eliminarProducto(producto)){
    		productos.remove(producto);
    		FacesMessage msg = new FacesMessage("Eliminado con Éxito", (producto).getNombre());  
    	    FacesContext.getCurrentInstance().addMessage(null, msg);  
    	}else{
    		FacesMessage msg = new FacesMessage("No se puede eliminar el Producto", (producto).getNombre());  
    	    FacesContext.getCurrentInstance().addMessage(null, msg);  
    	}*/
    	return "/productos/listado_prouctos.xhtml";
    	
    }

	
}  
