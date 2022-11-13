package com.mygdx.niklas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Raindrop {
    private final Texture texture;
    private final Rectangle rectangle;

    public Raindrop(){
        texture = new Texture("drop.png");
        rectangle = new Rectangle();
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }


}