package py.edu.una.pol.pw.backingBeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import py.edu.una.pol.pw.beans.Venta;
import py.edu.una.pol.pw.beans.VentaDetalle;
import py.edu.una.pol.pw.beans.Persona;
import py.edu.una.pol.pw.beans.Producto;
import py.edu.una.pol.pw.beans.Persona.TipoPersona;
import py.edu.una.pol.pw.manager.DBManager;
  
@ManagedBean
@SessionScoped
public class VentaBean {  
private Venta venta;
	
	private String codProducto;
	private String codCliente;
	
	private Producto producto;
	private VentaDetalle seleccionado;
	
	private List<Persona> clientes;
	private List<Producto> productos;
	private BigDecimal total;
	
	public VentaBean(){
		if (this.venta==null){
			this.venta=new Venta();
		}
	}
    public String reinit() {
    		VentaDetalle aux = new VentaDetalle();
    		aux.setCantidad(this.venta.getDetalle().getCantidad());
    		aux.setVenta(this.venta);
    		aux.setId(this.venta.getDetalle().getId());
    		aux.setPrecio(this.venta.getDetalle().getPrecio());
    		aux.setProducto(this.getProducto());
    		this.venta.getDetalles().add(aux);
    		this.venta.setDetalle(new VentaDetalle());
	        return null;  
	    }
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Venta getVenta() {
		return venta;
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = new BigDecimal(0);
		if (venta!=null){
			for (VentaDetalle det : venta.getDetalles()){
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
	
	public VentaDetalle getSeleccionado(){
		return seleccionado;
	}
	public void setSeleccionado(VentaDetalle venta) {
		seleccionado = venta;
		this.venta.getDetalles().remove(venta);
	}
	
	public String insertarVenta(){
		DBManager dbm = new DBManager();
		if (dbm.guardarVenta(this.venta)){
			FacesMessage msg = new FacesMessage("venta Generada con Éxito" );  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage("Venta No generada, fijarse en Registro");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("#{VentaBean}");
		setearANull();
		return "";
	}
	private void setearANull() {
		this.codProducto = "";
		this.codCliente = "";
		this.venta= new Venta();
		this.producto= new Producto();
		this.productos = new ArrayList<Producto>();
		this.clientes = new ArrayList<Persona>();
		this.seleccionado = new VentaDetalle();
		this.total= new BigDecimal(0);
		
	}
	public void remover(int pos){
		this.venta.getDetalles().remove(pos);
	}
	public List<Persona> getClientes() {
		DBManager dbm = new DBManager(); 
		
		this.clientes = dbm.getPersonas(TipoPersona.CLIENTE.toInt());
		
		return this.clientes;
	}
	public void setClientes(List<Persona> clientes) {
		this.clientes = clientes;
	}
	public List<Producto> getProductos() {
		DBManager dbm = new DBManager(); 
		
		this.productos = dbm.getProductos();
		
		return this.productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	

	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		DBManager mgr = new DBManager();
		this.setProducto(mgr.getProducto(codProducto.split("-")[0]));
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		DBManager mgr = new DBManager();
		this.venta.setCliente(mgr.getPersona(codCliente.split("-")[0]));
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
 
}