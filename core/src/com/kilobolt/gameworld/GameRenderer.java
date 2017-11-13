package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.zbHelpers.AssetLoader;

/**
 * Created by DhruvSingh on 07/11/2017.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int gameHeight;
    private int midPointY;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY){
        myWorld = world;
        //Orthographic Camera
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        //Attach batcher to camera
        batcher.setProjectionMatrix(cam.combined);
        //Shape Renderer for an object in GameWorld
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render(float runTime){

        Bird bird = myWorld.getBird();
        //Fill screen with black to prevent flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        //Draw the background colour
        shapeRenderer.setColor(55/255.0f, 80/255.0f, 100/255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        //Draw Grass
        shapeRenderer.setColor(111/255.0f, 186/255.0f, 45/255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        //Draw Dirt
        shapeRenderer.setColor(147/255.0f, 80/255.0f, 27/255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        //Begin SpriteBatch
        batcher.begin();

        //disable transparency
        //good for performance when drawing images that do not need transparency
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

        //bird need transparency so enable again
        batcher.enableBlending();

        //draw bird's coordinates
        //retrieve animation object from AssetLoader
        //pass runTime variable to get current frame
        batcher.draw((TextureRegion) AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());

        //end SpriteBatch
        batcher.end();

    }
}


//public void render(){

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
// }