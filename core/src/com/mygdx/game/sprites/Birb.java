package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

public class Birb {
    private static final int GRAVITY = -15;//only the bird will have gravity since he is the only actually effected by it
    private static final int MOVEMENT = 100;//the velocity of the bird
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;

    private Rectangle birbHB;

    private Animation birbAnimation;

    public Birb(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        texture = new Texture("birbanim.png");
        birbAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        birbHB = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());//there's 3 animations in the same texture
    }


    public void update(float dt){
        birbAnimation.update(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);//gravity is only add to the Y axis in order to pull the bird down
        }
        velocity.scl(dt);//the velocity needs to be scaled because of the changing time
        position.add(MOVEMENT * dt, velocity.y, 0);//making the bird move!
        if (position.y < 0){
           position.y = 0;
        }

        velocity.scl(1/dt);

        birbHB.setPosition(position.x, position.y);//the hitbox is aways updated to reflect the place of the bird

    }
    public void dispose(){
        texture.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBirb() {
        return birbAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 320;
    }

    public Rectangle getBirbHB() {
        return birbHB;
    }

    public static int getMOVEMENT() { return MOVEMENT; }

}
