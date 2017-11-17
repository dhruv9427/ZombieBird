package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.Grass;
import com.kilobolt.gameobjects.Pipe;
import com.kilobolt.gameobjects.ScrollHandler;

/**
 * Created by DhruvSingh on 07/11/2017.
 */
public class GameWorld {


    private Bird bird;
    private ScrollHandler scroller;

    //private Rectangle rect = new Rectangle(0, 0, 17, 12);

    public GameWorld(int midPointY){
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta){
        bird.update(delta);
        scroller.update(delta);
        //Gdx.app.log("Game Update", "Update");
        //Now allow the rectangle to scroll to the right and then reset after 137
        //rect.x++;
        //if (rect.x>137){
            //rect.x=0;
       // }
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
