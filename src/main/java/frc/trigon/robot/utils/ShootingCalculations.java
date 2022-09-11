package frc.trigon.robot.utils;

import edu.wpi.first.math.geometry.Translation2d;
import java.util.ArrayList;

public class ShootingCalculations {
    ArrayList<Waypoint> wayPoints = new ArrayList<>();
    public double getShootingVelocityFromDistance(double distance) {
        for(int i = 0; i < distance; i++) {
            if(wayPoints.get(i).distance == distance) {
                return wayPoints.get(i).velocity;
            }

            if(wayPoints.get(i).distance < distance && wayPoints.get(i + 1).distance > distance) {
                Translation2d firstPoint = new Translation2d(wayPoints.get(i).distance, wayPoints.get(i).velocity);
                Translation2d secondPoint = new Translation2d(wayPoints.get(i+1).distance, wayPoints.get(i+1).velocity);
                return calculateBetweenTranslations(firstPoint, secondPoint, distance);
            }
        }
        return 0;
    }


    public double getShootingAngelFromDistance(double distance) {
        for(int i = 0; i < distance; i++) {
            if(wayPoints.get(i).distance == distance) {
                return wayPoints.get(i).angle;
            }

            if(wayPoints.get(i).distance < distance && wayPoints.get(i + 1).distance > distance) {
                Translation2d firstPoint = new Translation2d(wayPoints.get(i).distance, wayPoints.get(i).angle);
                Translation2d secondPoint = new Translation2d(wayPoints.get(i+1).distance, wayPoints.get(i+1).angle);
                return calculateBetweenTranslations(firstPoint, secondPoint, distance);
            }
        }
        return 0;
    }

    public double calculateBetweenTranslations(
            Translation2d firstPoint, Translation2d secondPoint, double pointToKnow) {
        double m = (firstPoint.getY() - secondPoint.getY()) / (firstPoint.getX() - secondPoint.getX());
        return m * pointToKnow - m * firstPoint.getX() + firstPoint.getY();
    }

    public void addWayPoint(double distance, double velocity, double angle) {
        Waypoint toAdd = new Waypoint(distance, velocity, angle);
        wayPoints.add(toAdd);
    }

    private class Waypoint {
        public double distance, velocity, angle;

        public Waypoint(double distance, double velocity, double angle) {
            this.distance = distance;
            this.velocity = velocity;
            this.angle = angle;
        }
    }
}

