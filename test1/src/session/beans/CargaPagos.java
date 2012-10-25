package session.beans;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import session.beans.interfaces.CargaPagosLocal;
import session.beans.interfaces.CargaPagosRemote;
import session.beans.interfaces.CargaPagosWebService;

import entity.beans.Persona;
import entity.beans.RegistroPago;

/**
 * Session Bean implementation class CargaPagos
 */
@Stateful(mappedName= "CargaPago")
@WebService(endpointInterface = "session.beans.interfaces.CargaPagosWebService")
@Remote({CargaPagosRemote.class, CargaPagosWebService.class})
public class CargaPagos implements CargaPagosRemote, CargaPagosLocal, CargaPagosWebService {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName = "test1")
	private EntityManager mgr;
	
    public CargaPagos() {
        // TODO Auto-generated constructor stub
    }
    
    public void cargarPagos(String archivo){
		try {
			String strLine;
			String [] datos;
			String [] spliteado = archivo.split("\n");
			for (int i = 0 ; i<spliteado.length; i++ ) {
				strLine = spliteado[i];
				datos = strLine.split(",");
				String codPago = datos[0];
				String codPersona = datos[1];
				String monto = datos[2];
				String fecha = datos[3];
				//mgr.getTransaction().begin();
				//Validar los datos
				BigDecimal pago = new BigDecimal(monto);
				Persona persona = mgr.find(Persona.class, codPersona);
				try{
					
					
					//if (persona.getSaldoDisponible().compareTo(pago)>=0){ // puede pagar
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						Date fechaPago = sdf.parse(fecha);
						RegistroPago regPago = new RegistroPago();
						regPago.setCodPago(codPago);
						regPago.setEstado(1);
						regPago.setMensaje("OK");
						regPago.setCodPersona(""+persona.getCodPersona());
						regPago.setFecha(fechaPago);
						regPago.setMonto(pago);
						regPago.setTipo("Archivo");
						mgr.persist(regPago);
						persona.setSaldoDisponible(persona.getSaldoDisponible().subtract(pago));
						//mgr.getTransaction().commit();
//					}else{
//						RegistroPago regPago = new RegistroPago();
//						regPago.setCodPago(codPago);
//						regPago.setEstado(-1);
//						regPago.setMensaje("Monto mayor al saldo disponible de la Persona");
//						regPago.setCodPersona(persona.getCodPersona());
//						regPago.setFecha(null);
//						regPago.setMonto(pago);
//						regPago.setTipo("Archivo");
//						mgr.persist(regPago);
//						mgr.getTransaction().commit();
//					}
				}catch(RollbackException e){
		        	e.printStackTrace();
		        	if (mgr.getTransaction().isActive()) {
		                mgr.getTransaction().rollback();
		            }
		        	//mgr.getTransaction().begin();
		        	// se genera un registro de pago erroneo
		        	RegistroPago regPago = new RegistroPago();
					regPago.setCodPago("ErrorPago - " + System.currentTimeMillis()); 
					regPago.setEstado(-1);
					regPago.setMensaje("Error Saldo menor al pago: " + persona.getSaldoDisponible());
					regPago.setCodPersona(""+persona.getCodPersona());
					regPago.setFecha(new Date(System.currentTimeMillis()));
					regPago.setMonto(pago);
					regPago.setTipo("Archivo");
					mgr.persist(regPago);
					//mgr.getTransaction().commit();
				}catch (NumberFormatException e){
					RegistroPago regPago = new RegistroPago();
					regPago.setCodPago(codPago);
					regPago.setEstado(-1);
					regPago.setMensaje("Monto erroneo: " + monto);
					regPago.setCodPersona(codPersona);
					regPago.setFecha(null);
					regPago.setMonto(null);
					regPago.setTipo("Archivo");
					mgr.persist(regPago);
					//mgr.getTransaction().commit();
				}catch (ParseException e) {
					RegistroPago regPago = new RegistroPago();
					regPago.setCodPago(codPago);
					regPago.setEstado(-1);
					regPago.setMensaje("Fecha Erronea: (yyyyMMdd) " + fecha);
					regPago.setCodPersona(codPersona);
					regPago.setFecha(null);
					regPago.setMonto(new BigDecimal(monto));
					regPago.setTipo("Archivo");
					mgr.persist(regPago);
					//mgr.getTransaction().commit();
				}
				
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			return;
		}

    }

}
