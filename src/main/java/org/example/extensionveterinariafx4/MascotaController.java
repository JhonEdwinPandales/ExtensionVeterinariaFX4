package org.example.extensionveterinariafx4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.extensionveterinariafx4.model.*;

public class MascotaController {
    @FXML private TextField txtNombre;
    @FXML private TextField txtRaza;
    @FXML private TextField txtEdad;
    @FXML private TextField txtPeso;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<String> cmbTipoMascota;
    @FXML private ComboBox<String> cmbTamano;
    @FXML private TableView<Mascota> tablaMascotas;
    @FXML private TableColumn<Mascota, String> colNombre;
    @FXML private TableColumn<Mascota, String> colRaza;
    @FXML private TableColumn<Mascota, String> colCategoria;
    @FXML private Label lblEstado;

    private final ObservableList<Mascota> listaMascotas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbTipoMascota.getItems().addAll("Perro", "Gato", "Ave", "Reptil");
        cmbTamano.getItems().addAll("PEQUENO", "MEDIANO", "GRANDE");

        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        colRaza.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getRaza()));
        colCategoria.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(
                cell.getValue().getCategoriaEdad() != null ? cell.getValue().getCategoriaEdad().toString() : "N/A"
        ));

        tablaMascotas.setItems(listaMascotas);
    }

    @FXML
    public void agregarMascota() {
        try {
            String nombre = txtNombre.getText().trim();
            String raza = txtRaza.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            double peso = Double.parseDouble(txtPeso.getText().trim());
            String codigo = txtCodigo.getText().trim();
            String responsableNombre = txtResponsable.getText().trim();
            Responsable responsable = new Responsable(responsableNombre);

            String tipo = cmbTipoMascota.getValue();
            Mascota mascota;

            switch (tipo) {
                case "Perro":
                    Tamano tam = Tamano.valueOf(cmbTamano.getValue());
                    mascota = new Perro(nombre, raza, edad, peso, codigo, responsable, tam, "Intermedio", true);
                    break;
                case "Gato":
                    mascota = new Gato(nombre, raza, edad, peso, codigo, responsable, EsIndoor.INDOOR, 14, "Alta");
                    break;
                case "Ave":
                    mascota = new Ave(nombre, raza, edad, peso, codigo, responsable, "Común", true, 10);
                    break;
                case "Reptil":
                    mascota = new Reptil(nombre, raza, edad, peso, codigo, responsable, TipoHabitat.TERRESTRE, 28.0, "MEDIO");
                    break;
                default:
                    lblEstado.setText("Selecciona un tipo válido");
                    return;
            }

            listaMascotas.add(mascota);
            lblEstado.setText("Mascota agregada ✓");
            limpiarCampos();
        } catch (Exception e) {
            lblEstado.setText("Error: revisa los datos");
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtPeso.clear();
        txtCodigo.clear();
        txtResponsable.clear();
        cmbTipoMascota.getSelectionModel().clearSelection();
        cmbTamano.getSelectionModel().clearSelection();
    }
}
