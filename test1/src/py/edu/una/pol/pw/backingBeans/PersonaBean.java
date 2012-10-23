package py.edu.una.pol.pw.backingBeans;  

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import py.edu.una.pol.pw.manager.DBManager;
import entity.beans.Persona;
import entity.beans.Persona.TipoPersona;
  
@ManagedBean
public class PersonaBean {  
  
    private String ciruc;        
    private String nombre;       
    private String apellido;        
    private String direccion;  
    private String telefono;  
    private String saldoDisponible;
    
   
    public String getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(String saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public PersonaBean() { }
    
    public String getCiruc() {  
        return ciruc;  
    }  
  
    public void setCiruc(String ciruc) {  
        this.ciruc = ciruc;  
    }  
  
    public String getNombre() {  
        return nombre;  
    }  
  
    public void setNombre(String nombre) {  
        this.nombre = nombre;  
    }  
  
    public String getApellido() {  
        return apellido;  
    }  
  
    public void setApellido(String apellido) {  
        this.apellido = apellido;  
    }  
      
    public String getDireccion() {  
        return direccion;  
    }  
  
    public void setDireccion(String direccion) {  
        this.direccion = direccion;  
    }  
  
    public String getTelefono() {  
        return telefono;  
    }  
  
    public void setTelefono(String telefono) {  
        this.telefono = telefono;  
    }  
    public String guardarCliente(){
    	Persona persona = new Persona();
    	persona.setTipoPersona(TipoPersona.CLIENTE.toInt());
    	return this.guardarPersona(persona);
    }
    
    public String guardarProveedor(){
    	Persona persona = new Persona();
    	persona.setTipoPersona(TipoPersona.PROVEEDOR.toInt());
    	return this.guardarPersona(persona);
    	
    }
    private String guardarPersona(Persona persona){
    	DBManager mgr = new DBManager();
    	
    	persona.setNombre(this.getNombre());
    	persona.setApellido(this.getApellido());
    	persona.setDireccion(this.getDireccion());
    	persona.setNumeroDocumento(this.getCiruc());
    	persona.setSaldoDisponible(new BigDecimal((this.getSaldoDisponible()!=null && this.getSaldoDisponible()!="")?this.getSaldoDisponible():"0"));
    	persona.setTelefono(this.getTelefono());
    	mgr.guardarPersona(persona);
    	FacesMessage msg = new FacesMessage("Guardado con Éxito", this.getNombre() + " " + this.getApellido());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setearANull();
        return "";
    	
    	
    }
    
    private void setearANull(){
    	this.apellido = null;
    	this.ciruc = null;
    	this.direccion = null;
    	this.nombre = null;
    	this.saldoDisponible = null;
    	this.telefono = null;
    }
  
}  