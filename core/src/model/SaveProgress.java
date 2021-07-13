package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class SaveProgress {

    static Array<Card> Acard=new Array<Card>();
    public static int CardId;

    public static HealthBar HumanBar;
    public static HealthBar churchBar;
    public static HealthBar moneyBar;
    public static HealthBar witchBar;
    private Sprite Cardsprite;
    private Card card;
    private static float musicTime;

    public void SaveProgress(){
        this.CardId=0;
        churchBar= new HealthBar(150,150);
        churchBar.setValue(0.5f);
        HumanBar= new HealthBar(150,150);
        HumanBar.setValue(0.5f);
        moneyBar= new HealthBar(150,150);
        moneyBar.setValue(0.5f);
        witchBar= new HealthBar(150,150);
        witchBar.setValue(0.5f);
        this.musicTime=0.0f;
        
        //test
        Cardsprite = new Sprite(new Texture(Gdx.files.internal("man1.png")));
        card = new Card(Cardsprite, "Васян", "Смахни влево или вправо для ответа!", "Что?", "Кто?", HumanBar, 0.0f, churchBar, 0.0f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("man2.png")));
        card = new Card(Cardsprite, "Петро", "Ведьма! Нужен спирт. Одолжи, в долгу не останусь.", "Дать спирт!", "Не сегодня!", moneyBar, 0.2f, HumanBar, 0.2f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("woman.png")));
        card = new Card(Cardsprite, "Машка", "Танька увела моего мужа! Как ей отомстить?", "Превратить в жабу", "Иди отсюда!", witchBar, 0.2f, HumanBar, 0.2f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("pop.png")));
        card = new Card(Cardsprite, "Священник", "Я слышал ты ведьма!? Смотри мне тут, я за тобой наблюдаю", "Подайте денег..", "Взятка", moneyBar, 0.2f, churchBar, 0.2f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("man3.png")));
        card = new Card(Cardsprite, "Трохим", "Отец, я согрешил.. Ой, ты не священник!", "Я ведьма, дурак", "Разве?", witchBar, 0.2f, churchBar, 0.2f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("Witch.png")));
        card = new Card(Cardsprite, "Главная ведьма", "Завтра нужно совершить жертвоприношение. Выбери ребенка!", "Конечно!", "Жестоко!", witchBar, 0.2f, churchBar, 0.2f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("woman.png")));
        card = new Card(Cardsprite, "Татьяна", "Хочу привлечь Машкиного мужа, дай зелье приворотное.", "Обойдешься!", "Держи", churchBar, 0.2f, moneyBar, 0.2f,1);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("man3.png")));
        card = new Card(Cardsprite, "Ивасик", "А можно мне спирта?", "Да", "НЕТ!", moneyBar, 0.2f, HumanBar, 0.2f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("man1.png")));
        card = new Card(Cardsprite, "Петро", "Коза молоко не дает. Что делать?", "Принести в жертву", "Съесть", witchBar, 0.2f, HumanBar, 0.2f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("woman.png")));
        card = new Card(Cardsprite, "Ольга", "Что-то мне нехорошо уже который день, помоги!", "Хорошо!", "Я занята!", HumanBar, 0.2f, moneyBar, 0.2f);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("woman.png")));
        card = new Card(Cardsprite, "Мария", "Заболели дети. Помогите нам добрая ведьмочка!!!.", "Нет!", "Держи", churchBar, 0.2f, moneyBar, 0.2f,3);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("pop.png")));
        card = new Card(Cardsprite, "Петр", "Мне нужно зелья для устранения еретиков.Ты же понимаешь о чем я?? И также понимаешь чем обернеться отказ моей просьбе.", "Нет!", "Держи", churchBar, 0.2f, moneyBar, 0.2f,2);
        Acard.add(card);

        Cardsprite = new Sprite(new Texture(Gdx.files.internal("man2.png")));
        card = new Card(Cardsprite, "Афанасий", "Старушка моя совсем опечалена моим хозяйством, не могла ли бы ты что-нибудь наколдовать?", "Да", "Нет", moneyBar, 0.2f, HumanBar, 0.2f);
        Acard.add(card);


    }



    public void SaveProgress(Array<Card> Acard,int CardId,HealthBar HumanBar,HealthBar churchBar,HealthBar moneyBar,HealthBar witchBar){
        this.Acard=Acard;
        this.CardId=CardId;
        this.churchBar=churchBar;
        this.HumanBar=HumanBar;
        this.moneyBar=moneyBar;
        this.witchBar=witchBar;
    }
    public void SaveProgress(Array<Card> Acard,int CardId,HealthBar HumanBar,HealthBar churchBar,HealthBar moneyBar,HealthBar witchBar,float musicTime){
        this.Acard=Acard;
        this.CardId=CardId;
        this.churchBar=churchBar;
        this.HumanBar=HumanBar;
        this.moneyBar=moneyBar;
        this.witchBar=witchBar;
        this.musicTime=musicTime;
    }
    public static HealthBar ReturHumanBar(){
        return HumanBar;
    }
    public static float ReturnmusicTime(){
        return musicTime;
    }
    public static HealthBar ReturChurchBar(){
        return churchBar;
    }
    public static HealthBar ReturMoneyBar(){
        return moneyBar;
    }
    public static HealthBar ReturWitchBar(){
        return witchBar;
    }
    public  static Array<Card> ReturnCards(){
        return Acard;
    }
    public  static int ReturnCardId(){
        return CardId;
    }
}
