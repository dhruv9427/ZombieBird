package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by DhruvSingh on 16/11/2017.
 */
public class Pipe extends Scrollable {

    private Random r;

    private Rectangle skullUp, skullDown, barUp, barDown;

    public static final int VERTICAL_GAP = 60;
    public static final int SKULL_WIDTH = 18;
    public static final int SKULL_HEIGHT = 11;
    private float groundY;


    //When the pipe's constructor is invoked, we invoke the Scrollable (super)


    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();

        this.groundY = groundY;
    }

    @Override
    public void update(float delta){
        //Calling the update method in the super class Scrollable
        super.update(delta);
        //Using set method to set the top left corner x, y coordinates along with width, height of rectangle
        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + VERTICAL_GAP + height, width, groundY-(position.y + VERTICAL_GAP + height));
        skullUp.set(position.x - (SKULL_WIDTH - width)/2, position.y + height - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width)/2, barDown.y, SKULL_WIDTH, SKULL_HEIGHT);
    }

    @Override
    public void reset(float newX){
        //Call the reset method in super class Scrollable
        //This way both reset methods are called
        super.reset(newX);
        //Change the height to a random number
        height = r.nextInt(90) + 15;
    }

    public boolean collides(Bird bird){
        //Position x is the top left corner of where the pipe begins
        //So obviously collision is only possible when the bird's position is greater than Position x
        if(position.x < bird.getX() + bird.getWidth()){
            return(Intersector.overlaps(bird.getBoundingCircle(), barUp) || Intersector.overlaps(bird.getBoundingCircle(), barDown)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullDown) || Intersector.overlaps(bird.getBoundingCircle(), skullUp));
        }
        return false;
    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }


}
