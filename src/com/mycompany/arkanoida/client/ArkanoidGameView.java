package com.mycompany.arkanoida.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.mycompany.arkanoida.shared.*;


public class ArkanoidGameView extends Composite {

    private ArkanoidGame model;
    private GameController controller;
    private Canvas canvasBuffer;
    private Context2d bufCtx;
    private Canvas canvas;
    private Context2d ctx;
    private Timer timer;
    private Image paddleGame;
    private Image ballGame;
    private Image brickYellow;
    private Image brickBlue;
    private Image brickRed;
    private Image tlo;
    private Image heart;
    private Image gameover;
    private Image brickBlueDark;
    private Image brickReddark;
    private Image brickRedBlack;
    private boolean startGame = true;



    public ArkanoidGameView() {

        controller = new GameController();

        /** Canvas musi znajdować się w FocusPanel, jeśli będzie obsługiwać wprowadzania z klawiatury.*/
        if (startGame) {
            //focuspanel służy do przechwytywania zdażeń ruchu myszki lub klawiatury
        FocusPanel panel = new FocusPanel();
        panel.setSize(ArkanoidGame.WIDTH + "px", ArkanoidGame.HEIGHT + "px");

        //używamy buffera canvas
        this.canvasBuffer = Canvas.createIfSupported(); //jeśli przegladarka obsługuje zwraca nowy element canvas a jak nie to nulla
        canvasBuffer.setSize(ArkanoidGame.WIDTH + "px", ArkanoidGame.HEIGHT + "px"); //ustawiamy wysokość i szerokość
            //	setCoordinateSpaceHeight/Width - ustawia wysokość wewnętrznej przestrzeni współrzędnych obszaru roboczego.
        canvasBuffer.setCoordinateSpaceWidth(ArkanoidGame.WIDTH);
        canvasBuffer.setCoordinateSpaceHeight(ArkanoidGame.HEIGHT);
        this.bufCtx = canvasBuffer.getContext2d(); //Zwraca kontekst renderowania 2D.

        //widoczny canvas zawartwość buffora jest tutaj kopiowana
        this.canvas = Canvas.createIfSupported();
        canvas.setSize(ArkanoidGame.WIDTH + "px", ArkanoidGame.HEIGHT + "px");
        canvas.setCoordinateSpaceWidth(ArkanoidGame.WIDTH);
        canvas.setCoordinateSpaceHeight(ArkanoidGame.HEIGHT);
        this.ctx = canvas.getContext2d();
        panel.add(canvas);

        canvas.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                handleKeyDown(event);
            }
        });
        canvas.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                handleKeyUp(event);
            }
        });
        canvas.addMouseMoveHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent mouseMoveEvent) {
                handleMouseMoveLeft(mouseMoveEvent);
                handleMouseMoveRight(mouseMoveEvent);
            }
        });
        initWidget(panel);

        //timer animacji
        this.timer = new Timer() {
            @Override
            public void run() {
                if (model != null) {
                    controller.updateArkanoidGame(model);
                    paint();
                }
            }
        };
    }
}

    //lewa strzałka ruch w lewo
    protected void handleKeyDown(KeyDownEvent event) {
        if (event.isLeftArrow()) {
            controller.playerLeft(true);
        }
        if (event.isRightArrow()) {
            controller.playerRight(true);
        }
    }

    //prawa strzałka ruch w prawo
    protected void handleKeyUp(KeyUpEvent event) {
        if (event.isLeftArrow()) {
            controller.playerLeft(false);
        }
        if (event.isRightArrow()) {
            controller.playerRight(false);
        }
    }

    //ruch myszki w lewo
    protected void handleMouseMoveLeft(MouseMoveEvent event) {
        int x = event.getX();
        if (x <= model.getPaddle().getX() + PlayerPaddle.WIDTH / 2) {
            //  model.getPaddle().setX(x);
            controller.playerLeft(true);
        }
        if (x >= model.getPaddle().getX() + PlayerPaddle.WIDTH / 2 && x <= (ArkanoidGame.WIDTH - PlayerPaddle.WIDTH)) {
            // model.getPaddle().setX(x);
            controller.playerLeft(false);
        }
    }


    //ruch myszki w prawo
    protected void handleMouseMoveRight(MouseMoveEvent event) {
        int x = event.getX();
        if (x <= model.getPaddle().getX() + PlayerPaddle.WIDTH / 2) {
            //  model.getPaddle().setX(x);
            controller.playerRight(false);
        }
        if (x >= model.getPaddle().getX() + PlayerPaddle.WIDTH / 2 && x <= (ArkanoidGame.WIDTH - PlayerPaddle.WIDTH)) {
            // model.getPaddle().setX(x);
            controller.playerRight(true);
        }
    }


    /**
     * ustawiamy elementy która mają być wyśwuetlone
     */

    protected void paint() {


        //  bufCtx.setFillStyle("grey");
        bufCtx.clearRect(0, 0, ArkanoidGame.WIDTH, ArkanoidGame.HEIGHT);     //clearREct
        bufCtx.fillRect(0, 0, ArkanoidGame.WIDTH, ArkanoidGame.HEIGHT);

        //ustawiamy tło
        bufCtx.drawImage(
                (ImageElement) tlo.getElement().cast(),
                0,
                0);

        //ustawiamy paletke
        bufCtx.drawImage(
                (ImageElement) paddleGame.getElement().cast(),
                model.getPaddle().getX(),
                model.getPaddle().getY());


        //ustawiamy żółte cegiełki
        for (BrickY b : model.getBrickYlist()) {
            bufCtx.drawImage(
                    (ImageElement) brickYellow.getElement().cast(),
                    b.getX(),
                    b.getY());
        }

        //ustawiamy żółte niebieskie
        for (BrickB b : model.getBrickBlist()) {
            bufCtx.drawImage(
                    (ImageElement) brickBlue.getElement().cast(),
                    b.getX(),
                    b.getY());
        }

        //ustawiamy czerwone cegiełki
        for (BrickR b : model.getBrickRlist()) {
            bufCtx.drawImage(
                    (ImageElement) brickRed.getElement().cast(),
                    b.getX(),
                    b.getY());
        }

        //ustawiamy życia
        for (Life l : model.getLives()) {
            bufCtx.drawImage(
                    (ImageElement) heart.getElement().cast(),
                    l.getX(),
                    l.getY());
        }

        //jeśli nie mamy żyć wstawiagame over
        if (model.getLives().size() <= 0) {
            bufCtx.drawImage(
                    (ImageElement) gameover.getElement().cast(),
                    200, 200);
        }

        //jeżli udeżymy niebieską cegiełke zmienia kolor na ciemniejszy
        for (BrickB b : model.getBrickBlist()) {
            if (b.getCount() == 1) {
                bufCtx.drawImage((ImageElement) brickBlueDark.getElement().cast(),
                        b.getX(),
                        b.getY());
            }
        }

        //jeśli udeżymy w czerwona cegiełke raz to zmieni się na ciemniejszy czerwony a jak drugie raz to jeszcze bardziej ciemniejszy
        for (BrickR b : model.getBrickRlist()) {
            if (b.getCount() == 2) {
                bufCtx.drawImage((ImageElement) brickReddark.getElement().cast(),
                        b.getX(),
                        b.getY());
            } else if (b.getCount() == 1) {
                bufCtx.drawImage((ImageElement) brickRedBlack.getElement().cast(),
                        b.getX(),
                        b.getY());
            }
        }

        //ustawiamy piłke
        for (PlayerBall ball : model.getBallList()) {
            bufCtx.drawImage(
                    (ImageElement) ballGame.getElement().cast(),
                    ball.getX(),
                    ball.getY());
        }

        // kopiowanie bufferu do canvas
        ctx.drawImage((CanvasElement) canvasBuffer.getElement().cast(), 0, 0);
    }


        public void activate () {
            timer.scheduleRepeating(1000 / 60);
        }

        public void setModel (ArkanoidGame model){
            this.model = model;
        }

        public void setPaddleGame (Image paddleGame){
            this.paddleGame = paddleGame;
        }

        public void setBallGame (Image ballGame){
            this.ballGame = ballGame;
        }

        public void setBrickYellow (Image brickYellow){
            this.brickYellow = brickYellow;
        }

        public void setBrickBlue (Image brickBlue){
            this.brickBlue = brickBlue;
        }

        public void setBrickRed (Image brickRed){
            this.brickRed = brickRed;
        }

        public void setHeart (Image heart){
            this.heart = heart;
        }

        public void setTlo (Image tlo){
            this.tlo = tlo;
        }

        public void setBrickBlueDark (Image brickBlueDark){
            this.brickBlueDark = brickBlueDark;
        }

        public void setBrickReddark (Image brickReddark){
            this.brickReddark = brickReddark;
        }

        public void setBrickRedBlack (Image brickRedBlack){
            this.brickRedBlack = brickRedBlack;
        }

        public void setGameover (Image gameover){
            this.gameover = gameover;
        }

}
