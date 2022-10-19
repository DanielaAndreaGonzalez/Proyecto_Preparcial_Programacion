/**
 * 
 */
package parcial2.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import parcial2.persistence.Persistencia;
import parcial2.service.IEstudianteService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Estudiante implements  Serializable,IEstudianteService{
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String codigo;
	private double nota1;
	private double nota2;
	private double nota3;
	
	private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
	
	public Estudiante() {}


	
	/**
	 * @return the listaEstudiantes
	 */
	public ArrayList<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}



	/**
	 * @param listaEstudiantes the listaEstudiantes to set
	 */
	public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
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
	 * @return the nota1
	 */
	public double getNota1() {
		return nota1;
	}
	/**
	 * @param nota1 the nota1 to set
	 */
	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}
	/**
	 * @return the nota2
	 */
	public double getNota2() {
		return nota2;
	}
	/**
	 * @param nota2 the nota2 to set
	 */
	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}
	/**
	 * @return the nota3
	 */
	public double getNota3() {
		return nota3;
	}
	/**
	 * @param nota3 the nota3 to set
	 */
	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}
	@Override
	public Estudiante guardarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3) throws IOException {
		String contenido = "";
		Estudiante estudiante = null;
		
			estudiante = new Estudiante();
			estudiante.setCodigo(codigo);
			estudiante.setNombre(nombre);
			estudiante.setNota1(nota1);
			estudiante.setNota2(nota2);
			estudiante.setNota3(nota3);
			listaEstudiantes.add(estudiante);
			
			contenido += "nombre" +estudiante.getNombre()+ "codigo"+estudiante.getCodigo()+
					"notas: "+estudiante.getNota1()+"-"+estudiante.getNota2()+"-"+
					estudiante.getNota3();
			Persistencia.guardarEstudiantes(listaEstudiantes);
			Persistencia.guardarRegistroLog(contenido+"Estudiante - GuardarEstudiante ", 1, "Guardado de estudiantes");
			System.out.println("Se agregó el estudiante");
		return estudiante;
	}

	@Override
	public Estudiante actualizarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3) {
		
		
		
		
		return null;
	}
	
	

	@Override
	public Estudiante buscarEstudiante(String codigo) {
			
		return null;
	}



	
	
	
	
	
	
	

}
