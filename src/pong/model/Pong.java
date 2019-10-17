package pong.model;


import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import pong.event.ModelEvent;
import pong.event.EventBus;
import pong.view.PongGUI;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/*
 * Logic for the Pong Game
 * Model class representing the "whole" game
 * Nothing visual here
 *
 */
public class Pong {

    public static final double GAME_WIDTH = 600;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.02;
    public static final long HALF_SEC = 500_000_000;


    // TODO More attributes
    private int pointsLeft;
    private int pointsRight;
    private IPositionable ball, rightPaddle, leftPaddle, floor, ceiling;

    // TODO Constructor
    public Pong(Ball ball, Paddle rightPaddle, Paddle leftPaddle, Floor floor, Ceiling ceiling) {
        this.ball = ball;
        this.rightPaddle  = rightPaddle;
        this.leftPaddle = leftPaddle;
        this.floor = floor;
        this.ceiling = ceiling;
    }

    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions

    public void update(long now) {
       // tODO Gamelogic here
        for (IPositionable i : getPositionables()) {
            i.move();
        }
        String outside = ((Ball) ball).outsideWindow();
        if (outside.equals("right")) {
            pointsLeft++;
        } else if (outside.equals("left")) {
            pointsRight++;
        }
        checkCollision(now);
    }

    public void checkCollision(long now) {
        ((Ball) ball).wallCollision(floor, ceiling);
        ((Paddle) leftPaddle).wallCollision(floor, ceiling);
        ((Paddle) rightPaddle).wallCollision(floor, ceiling);
        if (timeForLastHit + HALF_SEC - now <= 0) {
            if (((Ball) ball).paddleCollision(rightPaddle, leftPaddle)) {
                EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.BALL_HIT_PADDLE));
                timeForLastHit = System.nanoTime();
            }
        }
    }


    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> drawables = new ArrayList<>();
        drawables.add(ball);
        drawables.add(rightPaddle);
        drawables.add(leftPaddle);
        drawables.add(floor);
        drawables.add(ceiling);
        return drawables;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public int getPointsRight() {
        return pointsRight;
    }

    public void setSpeedRightPaddle(double dy) {
        // TODO
        ((Paddle) rightPaddle).setDy(dy);
    }

    public void setSpeedLeftPaddle(double dy) {
        // TODO
        ((Paddle) leftPaddle).setDy(dy);
    }
}
