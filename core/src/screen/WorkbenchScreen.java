package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Witch;
import model.Caladron;
import model.Interface;
import model.WorkbanchItems;


public class WorkbenchScreen implements Screen {

    private SpriteBatch batch;
    private Stage stage;

    //Camera
    private Camera camera;
    private Viewport viewport;
    public static float CAMERA_WIDTH = Gdx.app.getGraphics().getWidth();
    public static float CAMERA_HEIGHT = Gdx.app.getGraphics().getHeight();
    public static float deltaTime;


    //Items create
    private static Sprite ItemsSprite;
    private WorkbanchItems items;

    //Caladrno create
    public Caladron caladron;
    public  int listRecept=1; // выбирается какой рецпт нужен


    Witch game;
    public static int buttomPresed=0;

    private static Sprite InterfaceSprite;
    Interface ModelInterface;
    private boolean Pressed=false;

    private  Music Backmusic=Gdx.audio.newMusic(Gdx.files.internal("00215.mp3"));;

    @Override
    public void show() {

        camera = new PerspectiveCamera();
        viewport = new FitViewport(CAMERA_WIDTH, CAMERA_HEIGHT, camera);

        batch =  new SpriteBatch();
        stage = new Stage();

        Backmusic.setVolume(0.1f);
        Backmusic.setLooping(true);
        Backmusic.play();


        InterfaceSprite= new Sprite( new Texture (Gdx.files.internal("back2.png")));
        Interface ModelInterface = new Interface(InterfaceSprite, 0, 0, 1920 * 3, 1080 * 3);
        stage.addActor(ModelInterface);

        caladron=new Caladron(CAMERA_WIDTH/2-400,100f,800f,800f);
        stage.addActor(caladron);

       ItemsSprite= new Sprite( new Texture (Gdx.files.internal("eye.png")));
        items=new WorkbanchItems(ItemsSprite,400,CAMERA_HEIGHT-700-300-300,200,200,1,caladron);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("rat.png")));
        items=new WorkbanchItems(ItemsSprite,100,CAMERA_HEIGHT-700-300-300,200,200,2,caladron);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("bee.png")));
        items=new WorkbanchItems(ItemsSprite,700,CAMERA_HEIGHT-700-300,200,200,3,caladron);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("blood.png")));
        items=new WorkbanchItems(ItemsSprite,400,CAMERA_HEIGHT-700-300,200,200,4,caladron);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("grass.png")));
        items=new WorkbanchItems(ItemsSprite,100,CAMERA_HEIGHT-700-300,200,200,5,caladron);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("flower.png")));
        items=new WorkbanchItems(ItemsSprite,700,CAMERA_HEIGHT-700,200,200,6,caladron);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("finger.png")));
        items=new WorkbanchItems(ItemsSprite,400,CAMERA_HEIGHT-700,200,200,7,caladron);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("mushroom.png")));
        items=new WorkbanchItems(ItemsSprite,100,CAMERA_HEIGHT-700,200,200,8,caladron);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("refresh.png")));
        items=new WorkbanchItems(ItemsSprite,CAMERA_WIDTH-250,CAMERA_HEIGHT-210,200,200,1,0);
        stage.addActor(items);

        listRecept=GameScreen.recept;

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("colba.png")));
        items=new WorkbanchItems(ItemsSprite,20,CAMERA_HEIGHT-210,200,200,2,listRecept);
        stage.addActor(items);

        ItemsSprite= new Sprite( new Texture (Gdx.files.internal("book.png")));
        items=new WorkbanchItems(ItemsSprite,CAMERA_WIDTH/2-100,CAMERA_HEIGHT-210,200,200,3,0);
        stage.addActor(items);



        Gdx.input.setInputProcessor(stage);
    }


    public WorkbenchScreen(Witch game){this.game = game;}
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(buttomPresed==1){
            game.setScreen(game.gameScreen);
            Backmusic.pause();
            buttomPresed=0;
        }
        if(buttomPresed==3 && !Pressed){
            switch (listRecept){
                case 1:{
                    InterfaceSprite= new Sprite( new Texture (Gdx.files.internal("recept1.png")));
                     ModelInterface = new Interface(InterfaceSprite, CAMERA_WIDTH/2-500, 200, 1000 , 1080*2 );
                    stage.addActor(ModelInterface);
                    break;
                }
                case 2:{
                    InterfaceSprite= new Sprite( new Texture (Gdx.files.internal("recept2.png")));
                     ModelInterface = new Interface(InterfaceSprite, CAMERA_WIDTH/2-500, 200, 1000 , 1080*2 );
                    stage.addActor(ModelInterface);
                    break;
                }
                case 3:{
                    InterfaceSprite= new Sprite( new Texture (Gdx.files.internal("recept3.png")));
                     ModelInterface = new Interface(InterfaceSprite, CAMERA_WIDTH/2-500, 200, 1000 , 1080*2 );
                    stage.addActor(ModelInterface);
                    break;
                }
            }
            Pressed=true;
            buttomPresed=0;
        }
        if(buttomPresed==3 && Pressed){
            ModelInterface.remove();
            Pressed=false;
            buttomPresed=0;
        }
        deltaTime= delta;
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();

    }

}