package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.FlappyProject;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;
    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("background.png");
        playButton = new Texture("playbutton.png");

    }
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            release();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FlappyProject.WIDTH, FlappyProject.HEIGHT);//drawing the background
        sb.draw(playButton, ( FlappyProject.WIDTH - playButton.getWidth() ) / 2, ( FlappyProject.HEIGHT - playButton.getHeight() ) / 2 );//centering the playbutton image
        sb.end();

    }

    @Override
    public void release() {
        background.dispose();
        playButton.dispose();
    }
}
