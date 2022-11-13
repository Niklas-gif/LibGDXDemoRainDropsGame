package com.mygdx.niklas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTestClass implements Batch {
    private static final int FRAME_COLS = 3, FRAME_ROWS = 3;
    Animation<TextureRegion> testAnimation;
    Texture spriteSheet;
    SpriteBatch spriteBatch;
    float stateTime;

    public AnimationTestClass(){
        spriteSheet = new Texture("bucketanimation-Sheet.png");
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet,
                spriteSheet.getWidth()/ FRAME_COLS, spriteSheet.getWidth()/FRAME_ROWS);
        TextureRegion[] testFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int index = 0;
        for(int i = 0; i<FRAME_ROWS;i++){
            for(int j = 0;j < FRAME_COLS;j++){
                testFrames[index++] = tmp[i][j];
            }
        }
        testAnimation = new Animation<>(0.075F,testFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public TextureRegion getCurrentFrame(){
       return testAnimation.getKeyFrame(stateTime,true);
    }

    @Override
    public void addToBatch(SpriteBatch sB){
        sB.draw(getCurrentFrame(), 50, 50);

    }
}
