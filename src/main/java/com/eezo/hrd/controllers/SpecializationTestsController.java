package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.SpecializationTest;
import com.eezo.hrd.entities.Test;
import com.eezo.hrd.entities.TestUnit;
import com.eezo.hrd.enums.TestType;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@Stateless
@Startup
public class SpecializationTestsController implements Serializable {
    private List<SpecializationTest> specializationTests;

    @PostConstruct
    public void init() {
        this.specializationTests = new ArrayList<>();
        initializeWebTests();
    }

    public List<SpecializationTest> getSpecializationTests() {
        return specializationTests;
    }

    public void setSpecializationTests(List<SpecializationTest> specializationTests) {
        this.specializationTests = specializationTests;
    }

    private void initializeWebTests() {
        SpecializationTest webTest = new SpecializationTest("web", "Web-дизайнер");

        TestUnit[] testUnits = new TestUnit[3];
        TestUnit testUnit1 = new TestUnit("prof", "ПРОФЕСІЙНА КОМПЕТЕНЦІЯ");
        testUnit1.setDesc("Знання стандартів роботи веб-дизайнера та їх ефективне застосування на практиці");
        testUnit1.setTests(initAndGetWeb1Tests());
        testUnit1.setTestLevels(initAndGetWeb1Levels());
        testUnits[0] = testUnit1;

        TestUnit testUnit2 = new TestUnit("com", "КОМУНІКАТИВНІ КОМПЕТЕНЦІЇ");
        testUnit2.setDesc("комунікабельність");
        testUnit2.setTests(initAndGetWeb2Tests());
        testUnit2.setTestLevels(initAndGetWeb2Levels());
        testUnits[1] = testUnit2;

        TestUnit testUnit3 = new TestUnit("pers", "ОСОБИСТІСНО-ДІЛОВІ КОМПЕТЕНЦІЇ");
        testUnit3.setDesc("аналітичне мисленн");
        testUnit3.setTests(initAndGetWeb3Tests());
        testUnit3.setTestLevels(initAndGetWeb3Levels());
        testUnits[2] = testUnit3;

        webTest.setTestUnits(testUnits);
        webTest.setUnitsWeights(new double[]{0.7d, 0.1d, 0.2d});

        this.specializationTests.add(webTest);
    }

    private void initializeSysAdminTest() {
        //
    }

    private List<Test> initAndGetWeb1Tests() {
        List<Test> tests = new ArrayList<>();

        Test test = new Test("Що адресує наступне посилання?<br/><a href=\"../images/1.jpg\" />", TestType.CHECKBOX);
        test.setDescription("Пояснення: Шлях виходу на рівень вгору, а потім переходити в папку images в батьківському каталозі.");
        test.addPossibleAnswer("val1", "Зображення, розташоване в каталозі \"images\", дочірнє по відношенню до поточного.");
        test.addPossibleAnswer("val2", "Зображення, розташоване в каталозі \"images\", батьківське по відношенню до поточного.");
        test.addPossibleAnswer("val3", "Зображення, розташоване в каталозі \"images\", яке розташоване в батьківському по відношенню до поточного каталогу.");
        test.setAnswersWeights(new int[]{-1, -1, 1});
        tests.add(test);

        test = new Test("Для чого використовується тег <TITLE>", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Визначає заголовок таблиці.");
        test.addPossibleAnswer("val2", "Визначає заголовок документа.");
        test.addPossibleAnswer("val3", "Визначає заголовок в тексті.");
        test.addPossibleAnswer("val4", "Визначає новий рядок в тексті.");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1});
        tests.add(test);

        test = new Test("Який атрибут тега <td> вказує кількість рядків, займаним осередком?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "cols.");
        test.addPossibleAnswer("val2", "rows.");
        test.addPossibleAnswer("val3", "vcells.");
        test.addPossibleAnswer("val4", "colspan.");
        test.addPossibleAnswer("val5", "rowspan.");
        test.setAnswersWeights(new int[]{-1, -1, -1, -1, 1});
        tests.add(test);

        test = new Test("Виберіть допустиме значення атрибута method у елементі form.", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "upload.");
        test.addPossibleAnswer("val2", "get.");
        test.addPossibleAnswer("val3", "request.");
        test.addPossibleAnswer("val4", "post.");
        test.addPossibleAnswer("val5", "submit.");
        test.setAnswersWeights(new int[]{-1, 1, -1, 1, -1});
        tests.add(test);

        test = new Test("Вкажіть правильний варіант створення гіпертекстового посилання в html.", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<a link=\"http://www.quizful.net\"> quizful </a>");
        test.addPossibleAnswer("val2", "<a href=\"http://www.quizful.net\"> quizful </a>");
        test.addPossibleAnswer("val3", "<a> http://www.quizful.net </a>");
        test.addPossibleAnswer("val4", "<a target=\"http://www.quizful.net\"> quizful </a>");
        test.addPossibleAnswer("val5", "<a url=\"http://www.quizful.net\"> quizful </a>");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1, -1});
        tests.add(test);

        test = new Test("Виберіть фрагмент HTML-коду, який створює посилання зі плаваючою підказкою.", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<a tip='підказка'> текст посилання </a>");
        test.addPossibleAnswer("val2", "<a baloon='підказка'> текст посилання </a>");
        test.addPossibleAnswer("val3", "<a help='підказка'> текст посилання </a>");
        test.addPossibleAnswer("val4", "<a title='підказка'> текст посилання </a>");
        test.addPossibleAnswer("val5", "<a tooltip='підказка'> текст посилання </a>");
        test.setAnswersWeights(new int[]{-1, -1, -1, 1, -1});
        tests.add(test);

        test = new Test("Який з наведених фрагментів коду вирівнює вміст комірки по правому краю?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<td float = \"right\">");
        test.addPossibleAnswer("val2", "<td right = \"right\">");
        test.addPossibleAnswer("val3", "<td align = \"right\">");
        test.addPossibleAnswer("val4", "<td valign = \"right\"> ");
        test.addPossibleAnswer("val5", "<td textalign = \"right\">");
        test.setAnswersWeights(new int[]{-1, -1, 1, -1, -1});
        tests.add(test);

        test = new Test("Яка властивість встановлює відстань від краю елементів таблиці до її вмісту?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "cellmargin.");
        test.addPossibleAnswer("val2", "cellpadding.");
        test.addPossibleAnswer("val3", "cellspacing.");
        test.addPossibleAnswer("val4", "cellspace.");
        test.addPossibleAnswer("val5", "Такого атрибуту не існує.");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1, -1});
        tests.add(test);

        test = new Test("Який html-тег створює поле введення?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<text>");
        test.addPossibleAnswer("val2", "<textfield>");
        test.addPossibleAnswer("val3", "<select>");
        test.addPossibleAnswer("val4", "<input>");
        test.addPossibleAnswer("val5", "<textbox>");
        test.setAnswersWeights(new int[]{-1, -1, -1, 1, -1});
        tests.add(test);

        test = new Test("Який код створює нумерований список, пронумеровані малими римський цифрами та нумерація котрого починається з цифри 5?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<ol type = \"i\" start = \"5\">\n" +
                "    <Li> Афіни </ li>\n" +
                "    <Li> Київ </ li>\n" +
                "    <Li> Рим </ li>\n" +
                "    <Li> Стамбул </ li>\n" +
                "</ol>\n");
        test.addPossibleAnswer("val2", "<ul type = \"5\" start = \"i\">\n" +
                "    <Li> Афіни </ li>\n" +
                "    <Li> Київ </ li>\n" +
                "    <Li> Рим </ li>\n" +
                "    <Li> Стамбул </ li>\n" +
                "</ul>\n");
        test.addPossibleAnswer("val3", "<dl type = \"a\" start = \"5\">\n" +
                "    <Li> Афіни </ li>\n" +
                "    <Li> Київ </ li>\n" +
                "    <Li> Рим </ li>\n" +
                "    <Li> Стамбул </ li>\n" +
                "</dl>\n");
        test.addPossibleAnswer("val4", "<ul type = \"i\" start = \"5\">\n" +
                "    <Li> Афіни </ li>\n" +
                "    <Li> Київ </ li>\n" +
                "    <Li> Рим </ li>\n" +
                "    <Li> Стамбул </ li>\n" +
                "</ul>\n");
        test.addPossibleAnswer("val5", "<ol type = \"1\" start = \"5\">\n" +
                "    <Li> Афіни </ li>\n" +
                "    <Li> Київ </ li>\n" +
                "    <Li> Рим </ li>\n" +
                "    <Li> Стамбул </ li>\n" +
                "</ol>\n");
        test.setAnswersWeights(new int[]{1, -1, -1, -1, -1});
        tests.add(test);

        test = new Test("Необхідно захистити текстове поле форми від зміни значення користувачем. Які з представлених фрагментів коду дозволяти вирішити поставлені завдання?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<input value = \"$ 999\" checked />");
        test.addPossibleAnswer("val2", "<input value = \"$ 999\" readonly />");
        test.addPossibleAnswer("val3", "<input value = \"$ 999\" disabled />");
        test.addPossibleAnswer("val4", "<input value = \"$ 999\" size = \"0\" />");
        test.addPossibleAnswer("val5", "<input value = \"$ 999\" maxlength = \"0\" />");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1, -1});
        tests.add(test);

        test = new Test("Як створити нумеруваний список?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<ul>");
        test.addPossibleAnswer("val2", "<list type = \"number\">");
        test.addPossibleAnswer("val3", "<ol>");
        test.addPossibleAnswer("val4", "<li>");
        test.addPossibleAnswer("val5", "<list type = \"ordered\">");
        test.addPossibleAnswer("val6", "<list>");
        test.setAnswersWeights(new int[]{-1, -1, 1, -1, -1, -1});
        tests.add(test);

        test = new Test("Який браузер не відображає коректно прозорість PNG-24 (альфа канал)?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Internet Explorer 6.");
        test.addPossibleAnswer("val2", "Firefox 2.0.");
        test.addPossibleAnswer("val3", "Internet Explorer 6, Internet Explorer 7.");
        test.addPossibleAnswer("val4", "Opera 9.5.");
        test.setAnswersWeights(new int[]{1, -1, -1, -1});
        tests.add(test);

        test = new Test("Який тег визначає зображення, яке буде використовуватися в якості фонового малюнку?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<body link = ...>");
        test.addPossibleAnswer("val2", "<body background = ...>");
        test.addPossibleAnswer("val3", "<body text ...>");
        test.addPossibleAnswer("val4", "<body bgcolor = ...>");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1});
        tests.add(test);

        test = new Test("Як Виглядає тег <form> на сторінці?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Тег <form> вдає Із собі поле для введений текст.");
        test.addPossibleAnswer("val2", "Тег <form »не має власного графічного представлення - це контейнер для других елементів.");
        test.addPossibleAnswer("val3", "Тег <form »не має власного графічного представлення - це контейнер для тексту.");
        test.addPossibleAnswer("val4", "Тег <form> вдає Із собі відає список.");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1});
        tests.add(test);

        test = new Test("Що роблять ці спецсимволи & laquo; и & raquo;?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Такого немає в мові html.");
        test.addPossibleAnswer("val2", "Збільшують першу та останню букви на 0,2 em.");
        test.addPossibleAnswer("val3", "Укладають текст у подвійні кутові лапки.");
        test.addPossibleAnswer("val4", "Укладають текст у одинарні кутові лапки.");
        test.setAnswersWeights(new int[]{-1, -1, 1, -1});
        tests.add(test);

        test = new Test("Який тег призначений для заголовків найменшого розміру?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<h1>");
        test.addPossibleAnswer("val2", "<h5>");
        test.addPossibleAnswer("val3", "<h6>");
        test.addPossibleAnswer("val4", "<h7>");
        test.addPossibleAnswer("val5", "<hmin>");
        test.setAnswersWeights(new int[]{-1, -1, 1, -1, -1});
        tests.add(test);

        test = new Test("Який тег використовується для створення параграфу?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<br>");
        test.addPossibleAnswer("val2", "<p>");
        test.addPossibleAnswer("val3", "<f>");
        test.addPossibleAnswer("val4", "<paragraph>");
        test.addPossibleAnswer("val5", "<div>");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1, -1});
        tests.add(test);

        test = new Test("Який атрибут тега <ol> починає нумерацію списку з певного значення?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "type");
        test.addPossibleAnswer("val2", "number");
        test.addPossibleAnswer("val3", "value");
        test.addPossibleAnswer("val4", "start");
        test.addPossibleAnswer("val5", "begin");
        test.setAnswersWeights(new int[]{-1, -1, -1, 1, -1});
        tests.add(test);

        test = new Test("Який тег в HTML5 відображає секцію навігаційних посилань?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "<Navigation>");
        test.addPossibleAnswer("val2", "<Nav>");
        test.addPossibleAnswer("val3", "<Navlinks>");
        test.addPossibleAnswer("val4", "<Headmenu>");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1});
        tests.add(test);

        return tests;
    }

    private Map<Integer, String> initAndGetWeb1Levels() {
        Map<Integer, String> web1UnitLevels = new LinkedHashMap<>();
        web1UnitLevels.put(5, "1 - Рівень некомпетентності: Не має теоретичного уявлення про стандарти роботи веб-дизайнера.");
        web1UnitLevels.put(10, "2 - Рівень розвитку: Має достатній рівень теоретичних уявлень про стандарти роботи веб-дизайнера, але не має практичного власного досвіду (умінь та навичок) їх застосування на практиці.");
        web1UnitLevels.put(15, "3 - Рівень досвіду: Має хороший рівень теоретичних уявлень про стандарти роботи веб-дизайнера і достатній практичний власний досвід (вміння та навички) їх застосування на практиці.");
        web1UnitLevels.put(Integer.MAX_VALUE, "4 - Рівень майстерності: Має відмінний рівень теоретичних уявлень про стандарти роботи веб-дизайнера та практичний власний досвід (вміння та навички) їх застосування на практиці. Активно пропонує вносити раціоналізаторські та інноваційні технології в стандарти своєї роботи.");
        return web1UnitLevels;
    }


    private List<Test> initAndGetWeb2Tests() {
        List<Test> tests = new ArrayList<>();

        Test test = new Test("Яку фразу при бесіді зі співробітником краще використовувати при відсутності результату, щоб підлеглий відчув свою відповідальність?");
        test.addPossibleAnswer("val1", "Результату немає");
        test.addPossibleAnswer("val2", "Ми не досягли результату");
        test.addPossibleAnswer("val3", "Ти не виконав завдання");
        test.setAnswersWeights(new int[]{1, 2, 3});
        tests.add(test);

        test = new Test("Яке питання необхідно поставити співробітнику, щоб сформувати відповідальне ставлення до ухваленого завдання для виконання?");
        test.addPossibleAnswer("val1", "Як ти зрозумів, що потрібно зробити?");
        test.addPossibleAnswer("val2", "Ти обіцяєш це зробити?");
        test.addPossibleAnswer("val3", "Ти знаєш як це робити?");
        test.setAnswersWeights(new int[]{2, 3, 1});
        tests.add(test);

        test = new Test("Керівник не зміг домовитися з керівництвом компанії щодо підвищення заробітної плати для свого співробітника. Що в такій ситуації скаже відповідальний керівник своєму співробітникові?");
        test.addPossibleAnswer("val1", "Я дуже старався, але нам з власником не вдалося домовитися");
        test.addPossibleAnswer("val2", "Я не зміг відстояти твоє прохання");
        test.addPossibleAnswer("val3", "Виявляється підвищення заробітної плати можливо лише для тих, хто відпрацював в компанії більше трьох років");
        test.setAnswersWeights(new int[]{2, 3, 1});
        tests.add(test);

        test = new Test("Що скаже підлеглий, готовий взяти на себе відповідальність за поставлене йому завдання?");
        test.addPossibleAnswer("val1", "Звичайно, я зроблю все, щоб досягти результат");
        test.addPossibleAnswer("val2", "Звичайно, я зроблю все, що тільки зможу");
        test.addPossibleAnswer("val3", "Звичайно, я постараюся все встигнути");
        test.setAnswersWeights(new int[]{3, 2, 1});
        tests.add(test);

        test = new Test("Підлеглий дорікнув вас в тому, що ви не виконали дану йому обіцянку. Що потрібно йому відповісти, щоб зберегти авторитет і продемонструвати лідерську відповідальність?");
        test.addPossibleAnswer("val1", "Компенсувати співробітнику завдані незручності, наприклад, надати позачерговий відгул");
        test.addPossibleAnswer("val2", "Роз'яснити ситуацію, за якої ви змушені були так вчинити");
        test.addPossibleAnswer("val3", "Визнати свою помилку і вибачитись");
        test.setAnswersWeights(new int[]{1, 2, 3});
        tests.add(test);

        test = new Test("Керівник попросив вас порекомендувати співробітника для управління новим проектом. На кому лежить відповідальність, якщо рекомендований вами співробітник \"провалить\" довірений йому проект?");
        test.addPossibleAnswer("val1", "На співробітника, адже він несе відповідальність за результати проекту");
        test.addPossibleAnswer("val2", "На вас, адже ви його рекомендували");
        test.addPossibleAnswer("val3", "На вашого керівника, адже він приймав остаточне рішення щодо кандидатури");
        test.setAnswersWeights(new int[]{1, 3, 2});
        tests.add(test);

        test = new Test("Який з озвучених співробітником аргументів є дійсно об'єктивною причиною невиконання завдання?");
        test.addPossibleAnswer("val1", "Бухгалтерія не надала необхідні дані");
        test.addPossibleAnswer("val2", "Економіка знаходиться в рецесії");
        test.addPossibleAnswer("val3", "Об'єктивних причин невиконання не буває");
        test.setAnswersWeights(new int[]{1, 2, 3});
        tests.add(test);

        test = new Test("Що повинен зробити керівник, щоб сформувати відповідальну позицію у підлеглого, якщо той не впорався з важливим завданням?");
        test.addPossibleAnswer("val1", "Позбавити премії, щоб виключити повторення подібних ситуацій");
        test.addPossibleAnswer("val2", "Обговорити, де співробітник помилився, і дати раду, як йому чинити в подібних ситуаціях в майбутньому");
        test.addPossibleAnswer("val3", "Дати співробітнику можливість самостійно виправити ситуацію");
        test.setAnswersWeights(new int[]{1, 3, 2});
        tests.add(test);

        test = new Test("Ваш заступник просить вас узгодити премії співробітникам, які беруть участь у додатковій проектній роботі в їх робочий час. Чи відповідально він надходить?");
        test.addPossibleAnswer("val1", "Так, тому що в наслідок впровадження проекту виручка компанії збільшиться");
        test.addPossibleAnswer("val2", "Так, додаткова робота повинна оплачуватися в будь-якому випадку");
        test.addPossibleAnswer("val3", "Ні, він повинен знайти способи нематеріальної мотивації");
        test.setAnswersWeights(new int[]{2, 1, 3});
        tests.add(test);

        test = new Test("Чи можна вважати відповідальним вашого співробітника, якщо він відмовляється від виконання завдання, яке знаходиться поза рамками його посадових обов'язків?");
        test.addPossibleAnswer("val1", "Так, незалежно від причини відмови");
        test.addPossibleAnswer("val2", "Так, за умови, що він не вміє робити те, що йому доручили");
        test.addPossibleAnswer("val3", "Ні, він у будь-якому випадку надходить безвідповідально");
        test.setAnswersWeights(new int[]{3, 2, 1});
        tests.add(test);

        return tests;
    }

    private Map<Integer, String> initAndGetWeb2Levels() {
        Map<Integer, String> web1UnitLevels = new LinkedHashMap<>();
        web1UnitLevels.put(10, "1 - Рівень некомпетентності: Ви даєте можливість співробітникам піти від особистої відповідальності за результати їх роботи, оскільки не демонструєте на особистому прикладі Готовність визнавати свої недоробки й помилки. У складних ситуаціях ви віддаєте перевагу виправдовуючись Відсутність результату діями других людей або обставини, на які вам важко впливати. Ви не формуєте зі співробітником чітких домовленостей про те, щоб ви хотіли бачити в якості кінцевого результату. У зв'язку з цим вам важко закликати його до відповідальності при невиконанні поставлених планів, так як він завжди може сказати, що не зрозумів завдання, або ви не дали чітких вказівок, що та коли потрібно було зробити. Ви надмірно покладаєтеся на матеріальні методи стимулювання персоналу, витрачаючи бюджетні кошти там, де можна було б уникнути додаткових витрат");
        web1UnitLevels.put(23, "2 - Ріень розвитку: Для вас важливо, щоб в умовах недовіри \"наверху\" чітко і зрозуміло було визначено перспективні цілі. В іншому випадку відповідальність за складні та ризикові рішення ви перекладає на вищу керівництво. По відношенню до підлеглого у вас достатньо демократичного стилю керівництва: ви готові докладно обговорити з співробітником способи досягнення цілі та можливі труднощі, а також надавати допомогу та підтримку в важких для нього ситуаціях. Однак у цьому випадку, якщо працівник запропонував шлях, з яким ви не погоджуєтесь, то, швидше за все, перекладіть відповідальність за відсутності результату на підлеглого. У проблемних ситуаціях ви багато часу приділяєте увагу, допомагаєте співробітнику справитися з завданням, але, можливо, підлеглий чекає від вас не пояснення, а конкретні і рішучі дії. Вашим співробітникам часом вдається відмовитися від відповідальності за невиконані завдання, особливо в тих випадках, коли вони володіють добре розвиненими комунікативними компетенціями і можуть переконати вас в тому, що у них не було необхідних ресурсів, щоб справитися з завданням. При цьому частина роботи, яку повинні робити підлеглі, ви виконуєте самостійно, залишаючи їх, таким чином, незгруженими.");
        web1UnitLevels.put(Integer.MAX_VALUE, "3 - Рівень майстерності: При постановці завдання ви чітко фіксуєте договори з співробітником, а контролюючи виконання, прямо вказуєте на його особисті недоробки. При цьому готові визнати свої помилки і взяти на себе відповідальність за власні дії та рішення. Навіть якщо якесь запитання вимагає узгодження з вищим керівництвом, і на документі стоїть його підпис, ви не відмовляєтесь від відповідальності за запропоновані вами ідеї. Ви розумієте, що об'єктивні причини невиконання задач не бувають, існують лише непродумані ризики та погано сплановані дії щодо їх мінімізації. Ви володієте конструктивними способами нематеріальної мотивації співробітників та техніки розвитку у них відповідального ставлення до діяльності. Ви з повагою ставляться до підлеглиї, які відкрито заявляють про свою незгоду з вашими рішеннями, і готові обговорити з ними найбільш ефективні шляхи розвитку ситуації в рамках досягнення спільних цілей.");
        return web1UnitLevels;
    }


    private List<Test> initAndGetWeb3Tests() {
        List<Test> tests = new ArrayList<>();

        Map<String, String> possibleAnswers = new LinkedHashMap<>();
        possibleAnswers.put("val1", "ніколи");
        possibleAnswers.put("val2", "рідко");
        possibleAnswers.put("val3", "іноді");
        possibleAnswers.put("val4", "часто");
        possibleAnswers.put("val5", "завжди");

        int[] answersWeights1to5 = new int[]{1, 2, 3, 4, 5};
        int[] answersWeights5to1 = new int[]{5, 4, 3, 2, 1};

        Test test = new Test("Коли у мене виникає проблема, я намагаюся вирішити її сам, перш ніж питати боса що робити.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Коли я делегую роботу, я передаю її тому, у кого є більше вікон в розкладі.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights5to1);
        tests.add(test);

        test = new Test("Я коректую членів команди щоразу, коли бачу, що їх поведінка негативно впливає на рівень обслуговування клієнтів.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Я приймаю рішення після ретельного аналізу, а не покладаюся на інтуїцію.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Я не даю колективу втрачати багато часу на обговорення стратегій і розподіл ролей, під час реалізації завдань все одно може статися багато змін.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights5to1);
        tests.add(test);

        test = new Test("Я чекаю, перш ніж дисциплінувати співробітника, надаючи шанс виправитися самостійно.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights5to1);
        tests.add(test);

        test = new Test("Уміння ідеально робити роботу, яку виконують мої співробітники - це ті навички, які мені потрібні, щоб бути ефективним менеджером.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights5to1);
        tests.add(test);

        test = new Test("Я виділяю час для обговорення з командою того, що йде добре, а що потребує поліпшення.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("В ході зустрічей, я беру на себе роль ведучого / сприяючого, коли це необхідно. Це допомагає команді досягти кращого розуміння питання або прийти до консенсусу.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Я повністю розумію, як працюють бізнес-процеси в моєму відділі, і усуваються вузькі місця.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Коли необхідно зібрати команду, я визначаю, які навички потрібні - і шукаю людей, найкраще відповідають обраним критеріям.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Я роблю все, що можу для уникнення конфліктів в команді.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights5to1);
        tests.add(test);

        test = new Test("Я намагаюся мотивувати людей, адаптуючи свої підходи до них, щоб відповідати потребам кожного співробітника.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Коли команда робить грубу помилку, я повідомляю про неї босові, а потім аналізую важливість отриманого уроку.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("При виникненні конфлікту в новій команді, я сприймаю це як неминучий етап процесу її розвитку.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Я обговорюю з членами команди їх індивідуальні цілі, і поєдную це з цілями всієї організації.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Якщо я формую команду, то вибираю схожі особистості, віку, термін роботи в компанії, і інші характеристики.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights5to1);
        tests.add(test);

        test = new Test("Я думаю, що твердження: «Якщо хочеш зробити добре, зроби сам» вірно.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights5to1);
        tests.add(test);

        test = new Test("Я знаходжу індивідуальний підхід до кожного, щоб забезпечити ефективну, комфортну і продуктивну роботу.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        test = new Test("Я інформую членів команди про те, що відбувається в організації.");
        test.setPossibleAnswers(possibleAnswers);
        test.setAnswersWeights(answersWeights1to5);
        tests.add(test);

        return tests;
    }

    private Map<Integer, String> initAndGetWeb3Levels() {
        Map<Integer, String> web1UnitLevels = new LinkedHashMap<>();
        web1UnitLevels.put(46, "1 - Рівень некомпетентності: Навички аналітичного мислення в терміновому порядку необхідно покращувати");
        web1UnitLevels.put(73, "2 - Рівень розвитку: Ряд компетенцій вже успішно розвинені і забезпечують певну долю комфорту на роботі");
        web1UnitLevels.put(Integer.MAX_VALUE, "3 - Рівень майстерності: Ведеться сильна робота в управлінні командою. Важливо не зупинятися на досягнутому та продовжувати нарощувати свої навички");
        return web1UnitLevels;
    }
}
