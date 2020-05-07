package com.mycompany.arkanoida.shared;


public class BallBrickController {

    private GameSound gameSound = new GameSound();

    /** ustawiamy reakcje na zdażenia*/

    public void update(final PlayerBall ball, ArkanoidGame game, double ballSpeed) {

        Double motionX = ball.getMotionX();
        Double motionY = ball.getMotionY();
        if (motionX  == null && motionY  == null) {
            motionX = 0.0;
            motionY = ballSpeed;
            ball.setMotionX(motionX);
            ball.setMotionY(motionY);
        }

        //sprawdzanie czy piłka nie dotkneła już krawędzi jeśli tak to zmieniamy kierunek na przeciwny
        double nextX = (ball.getX() + motionX);
        double nextY = (ball.getY() + motionY );


        //odbicie lewo prawo piłka zmienia kierunek
        if (nextX < ArkanoidGame.EDGE || nextX + PlayerBall.WIDTH >= ArkanoidGame.WIDTH - ArkanoidGame.EDGE) {

            gameSound.getBallSound();
            motionX *= -1;
            ball.setMotionX(motionX);

        }

        //odbicie góra dół piłka zmienia kierunek
        if (nextY < ArkanoidGame.EDGE || nextY + PlayerBall.HEIGHT >= ArkanoidGame.HEIGHT - ArkanoidGame.EDGE ){

            motionY *= -1;
            ball.setMotionY(motionY);
        }

        //odbicie od paletki
        if(nextY + PlayerBall.HEIGHT >= ArkanoidGame.HEIGHT - PlayerPaddle.HEIGHT -10
                && nextX > game.getPaddle().getX() && nextX < game.getPaddle().getX()+PlayerPaddle.WIDTH){

            //odległość piłki od środka paletki po lewej stronie paletki
            int left = (int)(((game.getPaddle().getX()+(PlayerPaddle.WIDTH/2)) - nextX)/10);

            //odległość piłki od środka paletki po prawej stronie paletki
            int right = (int) (((game.getPaddle().getX()+PlayerPaddle.WIDTH) - (game.getPaddle().getX()+(PlayerPaddle.WIDTH/2)))/10);


            //jeśli piłka jest na lewej stronie paletki
            if(nextX < game.getPaddle().getX()+(PlayerPaddle.WIDTH/2)){

                double[] arr ;
                arr = angle(ball,left);
                double resultX = arr[0];
                double resultY = arr[1];
                ball.setMotionX(-resultX);
                ball.setMotionY(resultY);
            }

            //jeśli piłka jest na prawej stronie paletki
            else if(nextX >= game.getPaddle().getX()+(PlayerPaddle.WIDTH/2) && nextX < game.getPaddle().getX()+PlayerPaddle.WIDTH){

                double[] arr ;
                arr = angle(ball,right);
                double resultX = arr[0];
                double resultY = arr[1];
                ball.setMotionX(resultX);
                ball.setMotionY(resultY);
            }
            motionY *= -1;
            gameSound.playPaddle();
            ball.setMotionY(motionY);
        }

        //jeśli piłka spanie poniżej paletki
        if(nextY + PlayerBall.HEIGHT >= ArkanoidGame.HEIGHT - PlayerPaddle.HEIGHT +20){
            ball.setX(ArkanoidGame.WIDTH / 2 - PlayerBall.WIDTH / 2);
            ball.setY(ArkanoidGame.HEIGHT - PlayerBall.HEIGHT - 20 - PlayerPaddle.HEIGHT);
            game.getPaddle().setX(ArkanoidGame.WIDTH / 2 - PlayerPaddle.WIDTH / 2);
            game.getPaddle().setY(ArkanoidGame.HEIGHT - PlayerPaddle.HEIGHT - 20);
            game.getLives().remove(0);
            gameSound.playDropball();
            if (game.getLives().size()<=0){
                ball.setMotionX(0.0);
                ball.setMotionY(0.0);
                gameSound.playGameover();
            }
        }

        //jeśli piłka udeży w żółtą cegiełke
        for(BrickY b : game.getBrickYlist()) {
            if (nextY + PlayerBall.HEIGHT <= ArkanoidGame.HEIGHT - 550
                    && nextX > b.getX() && nextX < b.getX() + BrickY.WIDTH) {

                gameSound.playBrickball();
                game.getBrickYlist().remove(b);
                motionY *= -1;
                ball.setMotionY(motionY);
            }
        }

        //jeśli piłka udeży w niebieską cegiełke
            for(BrickB c: game.getBrickBlist()) {
                if(nextY + PlayerBall.HEIGHT <= ArkanoidGame.HEIGHT -600
                        && nextX > c.getX() && nextX < c.getX()+BrickB.WIDTH){

                    gameSound.playBrickball();
                    motionY *= -1;
                    ball.setMotionY(motionY);
                    c.setCount(c.getCount()-1);
                    }
                if(c.getCount()<1) {
                    game.getBrickBlist().remove(c);
                }
        }

        //jeśli piłka udeży w czerwoną cegiełke
        for(BrickR c: game.getBrickRlist()) {
            if(nextY + PlayerBall.HEIGHT <= ArkanoidGame.HEIGHT -650
                    && nextX > c.getX() && nextX < c.getX()+BrickR.WIDTH){

                gameSound.playBrickball();
                motionY *= -1;
                ball.setMotionY(motionY);
                c.setCount(c.getCount()-1);
            }
            if(c.getCount()<1) {
                game.getBrickRlist().remove(c);
            }
        }

        //ruch piłki
        ball.setX(ball.getX() + motionX  * ballSpeed);
        ball.setY(ball.getY() + motionY  * ballSpeed);

    }

    //ustawienia kąta ruchu piłki
    public double[] angle(PlayerBall ball, int i){

        Double motionX = ball.getMotionX();
        Double motionY = ball.getMotionY();
        double[] arr = new double[1];

        switch (i){
            case 0:
                motionX=0.0;
                motionY =1.0;
                break;
            case 1:
                motionX=0.1;
                motionY =0.9;
                break;
            case 2:
                motionX=0.2;
                motionY =0.8;
                break;
            case 3:
                motionX=0.3;
                motionY =0.7;
                break;
            case 4:
                motionX=0.4;
                motionY =0.6;
                break;
            case 5:
                motionX=0.6;
                motionY =0.5;
                break;
            case 6:
                motionX=0.8;
                motionY =0.4;
                break;
            case 7:
                motionX=1.0;
                motionY =0.3;
                break;
            case 8:
                motionX=1.2;
                motionY =0.2;
                break;
        }
        arr[0]= motionX;
        arr[1]= motionY;
        return arr;
    }

}
