package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.kilobolt.zbHelpers.AssetLoader;

/**
 * Created by DhruvSingh on 12/11/2017.
 */
public class Bird {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    private Circle boundingCircle;

    private Boolean isAlive;

    public Bird(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
        isAlive = true;
    }

    public void update(float delta){
        velocity.add(acceleration.cpy().scl(delta));


        if(velocity.y > 200){
            velocity.y = 200;
        }

        //CEILING CHECK
        //This is a test again
        //Another test
        //3rd test
        //4th test
        //5th attempt with key
        //6th attempt with the PAT from Github
        if(position.y<-13){
            position.y=-13;
            velocity.y = 0;
        }

        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        //Rotation counterclockwise
        if(velocity.y<0)
        {
            rotation-= 600* delta;
            if(rotation<-20){
                rotation=-20;
            }
        }
        //Rotation clockwise
        if(isFalling() || !isAlive){
            rotation+=480*delta;
            if(rotation>90){
                rotation=90;
            }

        }


    }

    public void onClick(){
        if(isAlive){
            AssetLoader.flap.play();
            velocity.y = -140;
        }
    }

    public void onRestart(int y){
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }

    public void die(){

        isAlive = false;
        velocity.y = 0;
    }

    public void decelerate(){
        //bird should stop accelerating downwards once it is dead
        acceleration.y = 0;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public boolean isFalling(){
        return velocity.y>110;
    }

    public boolean shouldntFlap(){
        return velocity.y>70 || !isAlive;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public Boolean isAlive() {
        return isAlive;
    }
}
