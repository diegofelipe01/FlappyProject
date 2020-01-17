package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyProject;
import com.mygdx.game.sprites.Birb;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {
    private static final int SPACE = 120;
    private static final int MAXTUBEQNT = 4;

    private Array<Tube> tubes;

    private Birb birb;

    private Texture background;
    private Texture ground;

    private Vector2 groundPos1, groundPos2;

    private Sound hit;

    public PlayState(GameStateManager gam) {
        super(gam);
        hit = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"));
        birb = new Birb(50, 300);
        camera.setToOrtho(false, FlappyProject.WIDTH / 2, FlappyProject.HEIGHT / 2);
        background = new Texture("background.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, 0);//so that it's rendered from the left
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), 0);//so that it's render after the other ground

        tubes = new Array<Tube>();
        for(int i = 0; i < MAXTUBEQNT; i++){
            tubes.add(new Tube(i *(SPACE + Tube.TUBEWIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            birb.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        birb.update(dt);
        for(Tube tube : tubes){
            if(camera.position.x - (camera.viewportWidth / 2) > tube.getpTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getpTopTube().x + ((Tube.TUBEWIDTH + SPACE) * MAXTUBEQNT));
            }

            if(tube.collision(birb.getBirbHB()) || birb.getPosition(). y <= ground.getHeight()){
                hit.play();
                gsm.set(new PlayState(gsm));
                break;
            }
        }
        camera.position.x = birb.getPosition().x + 80;//this makes the camera follow the bird and leaves him a little bit back on the screen
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(birb.getBirb(), birb.getPosition().x, birb.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getpTopTube().x, tube.getpTopTube().y);
            sb.draw(tube.getBotTube(), tube.getpBotTube().x, tube.getpBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }

    private void updateGround(){
        if(camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }

        if(camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }

    }

    @Override
    public void dispose() {
        background.dispose();
        birb.dispose();
        ground.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
        System.out.println("PlayState Disposed!");

    }
}
