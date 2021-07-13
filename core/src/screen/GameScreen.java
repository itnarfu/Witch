package screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Witch;

import java.util.Random;

import model.Buttons;
import model.Card;
import model.HealthBar;
import model.Interface;
import model.SaveProgress;

public class GameScreen implements Screen {

    private static SaveProgress save;
    private static Random random;
    private SpriteBatch batch;

    private Sprite statusBarTextureHum;
    private Sprite statusBarTextureChurch;
    private Sprite statusBarTextureMoney;
        private Sprite statusBarTextureWithc;
        private static Sprite Cardsprite;
        private static Sprite InterfaceSprite;
    private static Sprite ButtomSprite;

        public static  Card card;
        //Camera
        private Camera camera;
        private Viewport viewport;
        public static float CAMERA_WIDTH = Gdx.app.getGraphics().getWidth();
        public static float CAMERA_HEIGHT = Gdx.app.getGraphics().getHeight();
        public static float deltaTime;


        private static Stage stage;

        public static HealthBar HumanBar;
        public static HealthBar churchBar;
        public static HealthBar moneyBar;
        public static HealthBar witchBar;

        private Group  statusHumanBarGroup;
        private Group  statusCharchBarGroup;
         private Group  statusMoneyBarGroup;
        private Group  statusWitchBarGroup;

        //Interface
        Interface ModelInterface;


        //Text Work
        private FreeTypeFontGenerator fontGenerator;
        private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
        private BitmapFont font;
        final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        public Label labelOrder;
    private FreeTypeFontGenerator fontGeneratorS;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameterS;
    private BitmapFont fontS;


    private Buttons buttom;
    public static int buttomPresed=0;
   public static Witch game;
   //Card Array
   static Array <Card> Acard=new Array<Card>();
    static Array <Card> DefaultAcard=new Array<Card>();
   static int IndexCard=0;
   private static Card CurrentCard;
   private static Card Dethcard;

   static int countDay=0;
    static int day=1;
    static int  recept=0;

    private static Music music=Gdx.audio.newMusic(Gdx.files.internal("Minstrelosity.mp3"));;

    @Override
        public void show() {
            camera = new PerspectiveCamera();
            viewport = new FitViewport(CAMERA_WIDTH, CAMERA_HEIGHT, camera);
            random = new Random();
            music.setVolume(0.8f);
            music.setLooping(true);
            if(!music.isPlaying()){
                music.play();
            }else{
                music.setPosition(save.ReturnmusicTime());
            }
            batch =  new SpriteBatch();
            stage = new Stage();
            //create interface bitch
            InterfaceSprite= new Sprite( new Texture (Gdx.files.internal("back.png")));
            ModelInterface= new Interface(InterfaceSprite,0,0,1920*3,1080*3);
            stage.addActor(ModelInterface);

            InterfaceSprite= new Sprite( new Texture (Gdx.files.internal("banner.png")));
            ModelInterface= new Interface(InterfaceSprite,0,0,200*6,200);
            stage.addActor(ModelInterface);

            InterfaceSprite= new Sprite( new Texture (Gdx.files.internal("banner.png")));
            ModelInterface= new Interface(InterfaceSprite,0,CAMERA_HEIGHT-200,200*6,200);
            stage.addActor(ModelInterface);



            ButtomSprite= new Sprite( new Texture (Gdx.files.internal("homebtn.png")));
            buttom=new Buttons(ButtomSprite,CAMERA_WIDTH-200,0,200,200,3);
            stage.addActor(buttom);
                //Status bar church

                        statusBarTextureChurch = new Sprite(new Texture(Gdx.files.internal("churchban.png")));
                        statusBarTextureChurch.setPosition(CAMERA_WIDTH / 12, InterfaceSprite.getY() + 20);
                        churchBar = save.ReturChurchBar();
                        //churchBar.setValue(churchBar.getValue());
                        statusCharchBarGroup = new Group();
                        statusCharchBarGroup.addActor(churchBar);
                        statusCharchBarGroup.setOrigin(churchBar.getWidth() / 2, churchBar.getHeight() / 2);
                        statusCharchBarGroup.setRotation(90);
                        statusCharchBarGroup.setPosition(statusBarTextureChurch.getX(), statusBarTextureChurch.getY());
                        stage.addActor(statusCharchBarGroup);

                statusBarTextureHum = new Sprite(new Texture(Gdx.files.internal("humanban.png")));
                statusBarTextureHum.setPosition(statusCharchBarGroup.getX() + 250, InterfaceSprite.getY() + 20);

                //Status Bar hum
                HumanBar = save.ReturHumanBar();
                statusHumanBarGroup = new Group();
                statusHumanBarGroup.addActor(HumanBar);
                statusHumanBarGroup.setOrigin(HumanBar.getWidth() / 2, HumanBar.getHeight() / 2);
                statusHumanBarGroup.setRotation(90);
                statusHumanBarGroup.setPosition(statusBarTextureHum.getX(), statusBarTextureHum.getY());
                stage.addActor(statusHumanBarGroup);

                statusBarTextureMoney = new Sprite(new Texture(Gdx.files.internal("moneybar.png")));
                statusBarTextureMoney.setPosition(statusBarTextureHum.getX() + 250, InterfaceSprite.getY() + 20);

                //Status Bar money
                 moneyBar = save.ReturMoneyBar();

                statusMoneyBarGroup = new Group();
                statusMoneyBarGroup.addActor(moneyBar);
                statusMoneyBarGroup.setOrigin(moneyBar.getWidth() / 2, moneyBar.getHeight() / 2);
                statusMoneyBarGroup.setRotation(90);
                statusMoneyBarGroup.setPosition(statusBarTextureMoney.getX(), statusBarTextureMoney.getY());
                stage.addActor(statusMoneyBarGroup);

                //Witch

                statusBarTextureWithc = new Sprite(new Texture(Gdx.files.internal("witchesban.png")));
                statusBarTextureWithc.setPosition(statusBarTextureMoney.getX() + 250, InterfaceSprite.getY() + 20);

                //Status Bar money
                witchBar = save.ReturWitchBar();
                statusWitchBarGroup = new Group();
                statusWitchBarGroup.addActor(witchBar);
                statusWitchBarGroup.setOrigin(witchBar.getWidth() / 2, witchBar.getHeight() / 2);
                statusWitchBarGroup.setRotation(90);
                statusWitchBarGroup.setPosition(statusBarTextureWithc.getX(), statusBarTextureWithc.getY());
                stage.addActor(statusWitchBarGroup);

                 InterfaceSprite= new Sprite( new Texture (Gdx.files.internal("ramka.png")));
                 ModelInterface= new Interface(InterfaceSprite,90,CAMERA_HEIGHT/2-700,InterfaceSprite.getWidth()*1.5f,InterfaceSprite.getHeight()*1.5f);
                stage.addActor(ModelInterface);


        //Card create

            Gdx.input.setInputProcessor(stage);
        DefaultAcard=save.ReturnCards();

            Acard=save.ReturnCards();
            CurrentCard=Acard.get(IndexCard);
            stage.addActor(CurrentCard);


            Cardsprite = new Sprite(new Texture(Gdx.files.internal("death.png")));
            Dethcard = new Card(Cardsprite, "Казнь", "Выпогибли", "Выйти!", "Заново!!!");



        ////Text
            fontGenerator=new FreeTypeFontGenerator(Gdx.files.internal("Bold.ttf"));
            fontParameter= new FreeTypeFontGenerator.FreeTypeFontParameter ();
            fontParameter.size=90;
            fontParameter.characters=FONT_CHARS;
            fontParameter.borderWidth=1;
            fontParameter.borderColor=Color.BLACK;
            fontParameter.color=Color.BROWN;
            font=fontGenerator.generateFont(fontParameter);

            fontGeneratorS=new FreeTypeFontGenerator(Gdx.files.internal("Bold.ttf"));
            fontParameterS= new FreeTypeFontGenerator.FreeTypeFontParameter ();
            fontParameterS.size=50;
            fontParameterS.characters=FONT_CHARS;
            fontParameterS.borderWidth=1;
            fontParameterS.borderColor=Color.BLACK;
            fontParameterS.color=Color.BROWN;
            fontS=fontGenerator.generateFont(fontParameterS);

            LabelStyle labelStyle = new LabelStyle(fontS,fontS.getColor());
            labelOrder = new Label("", labelStyle);
            labelOrder.setWrap(true);

            //table

            Table table = new Table();
            table.setFillParent(true);
            table.add(labelOrder).width(800);
            table.setPosition(20, CurrentCard.getY()+380);
            stage.addActor(table);

        }
        @Override
        public void render(float delta) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();
                deltaTime = delta;
                batch.begin();
                font.draw(batch, CurrentCard.getName(), CurrentCard.getX() + CurrentCard.getWidth() / 2 - 200, CurrentCard.getY() - 50);
                font.draw(batch, "День: "+day, 10,  120);
                batch.draw(statusBarTextureHum, statusBarTextureHum.getX(), statusBarTextureHum.getY(), 150, 150);
                batch.draw(statusBarTextureChurch, statusBarTextureChurch.getX(), statusBarTextureChurch.getY(), 150, 150);
                batch.draw(statusBarTextureMoney, statusBarTextureMoney.getX(), statusBarTextureMoney.getY(), 150, 150);
                batch.draw(statusBarTextureWithc, statusBarTextureWithc.getX(), statusBarTextureWithc.getY(), 150, 150);
                labelOrder.setText(CurrentCard.getOrder());
                if (CurrentCard.CanShowSideAnswer) {
                    if (CurrentCard.Answer == 2) {
                        font.draw(batch, CurrentCard.getLeftAnswer(), CurrentCard.getX() - 200, CurrentCard.getY() + CurrentCard.getHeight() + 300);
                    } else {
                        font.draw(batch, CurrentCard.getRightAnswer(), CurrentCard.getWidth() + CurrentCard.getX() - 200, CurrentCard.getY() + CurrentCard.getHeight() + 300);
                    }
                }
                batch.end();
                if (buttomPresed == 3) {
                    save = new SaveProgress();
                    save.SaveProgress(Acard,IndexCard,HumanBar,churchBar,moneyBar,witchBar,music.getPosition());
                    music.pause();
                    game.setScreen(game.mainMenu);
                    buttomPresed=0;
                }
            if (buttomPresed == 4) {
                CurrentCard.remove();
                day=0;
                countDay=0;
                buttomPresed=0;
                Acard.addAll(DefaultAcard);
                CurrentCard=Acard.get(0);
                IndexCard=0;
                stage.addActor(CurrentCard);
                moneyBar.setValue(0.5f);
                witchBar.setValue(0.5f);
                churchBar.setValue(0.5f);
                HumanBar.setValue(0.5f);
                music.play();

            }
            if (buttomPresed == 5) {
                dispose();
                Gdx.app.exit();
                buttomPresed=0;
            }
            }


    public GameScreen(Witch game){this.game = game;}

    public static void SetEffectToStatusBar(HealthBar subtractBarChange,float changeValueSubtract, HealthBar AddBarChange,float changeValueAdd) {

            subtractBarChange.setValue(subtractBarChange.getValue() - changeValueSubtract);
            AddBarChange.setValue(AddBarChange.getValue()+changeValueAdd);
            CurrentCard.setRotation(0f);
            CurrentCard.remove();
            if(AddBarChange.getValue()==1f ||subtractBarChange.getValue()==0f) {
                System.out.println("Die");
                CurrentCard=Dethcard;
                stage.addActor(CurrentCard);
                music.pause();
                return;
            }
            if(countDay==3){
                day+=1;
                countDay=0;
            }else{
                countDay+=1;
            }
            setNewCard();
        }
        public static void StartCoocking(int receptId){
            recept=receptId;
            save = new SaveProgress();
            save.SaveProgress(Acard,IndexCard,HumanBar,churchBar,moneyBar,witchBar,music.getPosition());
            game.setScreen(game.workbenchScreen);

        }

        private static void setNewCard() {
            Acard.removeIndex(IndexCard);
            int i = random.nextInt(Acard.size);
            CurrentCard=Acard.get(i);
            stage.addActor(CurrentCard);
            IndexCard=i;
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

        public void setSize (int w, int h) {

        }
        @Override
        public void dispose() {
            batch.dispose();
            stage.dispose();
            font.dispose();
        }

    }