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
import session.beans.PersonaLocal;
import entity.beans.Persona;
  
@ManagedBean  
public class ListadoClientesBean{  

	
	private List<Persona> personas;
	

    public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public ListadoClientesBean() {  
		
		Context context;
		  try {
		   context = new InitialContext();
		   ListadosLocal listadosBean = (ListadosLocal) context
		     .lookup("java:app/test1/Listados!session.beans.ListadosLocal");
		   personas = listadosBean.getClientes();
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
		
    	//dbmgr = new DBManager();
    	//personas = dbmgr.getPersonas(TipoPersona.CLIENTE.toInt());
    }  
  
  
    public void onEdit(RowEditEvent event) {  
    	//dbmgr = new DBManager();
    	//dbmgr.guardarPersona((Persona) event.getObject());
    	Context context;
		  try {
		   context = new InitialContext();
		   PersonaLocal personasBean = (PersonaLocal) context
		     .lookup("java:app/test1/Persona!session.beans.PersonaLocal");
		     personasBean.guardarPersona((Persona) event.getObject()); 
		  } catch (NamingException e) {
		   e.printStackTrace();
		  }
    	FacesMessage msg = new FacesMessage("Cliente Editado", ((Persona) event.getObject()).getNombre() + " " + ((Persona) event.getObject()).getApellido());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {
	       eliminarCliente((Persona) event.getObject());
    }
    
    public void eliminarCliente(Persona persona){
    	/*dbmgr = new DBManager();
    	if (dbmgr.eliminarPersona(persona)){
	 		personas.remove(persona);
			FacesMessage msg = new FacesMessage("Eliminado con Éxito", (persona).getNombre() + " " + (persona).getApellido());  
		    FacesContext.getCurrentInstance().addMessage(null, msg);  
	 	}else{
	 		FacesMessage msg = new FacesMessage("No se puede eliminar", (persona).getNombre() + " " + (persona).getApellido());  
		    FacesContext.getCurrentInstance().addMessage(null, msg);  
	 	} */
    	Context context;
		  try {
		   context = new InitialContext();
		   PersonaLocal personasBean = (PersonaLocal) context
		     .lookup("java:app/test1/Persona!session.beans.PersonaLocal");
		     personasBean.eliminarPersona(persona); 
		     FacesMessage msg = new FacesMessage("Eliminado con Éxito", (persona).getNombre() + " " + (persona).getApellido());  
			 FacesContext.getCurrentInstance().addMessage(null, msg);  
		  } catch (NamingException e) {
		   e.printStackTrace();
		   FacesMessage msg = new FacesMessage("No se puede eliminar", (persona).getNombre() + " " + (persona).getApellido());  
		   FacesContext.getCurrentInstance().addMessage(null, msg);  
		  }
    }
}  
