package py.edu.una.pol.pw.backingBeans;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import session.beans.CompraLocal;
import session.beans.ListadosLocal;
import entity.beans.Compra;
import entity.beans.CompraDetalle;
import entity.beans.Persona;
import entity.beans.Producto;
  
@ManagedBean
@SessionScoped
public class CompraBean {  
	private Compra compra;
	
	private String codProducto;
	private String codProveedor;
	
	private Producto producto;
	private CompraDetalle seleccionado;
	
	private List<Persona> proveedores;
	private List<Producto> productos;
	private BigDecimal total;
	
	public CompraBean(){
		if (this.compra==null){
			this.compra=new Compra();
		}
	}
    public String reinit() {
    		CompraDetalle aux = new CompraDetalle();
    		aux.setCantidad(this.compra.getDetalle().getCantidad());
    		aux.setCompra(this.compra);
    		aux.setId(this.compra.getDetalle().getId());
    		aux.setPrecio(this.compra.getDetalle().getPrecio());
    		aux.setProducto(this.getProducto());
    		this.compra.getDetalles().add(aux);
    		this.compra.setDetalle(new CompraDetalle());
	        return null;  
	    }
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Compra getCompra() {
		return compra;
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = new BigDecimal(0);
		if (compra!=null){
			for (CompraDetalle det : compra.getDetalles()){
				if (det.getPrecio()!=null && det.getCantidad()!=null)
					total = total.add(new BigDecimal(det.getPrecio()).multiply(new BigDecimal(det.getCantidad())));
			}
		}
		this.total = total;
		return this.total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public CompraDetalle getSeleccionado(){
		return seleccionado;
	}
	public void setSeleccionado(CompraDetalle compra) {
		seleccionado = compra;
		this.compra.getDetalles().remove(compra);
	}
	
	public String insertarCompra(){
		//DBManager dbm = new DBManager();
		Context context;
		  try {
		   context = new InitialContext();
		   CompraLocal compraBean = (CompraLocal) context.lookup("java:app/test1/Compra!session.beans.CompraLocal");
		   compraBean.guardarCompra(compra); 
			FacesMessage msg = new FacesMessage("Compra Generada con Éxito" );  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		  } catch (NamingException e) {
		   e.printStackTrace();
			FacesMessage msg = new FacesMessage("Compra No generada, fijarse en Registro");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		  }
		/*if (dbm.guardarCompra(this.compra)){
			FacesMessage msg = new FacesMessage("Compra Generada con Éxito" );  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage("Compra No generada, fijarse en Registro");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("#{CompraBean}");*/
		setearANull();
		return "";
	}
	
	private void setearANull() {
		this.codProducto = "";
		this.codProveedor = "";
		this.compra= new Compra();
		this.producto= new Producto();
		this.productos = new ArrayList<Producto>();
		this.proveedores = new ArrayList<Persona>();
		this.seleccionado = new CompraDetalle();
		this.total= new BigDecimal(0);
		
	}
	public void remover(int pos){
		this.compra.getDetalles().remove(pos);
	}
	public List<Persona> getProveedores() {
		//DBManager dbm = new DBManager(); 
		
		//this.proveedores = dbm.getPersonas(TipoPersona.PROVEEDOR.toInt());
		Context context;
		  try {
		   context = new InitialContext();
		   ListadosLocal listadosBean = (ListadosLocal) context
		     .lookup("java:app/test1/Listados!session.beans.ListadosLocal");
		   proveedores = listadosBean.getProveedores();
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
		return this.proveedores;
	}
	public void setProveedores(List<Persona> proveedores) {
		this.proveedores = proveedores;
	}
	public List<Producto> getProductos() {
		//DBManager dbm = new DBManager(); 
		//this.productos = dbm.getProductos();
		Context context;
		  try {
		   context = new InitialContext();
		   ListadosLocal listadosBean = (ListadosLocal) context
		     .lookup("java:app/test1/Listados!session.beans.ListadosLocal");
		   productos = listadosBean.getProductos();
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
		return this.productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	

	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		//DBManager mgr = new DBManager();
		Context context;
		  try {
		   context = new InitialContext();
		   CompraLocal compraBean = (CompraLocal) context
		     .lookup("java:app/test1/Compra!session.beans.CompraLocal");
		   //this.setProducto(compraBean.getProducto(codProducto.split("-")[0]));
		   this.setProducto(producto);
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
		//this.setProducto(mgr.getProducto(codProducto.split("-")[0]));
	}
	public String getCodProveedor() {
		return codProveedor;
	}
	public void setCodProveedor(String codProveedor) {
		//DBManager mgr = new DBManager();
		//this.compra.setProveedor(mgr.getPersona(codProveedor.split("-")[0]));
		Context context;
		  try {
		   context = new InitialContext();
		   CompraLocal compraBean = (CompraLocal) context
		     .lookup("java:app/test1/Compra!session.beans.CompraLocal");
		  // this.compra.setProveedor(compraBean.getPersona(codProveedor.split("-")[0]));
		   this.setCodProveedor(codProveedor);
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}  