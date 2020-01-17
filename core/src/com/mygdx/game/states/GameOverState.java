package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState extends State{
    private Texture background;
    private  Texture gameOverButton;

    public GameOverState(GameStateManager gam) {
        super(gam);
        background = new Texture("background.png");
        gameOverButton = new Texture("playbutton.png");

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {
        background.dispose();
        gameOverButton.dispose();
    }
}
