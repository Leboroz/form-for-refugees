package com.leboroz.data;

import org.bson.types.ObjectId;

import java.util.Objects;

public class Persona {
    private ObjectId id;
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

    public ObjectId getID() {
        return id;
    }

    public void setId(ObjectId id) {
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
        return Objects.equals(informacionPersona, persona.informacionPersona) && Objects.equals(informacionNacimiento, persona.informacionNacimiento) && Objects.equals(informacionVivienda, persona.informacionVivienda) && Objects.equals(otros, persona.otros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(informacionPersona, informacionNacimiento, informacionVivienda, otros);
    }
}
