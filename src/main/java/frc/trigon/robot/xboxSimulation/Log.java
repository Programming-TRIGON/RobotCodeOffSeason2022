package frc.trigon.robot.xboxSimulation;

public class Log {
    private final double rightX, rightY, leftX, leftY, rightTrigger, leftTrigger, time;
    private final boolean a, b, x, y, rightBumper, leftBumper;

    public Log(
            double rightX, double rightY, double leftX, double leftY, double rightTrigger, double leftTrigger,
            boolean a, boolean b, boolean x, boolean y, boolean rightBumper, boolean leftBumper, double time) {
        this.rightX = rightX;
        this.rightY = rightY;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightTrigger = rightTrigger;
        this.leftTrigger = leftTrigger;
        this.a = a;
        this.b = b;
        this.x = x;
        this.y = y;
        this.rightBumper = rightBumper;
        this.leftBumper = leftBumper;
        this.time = time;
    }

    public double getRightX() {
        return rightX;
    }

    public double getRightY() {
        return rightY;
    }

    public double getLeftX() {
        return leftX;
    }

    public double getLeftY() {
        return leftY;
    }

    public double getRightTrigger() {
        return rightTrigger;
    }

    public double getLeftTrigger() {
        return leftTrigger;
    }

    public boolean getA() {
        return a;
    }

    public boolean getB() {
        return b;
    }

    public boolean getX() {
        return x;
    }

    public boolean getY() {
        return y;
    }

    public boolean getRightBumper() {
        return rightBumper;
    }

    public boolean getLeftBumper() {
        return leftBumper;
    }

    public double getTime() {
        return time;
    }
}
