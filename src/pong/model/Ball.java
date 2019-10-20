package pong.model;

import java.lang.reflect.WildcardType;
import java.util.Random;
import static pong.model.Pong.GAME_HEIGHT;
import static pong.model.Pong.GAME_WIDTH;
import static pong.model.Pong.BALL_SPEED_FACTOR;

/*
 * A Ball for the Pong game
 * A model class
 */
public class Ball implements IPositionable  {

    public static final double WIDTH = 40;
    public static final double HEIGHT = 40;
    public static final double BALL_BEGIN_SPEED = 3.0;
    public double speed;
    public int dx, dy;
    public double x,y;

    public Ball() {
        this.x = GAME_WIDTH / 2 - WIDTH / 2;
        this.y = GAME_HEIGHT / 2 - HEIGHT / 2;
        speed = BALL_BEGIN_SPEED;
        setRandomDirections();
    }

    public void move() {
        this.x += speed * dx;
        this.y += speed * dy;
    }

    public void accel() {
        speed *= BALL_SPEED_FACTOR;
    }

    public void setRandomDirections() {
        int[] dirs = {1, -1};
        Random r = new Random();
        dy = dirs[r.nextInt(1)];
        dx = dirs[r.nextInt(1)];
    }

    public String outsideWindow() {
        if (x + WIDTH / 2 >= GAME_WIDTH) {
            resetBall();
            return "right";
        } else if (x + WIDTH / 2 <= 0) {
            resetBall();
            return "left";
        }
        return "";
    }

    private void resetBall() {
        this.x = GAME_WIDTH / 2 - WIDTH / 2;
        this.y = GAME_HEIGHT / 2 - HEIGHT / 2;
        speed = BALL_BEGIN_SPEED;
        setRandomDirections();
    }

    public void wallCollision(IPositionable floor, IPositionable ceiling) {
        if (y + HEIGHT >= floor.getY() || y < ceiling.getY()) {
            dy = dy * -1;
        }
    }

    public boolean paddleCollision(IPositionable rightPaddle, IPositionable leftPaddle) {
        if (x + WIDTH >= rightPaddle.getX() && y + HEIGHT > rightPaddle.getY() && y < rightPaddle.getY() + rightPaddle.getHeight()) {
            dy = ((Paddle) rightPaddle).spin(dy);
            dx = dx * -1;
            accel();
            return true;
        } else if (x <= leftPaddle.getX() + leftPaddle.getWidth() && y + HEIGHT > leftPaddle.getY() && y < leftPaddle.getY() + leftPaddle.getHeight()) {
            dy = ((Paddle) leftPaddle).spin(dy);
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
