package py.edu.una.pol.pw.backingBeans;  

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import session.beans.ProductoLocal;
import entity.beans.Producto;
  
@ManagedBean
public class ProductoBean {  
  
    private String codigo; 
    private String name; 
    private String descripcion;       
    private BigDecimal precio;         
    private BigDecimal existencia;  
   
    
    public ProductoBean() {
        
    }
    
    
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
   
    	this.codigo = codigo;
    }

    

    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getExistencia() {
        return existencia;
    }

    public void setExistencia(BigDecimal existencia) {
        this.existencia = existencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
   

   
    public String guardarProducto(){
    	Producto producto= new Producto();
    	return this.guardarProducto(producto);
    }
 
    private String guardarProducto(Producto producto){
    	//DBManager mgr = new DBManager();
    
    	producto.setNombre(this.getName());
    	producto.setDescripcion(this.getDescripcion());
    	producto.setPrecio(this.getPrecio());
    	producto.setCantidad(this.getExistencia());
    	Context context;
		  try {
		   context = new InitialContext();
		   ProductoLocal productoBean = (ProductoLocal) context
		     .lookup("java:app/test1/Producto!session.beans.ProductoLocal");
		     productoBean.guardarProducto(producto);
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
    	//mgr.guardarProducto(producto);
    	FacesMessage msg = new FacesMessage("Guardado con Éxito", this.getName());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setearANull();
        return "";
    	
    	
    	
    }
    
 

	private void setearANull(){
    	this.descripcion = null;
    	this.existencia = null;
    	this.precio= null;
    	this.name=null;
    	this.codigo= null;
  
    }


}  