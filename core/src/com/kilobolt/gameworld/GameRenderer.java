package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DhruvSingh on 07/11/2017.
 */
public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world){
        myWorld = world;
        //Orthographic Camera
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);
        //Shape Renderer for an object in GameWorld
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render(){
//        Gdx.app.log("Game renderer", "render");
//
//        //1. Draw a black background to prevent flickering
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        //2. Draw the filled rectangle
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        //Choose RGB colour at full opacity
//        shapeRenderer.setColor(87/255.0f, 109/255.0f, 102/255.0f, 1);
//        //draws the rectangle from myWorld using ShapeType.Filled
//        shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y, myWorld.getRect().height, myWorld.getRect().width);
//        //tell it to finish rendering (very important!)
//        shapeRenderer.end();
//
//        //3. Draw an outline of the following shape
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        //Choose RGB colour at full opacity
//        shapeRenderer.setColor(255/255.0f, 109/255.0f, 120/255.0f, 1);
//        //draws the rectangle from myWorld using ShapeType.Line
//        shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y, myWorld.getRect().height, myWorld.getRect().width);
//        //tell it to finish rendering (very important!)
//        shapeRenderer.end();
    }
}
