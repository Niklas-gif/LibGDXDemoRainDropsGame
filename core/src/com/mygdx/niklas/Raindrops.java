package com.mygdx.niklas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Raindrops implements Batch{
    private final Raindrop raindrop;
    private final Array<Rectangle> raindrops;
    private long lastDropTime;

    public Raindrops(){
        raindrop = new Raindrop();
        raindrops = new Array<>();
    }

    public void spawnRaindrops(){
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64); //Places the rain drops random on the screens x axis
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    public Raindrop getRaindrop() {
        return raindrop;
    }

    public long getLastDropTime() {
        return lastDropTime;
    }

    public Array<Rectangle> getRaindrops() {
        return raindrops ;
    }

    public void moveRain(int speed,Array<Rectangle> raindrops,Bucket bucket){
        for(Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext();){
            Rectangle raindrop = iter.next();
            raindrop.y -= speed * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            if(raindrop.overlaps(bucket.getRectangle())){
                iter.remove();
                //TODO Sound dropSound.play()
            }
        }
    }
    @Override
    public void addToBatch(SpriteBatch batch){
        for(Rectangle raindrops: raindrops){
            batch.draw(raindrop.getTexture(), raindrops.x,raindrops.y);
        }
    }
}
