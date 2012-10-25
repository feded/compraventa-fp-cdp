package session.beans.interfaces;

import javax.jws.WebService;

import java.io.InputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface CargaPagosWebService  extends Remote {
	public void cargarPagos(String archivo) throws RemoteException;
}
