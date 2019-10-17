package pong.model;

import static pong.model.Pong.GAME_HEIGHT;

public class Floor implements IPositionable {

        private double x,y;

    public Floor() {
            this.x = 0;
            this.y = GAME_HEIGHT;
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
