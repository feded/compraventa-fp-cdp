package entity.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Check;

@Entity
@Check(constraints =  "saldoDisponible >= 0")
public class Persona implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum TipoPersona{
		PROVEEDOR, CLIENTE;
		
		public int toInt(){
			switch (this) {
			case CLIENTE:
				return 1;
			case PROVEEDOR:
				return 2;
			default:
				return -1;
			}
		}
		
		public static TipoPersona fromInt(int i){
			switch (i) {
			case 1:
				return CLIENTE;
			case 2:
				return PROVEEDOR;
			default:
				return null;
			}
		}
	}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer codPersona;
	@Column //esto no hace falta poner
	private String numeroDocumento;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private String telefono;
	@Column
	private String direccion;
	@Column
	private int tipoPersona;
	@Column
	private BigDecimal saldoDisponible; // solo si es cliente se carga
	
	
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(int tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	
	
}
