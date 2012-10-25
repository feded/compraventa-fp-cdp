package session.beans.interfaces;

import java.io.InputStream;

import javax.ejb.Remote;

@Remote
public interface CargaPagosRemote {
	public void cargarPagos(String archivo);
}
