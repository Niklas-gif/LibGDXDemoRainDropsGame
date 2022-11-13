package com.mygdx.niklas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Bucket implements Batch {
    private  Texture texture;
    private final Rectangle rectangle;
    private final int positionX;
    private final int positionY;
    public Animation animation;
    //private Texture textureAnimation;

    Bucket(Texture texture,Rectangle rectangle,int positionX,int positionY,int width,int height){
        this.texture = texture;
        this.rectangle = rectangle;
        this.positionX = positionX;
        this.positionY = positionY;
        rectangle.x = positionX;
        rectangle.y = positionY;
        rectangle.width = width;
        rectangle.height = height;
    }

    Bucket(){
        texture = new Texture("bucket.png");
        rectangle = new Rectangle();

        positionX = 800/ 2 -64 /2;
        positionY = 20;
        rectangle.x = positionX;
        rectangle.y = positionY;
        rectangle.width = 64;
        rectangle.height = 64;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getAnimationTexture(){
        return animation.getFrame();
    }

    @Override
    public void addToBatch(SpriteBatch sB){
        sB.draw(texture, rectangle.x, rectangle.y);
        //sB.draw(textureAnimation, rectangle.x,rectangle.y);
    }
}
