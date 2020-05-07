package com.mycompany.arkanoida.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.media.client.Audio;

public class GameSound {

    private Audio ballSound;
    private Audio brickballSound;
    private Audio dropball;
    private Audio gameover;
    private Audio paddle;

    public GameSound() {

        ballSound = Audio.createIfSupported();
        ballSound.addSource(GWT.getModuleBaseForStaticFiles() + "wallball.wav", AudioElement.TYPE_WAV);

        brickballSound = Audio.createIfSupported();
        brickballSound.addSource(GWT.getModuleBaseForStaticFiles() + "brickball.wav", AudioElement.TYPE_WAV);

        dropball = Audio.createIfSupported();
        dropball.addSource(GWT.getModuleBaseForStaticFiles() + "dropball.wav", AudioElement.TYPE_WAV);

        gameover = Audio.createIfSupported();
        gameover.addSource(GWT.getModuleBaseForStaticFiles() + "gameover.wav", AudioElement.TYPE_WAV);

        paddle = Audio.createIfSupported();
        paddle.addSource(GWT.getModuleBaseForStaticFiles() + "paddle.wav", AudioElement.TYPE_WAV);
    }

    public void getBallSound() {
        ballSound.play();
    }

    public void playBrickball(){
        brickballSound.play();
    }

    public void playDropball(){
        dropball.play();
    }

    public void playGameover(){
        gameover.play();
    }

    public void playPaddle(){
        paddle.play();
    }
}
