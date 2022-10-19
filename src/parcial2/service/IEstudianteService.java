/**
 * 
 */
package parcial2.service;

import java.io.IOException;

import parcial2.model.Estudiante;

/**
 * @author GonzalezHDanielaA
 *
 */
public interface IEstudianteService {
	
	
	public Estudiante guardarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3) throws IOException;

	public Estudiante buscarEstudiante(String codigo);
	
	public Estudiante actualizarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3);
}
