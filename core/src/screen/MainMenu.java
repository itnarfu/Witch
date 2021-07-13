package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Witch;

import model.Buttons;
import model.Interface;



public class MainMenu implements Screen  {

    Witch game;
    //camera
    private Camera camera;
    private Viewport viewport;
    public static float CAMERA_WIDTH = Gdx.app.getGraphics().getWidth();
    public static float CAMERA_HEIGHT = Gdx.app.getGraphics().getHeight();
    public static float deltaTime;

    private Stage stage;

    private static SpriteBatch batch;
    private Sprite ButtomSprite;

    //create buttoms
    private Buttons buttom;

    public static int buttomPresed=0;


    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private static BitmapFont font;
    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

   private GameScreen gameScreen;
    Interface ModelInterface;


    @Override
    public void show() {
        //camera
        camera = new PerspectiveCamera();
        viewport = new FitViewport(CAMERA_WIDTH, CAMERA_HEIGHT, camera);

        batch =  new SpriteBatch();
        stage = new Stage();

        Sprite InterfaceSprite = new Sprite(new Texture(Gdx.files.internal("back.png")));
        Interface ModelInterface = new Interface(InterfaceSprite, 0, 0, 1920 * 3, 1080 * 3);
        stage.addActor(ModelInterface);


        //Text
        fontGenerator=new FreeTypeFontGenerator(Gdx.files.internal("Bold.ttf"));
        fontParameter= new FreeTypeFontGenerator.FreeTypeFontParameter ();
        fontParameter.size=350;
        fontParameter.characters=FONT_CHARS;
        fontParameter.borderWidth=15;
        fontParameter.borderColor=Color.BLACK;
        fontParameter.color=Color.BROWN;
        font=fontGenerator.generateFont(fontParameter);
        //crete
        ButtomSprite= new Sprite( new Texture (Gdx.files.internal("playbtn.png")));
        buttom=new Buttons(ButtomSprite,CAMERA_WIDTH/2-300,CAMERA_HEIGHT/2-100,600,200,2);
        stage.addActor(buttom);

        ButtomSprite= new Sprite( new Texture (Gdx.files.internal("closebtn.png")));
        buttom=new Buttons(ButtomSprite,CAMERA_WIDTH-210,CAMERA_HEIGHT-195,200,200,1);
        stage.addActor(buttom);

        Gdx.input.setInputProcessor(stage);



    }
    public MainMenu(Witch game){
        this.game = game;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        deltaTime= delta;
        if(buttomPresed==1) {
            dispose();
            buttomPresed=0;
            Gdx.app.exit();
        }
        if(buttomPresed==2) {
            dispose();
            game.setScreen(game.gameScreen);
            buttomPresed=0;

        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();
        font.draw(batch,"Witch",CAMERA_WIDTH/2-420,CAMERA_HEIGHT-400);
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
        font.dispose();
    }


}
