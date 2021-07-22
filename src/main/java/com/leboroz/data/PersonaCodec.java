package com.leboroz.data;

import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class PersonaCodec implements CollectibleCodec<Persona> {

    private Codec<Document> documentCodec;

    public PersonaCodec() {
        super();
        documentCodec = new DocumentCodec();
    }

    @Override
    public Persona generateIdIfAbsentFromDocument(Persona document) {
        return document;
    }

    @Override
    public boolean documentHasId(Persona document) {
        return null != document.getID();
    }

    @Override
    public BsonValue getDocumentId(Persona document) {
        return new BsonObjectId(document.getID());
    }

    @Override
    public Persona decode(BsonReader reader, DecoderContext decoderContext) {
        Document personaDocument = documentCodec.decode(reader, decoderContext);
        Document informacion_de_nacimiento = (Document) personaDocument.get("Informacion_de_Nacimiento");
        Document informacion_de_persona =(Document) personaDocument.get("Informacion_de_Persona");
        Document informacion_de_vivienda = (Document) personaDocument.get("Informacion_de_Vivienda");
        Document otros = (Document) personaDocument.get("Otros");
        InformacionPersona informacionPersona = new InformacionPersona(
                informacion_de_persona.getString("Nrc"),
                informacion_de_persona.getString("Tipo_de_Identificacion"),
                informacion_de_persona.getString("Nombres"),
                informacion_de_persona.getString("Sexo"),
                informacion_de_persona.getString("Telefono_fijo"),
                informacion_de_persona.getString("Cedula"),
                informacion_de_persona.getString("Nacionalidad"),
                informacion_de_persona.getString("Apellidos"),
                informacion_de_persona.getString("#_de_personas_en_el_hogar"),
                informacion_de_persona.getString("Celular"),
                informacion_de_persona.getString("Hard_to_reach"),
                informacion_de_persona.getString("Perfil_poblacional"));
        InformacionNacimiento informacionNacimiento = new InformacionNacimiento(
                LocalDate.ofEpochDay(informacion_de_nacimiento.getLong("Fecha_de_nacimiento")),
                informacion_de_nacimiento.getString("Edad")
        );
        InformacionVivienda informacionVivienda = new InformacionVivienda(
                informacion_de_vivienda.getString("Estado"),
                informacion_de_vivienda.getString("Municipio"),
                informacion_de_vivienda.getString("Parroquia")
        );
        Otros otros1 = new Otros(
                otros.getString("Dia_de_atencion"),
                otros.getString("Mes_de_atencion"),
                otros.getString("Año_de_atencion"),
                otros.getString("Core_Competency"),
                otros.getString("Indicador"),
                otros.getString("Servicio"),
                otros.getString("Comentario"),
                otros.getString("Covid")
        );


        Persona persona = new Persona(informacionPersona,
                informacionNacimiento,
                informacionVivienda,
                otros1
        );
        persona.setId((ObjectId) personaDocument.get("_id"));
        return persona;
    }

    @Override
    public void encode(BsonWriter writer, Persona value, EncoderContext encoderContext) {
        Document informacionPersonaDocument = new Document();

        putDocument(informacionPersonaDocument, value.getInformacionPersona().getCedula(), "Cedula");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getNombres(), "Nombres");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getApellidos(), "Apellidos");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getNrc(), "Nrc");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getNumeroPersonas(), "#_de_personas_en_el_hogar");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getHardToReach(), "Hard_to_Reach");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getNacionalidad(), "Nacionalidad");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getSexo(), "Sexo");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getCelular(), "Celular");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getTelefonoFijo(), "Telefono_fijo");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getTipoID(), "Tipo_de_Identificacion");
        putDocument(informacionPersonaDocument, value.getInformacionPersona().getPerfilPoblacional(), "Perfil_poblacionaltem");


        Document informacionNacimientoDocument = new Document();
        putDocument(informacionNacimientoDocument, value.getInformacionNacimiento().getFechaNacimiento().toEpochDay(), "Fecha_de_nacimiento");
        putDocument(informacionNacimientoDocument, value.getInformacionNacimiento().getEdad(), "Edad");

        Document informacionViviendaDocument = new Document();

        putDocument(informacionViviendaDocument, value.getInformacionVivienda().getEstado(), "Estado");
        putDocument(informacionViviendaDocument, value.getInformacionVivienda().getMunicipio(), "Municipio");
        putDocument(informacionViviendaDocument, value.getInformacionVivienda().getParroquia(), "Parroquia");

        Document otrosDocument = new Document();

        putDocument(otrosDocument, value.getOtros().getAñoAtencion(), "Año_de_atencion");
        putDocument(otrosDocument, value.getOtros().getComentario(), "Comentario");
        putDocument(otrosDocument, value.getOtros().getCoreCompetency(), "Core_Competency");
        putDocument(otrosDocument, value.getOtros().getCovid(), "Covid");
        putDocument(otrosDocument, value.getOtros().getIndicador(), "Indicador");
        putDocument(otrosDocument, value.getOtros().getDiaAtencion(), "Dia_de_atencion");
        putDocument(otrosDocument, value.getOtros().getMesAtencion(), "Mes_de_atencion");
        putDocument(otrosDocument, value.getOtros().getServicio(), "Servicio");

        Document personaDocument = new Document();

        putDocument(personaDocument, informacionPersonaDocument, "Informacion_de_Persona");
        putDocument(personaDocument, informacionNacimientoDocument, "Informacion_de_Nacimiento");
        putDocument(personaDocument, informacionViviendaDocument, "Informacion_de_Vivienda");
        putDocument(personaDocument, otrosDocument, "Otros");

        documentCodec.encode(writer, personaDocument, encoderContext);
    }

    @Override
    public Class<Persona> getEncoderClass() {
        return Persona.class;
    }

    private <t> void putDocument(Document document, t value, String key) {
        if (value != null) {
            document.put(key, value);
        }
    }
}
