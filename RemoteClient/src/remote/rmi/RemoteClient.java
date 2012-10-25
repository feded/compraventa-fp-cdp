package remote.rmi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import session.beans.interfaces.CargaPagosRemote;

public class RemoteClient {

	public static void guardarPagos(InputStream stream) {

		Context context;
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jboss.naming.remote.client.InitialContextFactory");
			props.put(Context.PROVIDER_URL, "remote://localhost:4447");
			props.put(Context.SECURITY_PRINCIPAL, "fede");
			props.put(Context.SECURITY_CREDENTIALS, "feded");
			context = new InitialContext(props);
			System.out.println("Got context");
			CargaPagosRemote pagosRemote = (CargaPagosRemote) context
					.lookup("test1/CargaPagos!session.beans.interfaces.CargaPagosRemote");
			DataInputStream in = new DataInputStream(stream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String archivo = "";
			while ((strLine = br.readLine()) != null) {
				archivo = archivo + strLine + "\n";
			}
			in.close();
			pagosRemote.cargarPagos(archivo);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
