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
     * Deletes the given file.
     *
     * @param absolutePath the files absolute path
     * @throws IOException if the method failed to delete the specified file
     */
    public static void deleteFile(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        if(!file.delete()) {
            throw new IOException("Failed to delete the file \"" + absolutePath + "\".");
        }
    }

    /**
     * Writes the given String to a file.
     *
     * @param absolutePath the files absolute path
     * @param str          the string to write to the file
     * @throws IOException if the method failed to write the string to the file.
     */
    public static void writeStringToFile(String absolutePath, String str) throws IOException {
        FileWriter fileWriter = new FileWriter(absolutePath);
        fileWriter.write(str);
        fileWriter.close();
    }

    /**
     * Renames a file.
     *
     * @param absolutePath the absolute path of the file to rename
     * @param newName      the new name of the desired name
     * @throws IOException if the method failed to rename the file
     */
    public static void renameFile(String absolutePath, String newName) throws IOException {
        File file = new File(absolutePath);
        if(!file.renameTo(new File(extractPathFromAbsolutePath(absolutePath) + newName))) {
            throw new IOException("Failed to rename file " + absolutePath + " to " + newName);
        }
    }

    /**
     * Creates a file using a safe method of writing.
     * This method will write the string to a temporary file,
     * rename the existing file to its name.bak,
     * rename the temporary file to the desired name,
     * and delete the .bak file.
     *
     * @param absolutePath the absolute path of the file to write to
     * @param str          the string to write to the file
     * @throws IOException if the method failed to safe write the file
     */
    public static void safeWrite(String absolutePath, String str) throws IOException {
        writeStringToFile(absolutePath + ".tmp", str);
        if(fileExists(absolutePath))
            renameFile(absolutePath, extractNameFromAbsolutePath(absolutePath) + ".bak");
        renameFile(absolutePath + ".tmp", extractNameFromAbsolutePath(absolutePath));
        if(fileExists(absolutePath + ".bak"))
            deleteFile(absolutePath + ".bak");
    }

    /**
     * Reads a file and returns its content as a string.
     * If the files does not exist, it will check for the .tmp file.
     *
     * @param absolutePath the absolute path of the file to read
     * @return the file content
     * @throws IOException if the method failed to read the file
     */
    public static String readFile(String absolutePath) throws IOException {
        String newPath = !fileExists(absolutePath) && fileExists(absolutePath + ".tmp") ?
                         absolutePath + ".tmp" :
                         absolutePath;
        return Files.readString(Path.of(newPath));
    }

    private static boolean fileExists(String absolutePath) {
        return new File(absolutePath).exists();
    }

    private static String extractPathFromAbsolutePath(String absolutePath) {
        List<String> absolutePathSplit = new ArrayList<>(List.of(absolutePath.split("\\\\")));
        absolutePathSplit.remove(absolutePathSplit.size() - 1);
        return String.join("\\", absolutePathSplit) + "\\";
    }

    private static String extractNameFromAbsolutePath(String absolutePath) {
        return absolutePath.split("\\\\")[absolutePath.split("\\\\").length - 1];
    }
}
