package com.leboroz;

import com.leboroz.data.InformacionPersona;
import com.leboroz.data.Persona;
import com.leboroz.data.PostgresCon;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Controller {
    @FXML
    public ToolBar toolBar;
    @FXML
    private MenuBar mainBar;
    @FXML
    private Button min;
    @FXML
    private Button close;
    @FXML
    private CustomTextField searchTextField;
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

    public void initialize() {
        Glyph addGlyph = Glyph.create("FontAwesome|" + FontAwesome.Glyph.PLUS_SQUARE.name()).size(30.0);
        addGlyph.setPadding(new Insets(0.0, 5.0, 0.0, 5.0));
        Glyph deleteGlyph = Glyph.create("FontAwesome|" + FontAwesome.Glyph.TRASH.name()).size(30.0);
        deleteGlyph.setPadding(new Insets(0.0, 5.0, 0.0, 5.0));
        addGlyph.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleClickDialog());

        deleteGlyph.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> HandleClickDelete());
        close.setGraphic(Glyph.create("FontAwesome|" + FontAwesome.Glyph.CLOSE));
        close.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
        min.setGraphic(Glyph.create("FontAwesome|" + FontAwesome.Glyph.MINUS));
        min.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });


        toolBar.getItems().addAll(addGlyph, deleteGlyph);
//        MongoCon.connect(primary);
        personas = PostgresCon.getPersonas();
        if (personas != null)
            informacionPersonas = FXCollections.observableArrayList(personas.stream().map(Persona::getInformacionPersona).collect(Collectors.toList()));
        personasTable.setItems(informacionPersonas);
        personasTable.getSelectionModel().selectFirst();
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
            alert.setContentText("Confirme la operación");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.isPresent() && optional.get().equals(ButtonType.OK) ) {
                InformacionPersona informacionPersona = personasTable.getSelectionModel().getSelectedItem();
                Persona selectedPersona = new Persona();
                for (Persona persona : personas){
                    if (persona.getInformacionPersona().getCedula().equals(informacionPersona.getCedula())){
                        selectedPersona = persona;
                    }
                }
                if (PostgresCon.delete(selectedPersona)) {
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
            Platform.runLater(() -> {
                dialog.setY(0.0);
                TranslateTransition tt = new TranslateTransition(Duration.millis(3000), dialog.getGraphic());
                tt.setByY(400);
                tt.play();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        final Button BTN_OK = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        PersonDialogController personDialogController = fxmlLoader.getController();
        BTN_OK.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            ObservableList<Node> children = personDialogController.dialogGridPane.getChildren();
            boolean fieldsMissing = false;
            for (Node child : children) {
                if (child.getClass() == CustomTextField.class && ((CustomTextField) child).getText().equals("") ||
                        child.getClass() == ComboBox.class && ((ComboBox) child).getValue() == null ||
                        child.getClass() == DatePicker.class && ((DatePicker) child).getValue() == null
                ) {
                    DropShadow dropShadow = new DropShadow();
                    dropShadow.setColor(Color.RED);
                    child.setEffect(dropShadow);
                    fieldsMissing = true;
                }
            }
            if (fieldsMissing) {
                actionEvent.consume();
            }
        });


        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Persona persona = personDialogController.getPersona();
            PostgresCon.create(persona);
            personas.add(persona);
            informacionPersonas.add(persona.getInformacionPersona());
        }
    }

    @FXML
    public void handleClickTableItem() throws NullPointerException {
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

    private double x = 0, y = 0;

    @FXML
    public void handleDrag(MouseEvent mouseEvent) {
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setX(mouseEvent.getScreenX() - x);
        window.setY(mouseEvent.getScreenY() - y);

    }

    @FXML
    public void handleBarClicked(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
    }

    @FXML
    public void handleAbrir(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo csv");
        fileChooser.getExtensionFilters().setAll(
                new FileChooser.ExtensionFilter("Text(.csv)", "*.csv")
        );
        File file = fileChooser.showOpenDialog(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());
        List<String> lines;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            lines = br.lines().collect(Collectors.toList());
        }
        Pattern pattern = Pattern.compile("([\\w\\d]+);");
        for (String s : lines) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                System.out.println("group: " + matcher.group());
            }
        }

//                if (!personas.contains(persona)) {
//                    if(MongoCon.create(persona)){
//                        personas.add(persona);
//                        informacionPersonas.add(persona.getInformacionPersona());
//                    }
//                }

    }

    @FXML
    public void handleGuardar(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo csv");
        fileChooser.getExtensionFilters().setAll(
                new FileChooser.ExtensionFilter("Text(.csv)", "*.csv")
        );
        File file = fileChooser.showSaveDialog(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Persona persona : personas) {
                bufferedWriter.write(
                        persona.getInformacionPersona().toString() + persona.getInformacionNacimiento().toString() + persona.getInformacionVivienda().toString() + persona.getOtros().toString()
                );
                bufferedWriter.newLine();
            }
        }
    }

    public void handlePoblar(ActionEvent actionEvent) {

    }
}