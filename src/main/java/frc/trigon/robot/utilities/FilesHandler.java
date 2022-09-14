package frc.trigon.robot.utilities;

import edu.wpi.first.wpilibj.Filesystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilesHandler {

    private static final String PATH = Filesystem.getDeployDirectory().getPath() + "\\";

    /**
     * Deletes a file.
     *
     * @param name the file name
     * @throws IOException if the method failed to delete the specified file
     */
    public static void deleteFile(String name) throws IOException {
        File file = new File(PATH + name);
        if(!file.delete()) {
            throw new IOException("Failed to delete file.");
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
        FileWriter fileWriter = new FileWriter(PATH + name);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Renames a file.
     *
     * @param oldName the name of the file to rename
     * @param newName the new name of the desired name
     * @throws IOException if the method failed to rename the file
     */
    public static void renameFile(String oldName, String newName) throws IOException {
        File file = new File(PATH + oldName);
        if(!file.renameTo(new File(newName))) {
            throw new IOException("Failed to rename file " + oldName + " to " + newName);
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
     * @throws IOException if the method failed to safe write the file
     */
    public static void safeWrite(String text, String name) throws IOException {
        writeStringToFile(name + ".tmp", text);
        renameFile(name, name + ".bak");
        renameFile(name + ".tmp", name);
        deleteFile(name + ".bak");
    }
}
