package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by DhruvSingh on 07/11/2017.
 */
public class GameWorld {

    private Rectangle rect = new Rectangle(0, 0, 17, 12);

    public void update(float delta){
        Gdx.app.log("Game Update", "Update");
        //Now allow the rectangle to scroll to the right and then reset after 137
        rect.x++;
        if (rect.x>137){
            rect.x=0;
        }
    }

    public Rectangle getRect() {
        return rect;
    }
}
