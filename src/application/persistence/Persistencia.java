/**
 * 
 */
package application.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import application.model.Estudiante;
import application.model.Programa;
import application.model.Universidad;


/**
 * @author GonzalezHDanielaA
 *
 */
public class Persistencia {
	
	//public static final String RUTA_ARCHIVO_EMPLEADOS = "src/resources/archivoEmpleados.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "C://td//persistencia//Archivos//";
	public static final String RUTA_ARCHIVO_LOG = "src//application//resource//logEstudiantes.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO = "C://td//persistencia//usuarioBin.dat";
	public static final String RUTA_ARCHIVO_MODELO_PROGRAMA_XML = "src/application/resource/model.xml";
	
	public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/application/resource/estudiantes.txt";
	
	
	public static final String SEPARADOR = ",";
	
	
//	public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExcepcion {
//		
//		if(validarUsuario(usuario,contrasenia)) {
//			return true;
//		}else {
//			throw new UsuarioExcepcion("Usuario no existe");
//		}
//		
//	}
//	
//	private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException 
//	{
//		SubastaQuindio subastaQuindio=  Persistencia.cargarRecursoSubastaQuindioBinario();
//		
//		ArrayList<Persona> usuarios = subastaQuindio.getListaPersonas();
//		
//		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
//		{
//			Usuario usuarioAux = usuarios.get(indiceUsuario);
//			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
//				return true;
//			}
//		}
//		return false;
//	}
	public static Programa guardarPrograma(String codigo, String nombre, String modalidad)throws IOException {			
		Programa programa= null;
		programa = new Programa();
		
		programa.setCodigo(codigo);
		programa.setNombre(nombre);
		programa.setModalidad(modalidad);
		//getListaPrograma().add(programa);
		System.out.println("Programa guardado");
		Persistencia.guardarRegistroLog("Guardar programa", 1, "Programar guardado");
		
		return programa;
	}
	
	
	
	public static void guardarRecursoProgramaXML(Universidad programa)
	{
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PROGRAMA_XML, programa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("hola");
			e.printStackTrace();
		}	
	}
	public static void guardarEstudiantes(ArrayList<Estudiante> estudiantes) throws IOException
	{
		String contenido = "";
		for(Estudiante est : estudiantes)
		{
			contenido += est.getCodigo()+SEPARADOR+est.getNombre()+SEPARADOR+est.getNota1()+
					SEPARADOR+est.getNota2()+SEPARADOR+est.getNota3()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido, true);
	}
	
	
	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}
	
	
	public static ArrayList<Estudiante> cargarInformacionEstudiante() throws IOException
	{
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ESTUDIANTES);
		String linea = "";
		for(int i=0; i<contenido.size();i++)
		{
			linea = contenido.get(i);
			Estudiante estudiante = new Estudiante();
			
			estudiante.setCodigo(linea.split(",")[0]);
			estudiante.setNombre(linea.split(",")[1]);
			estudiante.setNota1(Double.parseDouble(linea.split(",")[2]));
			estudiante.setNota2(Double.parseDouble(linea.split(",")[3]));
			estudiante.setNota3(Double.parseDouble(linea.split(",")[4]));
			estudiantes.add(estudiante);	
		}
		return estudiantes;
	}
	
	public static Estudiante actualizarEstudiante(String codigo, String nombre, double nota1, double nota2, double nota3) throws IOException
	{
		ArrayList<Estudiante> estudiantes = cargarInformacionEstudiante();
		Estudiante estudiante = null;
		//estudiante = Persistencia.buscarEst(codigo);
					
		
		for (int i = 0; i < estudiantes.size(); i++) {	
			   if(estudiantes.get(i).getCodigo().equalsIgnoreCase(codigo))
			   {
				   estudiantes.get(i).setCodigo(codigo);
				   estudiantes.get(i).setNombre(nombre);
				   estudiantes.get(i).setNota1(nota1);
				   estudiantes.get(i).setNota2(nota2);
				   estudiantes.get(i).setNota3(nota3);
				   estudiante = estudiantes.get(i);
				   ArchivoUtil.eliminarArchivo(RUTA_ARCHIVO_ESTUDIANTES);
				   Persistencia.guardarEstudiantes(estudiantes);
			   }
		}
		
		return estudiante;	
	}
	
	public static boolean eliminarEstudiante(String codigo) throws IOException
	{
		boolean eliminado=false;
		ArrayList<Estudiante> estudiantes = cargarInformacionEstudiante();
		Estudiante estudiante = buscarEst(codigo);
		ArchivoUtil.eliminarArchivo(RUTA_ARCHIVO_ESTUDIANTES);
		for (int i = 0; i < estudiantes.size(); i++) {	
			   if(estudiantes.get(i).getCodigo().equalsIgnoreCase(codigo))
			   {
				   estudiantes.remove(estudiantes.get(i));
				   eliminado = true;
			   }	
		}
		Persistencia.guardarEstudiantes(estudiantes);
		return eliminado;
	}
	
	public static boolean eliminarPrograma(String codigo)
	{
		boolean eliminado = false;
		//ArrayList<Programa> programas = cargarInformacionPrograma();
		Universidad universidad = Persistencia.cargarRecursoUniversidadXML();	
		for(int i=0; i<universidad.getListaPrograma().size();i++)
		{
			if(universidad.getListaPrograma().get(i).getCodigo().equalsIgnoreCase(codigo)) {
				universidad.getListaPrograma().remove(universidad.getListaPrograma().get(i));
				eliminado=true;
				ArchivoUtil.eliminarArchivo(RUTA_ARCHIVO_MODELO_PROGRAMA_XML);
				Persistencia.guardarRecursoProgramaXML(universidad);
				break;
			}
		}
		return eliminado;		
	}
	
	
	public static ArrayList<Programa> cargarInformacionPrograma()
	{
		ArrayList<Programa> programas = new ArrayList<>();
		Universidad programa = Persistencia.cargarRecursoUniversidadXML();
		programas = programa.getListaPrograma();
		return programas;
	}
	/**
	 * 
	 * @return
	 */
	public static Universidad cargarRecursoUniversidadXML() {
		Universidad universidad = null;
		try {
			universidad = (Universidad) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PROGRAMA_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return universidad;
	}
	
	public static Estudiante buscarEst(String codigo) throws IOException
	{
		ArrayList<Estudiante> lista = Persistencia.cargarInformacionEstudiante();
		
		Estudiante estudianteEncontrado = new Estudiante();
		
		for(int i =0; i<lista.size(); i++)
		{
			Estudiante estudianteAux = lista.get(i);
			if(estudianteAux.getCodigo().equalsIgnoreCase(codigo))
			{
				estudianteEncontrado = estudianteAux;
			}	
		}
		return estudianteEncontrado;
	}
	
	
	public static Programa buscarPrograma(String codigo)throws IOException
	{
		ArrayList<Programa> listaProg = Persistencia.cargarInformacionPrograma();
		Programa programaEncontrado = new Programa();
		for(int i=0; i<listaProg.size(); i++)
		{
			Programa programaAux = listaProg.get(i);
			if(programaAux.getCodigo().equalsIgnoreCase(codigo))
			{
				programaEncontrado = programaAux;
			}
		}
		return programaEncontrado;
	}
	
	public static Programa actualizarPrograma(String codigo, String nombre, String modalidad)
	{
		ArrayList<Programa> listaProg = Persistencia.cargarInformacionPrograma();
		Programa programa = new Programa();
		Universidad universidad = Persistencia.cargarRecursoUniversidadXML();
		for(int i=0; i<listaProg.size();i++)
		{	
			if(listaProg.get(i).getCodigo().equals(codigo))
			{	
				universidad.getListaPrograma().get(i).setCodigo(codigo);
				universidad.getListaPrograma().get(i).setNombre(nombre);
				universidad.getListaPrograma().get(i).setModalidad(modalidad);
				programa = listaProg.get(i);
				ArchivoUtil.eliminarArchivo(RUTA_ARCHIVO_MODELO_PROGRAMA_XML);
				Persistencia.guardarRecursoProgramaXML(universidad);
			}	
		}
		return programa;
	}
	
//	/**
//	 * 
//	 * @param nombreArchivoOrigen nombre del archivo destino con extencion
//	 * @param nombreArchivoDestino nombre del archivo destino sin extencion
//	 */
//	public static void hacerBackupArchivo(String nombreArchivoOrigen, String nombreArchivoDestino) {			
//		String rutaOrigen = RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL + nombreArchivoOrigen;
//		String rutaOrigenDestino = RUTA_ARCHIVO_COPIA_DESTINO_GENERAL + nombreArchivoDestino;
//		ArchivoUtil.hacerBackupArchivo(rutaOrigen,rutaOrigenDestino);
//	}
	
//	/**
//	 * 
//	 * @param nombreArchivoOrigen nombre del archivo destino con extencion
//	 * @param nombreArchivoDestino nombre del archivo destino sin extencion
//	 */
//	public static void hacerBackupArchivosSerializados(String nombreArchivoOrigen, String nombreArchivoDestino, String extension) {			
//		String rutaOrigen = RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL_SERIAL + nombreArchivoOrigen;
//		String rutaOrigenDestino = RUTA_ARCHIVO_COPIA_DESTINO_GENERAL + nombreArchivoDestino;
//		ArchivoUtil.hacerBackupArchivo(rutaOrigen,rutaOrigenDestino);
//	}
	/**
	 * 
	 * @param cedula
	 * @return
	 */
	public static boolean buscarPersona(String cedula)
	{
		boolean bandera=false;
		try {
			if(ArchivoUtil.searchPerson(cedula)) {
				bandera= true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bandera;
	}

}
