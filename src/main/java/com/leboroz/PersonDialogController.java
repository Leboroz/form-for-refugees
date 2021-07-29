package com.leboroz;

import com.leboroz.data.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.textfield.CustomTextField;

public class PersonDialogController {
    @FXML
    public GridPane dialogGridPane;
    @FXML
    private CustomTextField nrc;
    @FXML
    private ComboBox<String> tipoID;
    @FXML
    private CustomTextField nombres;
    @FXML
    private ComboBox<String> sexo;
    @FXML
    private CustomTextField telefonoFijo;
    @FXML
    private CustomTextField id;
    @FXML
    private ComboBox<String> nacionalidad;
    @FXML
    private CustomTextField apellidos;
    @FXML
    private CustomTextField numeroPersonas;
    @FXML
    private CustomTextField celular;
    @FXML
    private ComboBox<String> hardToReach;
    @FXML
    private ComboBox<String> perfilPoblacional;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private CustomTextField edad;
    @FXML
    private ComboBox<String> estado;
    @FXML
    private CustomTextField municipio;
    @FXML
    private CustomTextField parroquia;
    @FXML
    private CustomTextField diaAtencion;
    @FXML
    private CustomTextField mesAtencion;
    @FXML
    private CustomTextField anoAtencion;
    @FXML
    private ComboBox<String> coreCompetency;
    @FXML
    private ComboBox<String> indicador;
    @FXML
    private ComboBox<String> servicio;
    @FXML
    private TextArea comentario;
    @FXML
    private ComboBox<String> covid;

    public void initialize() {


        tipoID.getItems().setAll("Cédula de Identidad", "Partida de Nacimiento", "Targeta de Identidad", "No Registrado", "Pasaporte", "Documento Provisional", "Cédula de Extrangero", "Carné PTH", "Registro Civil", "Apátrida");
        nacionalidad.getItems().setAll("Venezuela", "Colombia", "Brazil", "Peru", "Ecuador", "Otros");
        sexo.getItems().setAll("Masculino", "Femenino", "Otros");
        hardToReach.getItems().setAll("si", "no");
        perfilPoblacional.getItems().setAll("Desplazado Interno(IDP)", "Poblacion Local(Host Community)", "Refugiado(no Venezolano)", "Retornado(Returnee)", "no affectado por desplazamiento", "Población Indigena");
        estado.getItems().setAll(
                "Amazonas",
                "Anzoategui",
                "Estado Amazonas",
                "Anzoategui",
                "Apure",
                "Aragua",
                "Barinas",
                "Bolívar",
                "Carabobo",
                "Cojedes",
                "Delta Amacuro",
                "Distrito Capital",
                "Falcón",
                "Guárico",
                "Lara",
                "Mérida",
                "Miranda",
                "Monagas",
                "Nueva Esparta",
                "Portuguesa",
                "Sucre",
                "Táchira",
                "Trujillo",
                "Vargas",
                "Yaracuy",
                "Zulia"
        );
        coreCompetency.getItems().setAll("WASH");
        covid.getItems().setAll("si", "no");

    }
    /*
     */

    public Persona getPersona() {
        InformacionPersona informacionPersona = new InformacionPersona(
                nrc.getText(),
                tipoID.getValue(),
                nombres.getText(),
                sexo.getValue(),
                telefonoFijo.getText(),
                id.getText(),
                nacionalidad.getValue(),
                apellidos.getText(),
                numeroPersonas.getText(),
                celular.getText(),
                hardToReach.getValue(),
                perfilPoblacional.getValue()
        );
        InformacionNacimiento informacionNacimiento = new InformacionNacimiento(
                fechaNacimiento.getValue()
        );
        InformacionVivienda informacionVivienda = new InformacionVivienda(
                estado.getValue(),
                municipio.getText(),
                parroquia.getText()
        );
        Otros otros = new Otros(
                diaAtencion.getText(),
                mesAtencion.getText(),
                anoAtencion.getText(),
                coreCompetency.getValue(),
                indicador.getValue(),
                servicio.getValue(),
                comentario.getText(),
                covid.getValue()
        );
        return new Persona(informacionPersona, informacionNacimiento, informacionVivienda, otros);
    }
}
