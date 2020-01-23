package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/score.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont scoreFont;
    private Label hScoreLabel;
    private Label currentScore;
    private Label.LabelStyle labelStyle;


    public GameOverState(GameStateManager gam, int score) {
        super(gam);

        hit.play();

        preferences = Gdx.app.getPreferences("flappyPrefs");
        if(preferences.getInteger("highscore") < score){
            preferences.putInteger("highscore", score);
            preferences.flush();
        }

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        stage.addActor(table);

        scoreFont = generator.generateFont(parameter);//TODO: MAKE THESE LABELS LOOK GOOD / MAKE A SKIN ;-;
        labelStyle = new Label.LabelStyle();
        labelStyle.font = scoreFont;

        currentScore = new Label("Score: "+score, labelStyle);
        currentScore.setColor(0,0,0,1);
        currentScore.setFontScale(3);
        table.add(currentScore);
        table.row();
        hScoreLabel = new Label("Highscore: "+preferences.getInteger("highscore"), labelStyle);
        hScoreLabel.setColor(0,0,0,1);
        hScoreLabel.setFontScale(4);
        table.add(hScoreLabel);
        table.row();
        gameOverButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/gameoverup.png")))), new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/gameoverdown.png")))));
        table.add(gameOverButton);
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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        //background.dispose();
        stage.dispose();
        hit.dispose();
        System.out.println("GameOverStage disposed!");

    }

}
