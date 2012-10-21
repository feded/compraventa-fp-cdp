package py.edu.una.pol.pw.backingBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

import py.edu.una.pol.pw.beans.Persona;
import py.edu.una.pol.pw.beans.Persona.TipoPersona;
import py.edu.una.pol.pw.manager.DBManager;
  
@ManagedBean  
public class ListadoClientesBean{  

	private DBManager dbmgr;
	
	private List<Persona> personas;
	
	
  
    public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public ListadoClientesBean() {  
    	dbmgr = new DBManager();
    	personas = dbmgr.getPersonas(TipoPersona.CLIENTE.toInt());
    }  
  
  
    public void onEdit(RowEditEvent event) {  
    	dbmgr = new DBManager();
    	dbmgr.guardarPersona((Persona) event.getObject());
    	FacesMessage msg = new FacesMessage("Cliente Editado", ((Persona) event.getObject()).getNombre() + " " + ((Persona) event.getObject()).getApellido());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {
	       eliminarCliente((Persona) event.getObject());
    }
    
    public void eliminarCliente(Persona persona){
    	dbmgr = new DBManager();
    	if (dbmgr.eliminarPersona(persona)){
	 		personas.remove(persona);
			FacesMessage msg = new FacesMessage("Eliminado con Éxito", (persona).getNombre() + " " + (persona).getApellido());  
		    FacesContext.getCurrentInstance().addMessage(null, msg);  
	 	}else{
	 		FacesMessage msg = new FacesMessage("No se puede eliminar", (persona).getNombre() + " " + (persona).getApellido());  
		    FacesContext.getCurrentInstance().addMessage(null, msg);  
	 	} 
    }
}  
