package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.Grass;
import com.kilobolt.gameobjects.Pipe;
import com.kilobolt.gameobjects.ScrollHandler;
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

    //Game Objects
    private Bird bird;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    //Game Assets
    private TextureRegion bg, grass;
    private Animation birdAnimation;
    private TextureRegion birdMid, birdUp, birdDown;
    private TextureRegion skullUp, skullDown, bar;


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

        //Call helper methods to initialise instance variables
        initGameObjects();
        initAssets();

    }

    private void initGameObjects(){
        bird = myWorld.getBird();
        scroller = myWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    private void initAssets(){
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        birdUp = AssetLoader.birdUp;
        birdDown = AssetLoader.birdDown;
        skullDown = AssetLoader.skullDown;
        skullUp = AssetLoader.skullUp;
        bar = AssetLoader.bar;
    }

    private void drawGrass(){
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls(){
        batcher.draw(skullUp, pipe1.getX()-1, pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe1.getX()-1, pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe2.getX()-1, pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe2.getX()-1, pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe3.getX()-1, pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe3.getX()-1, pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes(){
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(), pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45, pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(), pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45, pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(), pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45, pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));

    }

    public void render(float runTime){

        //Bird bird = myWorld.getBird();
        //Fill screen with black to prevent flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);
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
        batcher.disableBlending();
        batcher.draw(bg, 0, midPointY + 23, 136, 43);

        drawGrass();
        drawPipes();

        //bird needs transparency so enable again
        batcher.enableBlending();
        drawSkulls(); //skulls requires transparency
        //draw bird's coordinates
        //retrieve animation object from AssetLoader
        //pass runTime variable to get current frame
        //batcher.draw((TextureRegion) AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());


        if (bird.shouldntFlap()){
            batcher.draw(birdMid, bird.getX(), bird.getY(), bird.getWidth()/2.0f, bird.getHeight()/2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        }
        else{batcher.draw((TextureRegion) birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth()/2.0f, bird.getHeight()/2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        }

        //end SpriteBatch
        batcher.end();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(bird.getBoundingCircle().x, bird.getBoundingCircle().y, bird.getBoundingCircle().radius);
        shapeRenderer.end();
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