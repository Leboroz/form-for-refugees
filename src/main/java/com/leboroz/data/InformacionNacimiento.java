package com.leboroz.data;

import java.time.LocalDate;
import java.util.Objects;

public class InformacionNacimiento {
    private LocalDate fechaNacimiento;
    private final String edad;

    public InformacionNacimiento(LocalDate fechaNacimiento, String edad) {
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
    }

    public InformacionNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        this.edad = LocalDate.now().getYear() - fechaNacimiento.getYear() + "";
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "date '" +
                fechaNacimiento.toString() + '\'' + ", '" +
                edad + "', ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformacionNacimiento that = (InformacionNacimiento) o;
        return Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(edad, that.edad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaNacimiento, edad);
    }
}
