package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.Test;
import com.eezo.hrd.entities.TestAdditional;
import com.eezo.hrd.enums.TestType;
import com.eezo.hrd.facades.UserFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@SessionScoped
public class WebDesignTestsController implements Serializable {
    @Inject
    private IndexController indexController;
    @EJB
    private UserFacade userFacade;
    private List<Test> webDesignerTests1;
    private List<Test> webDesignerTests2;
    private List<Test> webDesignerTests3;
    private Map<String, TestAdditional> testsAdditionals;
    private int counter = 0;

    @PostConstruct
    public void init() {
        this.testsAdditionals = new HashMap<>();
        initWebDesigner1Tests();
        initWebDesigner2Tests();
        initWebDesigner3Tests();
    }

    public String getNextTest() {
        Map<String, Integer> passedTests = indexController.getCurrent().getPassedTests();
        if (passedTests == null) {
            passedTests = new LinkedHashMap<>();
            passedTests.put("web1", 0);
            passedTests.put("web2", 0);
            passedTests.put("web3", 0);
            passedTests.put("sys1", 0);
            passedTests.put("sys2", 0);
            indexController.getCurrent().setPassedTests(passedTests);
            userFacade.edit(indexController.getCurrent());
        }
        for (String testName : passedTests.keySet()) {
            if (passedTests.get(testName) == 0) {
                return testName;
            }
        }
        return "";
    }

    public String handleResults() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.get("web-test-1") != null) {
            int i = 0;
            int rightAnswersCounter = 0;
            for (Test test : webDesignerTests1) {
                String answer = params.get("group" + i);
                for (String rightAnswer : test.getRightAnswers()) {
                    if (rightAnswer.equals(answer)) {
                        rightAnswersCounter++;
                    }
                }
                i++;
            }
            indexController.getCurrent().getPassedTests().put("web1", rightAnswersCounter);
            userFacade.edit(indexController.getCurrent());
        }
        return "index.xhtml?faces-redirect=true";
    }

    public List<Test> getSpecifiedTest(String testName) {
        switch (testName) {
            case "web1":
                return webDesignerTests1;
            case "web2":
                return webDesignerTests2;
            case "web3":
                return webDesignerTests3;
            default:
                return webDesignerTests1;
        }
    }

    private void initWebDesigner1Tests() {
        this.webDesignerTests1 = new ArrayList<>();
        TestAdditional testAdditional = new TestAdditional();
        testAdditional.setTitle("ПРОФЕССИОНАЛЬНАЯ КОМПЕТЕНЦИЯ");
        testAdditional.setDesc("Знание стандартов работы веб-дизайнера и их эффективное применение на практике");
        testAdditional.setSubDesc("Теоретические знания стандартов работы веб-дизайнера и эффективные навыки/опыт их применение на собственной профессиональной практике");
        this.testsAdditionals.put("web1", testAdditional);

        Test newTest = new Test("Что адресует следующая ссылка? <a href=\"../images/1.jpg\" />");
        newTest.setDescription("Путь выходит на уровень вверх, а затем переходит в папку images в родительском каталоге.");
        newTest.addPossibleAnswer("val1", "изображение, расположенное в каталоге &quot;images&quot;, дочернем по отношению к текущему.");
        newTest.addPossibleAnswer("val2", "изображение, расположенное в каталоге &quot;images&quot;, родительском по отношению к текущему.");
        newTest.addPossibleAnswer("val3", "изображение, расположенное в каталоге &quot;images&quot;, который расположен в родительском по отношению к текущему каталогу.");
        newTest.setRightAnswers("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Для чего используется тег <TITLE>");
        newTest.setDescription("тег <TITLE> определяет заголовок документа. Элемент <TITLE> не является частью документа и не показывается напрямую на веб-странице. В операционной системе Windows текст заголовка отображается в левом верхнем углу окна браузера. Допускается использовать только один тег <TITLE> на документ и размещать его в контейнере <HEAD>");
        newTest.addPossibleAnswer("val1", "Определяет заголовок таблицы.");
        newTest.addPossibleAnswer("val2", "Определяет заголовок документа.");
        newTest.addPossibleAnswer("val3", "Определяет заголовок в тексте.");
        newTest.addPossibleAnswer("val4", "Определяет красную строку в тексте.");
        newTest.setRightAnswers("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой атрибут тега <td> указывает количество строк, занимаемых ячейкой?");
        newTest.addPossibleAnswer("val1", "cols");
        newTest.addPossibleAnswer("val2", "rows");
        newTest.addPossibleAnswer("val3", "vcells");
        newTest.addPossibleAnswer("val4", "colspan");
        newTest.addPossibleAnswer("val5", "rowspan");
        newTest.setRightAnswers("val5");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Выберите все допустимые значения атрибута method у элемента form.", TestType.CHECKBOX);
        newTest.addPossibleAnswer("val1", "upload");
        newTest.addPossibleAnswer("val2", "get");
        newTest.addPossibleAnswer("val3", "request");
        newTest.addPossibleAnswer("val4", "post");
        newTest.addPossibleAnswer("val5", "submit");
        newTest.setRightAnswers("val2", "val4");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Укажите правильный вариант создания гипертекстовой ссылки в html.");
        newTest.addPossibleAnswer("val1", "<a link=\"http://www.quizful.net\">quizful</a>");
        newTest.addPossibleAnswer("val2", "<a href=\"http://www.quizful.net\">quizful</a>");
        newTest.addPossibleAnswer("val3", "<a>http://www.quizful.net</a>");
        newTest.addPossibleAnswer("val4", "<a target=\"http://www.quizful.net\">quizful</a>");
        newTest.addPossibleAnswer("val5", "<a url=\"http://www.quizful.net\">quizful</a>");
        newTest.setRightAnswers("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Выберите фрагмент HTML-кода, создающий ссылку со всплывающей подсказкой.");
        newTest.addPossibleAnswer("val1", "<a tip='подсказка'>текст ссылки</a>");
        newTest.addPossibleAnswer("val2", "<a balloon='подсказка'>текст ссылки</a>");
        newTest.addPossibleAnswer("val3", "<a help='подсказка'>текст ссылки</a>");
        newTest.addPossibleAnswer("val4", "<a title='подсказка'>текст ссылки</a>");
        newTest.addPossibleAnswer("val5", "<a tooltip='подсказка'>текст ссылки</a>");
        newTest.setRightAnswers("val4");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой из приведенных фрагментов кода выравнивает содержимое ячейки по правому краю?");
        newTest.addPossibleAnswer("val1", "<td float=\"right\">");
        newTest.addPossibleAnswer("val2", "<td right=\"right\">");
        newTest.addPossibleAnswer("val3", "<td align=\"right\">");
        newTest.addPossibleAnswer("val4", "<td valign=\"right\">");
        newTest.addPossibleAnswer("val5", "<td textalign=\"right\">");
        newTest.setRightAnswers("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какое свойство устанавливает расстояние от края ячейки таблицы до её содержимого?");
        newTest.addPossibleAnswer("val1", "cellmargin");
        newTest.addPossibleAnswer("val2", "cellpadding");
        newTest.addPossibleAnswer("val3", "cellspacing");
        newTest.addPossibleAnswer("val4", "cellspace");
        newTest.addPossibleAnswer("val5", "Такого атрибута не существует");
        newTest.setRightAnswers("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой html-тег создает поле ввода?");
        newTest.addPossibleAnswer("val1", "<text>");
        newTest.addPossibleAnswer("val2", "<textfield>");
        newTest.addPossibleAnswer("val3", "<select>");
        newTest.addPossibleAnswer("val4", "<input>");
        newTest.addPossibleAnswer("val5", "<textbox>");
        newTest.setRightAnswers("val4");
        newTest.setDescription("Тег <input> является одним из разносторонних элементов формы и позволяет создавать разные элементы интерфейса и обеспечить взаимодействие с пользователем. Главным образом <input> предназначен для создания текстовых полей, различных кнопок, переключателей и флажков.");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой код создает нумерованный список, пронумерованный строчными римскими цифрами и нумерация которого начинается с цифры 5?");
        newTest.addPossibleAnswer("val1", "<ol type=\"i\" start=\"5\">\n" +
                "    <li>Афины</li>\n" +
                "    <li>Киев</li>\n" +
                "    <li>Рим</li>\n" +
                "    <li>Стамбул</li>\n" +
                "</ol>\n");
        newTest.addPossibleAnswer("val2", "<ul type=\"5\" start=\"i\">\n" +
                "    <li>Афины</li>\n" +
                "    <li>Киев</li>\n" +
                "    <li>Рим</li>\n" +
                "    <li>Стамбул</li>\n" +
                "</ul>\n");
        newTest.addPossibleAnswer("val3", "<dl type=\"a\" start=\"5\">\n" +
                "    <li>Афины</li>\n" +
                "    <li>Киев</li>\n" +
                "    <li>Рим</li>\n" +
                "    <li>Стамбул</li>\n" +
                "</dl>\n");
        newTest.addPossibleAnswer("val4", "<ul type=\"i\" start=\"5\">\n" +
                "    <li>Афины</li>\n" +
                "    <li>Киев</li>\n" +
                "    <li>Рим</li>\n" +
                "    <li>Стамбул</li>\n" +
                "</ul>\n");
        newTest.addPossibleAnswer("val5", "<ol type=\"1\" start=\"5\">\n" +
                "    <li>Афины</li>\n" +
                "    <li>Киев</li>\n" +
                "    <li>Рим</li>\n" +
                "    <li>Стамбул</li>\n" +
                "</ol>\n");
        newTest.setRightAnswers("val1");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Необходимо защитить текстовое поле формы от изменения значения пользователем.\n" +
                "Какие из представленных фрагментов кода позволят решить поставленную задачу?");
        newTest.addPossibleAnswer("val1", "<input value=\"$999\" checked/>");
        newTest.addPossibleAnswer("val2", "<input value=\"$999\" readonly/>");
        newTest.addPossibleAnswer("val3", "<input value=\"$999\" disabled/>");
        newTest.addPossibleAnswer("val4", "<input value=\"$999\" size=\"0\"/>");
        newTest.addPossibleAnswer("val5", "<input value=\"$999\" maxlength=\"0\"/>");
        newTest.setRightAnswers("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Как создать нумерованный список?");
        newTest.addPossibleAnswer("val1", "<ul>");
        newTest.addPossibleAnswer("val2", "<ist type=\"number\">");
        newTest.addPossibleAnswer("val3", "<ol>");
        newTest.addPossibleAnswer("val4", "<li>");
        newTest.addPossibleAnswer("val5", "<list type=\"ordered\">");
        newTest.addPossibleAnswer("val6", "<list>");
        newTest.setRightAnswers("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой браузер не отображает корректно прозрачность PNG-24 (альфа канал)");
        newTest.addPossibleAnswer("val1", "Internet Explorer 6");
        newTest.addPossibleAnswer("val2", "Firefox 2.0");
        newTest.addPossibleAnswer("val3", "Internet Explorer 6, Internet Explorer 7");
        newTest.addPossibleAnswer("val4", "Opera 9.5");
        newTest.setRightAnswers("val1");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой тег определяет изображение, которое будет использоваться в качестве фонового рисунка?");
        newTest.addPossibleAnswer("val1", "<body link=...>");
        newTest.addPossibleAnswer("val2", "<body background=...>");
        newTest.addPossibleAnswer("val3", "<body text...>");
        newTest.addPossibleAnswer("val4", "<body bgcolor=...>");
        newTest.setRightAnswers("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Как выглядит тэг <form> на странице?");
        newTest.addPossibleAnswer("val1", "тэг <form> представляет из себя поле для ввода текста");
        newTest.addPossibleAnswer("val2", "тэг <form> не имеет собственного графического представления - это контейнер для других элементов");
        newTest.addPossibleAnswer("val3", "тэг <form> не имеет собственного графического представления - это контейнер для текста");
        newTest.addPossibleAnswer("val4", "тэг <form> представляет из себя выдающий список");
        newTest.setRightAnswers("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Что делают эти спецсимволы &laquo; и &raquo;");
        newTest.addPossibleAnswer("val1", "Такого нет в языке html");
        newTest.addPossibleAnswer("val2", "Увеличивают первую и последнюю буквы на 0,2 em");
        newTest.addPossibleAnswer("val3", "Заключают текст во двойные угловые кавычки");
        newTest.addPossibleAnswer("val4", "Заключают текст во одинарные угловые кавычки");
        newTest.setRightAnswers("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой тег предназначен для заголовков наименьшего размера?");
        newTest.addPossibleAnswer("val1", "<h1>");
        newTest.addPossibleAnswer("val2", "<h5>");
        newTest.addPossibleAnswer("val3", "<h6>");
        newTest.addPossibleAnswer("val4", "<h7>");
        newTest.addPossibleAnswer("val5", "<hmin>");
        newTest.setRightAnswers("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой тег используется для создания параграфа?");
        newTest.addPossibleAnswer("val1", "<br>");
        newTest.addPossibleAnswer("val2", "<p>");
        newTest.addPossibleAnswer("val3", "<f>");
        newTest.addPossibleAnswer("val4", "<paragraph>");
        newTest.addPossibleAnswer("val5", "<div>");
        newTest.setRightAnswers("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой атрибут тега <ol> начинает нумерацию списка с определённого значения?");
        newTest.addPossibleAnswer("val1", "type");
        newTest.addPossibleAnswer("val2", "number");
        newTest.addPossibleAnswer("val3", "value");
        newTest.addPossibleAnswer("val4", "start");
        newTest.addPossibleAnswer("val5", "begin");
        newTest.setRightAnswers("val4");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой тег в HTML5 отображает секцию навигационных ссылок?");
        newTest.addPossibleAnswer("val1", "<navigation>");
        newTest.addPossibleAnswer("val2", "<nav>");
        newTest.addPossibleAnswer("val3", "<navlinks>");
        newTest.addPossibleAnswer("val4", "<headmenu>");
        newTest.setRightAnswers("val2");
        this.webDesignerTests1.add(newTest);
    }

    private void initWebDesigner2Tests() {
        this.webDesignerTests2 = new ArrayList<>();
        TestAdditional testAdditional = new TestAdditional();
        testAdditional.setTitle("КОММУНИКАТИВНЫЕ КОМПЕТЕНЦИИ");
        testAdditional.setDesc("Коммуникабельность");
        testAdditional.setSubDesc("Способность легко выстраивать отношения с разными типами людей и влиять на их мнение/поведение, использую различные техники общения.");
        testAdditional.setWeightMap(new int[][]{
                {1, 2, 3}, {2, 3, 1}, {2, 3, 1}, {3, 2, 1}, {1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {3, 2, 1}
        });
        this.testsAdditionals.put("web2", testAdditional);

        Test newTest = new Test("Какую фразу при беседе с сотрудником лучше использовать при отсутствии результата, чтобы подчиненный почувствовал свою ответственность?");
        newTest.addPossibleAnswer("val1", "Результата нет");
        newTest.addPossibleAnswer("val2", "Мы не достигли результата");
        newTest.addPossibleAnswer("val3", "Ты не выполнил задание");
        newTest.setRightAnswers("val3");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Какой вопрос необходимо задать сотруднику, чтобы сформировать ответственное отношение к принятому заданию для выполнения");
        newTest.addPossibleAnswer("val1", "Как ты понял, что нужно сделать?");
        newTest.addPossibleAnswer("val2", "Ты обещаешь это сделать?");
        newTest.addPossibleAnswer("val3", "Ты знаешь как это делать?");
        newTest.setRightAnswers("val2");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Руководитель не смог добиться у руководства компании повышения заработной платы для своего сотрудника. Что в такой ситуации скажет ответственный руководитель своему сотруднику?");
        newTest.addPossibleAnswer("val1", "Я очень старался, но нам с собственником не удалось договориться");
        newTest.addPossibleAnswer("val2", "Я не смог отстоять твою просьбу");
        newTest.addPossibleAnswer("val3", "Оказывается повышение заработной платы возможно только для тех, кто отработал в компании более трех лет");
        newTest.setRightAnswers("val2");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Что скажет подчиненный, готовый взять на себя ответственность за поставленную ему задачу?");
        newTest.addPossibleAnswer("val1", "Конечно, я сделаю все, чтобы достичь результата");
        newTest.addPossibleAnswer("val2", "Конечно,я сделаю все, что только смогу");
        newTest.addPossibleAnswer("val3", "Конечно,я постараюсь все успеть");
        newTest.setRightAnswers("val1");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Подчиненный упрекнул вас в том, что вы не выполнили данное ему обещание. Что нужно ему ответить, чтобы сохранить авторитет и продемонстрировать лидерскую ответственность?");
        newTest.addPossibleAnswer("val1", "Компенсировать сотруднику причиненные неудобства, например, предоставить внеочередной отгул");
        newTest.addPossibleAnswer("val2", "Разъяснить ситуацию, по которой вы вынуждены были так поступить");
        newTest.addPossibleAnswer("val3", "Признать свою ошибку и извиниться");
        newTest.setRightAnswers("val3");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Вышестоящий руководитель попросил вас порекомендовать сотрудника для управления новым проектом. На ком лежит ответственность, если рекомендованный вами сотрудник \"провалит\" доверенный ему проект?");
        newTest.addPossibleAnswer("val1", "На сотруднике, ведь он несет ответственность за результаты проекта");
        newTest.addPossibleAnswer("val2", "На вас, ведь вы его рекомендовали");
        newTest.addPossibleAnswer("val3", "На вашем начальнике, ведь он принимал окончательное решение по кандидатуре");
        newTest.setRightAnswers("val2");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Какой из озвученных сотрудником аргументов является действительно объективной причиной невыполнения задания?");
        newTest.addPossibleAnswer("val1", "Бухгалтерия не предоставила необходимые данные");
        newTest.addPossibleAnswer("val2", "Экономика находится в рецессии");
        newTest.addPossibleAnswer("val3", "Объективных причин невыполнения не бывает");
        newTest.setRightAnswers("val3");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Что должен сделать руководитель, чтобы сформировать ответственную позицию у подчиненного, если тот не справился с важной задачей?");
        newTest.addPossibleAnswer("val1", "Лишить премии, чтобы исключить повторение подобных ситуаций");
        newTest.addPossibleAnswer("val2", "Обсудить, где сотрудник ошибся, и дать совет, как ему поступать в подобных ситуациях в будущем");
        newTest.addPossibleAnswer("val3", "Дать сотруднику возможность самостоятельно исправить ситуацию");
        newTest.setRightAnswers("val2");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Ваш заместитель просит вас согласовать премии сотрудникам, участвующим в дополнительной проектной работе в их рабочее время. Ответственно ли он поступает?");
        newTest.addPossibleAnswer("val1", "Да, потому что в следствие внедрения проекта выручка компании увеличится");
        newTest.addPossibleAnswer("val2", "Да, дополнительная работа должна оплачиваться в любом случае");
        newTest.addPossibleAnswer("val3", "Нет, он должен найти способы нематериальной мотивации");
        newTest.setRightAnswers("val3");
        this.webDesignerTests2.add(newTest);

        newTest = new Test("Можно ли считать ответственным вашего сотрудника, если он отказывается от выполнения задания, которое находится вне рамок его должностных обязанностей?");
        newTest.addPossibleAnswer("val1", "Да, вне зависимости от причины отказа");
        newTest.addPossibleAnswer("val2", "Да, при условии, что он не умеет делать то, что ему поручили");
        newTest.addPossibleAnswer("val3", "Нет, он в любом случае поступает безответственно");
        newTest.setRightAnswers("val1");
        this.webDesignerTests2.add(newTest);
    }

    private void initWebDesigner3Tests() {
        this.webDesignerTests3 = new ArrayList<>();
        TestAdditional testAdditional = new TestAdditional();
        testAdditional.setTitle("ЛИЧНОСТНО-ДЕЛОВЫЕ КОМПЕТЕНЦИИ");
        testAdditional.setDesc("Аналитическое мышление");
        testAdditional.setSubDesc("Способность анализировать проблемы и выделять составляющие их элементы, делать систематизированные и логичные выводы, основанные на правильно отобранной информации.");
        testAdditional.setWeightMap(new int[][]{
                {1, 2, 3, 4, 5}, {5, 4, 3, 2, 1}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {1, 2, 3, 4, 5}
        });
        this.testsAdditionals.put("web3", testAdditional);

        Map<String, String> possibleAnswers = new LinkedHashMap<>();
        possibleAnswers.put("val1", "Никогда");
        possibleAnswers.put("val2", "Редко");
        possibleAnswers.put("val3", "Иногда");
        possibleAnswers.put("val4", "Часто");
        possibleAnswers.put("val5", "Всегда");

        Test newTest = new Test("Какую фразу при беседе с сотрудником лучше использовать при отсутствии результата, чтобы подчиненный почувствовал свою ответственность?");
        newTest.setPossibleAnswers(possibleAnswers);
        newTest.setRightAnswers("val5");
        this.webDesignerTests3.add(newTest);
    }

    public List<Test> getWebDesignerTests1() {
        return webDesignerTests1;
    }

    public List<Test> getWebDesignerTests2() {
        return webDesignerTests2;
    }

    public List<Test> getWebDesignerTests3() {
        return webDesignerTests3;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public IndexController getIndexController() {
        return indexController;
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    public int getCounter() {
        return counter;
    }

    public void incCounter() {
        this.counter++;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public Map<String, TestAdditional> getTestsAdditionals() {
        return testsAdditionals;
    }
}
