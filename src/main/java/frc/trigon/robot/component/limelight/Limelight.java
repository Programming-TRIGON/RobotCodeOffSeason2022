package frc.trigon.robot.component.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    NetworkTableEntry tv, tx, ty, ts, ta, ledMode, driverCam, pipeline, snapshot;

    public Limelight(String table) {
        NetworkTable networkTable = NetworkTableInstance.getDefault().getTable(table);

        tv = networkTable.getEntry("tv");
        tx = networkTable.getEntry("tx");
        ty = networkTable.getEntry("ty");
        ts = networkTable.getEntry("ts");
        ta = networkTable.getEntry("ta");
        ledMode = networkTable.getEntry("ledMode");
        driverCam = networkTable.getEntry("camMode");
        pipeline = networkTable.getEntry("pipeline");
        snapshot = networkTable.getEntry("snapshot");
    }

    /**
     * @return vertical offset from crosshair to target (-20.5 degrees to 20.5 degrees)
     */
    public double getTy() {
        return ty.getDouble(0);
    }

    /**
     * @return horizontal offset from crosshair to target (-27 degrees to 27 degrees)
     */
    public double getTx() {
        return tx.getDouble(0);
    }

    /**
     * @return target skew or rotation (-90 degrees to 0 degrees)
     */
    public double getTs() {
        return ts.getDouble(0);
    }

    /**
     * @return target area (0% of image to 100% of image)
     */
    public double getTa() {
        return ta.getDouble(0);
    }

    /**
     * @return true if the limelight has any valid targets, false otherwise
     */
    public boolean hasTarget() {
        return tv.getDouble(0) == 1;
    }

    /**
     * @return true if the driverCam is used, false if the visionCam is used
     */
    public boolean driverCam() {
        return driverCam.getDouble(0) == 1;
    }

    /**
     * sets the DriverCamera Mode
     *
     * @param useDriverCam true for driver camera, false for vision processing
     */
    public void setDriverCam(boolean useDriverCam) {
        driverCam.setNumber(useDriverCam ? 1 : 0);
    }

    /**
     * @return led mode (0: use the LED Mode set in the current pipeline, 1: force off, 2: force blink, 3: force on)
     */
    public double getLedMode() {
        return ledMode.getDouble(0);
    }

    /**
     * sets the led mode
     *
     * @param mode (0: use the LED Mode set in the current pipeline, 1: force off, 2: force blink, 3: force on)
     */
    public void setLedMode(int mode) {
        ledMode.setNumber(mode);
    }

    /**
     * @return current pipeline
     */
    public double getPipeline() {
        return pipeline.getDouble(0);
    }

    /**
     * sets the pipeline
     *
     * @param pipeline (0-9)
     */
    public void setPipeline(int pipeline) {
        this.pipeline.setNumber(pipeline);
    }

    /**
     * @return snapshot mode (0: stop, 1: 2Hz)
     */
    public double getSnapshot() {
        return snapshot.getDouble(0);
    }

    /**
     * sets the snapshot mode
     *
     * @param mode (0: stop, 1: 2Hz)
     */
    public void setSnapshot(int mode) {
        snapshot.setNumber(mode);
    }
}
