package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private Texture topTube, botTube;
    private Vector2 pTopTube, pBotTube;//holders for the tubes and gap position
    private Rectangle top, bot;//hit box of the tubes and the gap
    private Random rng;//random to generate the tubes position

    private static final int MAX = 130; //the top tube position will be 130 tops
    private static final int GAP = 100;//the opening between the tubes will be whatever number is in this constant
    private static final int LOWGAP = 120;//the lowest position for the bottom tube
    public static final int TUBEWIDTH = 52;//The tube is 52 pixels wide


    public Tube(float x) {//x is the coordinate where the tube will be generated
        topTube = new Texture("toptube.png");
        botTube = new Texture("bottomtube.png");
        rng = new Random();

        pTopTube = new Vector2(x, rng.nextInt(MAX) + GAP + LOWGAP);
        pBotTube = new Vector2(x, pTopTube.y - GAP - botTube.getHeight());

        top = new Rectangle(pTopTube.x, pTopTube.y,  topTube.getWidth(), topTube.getHeight());
        bot = new Rectangle(pBotTube.x, pBotTube.y, botTube.getWidth(), botTube.getHeight());//setting the hitbox for the tubes
    }
    public void reposition(float x){
        pTopTube.set(x, rng.nextInt(MAX) + GAP + LOWGAP);
        pBotTube.set(x, pTopTube.y - GAP - botTube.getHeight());
        top.setPosition(pTopTube.x, pTopTube.y);
        bot.setPosition(pBotTube.x, pBotTube.y);
    }
    public boolean collision(Rectangle birb){
        return  birb.overlaps(top) || birb.overlaps(bot);//function that checks if the bird is touching the pipes
    }

    public void dispose(){
        botTube.dispose();
        topTube.dispose();
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }


    public Vector2 getpTopTube() {
        return pTopTube;
    }

    public Vector2 getpBotTube() {
        return pBotTube;
    }
}
