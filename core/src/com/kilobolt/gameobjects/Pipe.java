package com.kilobolt.gameobjects;

import java.util.Random;

/**
 * Created by DhruvSingh on 16/11/2017.
 */
public class Pipe extends Scrollable {

    private Random r;

    //When the pipe's constructor is invoked, we invoke the Scrollable (super)


    public Pipe(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
    }

    @Override
    public void reset(float newX){
        //Call the reset method in super class Scrollable
        //This way both reset methods are called
        super.reset(newX);
        //Change the height to a random number
        height = r.nextInt(90) + 15;
    }
}
