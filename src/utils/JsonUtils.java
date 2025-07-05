package utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class JsonUtils {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type typeOfT,
                                             JsonDeserializationContext context) throws JsonParseException {
                    return LocalDate.parse(json.getAsString());
                }
            })
            .create();
    private final String dir = "data/json/";

//    public List<Book> readBooks() throws FileNotFoundException {
//        JsonReader reader = new JsonReader(new FileReader(dir + "books.json"));
//
//        Type listType = new TypeToken<List<Book>>(){}.getType();
//        List<Book> books = gson.fromJson(reader, listType);
//        return books;
//    }

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
