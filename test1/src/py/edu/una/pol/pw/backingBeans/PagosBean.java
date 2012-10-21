package py.edu.una.pol.pw.backingBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import py.edu.una.pol.pw.manager.DBManager;

@ManagedBean
public class PagosBean {
	public void subirArchivo(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		DBManager dbm = new DBManager();
		dbm.cargarPagos(file);
        FacesMessage msg = new FacesMessage("Archivo", event.getFile().getFileName() + " subido con éxito.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}
