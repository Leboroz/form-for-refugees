package com.leboroz.data;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public final class MongoCon {
    private static ConnectionString CONNECTION_STRING;
    private static CodecRegistry POJO_CODEC_REGISTRY;
    private static MongoClientSettings SETTINGS;


    static {
        try {
            CONNECTION_STRING = new ConnectionString("mongodb+srv://Leboroz:Leboroz@cluster0.6eihu.mongodb.net/BaseDatos?retryWrites=true&w=majority");
            POJO_CODEC_REGISTRY = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromCodecs(new PersonaCodec()));
            SETTINGS = MongoClientSettings
                    .builder()
                    .applyConnectionString(CONNECTION_STRING)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("La base de datos no esta connectada");
            alert.show();
        }

    }

    public static void create(Persona persona) {
        MongoCollection<Persona> personas = connect();
        if (personas != null) {
            personas.insertOne(persona);
        }
    }

    public static List<Persona> getPersonas() {
        MongoCollection<Persona> personas = connect();
        return personas != null ? personas.find().into(new ArrayList<>()) : null;
    }

    public static boolean delete(Persona persona) {
        MongoCollection<Persona> personas = connect();
        try {
            Persona oneAndDelete = null;
            if (personas != null) {
                oneAndDelete = personas.findOneAndDelete(eq("_id", persona.getID()));
            }
            return oneAndDelete != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static MongoCollection<Persona> connect() {
        try{
            MongoClient mongoClient = MongoClients.create(SETTINGS);
            MongoDatabase db = mongoClient.getDatabase("BaseDatos");
            return db.getCollection("personas", Persona.class).withCodecRegistry(POJO_CODEC_REGISTRY);
        }catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Could not connect to database");
            alert.getButtonTypes().add(ButtonType.OK);
            alert.show();
        }
        return null;
    }
}
