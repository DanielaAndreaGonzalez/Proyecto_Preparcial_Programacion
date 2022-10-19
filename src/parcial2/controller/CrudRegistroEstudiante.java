/**
 * 
 */
package parcial2.controller;

import parcial2.model.Estudiante;

/**
 * @author GonzalezHDanielaA
 *
 */
public class CrudRegistroEstudiante {
	
	ModelFactoryController modelFactoryController;
	Estudiante estudiante;
	
	public CrudRegistroEstudiante(ModelFactoryController modelFactoryController)
	{
		this.modelFactoryController = modelFactoryController;
		estudiante = modelFactoryController.getEstudiante();
	}
	
	
	public Estudiante guardarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3)
	{
		return modelFactoryController.guardarEstudiante(codigo, nombre, nota1, nota2, nota3);
	}
	
	public Estudiante buscarEstudiante(String codigo)
	{
		return modelFactoryController.buscarEstudiante(codigo);
	}
	
	public Estudiante actualizarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3)
	{
		return modelFactoryController.actualizarEstudiante(codigo, nombre, nota1, nota2, nota3);
	}
	
	public boolean eliminarEstudiante(String codigo)
	{
		return modelFactoryController.eliminarEstudiante(codigo);
	}

}
