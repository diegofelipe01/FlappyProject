package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

import java.awt.Font;

/*************************************************
 *        Created on 01/15/2020 by Diego         *
 *   Check it out on github.com/diegofelipe01    *
 *************************************************
 *      Criado na data 15/01/2020 por Diego      *
 *Para mais informações: github.com/diegofelipe01*
 *************************************************/

public class FlappyProject extends ApplicationAdapter {
	//gobal variable
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Clone";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private BitmapFont gitLink;
	private Music mainTheme;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		mainTheme.setLooping(true);
		mainTheme.setVolume(0.1f);
		mainTheme.play();

		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}

	@Override
	public void dispose() {
		super.dispose();
		mainTheme.dispose();
	}

}
