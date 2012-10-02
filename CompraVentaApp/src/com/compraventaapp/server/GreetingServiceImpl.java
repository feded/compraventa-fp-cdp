package com.compraventaapp.server;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.List;

import com.compraventaapp.client.GreetingService;
import com.compraventaapp.client.model.Persona;
import com.compraventaapp.client.model.Producto;
import com.compraventaapp.client.model.RegistroPago;
import com.compraventaapp.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	private DBManager mgr;
	
	public GreetingServiceImpl(){
		mgr= new DBManager();
	}
	
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public List<Persona> getClientes() throws Exception {
		return mgr.getPersonas(Persona.TipoPersona.CLIENTE.toInt());
	}

	@Override
	public List<RegistroPago> getPagos() throws Exception {
		return mgr.getRegistroPagos();
	}
	
	@Override
	public List<Persona> getProveedores() throws Exception {
		return mgr.getPersonas(Persona.TipoPersona.PROVEEDOR.toInt());
	}

	@Override
	public List<Producto> getProductos() throws Exception {
		return mgr.getProductos();
	}
	
	@Override
	public void guardarProducto(Producto producto) throws Exception {
		if (!mgr.guardarProducto(producto)){
			throw new Exception("Error al guardar Producto");
		}
		
	}

	@Override
	public void guardarPersona(Persona persona) throws Exception {
		if (!mgr.guardarPersona(persona)){
			throw new Exception("Error al guardar Producto");
		}
		
	}

	@Override
	public void subirArchivo(InputStream file) throws Exception {
		mgr.cargarPagos(file);
		
	}

}
