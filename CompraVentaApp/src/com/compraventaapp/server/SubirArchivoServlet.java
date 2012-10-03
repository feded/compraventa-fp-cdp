package com.compraventaapp.server;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
 
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
 
import org.apache.commons.fileupload.FileItem;
 
public class SubirArchivoServlet extends UploadAction {
 
	private static final long serialVersionUID = 1L;
	DBManager mgr = new DBManager();
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		String response = "Received file:";
 
		for (FileItem item : sessionFiles) {
			if (!item.isFormField()) {
				try {
 
					// we can save the received file
					// File file = File.createTempFile("receivedFile", ".tmp",
					// new File("C:\\"));
					// item.write(file);
 
					// response += " " + file.getPath();
 
					response += " " + item.getName() + ", size=  "
					+ item.getSize() + ", ContentType= "
					+ item.getContentType();
					mgr.cargarPagos(item.getInputStream());
					
 
				} catch (Exception e) {
					throw new UploadActionException(e.getMessage());
				}
			}
		}
 
		try {
			// Remove files from session
			removeSessionFileItems(request);
		} catch (Exception e) {
			throw new UploadActionException(e.getMessage());
		}
 
		// Send information of the received files to the client.
		return response;
	}
 
}
