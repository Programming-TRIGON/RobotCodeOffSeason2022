package frc.trigon.robot.utilities;

import edu.wpi.first.math.geometry.Translation2d;
import java.util.ArrayList;

public class ShootingCalculations {
    static ArrayList<Waypoint> waypoints = new ArrayList<>();

    public static double getShootingVelocityFromDistance(double distance) {
        for(int i = 0; i < distance; i++) {
            if(waypoints.get(i).distance == distance) {
                return waypoints.get(i).velocity;
            }

            if(waypoints.get(i).distance < distance && waypoints.get(i + 1).distance > distance) {
                Translation2d firstPoint = new Translation2d(waypoints.get(i).distance, waypoints.get(i).velocity);
                Translation2d secondPoint = new Translation2d(
                        waypoints.get(i + 1).distance, waypoints.get(i + 1).velocity);
                return calculateBetweenTranslations(firstPoint, secondPoint, distance);
            }
        }
        return 0;
    }

    public static double getShootingAngelFromDistance(double distance) {
        for(int i = 0; i < distance; i++) {
            if(waypoints.get(i).distance == distance) {
                return waypoints.get(i).angle;
            }

            if(waypoints.get(i).distance < distance && waypoints.get(i + 1).distance > distance) {
                Translation2d firstPoint = new Translation2d(waypoints.get(i).distance, waypoints.get(i).angle);
                Translation2d secondPoint = new Translation2d(
                        waypoints.get(i + 1).distance, waypoints.get(i + 1).angle);
                return calculateBetweenTranslations(firstPoint, secondPoint, distance);
            }
        }
        return 0;
    }

    public static double calculateBetweenTranslations(
            Translation2d firstPoint, Translation2d secondPoint, double pointToKnow) {
        double m = (firstPoint.getY() - secondPoint.getY()) / (firstPoint.getX() - secondPoint.getX());
        return m * pointToKnow - m * firstPoint.getX() + firstPoint.getY();
    }

    public static void addWaypoint(double distance, double velocity, double angle) {
        Waypoint toAdd = new Waypoint(distance, velocity, angle);
        waypoints.add(toAdd);
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
