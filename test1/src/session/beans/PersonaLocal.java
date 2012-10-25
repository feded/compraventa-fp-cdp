package session.beans;

import javax.ejb.Local;

import entity.beans.Persona;

@Local
public interface PersonaLocal {
	public boolean guardarPersona(Persona persona);
	public boolean eliminarPersona(Persona persona);
	
}
