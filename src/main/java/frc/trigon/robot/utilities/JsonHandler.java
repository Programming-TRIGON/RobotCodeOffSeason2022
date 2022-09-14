package frc.trigon.robot.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.*;

public class JsonHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String PATH = Filesystem.getDeployDirectory().getPath() + "\\";

    /**
     * Parses the given object to JSON and writing to a JSON file,
     * using a safe way of writing.
     *
     * @param object the object to save
     * @param name   the name of the file to save to or create
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
        try(Reader reader = new FileReader(JsonHandler.PATH + fileName)) {
            return GSON.fromJson(reader, type);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void safeWrite(String text, String name) throws IOException {
        writeStringToFile(JsonHandler.PATH + name + ".tmp", text);
        renameFile(JsonHandler.PATH + name, JsonHandler.PATH + name + ".bak");
        renameFile(JsonHandler.PATH + name + ".tmp", JsonHandler.PATH + name);
        deleteFile(JsonHandler.PATH + name + ".bak");
    }

    private static void renameFile(String oldPath, String newPath) throws IOException{
        File file = new File(oldPath);
        if(!file.renameTo(new File(newPath))) {
            throw new IOException("Failed to rename file " + oldPath + " to " + newPath);
        }
    }

    private static void deleteFile(String path) throws IOException{
        File file = new File(path);
        if(!file.delete()) {
            throw new IOException("Failed to delete file.");
        }
    }

    private static String parseObjectToJson(Object object) {
        return GSON.toJson(object);
    }

    private static void writeStringToFile(String path, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }
}
