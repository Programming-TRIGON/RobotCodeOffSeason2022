package frc.trigon.robot.component;

public class HubLimelight extends Limelight {
    private static final double M = 1;
    private static final double B = 1;

    public HubLimelight(String table) {
        super(table);
    }

    /**
     * @return the distance from the hub in meters
     */
    public double getDistanceFromHub() {
        return M * getTy() + B;
    }
}
