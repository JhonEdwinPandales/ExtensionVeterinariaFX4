package org.example.extensionveterinariafx4.controller;

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
        cmbTipoMascota.getItems().addAll("Perro", "Gato");
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
            // Capturar los datos del formulario
            String nombre = txtNombre.getText();
            String raza = txtRaza.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            String codigo = txtCodigo.getText();
            String responsableNombre = txtResponsable.getText();

            Responsable responsable = new Responsable();
            responsable.setNombreCompleto(responsableNombre);

            String tipo = cmbTipoMascota.getValue();
            Mascota mascota;

            // Creación según tipo
            if ("Perro".equals(tipo)) {
                Tamano tamano = Tamano.valueOf(cmbTamano.getValue());
                mascota = new Perro(nombre, raza, edad, peso, codigo, responsable, tamano, "Intermedio", true);
            } else if ("Gato".equals(tipo)) {
                // Valores predeterminados para los atributos del gato
                EsIndoor indoor = EsIndoor.INDOOR;
                int horasSueno = 14;
                String nivelIndependencia = "Alto";

                mascota = new Gato(nombre, raza, edad, peso, codigo, responsable, indoor, horasSueno, nivelIndependencia);
            } else {
                lblEstado.setText("⚠️ Selecciona un tipo de mascota.");
                return;
            }

            listaMascotas.add(mascota);
            lblEstado.setText("✅ Mascota agregada correctamente.");
            limpiarCampos();

        } catch (Exception e) {
            lblEstado.setText("❌ Error: revisa los datos ingresados.");
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
