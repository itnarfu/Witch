package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

import screen.GameScreen;

public class Card  extends Actor {

    private boolean touchPress=false;
    private boolean StartDefaultRotate=false;
    private float defaultRotating;
    private int maxRotatingCardRate=20;

    private Vector2 lastTouch = new Vector2();
    private float delta;
    private float length;
    private float FirstsTouchX;

    public boolean CanShowSideAnswer=false;
    public int Answer=0;
    private boolean VibrateOne=true;
    //Card properties
    Sprite sprite;
    private String Name;
    private String Order;
    //Left
    private String AnswerOnLeft;
    public   HealthBar FirstBar;
    //effect
    private float EffectOnFirstBar;
    private HealthBar SecondBar;
    private float EffectOnSeondBar;
    //right
    private String AnswerOnRight;

    public Card(final Sprite sprite,final String Name,final String Order,final String AnswerOnLeft,final String AnswerOnRight, final HealthBar FirstBar,final float EffectOnFirstBar,final HealthBar SecondBar,final float EffectOnSeondBar) {
        this.sprite=sprite;
        sprite.setScale(1.5f);
        sprite.setPosition((GameScreen.CAMERA_WIDTH/2)-sprite.getWidth()/2, GameScreen.CAMERA_HEIGHT/2-700);

        sprite.setRotation(0);
        setOrigin(sprite.getWidth()/2,0);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setOrigin(sprite.getWidth()/2,0);

        defaultRotating=sprite.getRotation();

        this.Name=Name;
        this.Order=Order;
        this.AnswerOnLeft=AnswerOnLeft;
        this.AnswerOnRight=AnswerOnRight;
        this.FirstBar=FirstBar;
        this.EffectOnFirstBar=EffectOnFirstBar;
        this.SecondBar=SecondBar;
        this.EffectOnSeondBar=EffectOnSeondBar;

        setTouchable(Touchable.enabled);

        addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { {
                lastTouch.set(x,y);
                FirstsTouchX=x;
                return true;
            }}
            @Override
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                Vector2 newTouch = new Vector2(x, y);
                delta = lastTouch.x-newTouch.x;
                length = Math.abs(FirstsTouchX-newTouch.x);
                if(length>10 && length<150 && delta!=0){
                    touchPress=true;
                    RotateByAction rotatAction = new RotateByAction();
                    rotatAction.setAmount(1f);
                    rotatAction.setDuration(1f);
                    Card.this.addAction(rotatAction);
                }
                length=0;
                lastTouch = newTouch;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) { {
                touchPress=false;
                if(sprite.getRotation()<maxRotatingCardRate-2 && defaultRotating != sprite.getRotation() && sprite.getRotation()>-maxRotatingCardRate+2) {
                    StartDefaultRotate=true;
                }else {
                    if(sprite.getRotation()>=maxRotatingCardRate-2 && sprite.getRotation()<=maxRotatingCardRate ) {
                        GameScreen.SetEffectToStatusBar(FirstBar,EffectOnFirstBar,SecondBar,EffectOnSeondBar);
                        sprite.setRotation(0f);
                        return;
                    }
                    if(sprite.getRotation()<=-maxRotatingCardRate+2 && sprite.getRotation()>=-maxRotatingCardRate ) {
                        GameScreen.SetEffectToStatusBar(SecondBar,EffectOnSeondBar,FirstBar,EffectOnFirstBar);
                        sprite.setRotation(0f);
                        return;
                    }
                }
                length=0;
                lastTouch.set(0,0);
            } }
        });
    }
  public Card(final Sprite sprite, final String Name, final String Order, final String AnswerOnLeft, final String AnswerOnRight, final HealthBar FirstBar, final float EffectOnFirstBar, final HealthBar SecondBar, final float EffectOnSeondBar, final int receptId){
      this.sprite=sprite;
      sprite.setScale(1.5f);
      sprite.setPosition((GameScreen.CAMERA_WIDTH/2)-sprite.getWidth()/2, GameScreen.CAMERA_HEIGHT/2-700);
      sprite.setRotation(0);
      setOrigin(sprite.getWidth()/2,0);
      setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
      sprite.setOrigin(sprite.getWidth()/2,0);

      defaultRotating=sprite.getRotation();

      this.Name=Name;
      this.Order=Order;
      this.AnswerOnLeft=AnswerOnLeft;
      this.AnswerOnRight=AnswerOnRight;
      this.FirstBar=FirstBar;
      this.EffectOnFirstBar=EffectOnFirstBar;
      this.SecondBar=SecondBar;
      this.EffectOnSeondBar=EffectOnSeondBar;

      setTouchable(Touchable.enabled);

      addListener(new InputListener(){
          @Override
          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { {

              lastTouch.set(x,y);
              FirstsTouchX=x;
              return true;
          }
          }

          @Override
          public void touchDragged (InputEvent event, float x, float y, int pointer) {
              Vector2 newTouch = new Vector2(x, y);
              delta = lastTouch.x-newTouch.x;
              length = Math.abs(FirstsTouchX-newTouch.x);

              if(length>10 && length<150 && delta!=0){
                  touchPress=true;
                  RotateByAction rotatAction = new RotateByAction();
                  rotatAction.setAmount(1f);
                  rotatAction.setDuration(1f);
                  Card.this.addAction(rotatAction);

              }
              System.out.println("length "+length);
              System.out.println("delta "+delta);
              length=0;

              lastTouch = newTouch;
          }

          @Override
          public void touchUp (InputEvent event, float x, float y, int pointer, int button) { {
              touchPress=false;
              System.out.println("maxRotatingCardRate "+((-maxRotatingCardRate)));
              if(sprite.getRotation()<maxRotatingCardRate-2 && defaultRotating != sprite.getRotation() && sprite.getRotation()>-maxRotatingCardRate+2) {
                  StartDefaultRotate=true;
              }else {
                  if(sprite.getRotation()>=maxRotatingCardRate-2 && sprite.getRotation()<=maxRotatingCardRate ) {
                      //Left
                      System.out.println("Left Answer Chose ");
                      GameScreen.SetEffectToStatusBar(FirstBar,EffectOnFirstBar,SecondBar,EffectOnSeondBar);
                      GameScreen.StartCoocking(receptId);
                      sprite.setRotation(0f);
                      return;
                  }
                  if(sprite.getRotation()<=-maxRotatingCardRate+2 && sprite.getRotation()>=-maxRotatingCardRate ) {
                      //right
                      System.out.println("Right Answer Chose ");
                      GameScreen.SetEffectToStatusBar(SecondBar,EffectOnSeondBar,FirstBar,EffectOnFirstBar);
                      sprite.setRotation(0f);
                      return;
                  }
              }
              length=0;
              System.out.println("Touch Up ");
              lastTouch.set(0,0);
          }
          }

      });
  }
    public Card(final Sprite sprite, final String Name, final String Order, final String AnswerOnLeft, final String AnswerOnRight){
        this.sprite=sprite;
        sprite.setScale(1.5f);
        sprite.setPosition((GameScreen.CAMERA_WIDTH/2)-sprite.getWidth()/2, GameScreen.CAMERA_HEIGHT/2-700);
        sprite.setRotation(0);
        setOrigin(sprite.getWidth()/2,0);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setOrigin(sprite.getWidth()/2,0);

        defaultRotating=sprite.getRotation();

        this.Name=Name;
        this.Order=Order;
        this.AnswerOnLeft=AnswerOnLeft;
        this.AnswerOnRight=AnswerOnRight;

        setTouchable(Touchable.enabled);

        addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { {

                lastTouch.set(x,y);
                FirstsTouchX=x;
                return true;
            }
            }

            @Override
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                Vector2 newTouch = new Vector2(x, y);
                delta = lastTouch.x-newTouch.x;
                length = Math.abs(FirstsTouchX-newTouch.x);

                if(length>10 && length<150 && delta!=0){
                    touchPress=true;
                    RotateByAction rotatAction = new RotateByAction();
                    rotatAction.setAmount(1f);
                    rotatAction.setDuration(1f);
                    Card.this.addAction(rotatAction);

                }
                length=0;

                lastTouch = newTouch;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) { {
                touchPress=false;

                if(sprite.getRotation()<maxRotatingCardRate-1 && defaultRotating != sprite.getRotation() && sprite.getRotation()>-maxRotatingCardRate+1) {
                    StartDefaultRotate=true;
                }else {
                    if(sprite.getRotation()<=maxRotatingCardRate && sprite.getRotation()>=maxRotatingCardRate-5) {
                        //Left
                        GameScreen.buttomPresed=4;
                        sprite.setRotation(0f);
                        return;
                    }
                    if(sprite.getRotation()>=-maxRotatingCardRate && sprite.getRotation()<=-maxRotatingCardRate+5 ) {
                        //right
                        GameScreen.buttomPresed=5;
                        sprite.setRotation(0f);
                        return;
                    }
                }
                length=0;
                System.out.println("Touch Up ");
                lastTouch.set(0,0);
            }
            }

        });
    }

    @Override
    protected void rotationChanged() {
        if(touchPress) {
            System.out.println("Pepes "+sprite.getRotation()+(delta)*GameScreen.deltaTime);
            float Rotating= MathUtils.clamp(sprite.getRotation()+(delta/2)*GameScreen.deltaTime,-maxRotatingCardRate, maxRotatingCardRate);
            sprite.setRotation(Rotating);
            setRotation(0);
            super.rotationChanged();
            setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        }
    }

    public String getName() {
        return Name;
    }
    public String getOrder() {
        return Order;
    }
    public String getRightAnswer() {
        return AnswerOnRight;
    }
    public String getLeftAnswer() {
        return AnswerOnLeft;
    }

    @Override
    public void draw(Batch batch,float patentAlpha){
        if(StartDefaultRotate) {
            if(!(sprite.getRotation()>defaultRotating-0.2f && sprite.getRotation()<defaultRotating+0.2f  )) {
                if(sprite.getRotation()>359) {
                    sprite.setRotation(defaultRotating);
                }else {
                    sprite.setRotation(MathUtils.lerpAngleDeg(sprite.getRotation(), defaultRotating, 0.2f));
                }
            }else {
                StartDefaultRotate=false;
            }
        }
        if(sprite.getRotation()<=maxRotatingCardRate && sprite.getRotation()>=maxRotatingCardRate-5  ) {
            //Left
            CanShowSideAnswer=true;
            Answer=1;
        }else {
            if(sprite.getRotation()>=-maxRotatingCardRate && sprite.getRotation()<=-maxRotatingCardRate+5 ) {
                //right
                CanShowSideAnswer=true;
                Answer=2;
            }else {
                CanShowSideAnswer=false;
                Answer=0;
                VibrateOne=true;
            }
        }
        if(sprite.getRotation()==maxRotatingCardRate && VibrateOne || sprite.getRotation()==-maxRotatingCardRate && VibrateOne   ){
            Gdx.input.vibrate(60);
            VibrateOne=false;
        }
       sprite.draw(batch);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
