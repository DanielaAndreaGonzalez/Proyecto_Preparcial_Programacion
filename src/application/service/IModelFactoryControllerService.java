/**
 * 
 */
package application.service;

import application.model.Estudiante;
import application.model.Programa;

/**
 * @author GonzalezHDanielaA
 *
 */
public interface IModelFactoryControllerService {
	
	public Estudiante guardarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3);
	
	public Estudiante buscarEstudiante (String codigo);
	
	public Estudiante actualizarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3);
	
	public boolean eliminarEstudiante(String codigo);
	
	public Programa guardarPrograma(String codigo, String nombre, String modalidad);
	
	public Programa buscarPrograma (String codigo);
	
	public Programa actualizarPrograma(String codigo, String nombre, String modalidad);
	
	
	
}
