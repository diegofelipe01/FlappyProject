package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.FlappyProject;

public class MenuState extends State {

    private Stage stage;
    private ImageButton newGameButton;
    private Table table;

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSans.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont gitLink;

    public MenuState(final GameStateManager gsm){
        super(gsm);

        camera.setToOrtho(false, FlappyProject.WIDTH, FlappyProject.HEIGHT);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        stage.addActor(table);

        newGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/playup.png")))), new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/playdown.png")))));
        table.add(newGameButton);
        table.setFillParent(true);

        parameter.size = 20;
        gitLink = generator.generateFont(parameter);
        gitLink.setColor(0,0,0,1);

        newGameButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new PlayState(gsm));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

    }

    @Override
    public void handleInput() {
        //if(Gdx.input.justTouched()){
          //  gsm.set(new PlayState(gsm));

       // }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        stage.act();
        stage.draw();
        sb.begin();
        gitLink.draw(sb, "github.com/diegofelipe01", 2, 18);
        sb.end();


    }

    @Override
    public void dispose() {
        stage.dispose();

        System.out.println("MenuState Disposed!");
    }
}
