package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.SpecializationTest;
import com.eezo.hrd.entities.Test;
import com.eezo.hrd.entities.TestUnit;
import com.eezo.hrd.enums.TestType;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@ApplicationScoped
@Startup
public class SpecializationTestsController implements Serializable {
    private List<SpecializationTest> specializationTests;

    @PostConstruct
    public void init() {
        this.specializationTests = new ArrayList<>();
        initializeWebTests();
        initializeSysAdminTest();
        initializeOperatorPCTest();
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
        SpecializationTest webTest = new SpecializationTest("sys", "Сетевий адміністратор");

        TestUnit[] testUnits = new TestUnit[3];
        TestUnit testUnit1 = new TestUnit("prof", "ПРОФЕСІЙНА КОМПЕТЕНЦІЯ");
        testUnit1.setDesc("Знання стандартів роботи сетевого адміністратора та їх ефективне застосування на практиці");
        testUnit1.setTests(initAndGetSys1Tests());
        testUnit1.setTestLevels(initAndGetWeb1Levels());
        testUnits[0] = testUnit1;

        testUnits[1] = this.specializationTests.get(0).getTestUnits()[1];
        testUnits[2] = this.specializationTests.get(0).getTestUnits()[2];
        webTest.setTestUnits(testUnits);
        webTest.setUnitsWeights(new double[]{0.7d, 0.1d, 0.2d});

        this.specializationTests.add(webTest);
    }

    private void initializeOperatorPCTest() {
        SpecializationTest webTest = new SpecializationTest("pc", "Оператор ПК");

        TestUnit[] testUnits = new TestUnit[3];
        TestUnit testUnit1 = new TestUnit("prof", "ПРОФЕСІЙНА КОМПЕТЕНЦІЯ");
        testUnit1.setDesc("Знання стандартів роботи оператора ПК та їх ефективне застосування на практиці");
        testUnit1.setTests(initAndGetPC1Tests());
        testUnit1.setTestLevels(initAndGetWeb1Levels());
        testUnits[0] = testUnit1;

        testUnits[1] = this.specializationTests.get(0).getTestUnits()[1];
        testUnits[2] = this.specializationTests.get(0).getTestUnits()[2];
        webTest.setTestUnits(testUnits);
        webTest.setUnitsWeights(new double[]{0.7d, 0.1d, 0.2d});

        this.specializationTests.add(webTest);
    }

    private List<Test> initAndGetWeb1Tests() {
        List<Test> tests = new ArrayList<>();

        Test test = new Test("Що адресує наступне посилання? \n<a href=\"../images/1.jpg\" />", TestType.CHECKBOX);
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

    private List<Test> initAndGetSys1Tests() {
        List<Test> tests = new ArrayList<>();

        Test test = new Test("Як «прокинути» до локальної мережі білу адресу, якщо провайдер видав дві такі адреси, а на шлюзі встановлений Linux?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "proxyarp");
        test.addPossibleAnswer("val2", "DNAT");
        test.addPossibleAnswer("val3", "SNAT");
        test.addPossibleAnswer("val4", "reverse proxy");
        test.addPossibleAnswer("val5", "Жоден із запропонованих варіантів");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1, -1});
        tests.add(test);

        test = new Test("Сисадмін Володимир Бубнов вирішив завантажити чотири сезони серіалу «Теорія великого вибуху» в озвучці Кураж-Бамбі з одного відомого торрент-трекера.\nСумарний розмір архіву всіх серій становить 48.86 Гбайт. Володимир задумався - чи встигне він до вечора завантажити весь архів через робочий канал 8 Мбіт / сек і забрати його додому, якщо виділить під це діло окрему смугу з шириною каналу 4 Мбіт / сек, щоб не злити інших співробітників компанії?\nПовторіть розрахунки Бубнова і вкажіть, скільки годин потрібно нашому адміну для того, щоб викачати серіал при зазначених вище умовах?\n", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "6 годин");
        test.addPossibleAnswer("val2", "14 годин");
        test.addPossibleAnswer("val3", "28 годин");
        test.addPossibleAnswer("val4", "33 години");
        test.setAnswersWeights(new int[]{-1, -1, 1, -1});
        tests.add(test);

        test = new Test("Команда apt-cache search XXX в Ubuntu повертає список пакетів з описом.\nЯк встановити пакети з цього списку однією командою apt-get install без копіювання імені кожного пакета вручну?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "sudo apt-get install XXX");
        test.addPossibleAnswer("val2", "apt-cache search XXX | sudo apt-get install");
        test.addPossibleAnswer("val3", "apt-cache search XXX | cut --delimiter=\" \" --fields=1 | sudo xargs apt-get -y install");
        test.addPossibleAnswer("val4", "apt-cache search XXX | cut -d=\" \" -f=1 | sudo xargs apt-get -y install");
        test.addPossibleAnswer("val5", "Жоден із запропонованих варіантів");
        test.setAnswersWeights(new int[]{-1, -1, -1, 1, -1});
        tests.add(test);

        test = new Test("Студент Вася, намагаючись дізнатися пароль генерального директора, отримав доступ до / etc / passwd на сервері. Але його чекало лише розчарування, так як замість рядка з паролем він виявив рядок director: x: 1000: 1000: director: / home / director: / bin / bash.\nЩо означає х в цьому рядку?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Користувачеві director вхід в систему заборонений");
        test.addPossibleAnswer("val2", "Пароль користувача director знаходиться в іншому місці");
        test.addPossibleAnswer("val3", "Користувач director може входити в систему з порожнім паролем");
        test.addPossibleAnswer("val4", "Користувач director має пароль xЖоден із запропонованих варіантів");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1});
        tests.add(test);

        test = new Test("В якому з конфігураційних файлів зустрічається номер автономної системи?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "/etc/ssh/sshd_config");
        test.addPossibleAnswer("val2", "/etc/sysctl.conf");
        test.addPossibleAnswer("val3", "/etc/bind/named.co");
        test.addPossibleAnswer("val4", "/etc/bird.conf");
        test.setAnswersWeights(new int[]{-1, -1, -1, 1});
        tests.add(test);

        test = new Test("Програміст Макс Ерланген все життя вірив в те, що 1 Кілобайт = 1024 Байта. Зовсім недавно він з подивом дізнався, що це не так.\nПідкажіть Максу, яку приставку правильно використовувати для величини, яка означає множення на 2 ^ 10 (два в десятому ступені), тобто в 1024 рази?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "КБ");
        test.addPossibleAnswer("val2", "Кібі");
        test.addPossibleAnswer("val3", "Дека");
        test.addPossibleAnswer("val4", "Кб");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1});
        tests.add(test);

        test = new Test("Припустимо, що перед вами стоїть завдання, маючи тільки адреса IPv6, підключитися по SSH до вузла, що має адресу IPv4. Ваші дії?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Використовувати проксі sixxs.com");
        test.addPossibleAnswer("val2", "Приписати в початок IPv4 :: ffff:");
        test.addPossibleAnswer("val3", "Встановити тунель Teredo");
        test.addPossibleAnswer("val4", "Жоден із запропонованих варіантів");
        test.setAnswersWeights(new int[]{-1, 1, -1, -1});
        tests.add(test);

        test = new Test("Як в конвеєрі Unix продублювати висновок на екран (або в будь-який інший стандартний вихід) і в наступну команду (a і b - це команди)?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "exec 4>&1;a | tee /dev/stderr 2>&4 | b");
        test.addPossibleAnswer("val2", "a | tee /dev/tt");
        test.addPossibleAnswer("val3", "a | tee /dev/stdout | b");
        test.addPossibleAnswer("val4", "<a | tee /dev/stderr 2>&4 | b 4>&1;");
        test.addPossibleAnswer("val4", "Жоден із запропонованих варіантів");
        test.setAnswersWeights(new int[]{1, -1, -1, -1, -1});
        tests.add(test);

        test = new Test("Є точка доступу, яка при розміщенні на офісній кухні дає дальність зв'язку 20 м. Потужність точки доступу 65 мВт. Точка налаштована на 8 канал. Коли мікрохвильовка моделі LG MB-4027, що знаходиться на кухні, включена, з неї \"витікають\" радіохвилі потужністю 1 Вт.\nНаскільки зменшиться при цьому максимальна дальність дії бездротового зв'язку?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Дальність зв'язку зменшиться в 10 разів");
        test.addPossibleAnswer("val2", "Дальність зв'язку зменшиться в 3 рази");
        test.addPossibleAnswer("val3", "Зв'язок повністю пропаде");
        test.addPossibleAnswer("val4", "Не зміниться");
        test.setAnswersWeights(new int[]{-1, -1, -1, 1});
        tests.add(test);

        test = new Test("Адмін Вася в суботу вдень налаштовував сервер, розташований в Німеччині, з домашнього комп'ютера по SSH. Доступ в інтернет у нього вдома організований через стандартний Linux-роутер з NAT.\nРаптом Васина улюблена бабуся випадково зачепила роутер шваброю, і той перезавантажився. Після того, як роутер знову почав працювати, виявилося, що SSH-сесія, відкрита через NAT, як і раніше функціонує.\nЧому SSH-сесія не розірвалася?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Linux кеширує conntrack на диску");
        test.addPossibleAnswer("val2", "Особливість реалізації conntrack в Linux");
        test.addPossibleAnswer("val3", "А в чому проблема? RST або FIN не приходили. Чому має розірватися?");
        test.addPossibleAnswer("val4", "Жоден із запропонованих варіантів");
        test.setAnswersWeights(new int[]{1, -1, -1, -1});
        tests.add(test);

        test = new Test("Файлова система btrfs (останні версії, починаючи з січня 2012) не може ...", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Зберігати файли розміром більше 16 Тб");
        test.addPossibleAnswer("val2", "Використовуватися спільно з ISCSI");
        test.addPossibleAnswer("val3", "Зберігати файли підкачки");
        test.addPossibleAnswer("val4", "Використовуватися в віртуальних машинах");
        test.setAnswersWeights(new int[]{-1, -1, 1, -1});
        tests.add(test);

        test = new Test("Які технології дозволяють SkyDNS блокувати активність ботнетів в локальній мережі на рівні протоколу DNS?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Заборона на використання локальних адрес для доменів (наприклад, 192.168. *. * Або 10.0. *. *), Що дозволяє запобігати атакам класу DNS Rebinding");
        test.addPossibleAnswer("val2", "Блокування IP-адрес заражених машин в локальній мережі для запобігання відправки особистих даних користувача на хости зловмисників");
        test.addPossibleAnswer("val3", "Аналіз всього трафіку локальної мережі та пошук підозрілих сигнатур з вірусних баз даних, а також використання спеціальних евристик для виявлення і блокування шкідливого коду.");
        test.setAnswersWeights(new int[]{1, -1, -1});
        tests.add(test);

        return tests;
    }

    private List<Test> initAndGetPC1Tests() {
        List<Test> tests = new ArrayList<>();

        Test test = new Test("Що таке файл?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Файл-це базова складова програмного забезпечення.");
        test.addPossibleAnswer("val2", "Файл - це певна кількість інформації (програма або дані), що мають ім'я і зберігаються в довгостроковій пам'яті");
        test.addPossibleAnswer("val3", "Файл - спеціальна програма, яка забезпечує управління і обмін інформацією між пристроями");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Що таке файлова система?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Це безпечна система зберігання інформації");
        test.addPossibleAnswer("val2", "Це однорангова система зберігання інформації");
        test.addPossibleAnswer("val3", "Це система зберігання файлів і організації каталогів");
        test.setAnswersWeights(new int[]{-1, -1, 1});
        tests.add(test);

        test = new Test("Що відноситься до атрибутів файлу?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Його ім'я, тип (розширення), значок, розмір, дата і час створення.");
        test.addPossibleAnswer("val2", "Його тип і місце розташування на диску");
        test.addPossibleAnswer("val3", "Його значок і місце розташування на диску");
        test.setAnswersWeights(new int[]{1, -1, -1});
        tests.add(test);

        test = new Test("Задано повне ім'я файлу C:\\DOC\\МОЇ ДОКУМЕНТИ\\7КЛАСС\\ПЕТРОВ\\PROBA.TXT. Яке ім'я каталогу, в якому знаходиться цей файл?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "7 КЛАС");
        test.addPossibleAnswer("val2", "ПЕТРОВ");
        test.addPossibleAnswer("val3", "ПЕТРОВ\\PROBA");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Продуктивність процесора залежить від", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "його типу і швидкості роботи");
        test.addPossibleAnswer("val2", "його розрядності і тактової частоти");
        test.addPossibleAnswer("val3", "його роз'ємів");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Шляхи підвищення продуктивності процесора полягають в", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "У вдосконаленні архітектури, введення кеш-пам'яті, використанні декількох ядер");
        test.addPossibleAnswer("val2", "в зміні функціональної схеми");
        test.addPossibleAnswer("val3", "в збільшенні числа роз'ємів процесора");
        test.setAnswersWeights(new int[]{1, -1, -1});
        tests.add(test);

        test = new Test("Інформація представлена в двійковому комп'ютерному коді (представляє собою послідовність 0 і 1) називається", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "програма");
        test.addPossibleAnswer("val2", "файл");
        test.addPossibleAnswer("val3", "дані");
        test.setAnswersWeights(new int[]{-1, -1, 1});
        tests.add(test);

        test = new Test("Для того щоб комп'ютер зміг виконати обробку даних за програмою, програма і дані повинні бути завантажені", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "на жорсткий диск");
        test.addPossibleAnswer("val2", "в оперативну пам'ять");
        test.addPossibleAnswer("val3", "в магістраль");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Які пристрої відносяться до пристроїв введення інформації?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "принтер, сканер, клавіатура");
        test.addPossibleAnswer("val2", "графічний планшет, клавіатура, мікрофон, сканер");
        test.addPossibleAnswer("val3", "монітор, сканер, клавіатура");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Для оптичного введення в комп'ютер і перетворення в комп'ютерну форму зображень, а також текстових документів використовується", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "графічний планшет");
        test.addPossibleAnswer("val2", "сканер");
        test.addPossibleAnswer("val3", "принтер");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Якість зображення монітора визначається", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "способом підключення");
        test.addPossibleAnswer("val2", "роздільною здатністю");
        test.addPossibleAnswer("val3", "типом відеокарти");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Які принтери забезпечують топографічної якості друку, високу швидкість друку при менших витратах на витратні матеріали?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "струменеві");
        test.addPossibleAnswer("val2", "лазерні");
        test.addPossibleAnswer("val3", "матричні");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Що зберігається в осередках оперативної пам'яті?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "мікрочіпи");
        test.addPossibleAnswer("val2", "модулі пам'яті");
        test.addPossibleAnswer("val3", "двійковий код довжиною 8 знаків");
        test.setAnswersWeights(new int[]{-1, -1, 1});
        tests.add(test);

        test = new Test("Інформація на оптичному диску зберігається", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "на одній спіралевидній доріжці, що йде від центру до периферії");
        test.addPossibleAnswer("val2", "на певних доріжках");
        test.addPossibleAnswer("val3", "в певних секторах");
        test.setAnswersWeights(new int[]{1, -1, -1});
        tests.add(test);

        test = new Test("Які мережі називають одноранговими?", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "Мережі з'єднані одним кабелем називаються одноранговими");
        test.addPossibleAnswer("val2", "Мережі в яких всі комп'ютери рівноправні");
        test.addPossibleAnswer("val3", "Мережі з'єднані через сервер");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        test = new Test("Мережа на основі сервера має топологію", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "зірка");
        test.addPossibleAnswer("val2", "кільце");
        test.addPossibleAnswer("val3", "загальна шина");
        test.setAnswersWeights(new int[]{1, -1, -1});
        tests.add(test);

        test = new Test("Кожен комп'ютер або принтер підключений до локальної мережі повинен мати ...", TestType.CHECKBOX);
        test.addPossibleAnswer("val1", "маршрутизатор");
        test.addPossibleAnswer("val2", "мережевий адаптер");
        test.addPossibleAnswer("val3", "комунікатор");
        test.setAnswersWeights(new int[]{-1, 1, -1});
        tests.add(test);

        return tests;
    }
}
