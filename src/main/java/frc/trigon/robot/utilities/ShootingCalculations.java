package frc.trigon.robot.utilities;

import edu.wpi.first.math.geometry.Translation2d;

import java.util.ArrayList;

/**
 * This class is used to calculate the shooting angle and distance for the shooter.
 */
public class ShootingCalculations {
    private static final ArrayList<Waypoint> waypoints = new ArrayList<>();

    /**
     * Gets the velocity needed for the shooter to shoot from a distance.
     *
     * @param distance The distance from the target
     * @return the velocity needed for the shooter
     */
    public static double getShootingVelocityFromDistance(double distance) {
        Waypoint[] waypointsFromDistance = getWaypointsFromDistance(distance);
        if(waypointsFromDistance == null)
            return 0;
        Translation2d
                firstPoint = new Translation2d(waypointsFromDistance[0].distance, waypointsFromDistance[0].velocity),
                secondPoint = new Translation2d(waypointsFromDistance[1].distance, waypointsFromDistance[1].velocity);
        return calculateBetweenTranslations(firstPoint, secondPoint, distance);
    }

    /**
     * Gets the angle needed for the shooter to shoot from a distance.
     *
     * @param distance The distance from the target
     * @return the angle needed for the shooter
     */
    public static double getShootingAngleFromDistance(double distance) {
        Waypoint[] waypointsFromDistance = getWaypointsFromDistance(distance);
        if(waypointsFromDistance == null)
            return 0;
        Translation2d
                firstPoint = new Translation2d(waypointsFromDistance[0].distance, waypointsFromDistance[0].angle),
                secondPoint = new Translation2d(waypointsFromDistance[1].distance, waypointsFromDistance[1].angle);
        return calculateBetweenTranslations(firstPoint, secondPoint, distance);
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

    private static class Waypoint {
        public double distance, velocity, angle;

        public Waypoint(double distance, double velocity, double angle) {
            this.distance = distance;
            this.velocity = velocity;
            this.angle = angle;
        }
    }
}
