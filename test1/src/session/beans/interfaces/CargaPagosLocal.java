package session.beans.interfaces;


import javax.ejb.Local;

@Local
public interface CargaPagosLocal {
	public void cargarPagos(String archivo);
}
