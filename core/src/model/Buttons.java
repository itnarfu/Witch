package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

import screen.GameScreen;
import screen.MainMenu;

public class Buttons  extends Actor{

    private float posX;
    private float posY;
    private float Widht;
    private float Height;
    Sprite sprite;
    public boolean buttonPressed =  false;
    private float speedScale= 1f;

    public Buttons(Sprite sprite, float x, float y, float width, float height,final int buttonOption) {
        this.sprite=sprite;
        sprite.setPosition(x, y);
        setBounds(sprite.getX(),sprite.getY(),width,height);
        setTouchable(Touchable.enabled);

        this.Widht=width;
        this.Height=height;

        addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { {
                ScaleToAction  action = new ScaleToAction();
                action.setScale(50f);
                action.setDuration(5f);
                System.out.println("Pressed ");
                Gdx.input.vibrate(60);
                return true;
            }}

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) { {
                switch(buttonOption) {
                    case 1:{
                        MainMenu.buttomPresed=1;
                        break;}
                    case 2:{
                        MainMenu.buttomPresed=2;
                        break;}
                    case 3:{
                        GameScreen.buttomPresed=3;
                        break;}
                    case 4:{
                        MainMenu.buttomPresed=4;
                        break;}
                    case 5:{
                        MainMenu.buttomPresed=5;
                        break;}
                }

            }}

        });

    }
    @Override
    public void draw(Batch batch,float patentAlpha) {
        batch.draw(sprite, sprite.getX(), sprite.getY(),Widht,Height);
    }



    @Override
    public void act(float delta) {
        super.act(delta);

    }




}
