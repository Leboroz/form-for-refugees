package com.leboroz;

import com.leboroz.data.InformacionPersona;
import com.leboroz.data.MongoCon;
import com.leboroz.data.Persona;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


public class Controller {
    @FXML
    public ToolBar toolBar;
    @FXML
    private TextField searchTextField;
    @FXML
    private Label hardToReach;
    @FXML
    private Label perfilPoblacional;
    @FXML
    private Label nrc;
    @FXML
    private Label tipoIdentificacion;
    @FXML
    private Label nacionalidad;
    @FXML
    private Label nombres;
    @FXML
    private Label apellidos;
    @FXML
    private Label sexo;
    @FXML
    private Label numeroPersonas;
    @FXML
    private Label telefonoFijo;
    @FXML
    private Label celular;
    @FXML
    private Label cedula;
    @FXML
    private BorderPane primary;
    @FXML
    private TableView<InformacionPersona> personasTable;
    private List<Persona> personas;
    private ObservableList<InformacionPersona> informacionPersonas;

    public void initialize(){
        Glyph addGlyph = Glyph.create("FontAwesome|" + FontAwesome.Glyph.PLUS_SQUARE.name()).size(15.0);
        Glyph deleteGlyph = Glyph.create("FontAwesome|" + FontAwesome.Glyph.TRASH.name()).size(15.0);

        addGlyph.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            handleClickDialog();
        });

        deleteGlyph.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            HandleClickDelete();
        });

        toolBar.getItems().addAll(addGlyph, deleteGlyph);
        personas = MongoCon.getPersonas();
        System.out.println(personas);
        informacionPersonas = FXCollections.observableArrayList(personas.stream().map(Persona::getInformacionPersona).collect(Collectors.toList()));
        personasTable.setItems(informacionPersonas);
        searchTextField.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.equals("")) {
                ObservableList<InformacionPersona> FilteredList = FXCollections.observableArrayList();
                for (InformacionPersona persona : informacionPersonas) {
                    if (persona.getCedula().contains(t1) ||
                            persona.getNombres().contains(t1.toLowerCase(new Locale("es", "ES"))) ||
                            persona.getApellidos().contains(t1.toLowerCase(new Locale("es", "ES")))) {
                        FilteredList.add(persona);
                    }
                }
                personasTable.setItems(
                        FilteredList
                );
            } else {
                personasTable.setItems(informacionPersonas);
            }
        });
    }

    @FXML
    private void HandleClickDelete() {
        int focusedIndex = personasTable.getSelectionModel().getFocusedIndex();
        if (personasTable.getSelectionModel().isSelected(focusedIndex)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Esta seguro");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.isPresent() && optional.get().equals(ButtonType.OK) ) {
                InformacionPersona informacionPersona = personasTable.getSelectionModel().getSelectedItem();
                Persona selectedPersona = new Persona();
                for (Persona persona : personas){
                    if (persona.getInformacionPersona().getCedula().equals(informacionPersona.getCedula())){
                        selectedPersona = persona;
                    }
                }
                if(MongoCon.delete(selectedPersona)){
                    informacionPersonas.remove(selectedPersona.getInformacionPersona());
                    clearFields();
                }
            }
        }
    }

    @FXML
    public void handleClickDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(primary.getScene().getWindow());
        dialog.setTitle("Añadir una persona");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/personDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        final Button BTN_OK = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        PersonDialogController personDialogController = fxmlLoader.getController();
        BTN_OK.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            ObservableList<Node> children = personDialogController.dialogGridPane.getChildren();
            for(Node child : children){
                if (child.getClass() == TextField.class  && ((TextField) child).getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Faltan campos por rellenar");
                    alert.show();
                    actionEvent.consume();
                    break;
                }
            }
        });
        Platform.runLater(() -> {
            dialog.setY(10.0);
        });

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Persona persona = personDialogController.getPersona();
            MongoCon.create(persona);
            informacionPersonas.add(persona.getInformacionPersona());
        }
    }
    @FXML
    public void handleClickTableItem() {
        InformacionPersona informacionPersona = personasTable.getSelectionModel().getSelectedItem();
        nrc.setText(informacionPersona.getNrc());
        tipoIdentificacion.setText(informacionPersona.getTipoID());
        nombres.setText(informacionPersona.getNombres());
        sexo.setText(informacionPersona.getSexo());
        telefonoFijo.setText(informacionPersona.getTelefonoFijo());
        cedula.setText(informacionPersona.getCedula());
        nacionalidad.setText(informacionPersona.getNacionalidad());
        apellidos.setText(informacionPersona.getApellidos());
        numeroPersonas.setText(informacionPersona.getNumeroPersonas());
        celular.setText(informacionPersona.getCelular());
        hardToReach.setText(informacionPersona.getHardToReach());
        perfilPoblacional.setText(informacionPersona.getPerfilPoblacional());
    }
    private void clearFields() {
        nrc.setText("");
        tipoIdentificacion.setText("");
        nombres.setText("");
        sexo.setText("");
        telefonoFijo.setText("");
        cedula.setText("");
        nacionalidad.setText("");
        apellidos.setText("");
        numeroPersonas.setText("");
        celular.setText("");
        hardToReach.setText("");
        perfilPoblacional.setText("");
    }
}