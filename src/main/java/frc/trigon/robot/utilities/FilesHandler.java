package frc.trigon.robot.utilities;

import edu.wpi.first.wpilibj.Filesystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilesHandler {

    public static final String DEPLOY_PATH = Filesystem.getDeployDirectory().getPath() + "\\";

    /**
     * Deletes a file.
     *
     * @param absolutePath the file name
     * @throws IOException if the method failed to delete the specified file
     */
    public static void deleteFile(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        if(!file.delete()) {
            throw new IOException("Failed to delete the file \"" + absolutePath + "\".");
        }
    }

    /**
     * Writes the given text to a file.
     *
     * @param absolutePath the file name
     * @param text the text to write to the file
     * @throws IOException if the method failed to write the text to the file.
     */
    public static void writeStringToFile(String absolutePath, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(absolutePath);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Renames a file.
     *
     * @param absolutePath    the name of the file to rename
     * @param newName the new name of the desired name
     * @throws IOException if the method failed to rename the file
     */
    public static void renameFile(String absolutePath, String newName) throws IOException {
        List<String> absolutePathSplit = new ArrayList<>(List.of(absolutePath.split("\\\\")));
        absolutePathSplit.remove(absolutePathSplit.size() - 1);
        absolutePathSplit.add(newName);
        newName =  String.join("\\", absolutePathSplit);
        File file = new File(absolutePath);
        if(!file.renameTo(new File(newName))) {
            throw new IOException("Failed to rename file " + absolutePath + " to " + newName);
        }
    }

    /**
     * Creates a file using a safe method of writing.
     * This method will write the string to a temporary file,
     * rename the existing file to its name.bak.
     * rename the temporary file to the desired name.
     * delete the .bak file.
     *
     * @param absolutePath the name of the file to create
     * @param text the text to write in the file
     * @throws IOException if the method failed to safe write the file
     */
    public static void safeWrite(String absolutePath, String text) throws IOException {
        String[] absolutePathSplit = absolutePath.split("\\\\");
        writeStringToFile(absolutePath + ".tmp", text);
        if(fileExist(absolutePath))
            renameFile(absolutePath, absolutePathSplit[absolutePathSplit.length-1]+".bak");
        renameFile(absolutePath + ".tmp", absolutePathSplit[absolutePathSplit.length-1]);
        if(fileExist(absolutePath + ".bak"))
            deleteFile(absolutePath + ".bak");
    }

    /**
     * Reads a file and returns its content as a string.
     * If the files does not exist, it will check for the .tmp file.
     *
     * @param absolutePath the name of the file to read
     * @return the file content
     * @throws IOException if the method failed to read the file
     */
    public static String readFile(String absolutePath) throws IOException {
        String newPath = !fileExist(absolutePath) && fileExist(absolutePath + ".tmp") ? absolutePath + ".tmp" : absolutePath;
        return Files.readString(Path.of(newPath));
    }

    private static boolean fileExist(String absolutePath) {
        return new File(absolutePath).exists();
    }
}
