package com.javarush.quest.ivanilov.utils.constants;

public class Strings {
    public static final String UNEXPECTED_VALUE = "Unexpected value: ";
    public static final String QUEST = "Quest";
    public static final String QUEST_NOT_FOUND = "Квест не найден.";
    public static final String HERO = "Hero";
    public static final String NAME = "name";
    public static final String STRENGTH = "strength";
    public static final String HEALTH = "health";
    public static final String HERO_NOT_FOUND = "Герой не найден.";
    public static final String EVENT_1 = "Event1";
    public static final String EVENT = "Event";
    public static final String BREAK = "Break";
    public static final String QUESTION = "Q";
    public static final String ANSWER = "Ans";
    public static final String WIN = "Win";
    public static final String LOSE = "Lose";
    public static final String FIGHT = "F";
    public static final String VILLAIN = "Villain";
    public static final String GOOD = "Good";
    public static final String BAD = "Bad";
    public static final String LOST = "Игра окончена - вы проиграли :(";
    public static final String WON = "Вы победили!";
    public static final String ABOUT_HIT = "%s наносит удар %s с силой %d. У %s остаётся %d здоровья.";
    public static final String ABOUT_BLOCK = "%s блокирует удар %s";
    public static final String NOT_PLAYING = "Не играет.";
    public static final String QUEST_SAMPLE = """
            Quest:Пример квеста;\\nHero:strength=6^health=50;\\n\\nEvent1:Начало.;\\nQ:Душным летним вечером пятницы ты в замешательстве. Бабушка уже давно просит помощи с огородом, но эгоистичный гедонист внутри просит тусы. Что собираешься делать?;\\nAns:Поехать на дачу.^Event2;\\nAns:Остаться дома и закатить вечеринку.^Event3;\\nBreak:Break;\\n\\nEvent2:Тяжёлый выбор.;\\nQ:На бабушкиной кухне пахнет свежими пирожками и навозом. Ты поел и полон сил. Что дальше?;\\nAns:Пойти полоть картошку.^Event4;\\nAns:Зря я не закатил вечеринку. Пора в деревенский клуб!^Event5;\\nBreak:Break;\\n\\nEvent3:Враг внутри.;\\nF: Ты позвал друзей на вечеринку, но никто не пришёл. Даже твой кот лёг спать пораньше. Скука толкает тебя в холодильник, где припрятана прошлогодняя марка. Забыв о том, что она предназначалась для троих, ешь её целиком. Берёт жёстко - ты видишь Домового, он идёт к тебе. Пора помахать кулаками.;\\nVillain:name=Домовой^strength=10^health=25;\\nGood:Event8;\\nBad:Event9;\\nBreak:Break;\\n\\nEvent4:Шулер.;\\nQ:Ты вернулся во двор после нескольких часов работы в поле. Руки-крюки, спину тянет. Зычный смех деда привлекает твоё внимание - на завалинке он с друзьями играет в преферанс. Ты вот уже 20 лет мечтаешь научиться в преферанс.;\\nAns:Мечты нужно реализовывать - иду к дедам.^Event6;\\nAns:Азартные игры опасны, лучше завалюсь на печь.^Event7;\\nBreak:Break;\\n\\nEvent5:Танцор Рик.;\\nF:Ты заходишь в лучших шмотках в деревенский клуб. Местные бросают на тебя недобрый взгляд, но ты - мужчина, поэтому смело идёшь на бар. «Шот водки мне!» - говоришь ты и дрожащей от страха рукой протягиваешь пятисотку бармену. Тот кладёт её в карман, давать сдачу он и не думает. Играет Меладзе, ты готов идти на танцпол. После танца к тебе подходит бойкий парень и харкает прямо на твои белые кеды от Lacoste. Хорошо в этой ситуации только одно - парень в стельку пьян. Стиснув зубы, ты делаешь взмах кулаком…;\\nVillain:name=Бойкий парень^strength=3^health=80;\\nGood:Event10;\\nBad:Event11;\\nBreak:Break;\\n\\nEvent6:Фиаско.;\\nLose:Ты остался без мобилы, одежды и квартиры.;\\nBreak:Break;\\n\\nEvent7:Weekend survivor.;\\nWin:Выходные искушали тебя на приключения, но ты предпочёл им родню. Это похвально, но скучно. Главное - ты в полном порядке.;\\nBreak:Break;\\n\\nEvent8:Игры разума.;\\nWin:Ты одолел Домового и пришёл в себя.;\\nBreak:Break;\\n\\nEvent9:Что происходит.;\\nLose:Домовой победил - ты сошёл с ума.;\\nBreak:Break;\\n\\nEvent10:Кровавый вкус победы.;\\nWin:Бойкий парень некисло тебе накостылял, но видели бы вы его самого.;\\nBreak:Break;\\n\\nEvent11:Это было жёстко.;\\nLose:Ты очнулся в районной больнице. Тело ломит, голова трещит, зубов не хватает, дух сломлен. Похоже, что нужно было всё-таки копать, а не бухать.;\\nBreak:Break;\\n
            """;
}
