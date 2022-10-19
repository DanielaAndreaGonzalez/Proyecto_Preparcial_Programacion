/**
 * 
 */
package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.JOptionPane;

import application.Main;
import application.controller.CrudRegistroEstudiante;
import application.controller.CrudRegistroProgramaController;
import application.controller.ModelFactoryController;
import application.model.Estudiante;
import application.model.Programa;
import application.persistence.ArchivoUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * @author GonzalezHDanielaA
 *
 */
public class PrincipalViewController {
	
	Main aplication;
	
	ModelFactoryController modelFactoryController;
	CrudRegistroEstudiante crudRegistroEstudiante;
	CrudRegistroProgramaController crudRegistroProgramaController;
	ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
	ObservableList<Programa> listaPrograma = FXCollections.observableArrayList();
	
	Estudiante estudiante;
	Programa programa;
	
	
	public ObservableList<Estudiante> getListaEstudiantesData()
	{
		listaEstudiantes.addAll(modelFactoryController.ObtenerListaEstudiantes());
		return listaEstudiantes;
	}
	
	public ObservableList<Programa> getListaProgramaData()
	{
		listaPrograma.addAll(modelFactoryController.obtenerListaPrograma());
		return listaPrograma;
	}
	
	@FXML
    private Button btnBuscarPrograma;
	
	@FXML
    private TextField txtNombreEst;

    @FXML
    private TextField txtNota3Est;

    @FXML
    private Button btnBuscarEst;

    @FXML
    private TextField txtNota2Est;

    @FXML
    private TextField txtNota1Est;

    @FXML
    private Button btnGuardarEst;

    @FXML
    private TextField txtCodigoEst;
    
    @FXML
    private TextField txtNombrePrograma;

    @FXML
    private Button btnGuardarPrograma;
    
    @FXML
    private Button btnActualizarPrograma;

    @FXML
    private TextField txtCodigoPrograma;
    
    @FXML
    private TableColumn<Estudiante, String> columnCodigoEstudiante;

    @FXML
    private TableColumn<Estudiante, String> columnNombreEstudiante;

    @FXML
    private TableColumn<Estudiante, Double> columnNota1;

    @FXML
    private TableColumn<Estudiante, Double> columnNota2;

    @FXML
    private TableColumn<Estudiante, Double> columnNota3;

    @FXML
    private TableView<Estudiante> tblEstudiantes;
    
    @FXML
    private TableColumn<Programa, String> columnCodigoPrograma;
    
    @FXML
    private TableColumn<Programa, String> columnNombrePrograma;
    
    @FXML
    private TableColumn<Programa, String> columnModalidadPrograma;
    
    @FXML
    private TableView<Programa> tblPrograma;
    
    @FXML
    private Button btnEliminarPrograma;
   
    @FXML
    private ComboBox<String> cboModalidad;
    
    
    @FXML
	void initialize()
	{
    	modelFactoryController = ModelFactoryController.getInstance();
    	crudRegistroEstudiante = new CrudRegistroEstudiante(modelFactoryController);
    	crudRegistroProgramaController = new CrudRegistroProgramaController(modelFactoryController);
    	cboModalidad.getItems().addAll(ArchivoUtil.cargarProperties("modalidad").split(","));
    	
    	
    	
    	//Tabla Estudiantes
    	this.columnCodigoEstudiante.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	this.columnNombreEstudiante.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	this.columnNota1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
    	this.columnNota2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
    	this.columnNota3.setCellValueFactory(new PropertyValueFactory<>("nota3"));
    	
    	tblEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    
    	estudiante = newSelection;
    	mostrarInformacionEstudiante(newSelection);	
    	});
    	
    	//Tabla Programa
    	
    	this.columnCodigoPrograma.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	this.columnNombrePrograma.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	this.columnModalidadPrograma.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
    	
    	tblPrograma.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {		
    	programa = newSelection;
    	mostrarInformacionPrograma(newSelection);
    	});
    	
	}
    
    @FXML
    void guardarAction(ActionEvent event) {
    	guardarEstudiante();
    }
    
    @FXML
    void buscarEstudianteAction(ActionEvent event) {
    	buscarEstudiante();
    }
    
    @FXML
    void actualizarEstAction(ActionEvent event) {
    	actualizarEstudiante();
    }
    
    @FXML
    void eliminarEstAction(ActionEvent event) {
    	eliminarEstudiante();
    }
	 
    @FXML
    void guardarProgramaAction(ActionEvent event) {
    	guardarPrograma();
    }
    
    @FXML
    void buscarProgramaAction(ActionEvent event) {
    	
    	buscarPrograma();
    }
    @FXML
    void actualizarProgramaAction(ActionEvent event) {
    		
    	actualizarPrograma();
    } 
   
    @FXML
    void eliminarProgramaAction(ActionEvent event) {
    	eliminarPrograma();
    }
    
    
    public void setAplicacion(Main aplicacion) {
    	this.aplication = aplicacion;
    	tblEstudiantes.getItems().clear();
    	tblEstudiantes.setItems(getListaEstudiantesData()); 	
    	
    	tblPrograma.getItems().clear();
    	tblPrograma.setItems(getListaProgramaData());
    }
    
    
    public void guardarEstudiante()
    {
    	 String codigo = txtCodigoEst.getText();
    	 String nombre = txtNombreEst.getText();
    	 double nota1 = Double.parseDouble(txtNota1Est.getText());
    	 double nota2 = Double.parseDouble(txtNota2Est.getText());
    	 double nota3 = Double.parseDouble(txtNota3Est.getText());
    	 //Estudiante estudiante = null;	 
    	 estudiante = crudRegistroEstudiante.guardarEstudiante(codigo, nombre, nota1, nota2, nota3);
    	 listaEstudiantes.add(estudiante);
    }
    
    public void buscarEstudiante()
    {
    	String codigo = txtCodigoEst.getText();
    	Estudiante estudiante = new Estudiante();
    	
    	estudiante = crudRegistroEstudiante.buscarEstudiante(codigo);
    	txtNombreEst.setText(estudiante.getNombre().toString());
    	txtNota1Est.setText(""+estudiante.getNota1());
    	txtNota2Est.setText(""+estudiante.getNota2());
    	txtNota3Est.setText(""+estudiante.getNota3());
    	System.out.println(estudiante.getNombre().toString());
    	System.out.println(estudiante.getNota1()+"-"+estudiante.getNota2()+"-"+
    						estudiante.getNota3());
    }

    public void actualizarEstudiante()
    {
    	String codigo = txtCodigoEst.getText();
    	String nombre = txtNombreEst.getText();
		double nota1 = Double.parseDouble(txtNota1Est.getText());
		double nota2 = Double.parseDouble(txtNota2Est.getText());
		double nota3 = Double.parseDouble(txtNota3Est.getText());
		
		//Estudiante estudiante= null;
		estudiante = crudRegistroEstudiante.actualizarEstudiante(codigo, nombre, nota1, nota2, nota3);
		tblEstudiantes.refresh();
		System.out.println("Estudiante actualizado");
    	
    }
    
    
    public void mostrarInformacionEstudiante(Estudiante estudianteSeleccionado)
    {
    	if(estudianteSeleccionado != null)
    	{
    		txtCodigoEst.setText(estudianteSeleccionado.getCodigo());
    		txtNombreEst.setText(estudianteSeleccionado.getNombre());
    		txtNota1Est.setText(""+estudianteSeleccionado.getNota1());
    		txtNota2Est.setText(""+estudianteSeleccionado.getNota2());
    		txtNota3Est.setText(""+estudianteSeleccionado.getNota3());
    	}
     }
    
    public void mostrarInformacionPrograma(Programa programaSeleccionado)
    {
    	if(programaSeleccionado != null)
    	{
    		txtCodigoPrograma.setText(programaSeleccionado.getCodigo());
    		txtNombrePrograma.setText(programaSeleccionado.getNombre());
    		cboModalidad.setValue(programaSeleccionado.getModalidad());
    	}
    }
    
    
    public void eliminarEstudiante()
    {
    	String codigo = txtCodigoEst.getText();
    	boolean eliminado =  crudRegistroEstudiante.eliminarEstudiante(codigo);
    	if(eliminado)
    	{
    		System.out.println("Se eliminó con éxito");
    		listaEstudiantes.remove(estudiante);
    		tblEstudiantes.getSelectionModel().clearSelection();
    	}	
    }
    
    public void guardarPrograma()
    {
    	String codigo = txtCodigoPrograma.getText();
    	String nombre = txtNombrePrograma.getText();
    	String modalidad = cboModalidad.getValue().toString();
    	Programa programa = null;
    	programa = crudRegistroProgramaController.guardarPrograma(codigo, nombre, modalidad);
    }
    
    
    public void buscarPrograma()
    {
    	String codigo =txtCodigoPrograma.getText();
    	Programa programa = new Programa();
    	
    	programa = crudRegistroProgramaController.buscarPrograma(codigo);
    	txtNombrePrograma.setText(programa.getNombre());
    	cboModalidad.setValue(programa.getModalidad());
    	System.out.println("Codigo" + programa.getCodigo()+" Modalidad: "+programa.getModalidad()+
    						" nombre: "+programa.getNombre());	
    }
    
 
    public void actualizarPrograma()
    {
    	String codigo = txtCodigoPrograma.getText();
    	String nombre = txtNombrePrograma.getText();
    	String modalidad = cboModalidad.getValue().toString();
    	
    	//Programa programa = null;
    	programa = crudRegistroProgramaController.actualizarPrograma(codigo, nombre, modalidad);
    	System.out.println("Programa actualizado");	
    }
    
    public void eliminarPrograma()
    {
    	String codigo = txtCodigoPrograma.getText();
    	boolean eliminado = crudRegistroProgramaController.eliminarPrograma(codigo);
    	if(eliminado)
    	{
    		listaPrograma.remove(programa);
    		tblPrograma.getSelectionModel().clearSelection();
    		JOptionPane.showMessageDialog(null, "Programa eliminado");
    	}
    }
    
    
}
