package com.mygdx.niklas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;


public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Raindrops raindrops;
	private Bucket bucket;
	private Music music;
	private AnimationTestClass atc; //Animationtest

	@Override
	public void create () {
		atc = new AnimationTestClass(); //Animationtest
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		bucket = new Bucket();
		raindrops =  new Raindrops();
		batch = new SpriteBatch();
		music();
	}

	@Override
	public void render () {

		//Animation test
		float tmpStateTime = atc.getStateTime();
		tmpStateTime += Gdx.graphics.getDeltaTime();
		atc.setStateTime(tmpStateTime);
		//Animation test
		ScreenUtils.clear(0.6F, 0.6F, 0.9F, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		atc.addToBatch(batch); //Animation test
		bucket.addToBatch(batch);
		raindrops.addToBatch(batch);
		batch.end();
		playerControls(bucket.getRectangle(),camera);
		if(Gdx.input.isKeyPressed(Input.Keys.M)){
			music.pause(); //TODO: PLACEHOLDER
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.P)){
			music.play();
		}
		if(TimeUtils.nanoTime() - raindrops.getLastDropTime() > 1000000000){
			raindrops.spawnRaindrops();
		}
		raindrops.moveRain(200,raindrops.getRaindrops(),bucket);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		raindrops.getRaindrop().getTexture().dispose();
		bucket.getTexture().dispose();
		batch.dispose();
	}

	private void playerControls(Rectangle player, OrthographicCamera camera){
		//Touch mouse input/movement
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0); //Gdx.input getX/Y returns the current mouse/touch position
			camera.unproject(touchPos); //To transform these coordinates to our camera’s coordinate system, we need to call the camera.unproject()
			player.x = touchPos.x - 32; //64/2
		}
		//Player movement
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)){
			player.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D)){
			player.x += 200 * Gdx.graphics.getDeltaTime();
		}
		//Keeps the player inside the screen boarders
		if(player.x < 0) player.x = 0;
		if(player.x > 800 - 64) player.x = 800 - 64;
	}

	private void music(){
		music = Gdx.audio.newMusic(Gdx.files.internal("jazzloop.mp3"));
		music.setVolume(0.5F);
		music.setLooping(true);
		music.play();
	}
}
