package model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Interface extends Actor {
    Sprite sprite;
    float Width;
    float Height;
    public Interface(Sprite sprite, float x, float y, final float width, final float height) {
        this.sprite=sprite;
        this.Height=height;
        this.Width=width;
        sprite.setPosition(x, y);
    }
    @Override
    public void draw(Batch batch, float patentAlpha) {
        batch.draw(sprite, sprite.getX(), sprite.getY(),Width,Height);
    }

}
