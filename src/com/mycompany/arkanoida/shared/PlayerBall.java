package com.mycompany.arkanoida.shared;

public class PlayerBall  extends Position {

    public static final int WIDTH = 29;
    public static final int HEIGHT = 25;
    private Double motionX;
    private Double motionY;


    public PlayerBall() {
    }

    public Double getMotionX() {
        return motionX;
    }

    public void setMotionX(Double motionX) {
        this.motionX = motionX;
    }

    public Double getMotionY() {
        return motionY;
    }

    public void setMotionY(Double motionY) {
        this.motionY = motionY;
    }

}
