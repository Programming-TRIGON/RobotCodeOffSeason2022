package frc.trigon.robot.components;

/**
 * A Limelight with the ability to calculate the distance from the hub.
 */
public class HubLimelight extends Limelight {
    private static final double A = 0.003;
    private static final double B = 0.0968;
    private static final double C = 2.0034;

    /**
     * Constructs a new HubLimelight.
     *
     * @param hostname the name of the Limelight
     */
    public HubLimelight(String hostname) {
        super(hostname);
    }

    /**
     * @return the distance from the hub in meters
     */
    public double getDistanceFromHub() {
        return (A * Math.pow(getTy(), 2)) + (B * getTy()) + C;
    }
}
