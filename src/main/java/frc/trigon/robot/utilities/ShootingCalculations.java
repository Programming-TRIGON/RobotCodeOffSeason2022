package frc.trigon.robot.utilities;

import edu.wpi.first.math.geometry.Translation2d;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used to calculate the shooting angle and distance for the shooter.
 */
public class ShootingCalculations {
    private static final ArrayList<Waypoint> waypoints = new ArrayList<>();

    public static Waypoint all(double distance) {
        Waypoint[] waypointsFromDistance = getWaypointsFromDistance(distance);
        if(waypointsFromDistance == null) {
            return null;
        }
        double velocity = calculateM(
                waypointsFromDistance[0].distance,
                waypointsFromDistance[0].velocity,
                waypointsFromDistance[1].distance,
                waypointsFromDistance[1].velocity,
                distance);
        double angle = calculateM(
                waypointsFromDistance[0].distance,
                waypointsFromDistance[0].angle,
                waypointsFromDistance[1].distance,
                waypointsFromDistance[1].angle,
                distance);

        return new Waypoint(distance, velocity, angle);
    }

    /**
     * Gets the velocity needed for the shooter to shoot from a distance.
     *
     * @param distance The distance from the target
     * @return the velocity needed for the shooter
     */
    public static double getShootingVelocityFromDistance(double distance) {
        return Objects.requireNonNullElse(all(distance), new Waypoint(0, 0, 0)).velocity;
    }

    /**
     * Gets the angle needed for the shooter to shoot from a distance.
     *
     * @param distance The distance from the target
     * @return the angle needed for the shooter
     */
    public static double getShootingAngleFromDistance(double distance) {

        return Objects.requireNonNullElse(all(distance), new Waypoint(0, 0, 0)).angle;
    }

    /**
     * Calculates the value between two translations.
     *
     * @param firstPoint  The first point
     * @param secondPoint The second point
     * @param pointToKnow The point to know the value of (between the first and second points)
     * @return the value between the two points
     */
    public static double calculateBetweenTranslations(
            Translation2d firstPoint, Translation2d secondPoint, double pointToKnow) {
        double m = (firstPoint.getY() - secondPoint.getY()) / (firstPoint.getX() - secondPoint.getX());
        return m * pointToKnow - m * firstPoint.getX() + firstPoint.getY();
    }

    /**
     * Checks if the given point is smaller than the point in the array point in the array.
     *
     * @param point the point to check
     * @param index the index of a point in the array
     * @return whether the given point is smaller than the point in the array
     */
    public static boolean isPointSmaller(double point, int index) {
        return point < waypoints.get(index).distance;
    }

    /**
     * Adds a waypoint to the waypoints list.
     *
     * @param distance the distance for the waypoint
     * @param velocity the velocity for the waypoint
     * @param angle    the angle for the waypoint
     */
    public static void addWaypoint(double distance, double velocity, double angle) {
        Waypoint toAdd = new Waypoint(distance, velocity, angle);
        waypoints.add(toAdd);
    }

    private static Waypoint[] getWaypointsFromDistance(double distance) {
        Waypoint[] toReturn = new Waypoint[2];
        for(int i = 0; i < waypoints.size(); i++) {
            if(isPointSmaller(distance, i)) {
                toReturn[0] = waypoints.get(i);
                toReturn[1] = waypoints.get(i - 1);
                return toReturn;
            }
        }
        return null;
    }

    public static double calculateM(double x1, double y1, double x2, double y2, double distance) {
        Translation2d
                firstPoint = new Translation2d(x1, y1),
                secondPoint = new Translation2d(x2, y2);
        return calculateBetweenTranslations(firstPoint, secondPoint, distance);
    }

    private static class Waypoint {
        public double distance, velocity, angle;

        public Waypoint(double distance, double velocity, double angle) {
            this.distance = distance;
            this.velocity = velocity;
            this.angle = angle;
        }
    }
}
