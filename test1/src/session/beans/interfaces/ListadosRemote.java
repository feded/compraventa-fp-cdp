package session.beans.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.beans.Persona;

@Remote
public interface ListadosRemote {
	public List<Persona> getClientes();
}
