package remote.webService;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import session.beans.interfaces.CargaPagosRemote;

public class WebServiceClient {
	public static void guardarPagos(InputStream stream) { 
		try{
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		String archivo = "";
		while ((strLine = br.readLine()) != null) {
			archivo = archivo + strLine + "\n";
		}
		in.close();

		URL url = new URL("http://Fede-PC:8080/test1/CargaPagos?wsdl");
		QName qname = new QName("http://beans.session/",
				"CargaPagosService");

		System.out.println("Creating a service Using: \n\t" + url + " \n\tand "
				+ qname);
		ServiceFactory factory = ServiceFactory.newInstance();
		Service remote = factory.createService(url, qname);

		System.out.println("Obteniendo referencia al proxy");
		CargaPagosRemote proxy = (CargaPagosRemote) remote.getPort(CargaPagosRemote.class);
		System.out.println("Accessed local proxy: " + proxy);

		
		System.out.println("Enviando archivo: ");
		proxy.cargarPagos(archivo);
		System.out.println("Enviado: ");
		}catch(IOException e){
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
