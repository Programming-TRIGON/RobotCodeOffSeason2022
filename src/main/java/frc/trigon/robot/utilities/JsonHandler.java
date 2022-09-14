package frc.trigon.robot.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static frc.trigon.robot.utilities.FilesHandler.safeWrite;

public class JsonHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Parses the given object to JSON and writing to a JSON file,
     * using a safe way of writing.
     *
     * @param object the object to save
     * @param name   the name of the file to save to or create
     * @throws IOException if the method failed to write the object to the file
     */
    public static void parseToJsonAndWrite(Object object, String name) throws IOException {
        safeWrite(parseObjectToJson(object), name);
    }

    /**
     * Parses a JSON file to an object.
     *
     * @param fileName the name of the file to read
     * @param type     the class to parse JSON to
     * @return the class
     */
    public static <T> T parseJsonToObject(String fileName, Class<T> type) {
        try {
            Reader reader = new FileReader(fileName);
            return GSON.fromJson(reader, type);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String parseObjectToJson(Object object) {
        return GSON.toJson(object);
    }
}
