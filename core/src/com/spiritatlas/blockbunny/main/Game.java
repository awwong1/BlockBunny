package com.spiritatlas.blockbunny.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spiritatlas.blockbunny.handlers.BBInput;
import com.spiritatlas.blockbunny.handlers.BBInputProcessor;
import com.spiritatlas.blockbunny.handlers.BoundedCamera;
import com.spiritatlas.blockbunny.handlers.Content;
import com.spiritatlas.blockbunny.handlers.GameStateManager;

public class Game implements ApplicationListener {

    public static final String TITLE = "Block Bunny";
    public static final int V_WIDTH = 320;
    public static final int V_HEIGHT = 240;
    public static final int SCALE = 2;
    public static final float STEP = 1 / 60f;

    private SpriteBatch sb;
    private BoundedCamera cam;
    private OrthographicCamera hudCam;

    private GameStateManager gsm;

    public static Content res;

    public void create() {
        Gdx.input.setInputProcessor(new BBInputProcessor());

        res = new Content();
        res.loadTexture("res/images/menu.png");
        res.loadTexture("res/images/bgs.png");
        res.loadTexture("res/images/hud.png");
        res.loadTexture("res/images/bunny.png");
        res.loadTexture("res/images/crystal.png");
        res.loadTexture("res/images/spikes.png");

        res.loadSound("res/sfx/jump.wav");
        res.loadSound("res/sfx/crystal.wav");
        res.loadSound("res/sfx/levelselect.wav");
        res.loadSound("res/sfx/hit.wav");
        res.loadSound("res/sfx/changeblock.wav");

        res.loadMusic("res/music/bbsong.ogg");
        res.getMusic("bbsong").setLooping(true);
        res.getMusic("bbsong").setVolume(0.5f);
        res.getMusic("bbsong").play();

        cam = new BoundedCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

        sb = new SpriteBatch();

        gsm = new GameStateManager(this);

    }

    public void render() {

        Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
        BBInput.update();

    }

    public void dispose() {
        res.removeAll();
    }

    public void resize(int w, int h) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public SpriteBatch getSpriteBatch() {
        return sb;
    }

    public BoundedCamera getCamera() {
        return cam;
    }

    public OrthographicCamera getHUDCamera() {
        return hudCam;
    }

}
