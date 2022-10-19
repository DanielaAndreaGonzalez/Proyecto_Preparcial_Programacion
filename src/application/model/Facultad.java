/**
 * 
 */
package application.model;

import java.io.Serializable;

import application.model.Programa;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Facultad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Programa programa;

	
	
	public Facultad() {}
	
	/**
	 * @return the programa
	 */
	public Programa getPrograma() {
		return programa;
	}

	/**
	 * @param programa the programa to set
	 */
	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
	

}
