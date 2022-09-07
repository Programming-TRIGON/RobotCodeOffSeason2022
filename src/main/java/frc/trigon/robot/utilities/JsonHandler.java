package frc.trigon.robot.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String path = Filesystem.getDeployDirectory().getPath();

    public static void saveToJsonFile(Object object, String path) {
        String json = gson.toJson(object);
        saveFile(JsonHandler.path+path +".tmp", "\""+object+"\"");
        renameFile(JsonHandler.path+path, JsonHandler.path+path+".bak");
        renameFile(JsonHandler.path+path +".tmp", JsonHandler.path+path);
        deleteFile(JsonHandler.path + path+".bak");
    }
    private static void renameFile(String oldPath, String newPath){
        File file = new File(oldPath);
        if( !file.renameTo(new File(newPath))){
            System.out.println("Failed to rename file.");
        }
    }
    private static void deleteFile(String path){
        File file = new File(path);
        if(!file.delete()){
            System.out.println("Failed to delete file.");
        }
    }
    private static void closeFile(FileWriter file){
        try{
            file.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
    private static void saveFile(String path, String text) {
        FileWriter file = createFile(path);
        if(file == null) return;
        writeToFile(file,text);
        closeFile(file);
    }

    private static FileWriter createFile(String path) {
        FileWriter file;
        try{
            file = new FileWriter(path);
            return file;
        }catch(IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    private static void writeToFile(FileWriter file, String text) {
        try {
            file.write(text);
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }
}
