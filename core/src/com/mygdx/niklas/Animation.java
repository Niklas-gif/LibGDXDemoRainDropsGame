package com.mygdx.niklas;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(TextureRegion textureRegion, int frameCount,float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = textureRegion.getRegionWidth() / frameCount;
        for(int i = 0; i<frameCount; i++){ // Takes our TextureAtlas a divides it into single frames
            frames.add(new TextureRegion(textureRegion, i * frameWidth,0,frameWidth, textureRegion.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float deltaTime){
        currentFrameTime += deltaTime;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return  frames.get(frame);
    }
}
