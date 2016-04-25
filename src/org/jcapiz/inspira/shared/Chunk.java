package org.jcapiz.inspira.shared;

import java.io.Serializable;

public class Chunk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -127390596435145852L;
	
	private UnidadAprendizaje[] unidadesAprendizaje;
	private String boleta;
	
	public Chunk(UnidadAprendizaje[] unidadesAprendizaje, String boleta){
		this.unidadesAprendizaje = unidadesAprendizaje;
		this.boleta = boleta;
	}
	
	public String getBoleta(){
		return boleta;
	}
	
	public UnidadAprendizaje[] getUnidadesAprendizaje(){
		return unidadesAprendizaje;
	}

}
