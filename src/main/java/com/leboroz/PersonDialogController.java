package com.leboroz;

import com.leboroz.data.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonDialogController {
    @FXML
    public GridPane dialogGridPane;
    @FXML
    private TextField nrc;
    @FXML
    private TextField tipoID;
    @FXML
    private TextField nombres;
    @FXML
    private TextField sexo;
    @FXML
    private TextField telefonoFijo;
    @FXML
    private TextField id;
    @FXML
    private TextField nacionalidad;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField numeroPersonas;
    @FXML
    private TextField celular;
    @FXML
    private TextField hardToReach;
    @FXML
    private TextField perfilPoblacional;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private TextField edad;
    @FXML
    private TextField estado;
    @FXML
    private TextField municipio;
    @FXML
    private TextField parroquia;
    @FXML
    private TextField diaAtencion;
    @FXML
    private TextField mesAtencion;
    @FXML
    private TextField anoAtencion;
    @FXML
    private TextField coreCompetency;
    @FXML
    private TextField indicador;
    @FXML
    private TextField servicio;
    @FXML
    private TextField comentario;
    @FXML
    private TextField covid;


    public Persona getPersona() {
        InformacionPersona informacionPersona = new InformacionPersona(
                nrc.getText(),
                tipoID.getText(),
                nombres.getText(),
                sexo.getText(),
                telefonoFijo.getText(),
                id.getText(),
                nacionalidad.getText(),
                apellidos.getText(),
                numeroPersonas.getText(),
                celular.getText(),
                hardToReach.getText(),
                perfilPoblacional.getText()
        );
        InformacionNacimiento informacionNacimiento = new InformacionNacimiento(
                fechaNacimiento.getValue(),
                edad.getText()
        );
        InformacionVivienda informacionVivienda = new InformacionVivienda(
                estado.getText(),
                municipio.getText(),
                parroquia.getText()
        );
        Otros otros = new Otros(
                diaAtencion.getText(),
                mesAtencion.getText(),
                anoAtencion.getText(),
                coreCompetency.getText(),
                indicador.getText(),
                servicio.getText(),
                comentario.getText(),
                covid.getText()
        );
        return new Persona(informacionPersona, informacionNacimiento, informacionVivienda, otros);
    }
}
