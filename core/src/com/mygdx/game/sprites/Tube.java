package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rng;//random to generate the tubes position

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rng = new Random();
    }
}
