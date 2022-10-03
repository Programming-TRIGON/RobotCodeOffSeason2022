package frc.trigon.robot.utilities;

import edu.wpi.first.math.geometry.Translation2d;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used to calculate the shooting angle and distance for the shooter.
 */
public class ShootingCalculations {
    private static final ArrayList<Waypoint> waypoints = new ArrayList<>();

    /**
     * @param distance the distance from the target
     * @return a waypoint with the distance, velocity and angle from the given distance
     */
    public static Waypoint getWaypointFromDistance(double distance) {
        Waypoint[] waypointsFromDistance = getWaypointsFromDistance(distance);
        if(waypointsFromDistance == null) {
            return null;
        }
        double velocity = calculateBetweenTranslations(
                new Translation2d(waypointsFromDistance[0].distance, waypointsFromDistance[0].velocity),
                new Translation2d(waypointsFromDistance[1].distance, waypointsFromDistance[1].velocity),
                distance
        );
        double angle = calculateBetweenTranslations(
                new Translation2d(waypointsFromDistance[0].distance, waypointsFromDistance[0].angle),
                new Translation2d(waypointsFromDistance[1].distance, waypointsFromDistance[1].angle),
                distance
        );
        return new Waypoint(distance, velocity, angle);
    }

    /**
     * Gets the velocity needed for the shooter to shoot from a distance.
     *
     * @param distance the distance from the target
     * @return the velocity needed for the shooter
     */
    public static double getShootingVelocityFromDistance(double distance) {
        return Objects.requireNonNullElse(getWaypointFromDistance(distance), new Waypoint(0, 0, 0)).velocity;
    }

    /**
     * Gets the angle needed for the shooter to shoot from a distance.
     *
     * @param distance The distance from the target
     * @return the angle needed for the shooter
     */
    public static double getShootingAngleFromDistance(double distance) {
        return Objects.requireNonNullElse(getWaypointFromDistance(distance), new Waypoint(0, 0, 0)).angle;
    }

    /**
     * Calculates the value between two translations.
     *
     * @param firstPoint  the first point
     * @param secondPoint the second point
     * @param pointToKnow the point to know the value of (between the first and second points)
     * @return the value between the two points
     */
    public static double calculateBetweenTranslations(
            Translation2d firstPoint, Translation2d secondPoint, double pointToKnow) {
        double m = (firstPoint.getY() - secondPoint.getY()) / (firstPoint.getX() - secondPoint.getX());
        return m * pointToKnow - m * firstPoint.getX() + firstPoint.getY();
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
            if(distance < waypoints.get(i).distance) {
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
