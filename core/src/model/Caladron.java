package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Caladron extends Actor {

    private Sprite sprite;
    private float PositionX;
    private float PositionY;
    private float width;
    private float height;

    int playerRecept[] = new int[8];
    //Recept
    int recept_1[] = new int[] {1, 2,3};
    int recept_2[] = new int[] {4,8,6,5,5,5,5,5};
    int recept_3[] = new int[] {6,7,3,2,2,2};

    public boolean endCock=false;

    public Caladron(final float PositionX,final float PositionY, final float width,final float height) {
        sprite= new Sprite( new Texture (Gdx.files.internal("greenwarevo.png")));
        sprite.setPosition(PositionX, PositionY);
        setBounds(sprite.getX(),sprite.getY(),width,height);
        this.PositionX=PositionX;
        this.PositionY=PositionY;
        this.width=width;
        this.height=height;
    }

    public void AddItems(int NumberItems) {

        switch(NumberItems) {
            case 1:{
                sprite= new Sprite( new Texture (Gdx.files.internal("gwarevo.png")));
                SetNewSprite(sprite);
                break;
            }
            case 2:{
                sprite= new Sprite( new Texture (Gdx.files.internal("redwarevo.png")));
                SetNewSprite(sprite);
                break;
            }
            case 3:{
                sprite= new Sprite( new Texture (Gdx.files.internal("yegrwarevo.png")));
                SetNewSprite(sprite);
                break;
            }
            case 4:{
                sprite= new Sprite( new Texture (Gdx.files.internal("yellowwarevo.png")));
                SetNewSprite(sprite);
                break;
            }case 5:{
                sprite= new Sprite( new Texture (Gdx.files.internal("gwarevo.png")));
                SetNewSprite(sprite);
                break;
            }
            case 6:{
                sprite= new Sprite( new Texture (Gdx.files.internal("redwarevo.png")));
                SetNewSprite(sprite);
                break;
            }
            case 7:{
                sprite= new Sprite( new Texture (Gdx.files.internal("yegrwarevo.png")));
                SetNewSprite(sprite);
                break;
            }
            case 8:{
                sprite= new Sprite( new Texture (Gdx.files.internal("yellowwarevo.png")));
                SetNewSprite(sprite);
                break;
            }
        }
        for(int i=0;i<playerRecept.length;i++) {
            if(playerRecept[i]==0) {
                playerRecept[i]=NumberItems;
                System.out.println("recept "+ playerRecept[i]);
                return;
            }
        }
    }

    public void EndCooking(int NumberRecept) {
        switch(NumberRecept) {
            case 1:{
                for(int i=0;i<recept_1.length;i++) {
                    if(playerRecept[i]!=recept_1[i]) {
                        endCock=false;
                        return;
                    }
                }
                endCock=true;
                break;
            }
            case 2:{
                for(int i=0;i<recept_2.length;i++) {
                    System.out.println("recept "+ playerRecept[i]);
                    System.out.println("recept2 "+ recept_2[i]);

                    if(playerRecept[i]!=recept_2[i]) {
                        endCock=false;
                        return;
                    }
                }
                endCock=true;
                break;
            }

            case 3:{
                for(int i=0;i<recept_3.length;i++) {
                    if(playerRecept[i]!=recept_3[i]) {
                        endCock=false;
                        return;
                    }
                }
                endCock=true;
                break;
            }
        }
    }

    public void Reset() {
        for(int i=0;i<playerRecept.length;i++) {
            playerRecept[i]=0;
        }
        sprite= new Sprite( new Texture (Gdx.files.internal("greenwarevo.png")));
        SetNewSprite(sprite);
    }
    public boolean getEndCock() {
        return endCock;
    }

    public void SetNewSprite(Sprite sprite) {
        sprite.setPosition(PositionX, PositionY);
        setBounds(sprite.getX(),sprite.getY(),width,height);
    }
    @Override
    public void draw(Batch batch,float patentAlpha) {

        batch.draw(sprite, sprite.getX(), sprite.getY(),width,height);
    }
}
