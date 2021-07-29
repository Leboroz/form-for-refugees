package com.leboroz.data;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


public final class PostgresCon {

    public static void main(String[] args) {
        connect();
    }

    private static Connection connection;
    private static Properties properties;

    public static boolean create(Persona persona) {
        try (Statement statement = connection.createStatement()) {
            return statement.execute("INSERT INTO personas(" +
                    "nrc," +
                    "tipoid," +
                    "nombres," +
                    "sexo," +
                    "telefonofijo," +
                    "cedula," +
                    "nacionalidad," +
                    "apellidos," +
                    "numeropersonas," +
                    "celular," +
                    "hardtoreach," +
                    "perfilpoblacional," +
                    "fechanacimiento," +
                    "edad," +
                    "estado," +
                    "municipio," +
                    "parroquia," +
                    "diaatencion," +
                    "mesatencion," +
                    "añoatencion," +
                    "corecompetency," +
                    "indicador," +
                    "servicio," +
                    "comentario," +
                    "covid" +
                    ") VALUES (" +
                    persona.getInformacionPersona() +
                    persona.getInformacionNacimiento() +
                    persona.getInformacionVivienda() +
                    persona.getOtros() + ")");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public static List<Persona> getPersonas() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM personas");
            List<Persona> personaList = new ArrayList<>();
            while (resultSet.next()) {
                InformacionPersona informacionPersona = new InformacionPersona(
                        resultSet.getString("nrc"),
                        resultSet.getString("tipoid"),
                        resultSet.getString("nombres"),
                        resultSet.getString("sexo"),
                        resultSet.getString("telefonofijo"),
                        resultSet.getString("cedula"),
                        resultSet.getString("nacionalidad"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("numeropersonas"),
                        resultSet.getString("celular"),
                        resultSet.getString("hardtoreach"),
                        resultSet.getString("perfilpoblacional")
                );
                List<Integer> fechanacimientos = Arrays
                        .stream(
                                resultSet
                                        .getString("fechanacimiento")
                                        .split("-")
                        )
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                InformacionNacimiento informacionNacimiento = new InformacionNacimiento(
                        LocalDate.of(
                                fechanacimientos.get(0),
                                fechanacimientos.get(1),
                                fechanacimientos.get(2)
                        ),
                        resultSet.getInt("edad") + ""
                );

                InformacionVivienda informacionVivienda = new InformacionVivienda(
                        resultSet.getString("estado"),
                        resultSet.getString("municipio"),
                        resultSet.getString("parroquia")
                );
                Otros otros = new Otros(
                        resultSet.getString("diaatencion"),
                        resultSet.getString("mesatencion"),
                        resultSet.getString("añoatencion"),
                        resultSet.getString("corecompetency"),
                        resultSet.getString("indicador"),
                        resultSet.getString("servicio"),
                        resultSet.getString("comentario"),
                        resultSet.getString("covid")
                );
                personaList.add(
                        new Persona(
                                informacionPersona,
                                informacionNacimiento,
                                informacionVivienda,
                                otros
                        )
                );
            }
            return personaList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static boolean delete(Persona persona) {
        try (Statement statement = connection.createStatement()) {
            return statement.execute("DELETE FROM personas " +
                    "WHERE cedula='" + persona.getInformacionPersona().getCedula() + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static void connect() {
        properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "23738070l");
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:7777/basedatos", properties);
            Statement statement = connection.createStatement();

//            String sql = "SELECT * FROM person";
//            ResultSet resultSet = statement.executeQuery(sql);
//            while(resultSet.next()){
//
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("something went wrong");
        }
    }
}


//statement.execute("CREATE TABLE personas(" +
//        "id BIGSERIAL NOT NULL PRIMARY KEY," +
//        "nrc VARCHAR(20) NOT NULL," +
//        "tipoID VARCHAR(50) NOT NULL," +
//        "nombres VARCHAR(50) NOT NULL," +
//        "sexo VARCHAR(20) NOT NULL," +
//        "telefonoFijo VARCHAR(20) NOT NULL," +
//        "cedula VARCHAR(10) NOT NULL," +
//        "nacionalidad VARCHAR(20) NOT NULL," +
//        "apellidos VARCHAR(50) NOT NULL," +
//        "numeroPersonas INTEGER NOT NULL," +
//        "celular VARCHAR(20) NOT NULL," +
//        "hardToReach VARCHAR(20) NOT NULL," +
//        "perfilPoblacional VARCHAR(50) NOT NULL," +
//        "fechaNacimiento DATE NOT NULL," +
//        "edad INTEGER NOT NULL," +
//        "estado VARCHAR(50) NOT NULL," +
//        "municipio VARCHAR(50) NOT NULL," +
//        "parroquia VARCHAR(50) NOT NULL," +
//        "diaAtencion VARCHAR(10) NOT NULL," +
//        "mesAtencion VARCHAR(10) NOT NULL," +
//        "añoAtencion VARCHAR(10) NOT NULL," +
//        "coreCompetency VARCHAR(50) NOT NULL," +
//        "indicador VARCHAR(50) NOT NULL," +
//        "servicio VARCHAR(50) NOT NULL," +
//        "comentario VARCHAR(500) NOT NULL," +
//        "covid VARCHAR(20) NOT NULL"+
//        ")");