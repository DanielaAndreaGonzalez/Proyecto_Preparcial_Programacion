/**
 * 
 */
package application.service;

import java.io.IOException;

import application.model.Programa;

/**
 * @author GonzalezHDanielaA
 *
 */
public interface IUniversidadService {
	
	public Programa guardarPrograma(String codigo, String nombre, String modalidad) throws IOException;

}
