package com.leboroz.data;

import java.util.Objects;

public class InformacionVivienda {

    private String estado;
    private String municipio;
    private String parroquia;

    public InformacionVivienda(String estado, String municipio, String parroquia) {
        this.estado = estado;
        this.municipio = municipio;
        this.parroquia = parroquia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    @Override
    public String toString() {
        return "InformacionVivienda{" +
                "estado='" + estado + '\'' +
                ", municipio='" + municipio + '\'' +
                ", parroquia='" + parroquia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformacionVivienda that = (InformacionVivienda) o;
        return Objects.equals(estado, that.estado) && Objects.equals(municipio, that.municipio) && Objects.equals(parroquia, that.parroquia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado, municipio, parroquia);
    }
}
