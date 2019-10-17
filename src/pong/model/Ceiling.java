package pong.model;

public class Ceiling implements IPositionable {

    private double x,y;

    public Ceiling() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public void move() {

    }
}
