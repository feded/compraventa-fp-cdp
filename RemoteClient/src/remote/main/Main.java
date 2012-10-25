package remote.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import remote.rmi.RemoteClient;
import remote.webService.WebServiceClient;

public class Main {
	
	public static void main(String[] args) {
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		Integer op = null;
		while (op == null){
			System.out.println("Ingrese su Opción: ");
			System.out.println("Carga de Pagos por WebService: 1");
			System.out.println("Carga de Pagos por ClienteRemoto(RMI): 2");
			try{
				String opcion = lectura.readLine();
				op = Integer.parseInt(opcion);
				if (op<1 || op>2){
					System.out.println("Opción no válida");
					op= null;
				}
			}catch(Exception e){
				System.out.println("Opción no válida");
				op= null;
			}
		}
		FileInputStream f = null;
		try{
			boolean ok = true;
			String ruta = "C:/Users/Fede/Desktop/pagos.txt";
			f = new FileInputStream(ruta);
			while(!ok){
				
					System.out.println("Cargue la ruta de su archivo de Pagos - Ej:(C:/..../pagos.txt): ");
					ruta = lectura.readLine();
					f = new FileInputStream(ruta);
					ok = true;
				
			}
		}catch(Exception e){
			System.out.println("No se puede leer el archivo. Vuelva a intentarlo");
		}  
		switch (op) {
			case 1:
				WebServiceClient.guardarPagos(f);
				break;
			case 2:
				RemoteClient.guardarPagos(f);
				break;
			case 3:
				break;
			default:
				break;
		}
		
	}
	
	

}
