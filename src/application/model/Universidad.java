/**
 * 
 */
package application.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import application.model.Programa;
import application.persistence.Persistencia;
import application.service.IUniversidadService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Universidad implements Serializable,IUniversidadService{
	
	private static final long serialVersionUID = 1L;
	private Programa programa;
	private Estudiante estudiante;

	ArrayList<Programa> listaPrograma = new ArrayList<>();
	
	public Universidad() {}
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
	/**
	 * @return the listaPrograma
	 */
	public ArrayList<Programa> getListaPrograma() {
		return listaPrograma;
	}
	/**
	 * @param listaPrograma the listaPrograma to set
	 */
	public void setListaPrograma(ArrayList<Programa> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}
	@Override
	public Programa guardarPrograma(String codigo, String nombre, String modalidad)throws IOException {			
		Programa programa= null;
		programa = new Programa();
		
		programa.setCodigo(codigo);
		programa.setNombre(nombre);
		programa.setModalidad(modalidad);
		getListaPrograma().add(programa);
		System.out.println("Programa guardado");
		Persistencia.guardarRegistroLog("Guardar programa", 1, "Programar guardado");
		
		return programa;
	}
	
	@Override
	public String toString() {
		return "Universidad [programa=" + programa + ", estudiante=" + estudiante + ", listaPrograma=" + listaPrograma
				+ "]";
	}
	
	
	

	

}
