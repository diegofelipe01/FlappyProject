package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.FlappyProject;

public class GameOverState extends State{
    private Stage stage;
    private ImageButton gameOverButton;
    private Table table;

    //private Texture background;



    public GameOverState(GameStateManager gam) {
        super(gam);

       // table = new Table();

        stage = new Stage();
        table = new Table();
        Gdx.input.setInputProcessor(stage);
        gameOverButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/gameoverup.png")))), new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/gameoverdown.png")))));
        table.add(gameOverButton);
        table.setFillParent(true);
        stage.addActor(table);

        gameOverButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new PlayState(gsm));
                return false;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
            }
        });


    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        stage.act();
        stage.draw();


    }

    @Override
    public void dispose() {
        //background.dispose();
        stage.dispose();

    }

}
