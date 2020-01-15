package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State (GameStateManager gam){
        this.gsm = gam;
        camera = new OrthographicCamera();
        mouse = new Vector3();

    }
    protected abstract void handleInput();
    public abstract void update(float dt);//dt is the delta time, that is the time between the frames rendered
    public abstract void render(SpriteBatch sb);//SpriteBatch is roughly a box where all of our sprites are going to be store for the use of rendering
    public abstract void release();

}
