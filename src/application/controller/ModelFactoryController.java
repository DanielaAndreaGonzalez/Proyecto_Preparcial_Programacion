/**
 * 
 */
package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import application.model.Estudiante;
import application.model.Programa;
import application.model.Universidad;
import application.persistence.Persistencia;
import application.service.IModelFactoryControllerService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class ModelFactoryController implements IModelFactoryControllerService{

	
	private static Persistencia persistencia =null;
	private Estudiante estudiante;
	private Universidad universidad;
	private Programa programa;
	
	//***********************************Singleton***********************************************
	//Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder{
		//El constructor de Singleton puede ser llamado desde aquí al ser protected 
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}
	
	
	//Metodo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance()
	{
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController()
	{
		
		inicializarDatos();
	}
	
	
	private void inicializarDatos()
	{
		 estudiante = new Estudiante();
		 universidad = new Universidad();
		 cargarXML();
		 
		 
		 
	}
	/**
	 * @return the persistencia
	 */
	public static Persistencia getPersistencia() {
		return persistencia;
	}
	/**
	 * @param persistencia the persistencia to set
	 */
	public static void setPersistencia(Persistencia persistencia) {
		ModelFactoryController.persistencia = persistencia;
	}
	
	/**
	 * @return the universidad
	 */
	public Universidad getUniversidad() {
		return universidad;
	}
	/**
	 * @param universidad the universidad to set
	 */
	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}
	/**
	 * @return the estudiante
	 */
	public Estudiante getEstudiante() {
		return estudiante;
	}

	/**
	 * @param estudiante the estudiante to set
	 */
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
	public ArrayList<Estudiante> ObtenerListaEstudiantes()
	{
		ArrayList<Estudiante> lista = new ArrayList<>();
		try {
			lista = Persistencia.cargarInformacionEstudiante();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
		
	@Override
	public Estudiante guardarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3) {
		
		Estudiante estudianteNuevo =null;
		try {
			estudianteNuevo = getEstudiante().guardarEstudiante(codigo, nombre, nota1, nota2, nota3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estudianteNuevo;
	}
	
	@Override
	public Estudiante actualizarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3) {
		
		
		Estudiante estudianteActualizar = null;		
		try {
			estudianteActualizar = Persistencia.actualizarEstudiante(codigo,nombre, nota1,nota2,nota3);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estudianteActualizar;
	}
	
	@Override
	public boolean eliminarEstudiante(String codigo) {
		
		boolean eliminado = false;
		try {
			eliminado = Persistencia.eliminarEstudiante(codigo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eliminado;
	}

	@Override
	public Estudiante buscarEstudiante(String codigo) {
		
		Estudiante estudianteEncontrado = new Estudiante();
		try {
			estudianteEncontrado = Persistencia.buscarEst(codigo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estudianteEncontrado;
	}

	@Override
	public Programa guardarPrograma(String codigo, String nombre, String modalidad) {
		
		//Programa programa = new Programa();
		try {
			programa = getUniversidad().guardarPrograma(codigo, nombre, modalidad);
			universidad.setPrograma(programa);
			Persistencia.guardarRecursoProgramaXML(universidad);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programa;
	}
	
	private void cargarXML()
	{
		universidad = Persistencia.cargarRecursoUniversidadXML();
	}

	@Override
	public Programa buscarPrograma(String codigo) {
		
		Programa programaEncontrado = null;
		try {
			programaEncontrado = Persistencia.buscarPrograma(codigo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programaEncontrado;
	}

	@Override
	public Programa actualizarPrograma(String codigo, String nombre, String modalidad) {
		Programa programa_actualizado = null;
		programa_actualizado=Persistencia.actualizarPrograma(codigo, nombre, modalidad);	
		return programa_actualizado;
	}

	

	
	
	
	

}
