package com.leboroz.data;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class InformacionPersona {
    private String nrc;
    private String tipoID;
    private SimpleStringProperty nombres;
    private String sexo;
    private String telefonoFijo;
    private SimpleStringProperty cedula;
    private String nacionalidad;
    private String apellidos;
    private String numeroPersonas;
    private String celular;
    private String hardToReach;
    private String perfilPoblacional;

    public InformacionPersona() {
    }

    public InformacionPersona(String nrc, String tipoID, String nombres, String sexo, String telefonoFijo, String cedula, String nacionalidad, String apellidos, String numeroPersonas, String celular, String hardToReach, String perfilPoblacional) {
        this.nrc = nrc;
        this.tipoID = tipoID;
        this.nombres = new SimpleStringProperty(nombres);
        this.sexo = sexo;
        this.telefonoFijo = telefonoFijo;
        this.cedula = new SimpleStringProperty(cedula);
        this.nacionalidad = nacionalidad;
        this.apellidos = apellidos;
        this.numeroPersonas = numeroPersonas;
        this.celular = celular;
        this.hardToReach = hardToReach;
        this.perfilPoblacional = perfilPoblacional;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getTipoID() {
        return tipoID;
    }

    public void setTipoID(String tipoID) {
        this.tipoID = tipoID;
    }

    public String getNombres() {
        return nombres.get();
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getCedula() {
        return cedula.get();
    }

    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(String numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getHardToReach() {
        return hardToReach;
    }

    public void setHardToReach(String hardToReach) {
        this.hardToReach = hardToReach;
    }

    public String getPerfilPoblacional() {
        return perfilPoblacional;
    }

    public void setPerfilPoblacional(String perfilPoblacional) {
        this.perfilPoblacional = perfilPoblacional;
    }

    @Override
    public String toString() {
        return '\'' + nrc + "', '" +
                tipoID + "', '" +
                nombres.get() + "', '" +
                sexo + "', '" +
                telefonoFijo + "', '" +
                cedula.get() + "', '" +
                nacionalidad + "', '" +
                apellidos + "', '" +
                numeroPersonas + "', '" +
                celular + "', '" +
                hardToReach + "', '" +
                perfilPoblacional + "', ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformacionPersona that = (InformacionPersona) o;
        return Objects.equals(nrc, that.nrc) && Objects.equals(tipoID, that.tipoID) && Objects.equals(nombres, that.nombres) && Objects.equals(sexo, that.sexo) && Objects.equals(telefonoFijo, that.telefonoFijo) && Objects.equals(cedula, that.cedula) && Objects.equals(nacionalidad, that.nacionalidad) && Objects.equals(apellidos, that.apellidos) && Objects.equals(numeroPersonas, that.numeroPersonas) && Objects.equals(celular, that.celular) && Objects.equals(hardToReach, that.hardToReach) && Objects.equals(perfilPoblacional, that.perfilPoblacional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrc, tipoID, nombres, sexo, telefonoFijo, cedula, nacionalidad, apellidos, numeroPersonas, celular, hardToReach, perfilPoblacional);
    }
}
