package com.mycompany.arkanoida.shared;

import java.util.ArrayList;
import java.util.List;

public class ArkanoidGame {

    /**Szerokość pola gry*/
    public static final int WIDTH = 640;

    /*** Wysokość pola gry*/
    public static final int HEIGHT = 800;

    private PlayerPaddle paddle;
    private PlayerBall ball;
    private List<PlayerBall> ballList;
    private List<BrickY> brickYlist;
    private BrickY brickY;
    private List<BrickB> brickBlist;
    private BrickB brickB;
    private List<BrickR> brickRlist;
    private BrickR brickR;
    private Life life;
    private List<Life> lives;
    public static final int EDGE = 2;

    public ArkanoidGame() {

        this.ballList = new ArrayList<PlayerBall>();
        this.paddle = new PlayerPaddle();
        this.ball = new PlayerBall();
        this.brickY = new BrickY();
        this.brickYlist = new ArrayList<BrickY>();
        this.brickB = new BrickB();
        this.brickBlist = new ArrayList<BrickB>();
        this.brickR = new BrickR();
        this.brickRlist = new ArrayList<BrickR>();
        this.life = new Life();
        this.lives = new ArrayList<Life>();

        ballList.add(ball);

        /** ustawiamy początkowe położenie elementów*/

        //paletka
        paddle.setX(ArkanoidGame.WIDTH / 2 - PlayerPaddle.WIDTH / 2);
        paddle.setY(ArkanoidGame.HEIGHT - PlayerPaddle.HEIGHT - 20);
        //piłka
        ball.setX(ArkanoidGame.WIDTH / 2 - PlayerBall.WIDTH / 2);
        ball.setY(ArkanoidGame.HEIGHT - PlayerBall.HEIGHT - 10 - PlayerPaddle.HEIGHT);

        //żółte ciegiełki
        for (int i = 0; i < 9; i++) {

            BrickY b = new BrickY();
            b.setX(70 * i);
            b.setY(200);
            brickYlist.add(b);
        }

        //niebieskie ciegiełki
        for (int i = 0; i < 9; i++) {

            BrickB b = new BrickB();
            b.setX(70 * i);
            b.setY(150);
            b.setCount(2);
            brickBlist.add(b);
        }

        //czerwone cegiełki
        for (int i = 0; i < 9; i++) {

            BrickR b = new BrickR();
            b.setX(70 * i);
            b.setY(100);
            b.setCount(3);
            brickRlist.add(b);
        }

        //życia
        for (int i = 0; i <3 ; i++) {
            Life life = new Life();
            life.setX(40*i);
            life.setY(0);
            lives.add(life);
        }
    }


    public PlayerPaddle getPaddle() {
        return paddle;
    }

    public PlayerBall getBall() {
        return ball;
    }

    public List<PlayerBall> getBallList() {
        return ballList;
    }

    public List<BrickY> getBrickYlist() {
        return brickYlist;
    }

    public BrickY getBrickY() {
        return brickY;
    }

    public List<BrickB> getBrickBlist() {
        return brickBlist;
    }

    public BrickB getBrickB() {
        return brickB;
    }

    public List<BrickR> getBrickRlist() {
        return brickRlist;
    }

    public BrickR getBrickR() {
        return brickR;
    }

    public Life getLife() {
        return life;
    }

    public List<Life> getLives() {
        return lives;
    }

}
