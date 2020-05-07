package com.mycompany.arkanoida.shared;

public class GameController {

    private boolean movePaddleLeft, movePaddleRight;
    private BallBrickController ballController;
    private PlayerPaddle paddle;

    public GameController() {
        ballController = new BallBrickController();
    }

    public void updateArkanoidGame( ArkanoidGame arkanoidGame){

        //
        if (movePaddleLeft && arkanoidGame.getPaddle().getX()>0 ) {
            arkanoidGame.getPaddle().setX(arkanoidGame.getPaddle().getX() - 2);
        }
        if (movePaddleRight && arkanoidGame.getPaddle().getX()< ArkanoidGame.WIDTH - PlayerPaddle.WIDTH) {
            arkanoidGame.getPaddle().setX(arkanoidGame.getPaddle().getX() + 2);
        }


        for (PlayerBall ball : arkanoidGame.getBallList()) {
            ballController.update(ball,arkanoidGame,2.0);
        }

    }
     /** metoda która sprawdza czy paletka porusza się w lewo jeśli tak to true , a nie to false*/
    public void playerLeft(boolean b) {
        movePaddleLeft = b;
    }

    /** metoda która sprawdza czy paletka porusza się w prawo jeśli tak to true , a nie to false*/
    public void playerRight(boolean b) {
        movePaddleRight = b;
    }
}
