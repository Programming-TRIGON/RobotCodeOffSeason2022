package frc.trigon.robot.xboxSimulation;

import frc.trigon.robot.utilities.JsonHandler;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class XboxLogsHandler {

    /**
     * @param logs to be written to the json
     * @throws IOException if write fails
     */
    public static void writeLogs(Log[] logs) throws IOException {
        JsonHandler.parseToJsonAndWrite("XboxLogs", logs);
    }

    /**
     * @return the logs from the json file
     */
    public static Log[] readLogs() {
        return JsonHandler.parseJsonFileToObject("XboxLogs", Log[].class);
    }
}
