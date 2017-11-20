package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.Grass;
import com.kilobolt.gameobjects.Pipe;
import com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbHelpers.AssetLoader;

/**
 * Created by DhruvSingh on 07/11/2017.
 */
public class GameWorld {


    private Bird bird;
    private ScrollHandler scroller;
    //private Boolean isAlive = true;
    private Rectangle ground;
    //private Rectangle rect = new Rectangle(0, 0, 17, 12);

    public GameWorld(int midPointY){
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta){
        //Adding a limit on delta so if the game takes too long to update the collision detection doesn't break
        if (delta > .15f){
            delta = .15f;
        }
        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()){
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if(Intersector.overlaps(bird.getBoundingCircle(), ground)){
            scroller.stop();
            bird.die();
            bird.decelerate();
        }
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    //    public Rectangle getRect() {
//        return rect;
//    }
}
