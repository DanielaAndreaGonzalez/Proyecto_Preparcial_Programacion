/**
 * 
 */
package application.model;

import java.io.Serializable;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Programa implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private String nombre;
	private String codigo;
	private String modalidad;
	
	public Programa(){
		
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}
	/**
	 * @param modalidad the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}


	@Override
	public String toString() {
		return "Programa [nombre=" + nombre + ", codigo=" + codigo + ", modalidad=" + modalidad + "]";
	}
	
	
	
	
	

}
