package utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import server.models.Patient;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private List<Patient> patientList = new ArrayList<>();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                            LocalDate.parse(json.getAsString()))
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                            LocalDateTime.parse(json.getAsString()))
            .registerTypeAdapter(LocalDate.class,
                    (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                            new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                            new JsonPrimitive(src.toString()))
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

//            .setPrettyPrinting()
//            .excludeFieldsWithoutExposeAnnotation()
//            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
//                @Override
//                public LocalDate deserialize(JsonElement json, Type typeOfT,
//                                             JsonDeserializationContext context) throws JsonParseException {
//                    return LocalDate.parse(json.getAsString());
//                }
//            })
//            .create();
    private final String dir = "data/json/";

    public List<Patient> readPatients() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(dir + "patients.json"));

        Type listType = new TypeToken<List<Patient>>(){}.getType();
        List<Patient> patients = gson.fromJson(reader, listType);
        patientList = patients;
        return patients;
    }

//    public List<User> readUsers() throws FileNotFoundException {
//        JsonReader reader = new JsonReader(new FileReader(dir + "users.json"));
//
//        Type listType = new TypeToken<List<User>>(){}.getType();
//        List<User> users = gson.fromJson(reader, listType);
//        return users;
//    }

//    public void addUsersToJson(List<User> users) throws FileNotFoundException {
//        try{
//            FileWriter writer = new FileWriter(dir + "users.json");
//            String write = gson.toJson(users);
//            writer.write(write);
//            writer.flush();
//            writer.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
