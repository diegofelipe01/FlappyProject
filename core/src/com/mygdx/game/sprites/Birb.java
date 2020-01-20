package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

public class Birb {
    private static final int GRAVITY = -15;//only the bird will have gravity since he is the only actually effected by it
    private static final int MOVEMENT = 100;//the velocity of the bird
    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;

    private Rectangle birbHB;

    private Animation birbAnimation;

    public Birb(int x, int y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        texture = new Texture("birbanim.png");
        birbAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        birbHB = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());//there's 3 animations in the same texture
    }


    public void update(float dt){
        birbAnimation.update(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY);//gravity is only add to the Y axis in order to pull the bird down
        }
        velocity.scl(dt);//the velocity needs to be scaled because of the changing time
        position.add(MOVEMENT * dt, velocity.y);//making the bird move!
        if (position.y < 0){
           position.y = 0;
        }

        velocity.scl(1/dt);

        birbHB.setPosition(position.x, position.y);//the hitbox is aways updated to reflect the place of the bird

    }
    public void dispose(){ texture.dispose(); }

    public Vector2 getPosition() {
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
