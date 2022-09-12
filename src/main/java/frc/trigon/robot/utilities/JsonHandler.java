package frc.trigon.robot.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.*;

public class JsonHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String path = Filesystem.getDeployDirectory().getPath() + "\\";

    /**
     * Saves an object to a new or existing Json file.
     *
     * @param object the object to save
     * @param name   the name of the file to save to or create
     */
    public static void saveToJsonFile(Object object, String name) throws IOException {
        saveFile(JsonHandler.path + name + ".tmp", object);
        renameFile(JsonHandler.path + name, JsonHandler.path + name + ".bak");
        renameFile(JsonHandler.path + name + ".tmp", JsonHandler.path + name);
        deleteFile(JsonHandler.path + name + ".bak");
    }

    /**
     * Gets an object from a Json file.
     *
     * @param fileName the name of the file to get from
     * @param type     the type of the object to get (a class)
     * @return the object
     */
    public static <T> T getObjectFromJson(String fileName, Class<T> type) {
        try(Reader reader = new FileReader(JsonHandler.path + fileName)) {
            return gson.fromJson(reader, type);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void renameFile(String oldPath, String newPath) {
        File file = new File(oldPath);
        if(!file.renameTo(new File(newPath))) {
            System.out.println("Failed to rename file.");
        }
    }

    private static void deleteFile(String path) {
        File file = new File(path);
        if(!file.delete()) {
            System.out.println("Failed to delete file.");
        }
    }

    private static void saveFile(String path, Object text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        gson.toJson(text, fileWriter);
        fileWriter.close();
    }
}
