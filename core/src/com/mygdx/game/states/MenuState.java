package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.FlappyProject;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;


    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("DroidSans.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont gitLink;

    private static final int DEVICEWIDTH = Gdx.graphics.getWidth();
    private static final int DEVICEHEIGHT = Gdx.graphics.getHeight();


    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("background.png");
        playButton = new Texture("playbutton.png");
        parameter.size = 20;
        gitLink = generator.generateFont(parameter);
        gitLink.setColor(0,0,0,1);


        camera.setToOrtho(false, DEVICEWIDTH, DEVICEHEIGHT);


    }
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, DEVICEWIDTH, DEVICEHEIGHT);//drawing the background
        sb.draw(playButton, ( DEVICEWIDTH - playButton.getWidth() ) / 2, ( DEVICEHEIGHT - playButton.getHeight() ) / 2 );//centering the playbutton image
        gitLink.draw(sb, "github.com/diegofelipe01", 0, 15);
        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("MenuState Disposed!");
    }
}
