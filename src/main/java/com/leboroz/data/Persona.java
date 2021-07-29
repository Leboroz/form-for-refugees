package com.leboroz.data;

import org.postgresql.util.PGobject;

import java.util.Objects;

public class Persona {
    private PGobject id;
    private InformacionPersona informacionPersona;
    private InformacionNacimiento informacionNacimiento;
    private InformacionVivienda informacionVivienda;
    private Otros otros;

    public Persona(){
    }
    public Persona(InformacionPersona informacionPersona, InformacionNacimiento informacionNacimiento, InformacionVivienda informacionVivienda, Otros otros) {
        this.informacionPersona = informacionPersona;
        this.informacionNacimiento = informacionNacimiento;
        this.informacionVivienda = informacionVivienda;
        this.otros = otros;
    }

    public InformacionPersona getInformacionPersona() {
        return informacionPersona;
    }

    public void setInformacionPersona(InformacionPersona informacionPersona) {
        this.informacionPersona = informacionPersona;
    }

    public InformacionNacimiento getInformacionNacimiento() {
        return informacionNacimiento;
    }

    public void setInformacionNacimiento(InformacionNacimiento informacionNacimiento) {
        this.informacionNacimiento = informacionNacimiento;
    }

    public InformacionVivienda getInformacionVivienda() {
        return informacionVivienda;
    }

    public void setInformacionVivienda(InformacionVivienda informacionVivienda) {
        this.informacionVivienda = informacionVivienda;
    }

    public Otros getOtros() {
        return otros;
    }

    public void setOtros(Otros otros) {
        this.otros = otros;
    }

    public PGobject getID() {
        return id;
    }

    public void setId(PGobject id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", informacionPersona=" + informacionPersona +
                ", informacionNacimiento=" + informacionNacimiento +
                ", informacionVivienda=" + informacionVivienda +
                ", otros=" + otros +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(informacionPersona.getCedula(), persona.informacionPersona.getCedula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(informacionPersona);
    }
}
