/**
 * 
 */
package parcial2.service;

import java.io.IOException;

import parcial2.model.Programa;

/**
 * @author GonzalezHDanielaA
 *
 */
public interface IUniversidadService {
	
	public Programa guardarPrograma(String codigo, String nombre, String modalidad) throws IOException;

}
