package frc.trigon.robot.component;

/**
 * A limelight with the ability to calculate the distance from the hub.
 */
public class HubLimelight extends Limelight {
    private static final double M = 1;
    private static final double B = 1;

    /**
     * Constructs a new HubLimelight.
     *
     * @param hostName the host of the limelight
     */
    public HubLimelight(String hostName) {
        super(hostName);
    }

    /**
     * @return the distance from the hub in meters
     */
    public double getDistanceFromHub() {
        return M * getTy() + B;
    }
}
