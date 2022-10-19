/**
 * 
 */
package application.controller;

import application.model.Programa;

/**
 * @author GonzalezHDanielaA
 *
 */
public class CrudRegistroProgramaController {
	
	
	ModelFactoryController modelFactoryController;
	Programa programa;
	
	public CrudRegistroProgramaController(ModelFactoryController modelFactoryController)
	{
		this.modelFactoryController = modelFactoryController;
		programa = modelFactoryController.getUniversidad().getPrograma();
	}
	
	public Programa guardarPrograma(String codigo, String nombre, String modalidad)
	{
		return modelFactoryController.guardarPrograma(codigo, nombre, modalidad);
	}
	
	public Programa buscarPrograma(String codigo)
	{
		return modelFactoryController.buscarPrograma(codigo);
	}
	
	public Programa actualizarPrograma(String codigo, String nombre, String modalidad)
	{
		return modelFactoryController.actualizarPrograma(codigo, nombre, modalidad);
		
	}
	

}
