package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.awt.TextArea;

public class Birb {
    private static final int GRAVITY = -15;//only the bird will have gravity since he is the only actually effected by it
    private Vector3 position;
    private Vector3 velocity;

    private Texture birb;

    public Birb(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        birb = new Texture("birb.png");
    }

    public void update(float dt){
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);//gravity is only add to the Y axis in order to pull the bird down
        }
        velocity.scl(dt);//the velocity needs to be scaled because of the changing time
        position.add(0, velocity.y, 0);//making the bird move!
        if (position.y < 0){
           position.y = 0;
        }

        velocity.scl(1/dt);


    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBirb() {
        return birb;
    }
    public void jump(){
        velocity.y = 250;
    }
}
