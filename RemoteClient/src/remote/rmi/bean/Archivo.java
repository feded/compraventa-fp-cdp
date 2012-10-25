package remote.rmi.bean;

import java.io.InputStream;
import java.io.Serializable;

public class Archivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InputStream stream;
	
	public Archivo(){
		
	}
	
	public Archivo(InputStream stream){
		this.stream = stream;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}
	
	
}
