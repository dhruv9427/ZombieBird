package com.kilobolt.gameworld;

import com.badlogic.gdx.Game;
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
    private int score = 0;

    public int midPointY;

    private GameState currentState;

    public enum GameState{
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY){
        currentState = GameState.READY;
        this.midPointY = midPointY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);

    }

    public void update(float delta){

        switch(currentState){
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    private void updateReady(float delta){

    }

    public void updateRunning(float delta){

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
            currentState = GameState.GAMEOVER;

            if(score> AssetLoader.getHighScore()){
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }

        }

    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment){
        score+= increment;
    }


    public boolean isReady(){
        return currentState == GameState.READY;
    }

    public void start(){
        currentState = GameState.RUNNING;
    }

    public void restart(){
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver(){
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore(){
        return currentState == GameState.HIGHSCORE;
    }


}
