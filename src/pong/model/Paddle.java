package pong.model;

import static pong.model.Pong.GAME_HEIGHT;
import static pong.model.Pong.GAME_WIDTH;

/*
 * A Paddle for the Pong game
 * A model class
 *
 */
public class Paddle implements IPositionable{

    public static final double PADDLE_WIDTH = 10;
    public static final double PADDLE_HEIGHT = 60;
    public static final double PADDLE_SPEED = 5;
    public double x,y,dy;

    public Paddle(String side) {
        this.y = GAME_HEIGHT / 2 - PADDLE_HEIGHT / 2;
        this.setSidePos(side);
    }

    public void setSidePos(String side) {
        if (side.equals("right")) {
            this.x = GAME_WIDTH - PADDLE_WIDTH;
        } else if (side.equals("left")) {
            this.x = 0;
        }
    }


    public void wallCollision(IPositionable floor, IPositionable ceiling) {
        if (y + PADDLE_HEIGHT >= floor.getY() || y < ceiling.getY()) {
            y -= PADDLE_SPEED * dy;
        }
    }

    public int spin(int balldy) {
        System.out.println("spin");
        if (balldy == 1 && dy == -1) {
            return -1;
        } else if (balldy == -1 && dy == 1) {
            return 1;
        }
        return balldy;
    }

    public void setDy(Double dy) {
        this.dy = dy;
    }

    @Override
    public void move() {
        y += PADDLE_SPEED * dy;
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
        return PADDLE_WIDTH;
    }

    @Override
    public double getHeight() {
        return PADDLE_HEIGHT;
    }

}
