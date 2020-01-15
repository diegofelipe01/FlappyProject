package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.FlappyProject;
import com.mygdx.game.sprites.Birb;

public class PlayState extends State {
    private Birb birb;
    private Texture background;

    public PlayState(GameStateManager gam) {
        super(gam);
        birb = new Birb(50, 300);
        camera.setToOrtho(false, FlappyProject.WIDTH / 2, FlappyProject.HEIGHT / 2);
        background = new Texture("background.png");

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            birb.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        birb.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(birb.getBirb(), birb.getPosition().x, birb.getPosition().y);
        sb.end();

    }

    @Override
    public void release() {


    }
}
