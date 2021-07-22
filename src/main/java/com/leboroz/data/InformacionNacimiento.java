package com.leboroz.data;

import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.BsonDocumentWriter;
import org.bson.Document;
import org.bson.codecs.jsr310.LocalDateCodec;

import java.time.LocalDate;
import java.util.Objects;

public class InformacionNacimiento {
    private LocalDate fechaNacimiento;
    private String edad;

    public InformacionNacimiento(LocalDate fechaNacimiento, String edad) {
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
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

    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "InformacionNacimiento{" +
                "fechaNacimiento=" + fechaNacimiento +
                ", edad='" + edad + '\'' +
                '}';
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
