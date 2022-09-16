package frc.trigon.robot.utilities;

import edu.wpi.first.wpilibj.Filesystem;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesHandler {

    public static final String DEPLOY_PATH = Filesystem.getDeployDirectory().getPath() + "\\";

    /**
     * Deletes a file.
     *
     * @param name the file name
     * @throws IOException if the method failed to delete the specified file
     */
    public static void deleteFile(String name) throws IOException {
        File file = new File(name);
        if(!file.delete()) {
            throw new IOException("Failed to delete the file \"" + name + "\".");
        }
    }

    /**
     * Writes the given text to a file.
     *
     * @param name the file name
     * @param text the text to write to the file
     * @throws IOException if the method failed to write the text to the file.
     */
    public static void writeStringToFile(String name, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Renames a file.
     *
     * @param name    the name of the file to rename
     * @param newName the new name of the desired name
     * @throws IOException if the method failed to rename the file
     */
    public static void renameFile(String name, String newName) throws IOException {
        File file = new File(name);
        if(!file.renameTo(new File(newName))) {
            throw new IOException("Failed to rename file " + name + " to " + newName);
        }
    }

    /**
     * Creates a file using a safe method of writing.
     * This method will write the string to a temporary file,
     * rename the existing file to its name.bak.
     * rename the temporary file to the desired name.
     * delete the .bak file.
     *
     * @param name the name of the file to create
     * @param text the text to write in the file
     * @throws IOException if the method failed to safe write the file
     */
    public static void safeWrite(String name, String text) throws IOException {
        writeStringToFile(name + ".tmp", text);
        if(fileExist(name))
            renameFile(name, name + ".bak");
        renameFile(name + ".tmp", name);
        if(fileExist(name + ".bak"))
            deleteFile(name + ".bak");
    }

    /**
     * Reads a file and returns its content as a string.
     * If the files does not exist, it will check for the .tmp file.
     *
     * @param name the name of the file to read
     * @return the file content
     * @throws IOException if the method failed to read the file
     */
    public static String readFile(String name) throws IOException {
        FileReader reader;
        StringBuilder fileContent = new StringBuilder();
        reader = new FileReader(!fileExist(name) && fileExist(name + ".tmp") ? name + ".tmp" : name);
        while(reader.read() != -1) {
            fileContent.append((char) reader.read());
        }
        return fileContent.toString();
    }

    /**
     * Checks if a file exists by its name.
     *
     * @param name the name of the file
     * @return true if the file exists, false otherwise
     */
    private static boolean fileExist(String name) {
        return new File(name).exists();
    }
}
