package com.kilobolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kilobolt.gameworld.GameRenderer;
import com.kilobolt.gameworld.GameWorld;
import com.kilobolt.zbHelpers.InputHandler;

/**
 * Created by DhruvSingh on 05/11/2017.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;



    public GameScreen(){
        //Gdx.app.log("GameScreen", "Attached");
        //As a scalable ratio for a good game, game height/game width = screen height/screen width
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight/(screenWidth/gameWidth);

        int midPointY = (int)(gameHeight/2);

        world =  new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void render(float delta) {
        runTime+= delta;
        world.update(delta);
        renderer.render(runTime);
        //passing delta into the method to perform frame-rate independent movement
        //Sets colour to fill the screen, RGB = 10,15,230
        //Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
        //Fills the screen with selected colour
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.app.log("GameScreen FPS", (1/delta) + "");
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        //Leave blank
    }




}
