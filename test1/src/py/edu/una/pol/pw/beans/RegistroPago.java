package py.edu.una.pol.pw.beans;

import java.io.Serializable;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RegistroPago
 *
 */
@Entity

public class RegistroPago implements Serializable {

	   
	@Id
	private String codPago;
	private String mensaje;
	private int estado;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private String codPersona;
	private BigDecimal monto;
	private String tipo;
	
	
	
	
	private static final long serialVersionUID = 1L;

	public RegistroPago() {
		super();
	}   
	public String getCodPago() {
		return this.codPago;
	}

	public void setCodPago(String codPago) {
		this.codPago = codPago;
	}   
	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}   
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(String codPersona) {
		this.codPersona = codPersona;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
   
}
