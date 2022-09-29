package frc.trigon.robot.xboxSimulation;

public class Log {
    private final double rightStickX, rightStickY, leftStickX, leftStickY,time;

    public Log(double rightStickX, double rightStickY, double leftStickX, double leftStickY, double time) {
        this.rightStickX = rightStickX;
        this.rightStickY = rightStickY;
        this.leftStickX = leftStickX;
        this.leftStickY = leftStickY;
        this.time = time;
    }

    public double getRightStickX() {
        return rightStickX;
    }

    public double getRightStickY() {
        return rightStickY;
    }

    public double getLeftStickX() {
        return leftStickX;
    }

    public double getLeftStickY() {
        return leftStickY;
    }

    public double getTime() {
        return time;
    }


}
