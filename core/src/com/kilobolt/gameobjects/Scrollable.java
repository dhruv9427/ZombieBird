package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by DhruvSingh on 16/11/2017.
 */
public class Scrollable {

    protected boolean isScrolledLeft;
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;


    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));
        //If the Scrollable object is no longer visible
        if(position.x + width < 0){
            isScrolledLeft = true;
        }
    }
    //Reset: We shouild Override this in the subclass for more specific behaviour
    public void reset(float newX){
        position.x = newX;
        isScrolledLeft = false;

    }

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getTailX() {
        return position.x + width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
