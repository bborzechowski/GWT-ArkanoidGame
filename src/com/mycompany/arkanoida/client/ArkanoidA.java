package com.mycompany.arkanoida.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.EntryPoint;
import com.mycompany.arkanoida.shared.ArkanoidGame;


public class ArkanoidA implements EntryPoint {

  private Image paddleGame;
  private Image ballGame;
  private Image brickYellow;
  private Image brickBlue;
  private Image brickRed;
  private Image tlo;
  private Image heart;
  private Image gameover;
  private Image brickBluedark;
  private Image brickReddark;
  private Image brickRedBlack;


  public void onModuleLoad() {

    //wgrywanie obrazów
    String paddleGameUrl = GWT.getModuleBaseForStaticFiles() + "PaddleGame.png";
    String ballGameUrl = GWT.getModuleBaseForStaticFiles() + "BallGame.png";
    String brickYellowUrl = GWT.getModuleBaseForStaticFiles() + "BrickYellow.png";
    String brickBlueUrl = GWT.getModuleBaseForStaticFiles() + "BrickBlue.png";
    String brickRedUrl = GWT.getModuleBaseForStaticFiles() + "BrickRed.png";
    String tloUrl = GWT.getModuleBaseForStaticFiles() + "tlo.jpg";
    String heartUrl = GWT.getModuleBaseForStaticFiles() + "heart.png";
    String gameoverUrl = GWT.getModuleBaseForStaticFiles() + "gameover.png";
    String brickBluedarkUrl = GWT.getModuleBaseForStaticFiles() + "BrickBluedark.png";
    String brickReddarkUrl = GWT.getModuleBaseForStaticFiles() + "BrickReddark.png";
    String brickRedBlackUrl = GWT.getModuleBaseForStaticFiles() + "BrickRedBlack.png";

    paddleGame = new Image(paddleGameUrl);
    ballGame = new Image(ballGameUrl);
    brickYellow = new Image(brickYellowUrl);
    brickBlue = new Image(brickBlueUrl);
    brickRed = new Image(brickRedUrl);
    tlo = new Image(tloUrl);
    heart = new Image(heartUrl);
    gameover = new Image(gameoverUrl);
    brickBluedark = new Image((brickBluedarkUrl));
    brickReddark = new Image(brickReddarkUrl);
    brickRedBlack = new Image(brickRedBlackUrl);

    //tworzenie gry i widoku
    ArkanoidGame game = new ArkanoidGame();
    ArkanoidGameView view = new ArkanoidGameView();
    view.setModel(game);

    //dodajemy widok
    RootLayoutPanel.get().add(view);

    //ustawiamy odniesienia widoku do obrazów
    view.setBallGame(ballGame);
    view.setPaddleGame(paddleGame);
    view.setBrickYellow(brickYellow);
    view.setBrickBlue(brickBlue);
    view.setBrickRed(brickRed);
    view.setTlo(tlo);
    view.setHeart(heart);
    view.setGameover(gameover);
    view.setBrickBlueDark(brickBluedark);
    view.setBrickReddark(brickReddark);
    view.setBrickRedBlack(brickRedBlack);

    view.activate();

  }

}
