package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import screen.WorkbenchScreen;

    public class WorkbanchItems extends Actor {

        private float delta;

        private Vector2 lastPosition = new Vector2();
        private boolean touchPress=false;
        private Vector2 NewPosition = new Vector2();

        private Vector2 DefaultPosition = new Vector2();


        //Items properties
        Sprite sprite;
        private float PositionX;
        private float PositionY;
        private float width;
        private float height;
        private float Ingrediant;
        public static Caladron caladron;


        public WorkbanchItems(final Sprite sprite,final float PositionX,final float PositionY, final float width,final float height,final int ingrediant,Caladron Caladron) {
            this.sprite=sprite;
            sprite.setPosition(PositionX, PositionY);
            setBounds(sprite.getX(),sprite.getY(),width,height);
            setTouchable(Touchable.enabled);
            DefaultPosition.set(PositionX, PositionY);
            this.PositionX=PositionX;
            this.PositionY=PositionY;
            this.width=width;
            this.height=height;
            this.Ingrediant=ingrediant;
            this.caladron=Caladron;
            NewPosition.x=PositionX;
            NewPosition.y=PositionY;
            addListener(new InputListener(){
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { {
                    lastPosition.set(x,y);
                    touchPress=true;
                    return true;
                }}

                @Override
                public void touchDragged (InputEvent event, float x, float y, int pointer) {
                    MoveToAction action = new MoveToAction();
                    action.setStartPosition(DefaultPosition.x, DefaultPosition.y);
                    action.setDuration(0f);
                    WorkbanchItems.this.addAction(action);
                    sprite.setPosition(x-50, y-50);
                    NewPosition.set(x-50,y-50);
                }
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) { {
                    touchPress=false;
                    if(x>=WorkbenchScreen.CAMERA_WIDTH/2-400 && x<=800 && y >= 100f && y<=800) {
                        caladron.AddItems(ingrediant);
                    }
                    sprite.setPosition(DefaultPosition.x, DefaultPosition.y);
                }}

            });
        }
        public WorkbanchItems(final Sprite sprite,final float PositionX,final float PositionY, final float width,final float height, final int Button,final  int recept) {
            this.sprite=sprite;
            sprite.setPosition(PositionX, PositionY);
            setBounds(sprite.getX(),sprite.getY(),width,height);
            setTouchable(Touchable.enabled);

            this.PositionX=PositionX;
            this.PositionY=PositionY;
            this.width=width;
            this.height=height;
            addListener(new InputListener(){
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { {
                    return true;
                }}

                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) { {
                    switch(Button) {
                        case 1:{
                            caladron.Reset();
                            break;
                        }
                        case 2:{
                            caladron.EndCooking(recept);
                            System.out.println("endCock "+caladron.endCock);
                            if(caladron.endCock){
                                WorkbenchScreen.buttomPresed=1;
                            }else{
                                Gdx.input.vibrate(120);
                            }
                            break;
                        }
                        case 3:{
                            WorkbenchScreen.buttomPresed=3;
                            break;
                        }
                    }

                }}

            });

        }

        @Override
        public void draw(Batch batch,float patentAlpha) {


            if(NewPosition.x<0 || NewPosition.y<0) {return;}
            batch.draw(sprite, sprite.getX(), sprite.getY(),width,height);
            if(!touchPress) {
                setBounds(sprite.getX(),sprite.getY(),width,height);
            }

        }

        @Override
        public void act(float delta) {
            super.act(delta);
        }

    }

