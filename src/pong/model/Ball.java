package pong.model;

import java.lang.reflect.WildcardType;
import java.util.Random;
import static pong.model.Pong.GAME_HEIGHT;
import static pong.model.Pong.GAME_WIDTH;

/*
 * A Ball for the Pong game
 * A model class
 */
public class Ball implements IPositionable  {

    public enum Direction{
        UP, DOWN, RIGHT, LEFT
    }

    public static final double WIDTH = 40;
    public static final double HEIGHT = 40;
    public static Pong pong;
    public double dx, dy;
    public double x,y;
    public Direction xdir, ydir;

    public Ball() {
        this.x = pong.GAME_WIDTH / 2 - WIDTH / 2;
        this.y = pong.GAME_HEIGHT / 2 - HEIGHT / 2;
        this.dx = 2.0;
        this.dy = 2.0;
        setRandomDirections();
    }

    public void move() {
        switch (xdir) {
            case LEFT: this.x -= this.dx; break;
            case RIGHT: this.x += this.dx; break;
        }
        switch (ydir) {
            case UP: this.y -= this.dy; break;
            case DOWN: this.y += this.dy; break;
        }
    }

    public void accel() {
        dx *= pong.BALL_SPEED_FACTOR;
        dy *= pong.BALL_SPEED_FACTOR;
    }

    public void setRandomDirections() {
        Random r = new Random();
        this.ydir = Direction.values()[r.nextInt(Direction.values().length / 2)];
        this.xdir = Direction.values()[r.nextInt(Direction.values().length / 2) + 2];
    }

    public String outsideWindow() {
        if (x + WIDTH / 2 >= GAME_WIDTH) {
            resetBall();
            System.out.println("right");
            return "right";
        } else if (x + WIDTH / 2 <= 0) {
            resetBall();
            System.out.println("left");
            return "left";
        }
        return "";
    }

    private void resetBall() {
        this.x = pong.GAME_WIDTH / 2 - WIDTH / 2;
        this.y = pong.GAME_HEIGHT / 2 - HEIGHT / 2;
        this.dx = 2.0;
        this.dy = 2.0;
        setRandomDirections();
    }

    public void wallCollision(IPositionable floor, IPositionable ceiling) {
        if (y + HEIGHT >= floor.getY() || y < ceiling.getY()) {
            dy = dy * -1;
        }
    }

    public boolean paddleCollision(IPositionable rightPaddle, IPositionable leftPaddle) {
        if (x + WIDTH >= rightPaddle.getX() && y + HEIGHT > rightPaddle.getY() && y < rightPaddle.getY() + rightPaddle.getHeight() ||
                x < leftPaddle.getX() + leftPaddle.getWidth() && y + HEIGHT > leftPaddle.getY() && y < leftPaddle.getY() + leftPaddle.getHeight()) {
            dx = dx * -1;
            accel();
            return true;
        }
        return false;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }
}
