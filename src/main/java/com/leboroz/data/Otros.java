package com.leboroz.data;

import java.util.Objects;

public class Otros {

    private String diaAtencion;
    private String  mesAtencion;
    private String añoAtencion;
    private String coreCompetency;
    private String indicador;
    private String servicio;
    private String comentario;
    private String covid;

    public Otros(String diaAtencion, String mesAtencion, String añoAtencion, String coreCompetency, String indicador, String servicio, String comentario, String covid) {
        this.diaAtencion = diaAtencion;
        this.mesAtencion = mesAtencion;
        this.añoAtencion = añoAtencion;
        this.coreCompetency = coreCompetency;
        this.indicador = indicador;
        this.servicio = servicio;
        this.comentario = comentario;
        this.covid = covid;
    }

    public String getDiaAtencion() {
        return diaAtencion;
    }

    public void setDiaAtencion(String diaAtencion) {
        this.diaAtencion = diaAtencion;
    }

    public String getMesAtencion() {
        return mesAtencion;
    }

    public void setMesAtencion(String mesAtencion) {
        this.mesAtencion = mesAtencion;
    }

    public String getAñoAtencion() {
        return añoAtencion;
    }

    public void setAñoAtencion(String añoAtencion) {
        this.añoAtencion = añoAtencion;
    }

    public String getCoreCompetency() {
        return coreCompetency;
    }

    public void setCoreCompetency(String coreCompetency) {
        this.coreCompetency = coreCompetency;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCovid() {
        return covid;
    }

    public void setCovid(String covid) {
        this.covid = covid;
    }

    @Override
    public String toString() {
        return "Otros{" +
                "diaAtencion='" + diaAtencion + '\'' +
                ", mesAtencion='" + mesAtencion + '\'' +
                ", añoAtencion='" + añoAtencion + '\'' +
                ", coreCompetency='" + coreCompetency + '\'' +
                ", indicador='" + indicador + '\'' +
                ", servicio='" + servicio + '\'' +
                ", comentario='" + comentario + '\'' +
                ", covid='" + covid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Otros otros = (Otros) o;
        return Objects.equals(diaAtencion, otros.diaAtencion) && Objects.equals(mesAtencion, otros.mesAtencion) && Objects.equals(añoAtencion, otros.añoAtencion) && Objects.equals(coreCompetency, otros.coreCompetency) && Objects.equals(indicador, otros.indicador) && Objects.equals(servicio, otros.servicio) && Objects.equals(comentario, otros.comentario) && Objects.equals(covid, otros.covid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diaAtencion, mesAtencion, añoAtencion, coreCompetency, indicador, servicio, comentario, covid);
    }
}
