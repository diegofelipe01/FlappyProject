package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameOverState extends State{
    private Stage stage;
    private ImageButton gameOverButton;
    private Table table;
    private Sound hit = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"));
    private Preferences preferences;
    private Skin skin;

    private Label hScoreLabel;
    private Label currentScore;


    public GameOverState(final GameStateManager gsm, int score) {
        super(gsm);

        hit.play();

        skin = new Skin(Gdx.files.internal("skins/commodore64/skin/uiskin.json"));

        preferences = Gdx.app.getPreferences("flappyPrefs");
        if(preferences.getInteger("highscore") < score){
            preferences.putInteger("highscore", score);
            preferences.flush();
        }

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        stage.addActor(table);


        currentScore = new Label("Score: "+score, skin);
        currentScore.setColor(0,0,0,1);
        currentScore.setFontScale(1.8f);
        table.add(currentScore);
        table.row();
        hScoreLabel = new Label("Highscore: "+preferences.getInteger("highscore"), skin);
        hScoreLabel.setColor(0,0,0,1);
        hScoreLabel.setFontScale(2.3f);
        table.add(hScoreLabel);
        table.row();
        gameOverButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/gameoverup.png")))), new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/gameoverdown.png")))));
        table.add(gameOverButton).pad(30);
        table.setFillParent(true);


        gameOverButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new PlayState(gsm));
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

        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        hit.dispose();
        System.out.println("GameOverStage disposed!");

    }

}
