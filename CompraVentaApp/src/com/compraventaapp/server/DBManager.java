package com.compraventaapp.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityResult;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;



import com.compraventaapp.client.model.Compra;
import com.compraventaapp.client.model.CompraDetalle;
import com.compraventaapp.client.model.Persona;
import com.compraventaapp.client.model.Producto;
import com.compraventaapp.client.model.RegistroPago;
import com.compraventaapp.client.model.Venta;
import com.compraventaapp.client.model.VentaDetalle;

@SqlResultSetMapping(entities = @EntityResult(entityClass = Persona.class), name = "Clientes")
public class DBManager {

	@PersistenceContext(name = "test1")
	private EntityManagerFactory emf;

	public DBManager() {
		emf = Persistence.createEntityManagerFactory("test1");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Persona> getPersonas(int tipoPersona) {

		List<Persona> personas = new ArrayList<Persona>();

		EntityManager mgr = null;
		try {
			mgr = emf.createEntityManager();
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(Persona.class);
			Root personaEntity = cq.from(Persona.class);
			Path tipo = personaEntity.get("tipoPersona");
			cq.where(cb.and(cb.equal(tipo, tipoPersona)));
			TypedQuery q = mgr.createQuery(cq);
			personas = q.getResultList();
			// mgr.getTransaction().begin();
			// Query qry =
			// mgr.createQuery("select codPersona, numeroDocumento, nombre, apellido, telefono, "
			// +
			// "direccion, tipoPersona, saldoDisponible from persona where tipoPersona = ?",
			// "Clientes");
			// qry.setParameter(1, 1);
			// return qry.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mgr != null)
				mgr.close();
		}
		return personas;
	}

	// Persona persona = new Persona();
	// persona.setNombre("Federico");
	// persona.setApellido("Daumas");
	// persona.setCodPersona("CLI_001");
	// persona.setDireccion("Cha 345");
	// persona.setNumeroDocumento("2518530");
	// persona.setSaldoDisponible(new BigDecimal("3000000"));
	// persona.setTelefono("660846");
	// persona.setTipoPersona(TipoPersona.CLIENTE);
	//
	// personas.add(persona);
	//
	// persona = new Persona();
	// persona.setNombre("Juan");
	// persona.setApellido("Perez");
	// persona.setCodPersona("CLI_002");
	// persona.setDireccion("Cha 346");
	// persona.setNumeroDocumento("2345632");
	// persona.setSaldoDisponible(new BigDecimal("8000000"));
	// persona.setTelefono("660666");
	// persona.setTipoPersona(TipoPersona.CLIENTE);
	//
	// personas.add(persona);
	//
	// persona = new Persona();
	// persona.setNombre("Pedro");
	// persona.setApellido("Perez");
	// persona.setCodPersona("CLI_003");
	// persona.setDireccion("Cha 346");
	// persona.setNumeroDocumento("2345633");
	// persona.setSaldoDisponible(new BigDecimal("2500000"));
	// persona.setTelefono("660666");
	// persona.setTipoPersona(TipoPersona.CLIENTE);
	//
	// personas.add(persona);
	//
	// return personas;

	public List<Producto> getProductos(){
		List<Producto> productos = new ArrayList<Producto>();
		
		EntityManager mgr = null;
		try{
			mgr = emf.createEntityManager(); 
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(Producto.class);
			Root personaEntity = cq.from(Producto.class);
			TypedQuery q = mgr.createQuery(cq);
			productos = q.getResultList();

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (mgr!=null) mgr.close();
		}
		return productos;
	}
	

	public boolean guardarPersona(Persona persona) {
		EntityManager mgr = null;
		try {
			mgr = emf.createEntityManager();
			mgr.getTransaction().begin();
			if (persona.getCodPersona() != null) {
				// TODO:ver como editar, probablemente con un query nativo..
				mgr.createNativeQuery(
						"update Persona set nombre = ? , apellido = ? , "
								+ "telefono = ? , direccion = ? where codPersona = ? ")
						.setParameter(1, persona.getNombre())
						.setParameter(2, persona.getApellido())
						.setParameter(3, persona.getTelefono())
						.setParameter(4, persona.getDireccion())
						.setParameter(5, persona.getCodPersona())
						.executeUpdate();
				mgr.getTransaction().commit();
				return true;
			} else {
				mgr.persist(persona);
				mgr.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (mgr != null)
				mgr.close();
		}
	}

	public boolean eliminarPersona(Persona persona) {
		EntityManager mgr = null;
		try {
			mgr = emf.createEntityManager();
			mgr.getTransaction().begin();
			if (persona.getCodPersona() != null) {
				mgr.createNativeQuery(
						"delete from persona where codPersona = ? ")
						.setParameter(1, persona.getCodPersona())
						.executeUpdate();
				mgr.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (mgr != null)
				mgr.close();
		}
		return false;
	}



	public void cargarPagos(InputStream file) {
		EntityManager mgr = null;
		try {
			mgr = emf.createEntityManager();
			//InputStream file = file.getInputstream();
			DataInputStream in = new DataInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String [] datos;
			while ((strLine = br.readLine()) != null) {
				datos = strLine.split(",");
				String codPago = datos[0];
				String codPersona = datos[1];
				String monto = datos[2];
				String fecha = datos[3];
				mgr.getTransaction().begin();
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
						regPago.setCodPersona(persona.getCodPersona());
						regPago.setFecha(fechaPago);
						regPago.setMonto(pago);
						regPago.setTipo("Archivo");
						mgr.persist(regPago);
						persona.setSaldoDisponible(persona.getSaldoDisponible().subtract(pago));
						mgr.getTransaction().commit();
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
		        	mgr.getTransaction().begin();
		        	// se genera un registro de pago erroneo
		        	RegistroPago regPago = new RegistroPago();
					regPago.setCodPago("ErrorPago - " + System.currentTimeMillis()); 
					regPago.setEstado(-1);
					regPago.setMensaje("Error Saldo menor al pago: " + persona.getSaldoDisponible());
					regPago.setCodPersona(persona.getCodPersona());
					regPago.setFecha(new Date(System.currentTimeMillis()));
					regPago.setMonto(pago);
					regPago.setTipo("Archivo");
					mgr.persist(regPago);
					mgr.getTransaction().commit();
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
					mgr.getTransaction().commit();
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
					mgr.getTransaction().commit();
				}
				
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			return;
		}

	}
	
	
		
		public boolean guardarProducto(Producto producto) {
			EntityManager mgr = null;
			try{
				mgr = emf.createEntityManager();
				mgr.getTransaction().begin();
				if (producto.getCodProducto()!=null){
					//TODO:ver como editar, probablemente con un query nativo..
					mgr.createNativeQuery("update Producto set cantidad = ? , descripcion = ? , " +
							"nombre = ? , precio = ? where codProducto = ? ")
							.setParameter(1, producto.getCantidad())
							.setParameter(2, producto.getDescripcion())
							.setParameter(3, producto.getNombre())
							.setParameter(4, producto.getPrecio())
							.setParameter(5, producto.getCodProducto())
							.executeUpdate();
					mgr.getTransaction().commit();
					return true;
				}else{
					mgr.persist(producto);
					mgr.getTransaction().commit();
					return true;
				}
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}finally{
				if (mgr!=null) mgr.close();
			}
			
		}


		public boolean eliminarProducto(Producto producto) {
				EntityManager mgr = null;
				try{
					mgr = emf.createEntityManager();
					mgr.getTransaction().begin();
					if (producto.getCodProducto()!=null){
						mgr.createNativeQuery("delete from producto where codProducto = ? ")
								.setParameter(1, producto.getCodProducto())
								.executeUpdate();
						mgr.getTransaction().commit();
						return true;
					}
				}catch (Exception e) {
					e.printStackTrace();
					return false;
				}finally{
					if (mgr!=null) mgr.close();
				}
				return false;
			
			
		}

		public boolean guardarCompra(Compra compra) {
			EntityManager em = emf.createEntityManager();
	        try {
	            // Se inicia la transaccion
	            em.getTransaction().begin();
	            em.persist(compra);
	            // Por cada detalle se actualiza la existencia del producto en
	            // cuestion aumentan su existencia en la cantidad comprada
	            for (CompraDetalle  cd : compra.getDetalles()) {
	                Producto p = em.find(Producto.class,
	                        cd.getProducto().getCodProducto());
	                if (cd.getCantidad()>=0) {
	                    p.setCantidad(p.getCantidad().add(new BigDecimal(cd.getCantidad())));
	                    em.merge(p);
	                } else {
	                    throw new Exception("No existe cantidad suficiente "
	                            + "para el Produto[" + p.getCodProducto() + "]");
	                }
	                // se aumenta el saldo del proveeedor
	                Persona prov = em.find(Persona.class, compra.getProveedor().getCodPersona());
	                Double vendido = cd.getCantidad() * cd.getPrecio();
	                prov.setSaldoDisponible(prov.getSaldoDisponible().add(new BigDecimal(vendido)));
	                em.merge(prov);
	            }
	            // se confirma la transaccion
	            em.getTransaction().commit();
	            return true;
	        } catch (Exception e) {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            e.printStackTrace();
	            return false;
	        } finally {
	            em.close();
	        }
		}

		public Persona getPersona(String id) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Persona p = em.find(Persona.class, id);
			em.getTransaction().commit();
			em.close();
			return p;
		}

		public Producto getProducto(String id) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Producto p = em.find(Producto.class, id);
			em.getTransaction().commit();
			em.close();
			return p;
		}
		
		
		public boolean guardarVenta(Venta venta){
	        // Se obtiene un entity manager
	        EntityManager em = emf.createEntityManager();
	        BigDecimal montoTotal = new BigDecimal(0);
	        try {
	            // Se inicia la transaccion
	            em.getTransaction().begin();

	           
	            em.persist(venta);
	           

	            // Por cada detalle se actualiza la existencia del producto en
	            // cuention disminuyensu existencia en la cantidad vendida
	           
	            Persona cliente = em.find(Persona.class, venta.getCliente().getCodPersona());
	            for (VentaDetalle vd : venta.getDetalles()) {
	                Producto p = em.find(Producto.class,
	                        vd.getProducto().getCodProducto());
	                if (vd.getCantidad() <= p.getCantidad().intValue()) {
	                    p.setCantidad(p.getCantidad().subtract(new BigDecimal(vd.getCantidad())));
	                    em.merge(p);
	                } else {
	                    return false;
	                }
	                
	                // se reduce el saldo del cliente
	                Double vendido = vd.getCantidad() * vd.getPrecio();
	                //if (cliente.getSaldoDisponible().compareTo(new BigDecimal(vendido))>=0){ // puede
		                cliente.setSaldoDisponible(cliente.getSaldoDisponible().subtract(new BigDecimal(vendido)));
		                em.merge(cliente);
	                //}
	                montoTotal = montoTotal.add(new BigDecimal(vendido));
	                
	            }
	            // se genera un registro de pago
	                RegistroPago regPago = new RegistroPago();
					regPago.setCodPago(montoTotal.toString()); // venta.codVenta deberia de ser.. =S
					regPago.setEstado(1);
					regPago.setMensaje("OK");
					regPago.setCodPersona(cliente.getCodPersona());
					regPago.setFecha(new Date(System.currentTimeMillis()));
					regPago.setMonto(montoTotal);
					regPago.setTipo("Web");
					em.persist(regPago);
	            
	            // se confirma la transaccion
	            em.getTransaction().commit();
	            return true;
	        }catch(RollbackException e){
	        	e.printStackTrace();
	        	if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	        	em.getTransaction().begin();
	        	Persona cliente = em.find(Persona.class, venta.getCliente().getCodPersona());
	        	// se genera un registro de pago erroneo
	        	RegistroPago regPago = new RegistroPago();
				regPago.setCodPago("ErrorPago - " + System.currentTimeMillis()); 
				regPago.setEstado(-1);
				regPago.setMensaje("Error Saldo menor al pago: " + cliente.getSaldoDisponible());
				regPago.setCodPersona(cliente.getCodPersona());
				regPago.setFecha(new Date(System.currentTimeMillis()));
				regPago.setMonto(montoTotal);
				regPago.setTipo("Web");
				em.persist(regPago);
				em.getTransaction().commit();
				return false;
	        }catch (Exception e) {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            e.printStackTrace();
	            return false;
	        } finally {
	            em.close();
	        }
	    }
		

			public List<Venta> getVentas() {
			List<Venta> ventas = new ArrayList<Venta>();
				
				EntityManager mgr = null;
				try{
					mgr = emf.createEntityManager(); 
					CriteriaBuilder cb = mgr.getCriteriaBuilder();
					CriteriaQuery cq = cb.createQuery(Venta.class);
					Root personaEntity = cq.from(Venta.class);
					TypedQuery q = mgr.createQuery(cq);
					ventas = q.getResultList();

				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					if (mgr!=null) mgr.close();
				}
				return ventas;
			}

			public List<Compra> getCompras() {
				List<Compra> compras = new ArrayList<Compra>();
				
				EntityManager mgr = null;
				try{
					mgr = emf.createEntityManager(); 
					CriteriaBuilder cb = mgr.getCriteriaBuilder();
					CriteriaQuery cq = cb.createQuery(Compra.class);
					Root personaEntity = cq.from(Compra.class);
					TypedQuery q = mgr.createQuery(cq);
					compras= q.getResultList();

				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					if (mgr!=null) mgr.close();
				}
				return compras;
			}

			public List<RegistroPago> getRegistroPagos() {
				List<RegistroPago> registroPagos = new ArrayList<RegistroPago>();
				
				EntityManager mgr = null;
				try{
					mgr = emf.createEntityManager(); 
					CriteriaBuilder cb = mgr.getCriteriaBuilder();
					CriteriaQuery cq = cb.createQuery(RegistroPago.class);
					Root personaEntity = cq.from(RegistroPago.class);
					TypedQuery q = mgr.createQuery(cq);
					registroPagos = q.getResultList();
					
			
					

				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					if (mgr!=null) mgr.close();
				}
				return registroPagos;
			}
		

}
