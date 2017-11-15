package com.kilobolt.zbHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by DhruvSingh on 13/11/2017.
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation birdAnimation;
    public static TextureRegion bird, birdDown, birdUp;

    public static TextureRegion skullUp, skullDown, bar;


    public static void load(){

        texture = new Texture(Gdx.files.internal("C:\\Users\\DhruvSingh\\Desktop\\ZombieBird\\core\\src\\com\\kilobolt\\zbHelpers\\texture.png"));
        //texture = new Texture(Gdx.files.internal("texture.png"));
        //C:\Users\DhruvSingh\Desktop\ZombieBird\core\src\com\kilobolt\zbHelpers\texture.png
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = {birdDown, bird, birdUp};//Creates an array of Texture regions
        birdAnimation = new Animation(0.06f, birds);//Each frame is 0.06 seconds long using the above array
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);//Sets play mode as ping pong so we see a bounce

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        //create skullDown by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

    }

    public static void dispose(){
        //We must dispose of the texture when finished
        texture.dispose();
    }
}
