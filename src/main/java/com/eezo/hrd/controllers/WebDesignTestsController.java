package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.Test;
import com.eezo.hrd.enums.TestType;
import com.eezo.hrd.facades.UserFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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

    @PostConstruct
    public void init() {
        initWebDesigner1Tests();
//        initWebDesigner2Tests();
//        initWebDesigner3Tests();
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
        }
        for (String testName : passedTests.keySet()) {
            if (passedTests.get(testName) == 0) {
                return testName;
            }
        }
        return "";
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

        Test newTest = new Test("Что адресует следующая ссылка? <a href=\"../images/1.jpg\" />");
        newTest.setDescription("Путь выходит на уровень вверх, а затем переходит в папку images в родительском каталоге.");
        newTest.addPossibleAnswer("val1", "изображение, расположенное в каталоге &quot;images&quot;, дочернем по отношению к текущему.");
        newTest.addPossibleAnswer("val2", "изображение, расположенное в каталоге &quot;images&quot;, родительском по отношению к текущему.");
        newTest.addPossibleAnswer("val3", "изображение, расположенное в каталоге &quot;images&quot;, который расположен в родительском по отношению к текущему каталогу.");
        newTest.setRightAnswer("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Для чего используется тег <TITLE>");
        newTest.setDescription("тег <TITLE> определяет заголовок документа. Элемент <TITLE> не является частью документа и не показывается напрямую на веб-странице. В операционной системе Windows текст заголовка отображается в левом верхнем углу окна браузера. Допускается использовать только один тег <TITLE> на документ и размещать его в контейнере <HEAD>");
        newTest.addPossibleAnswer("val1", "Определяет заголовок таблицы.");
        newTest.addPossibleAnswer("val2", "Определяет заголовок документа.");
        newTest.addPossibleAnswer("val3", "Определяет заголовок в тексте.");
        newTest.addPossibleAnswer("val4", "Определяет красную строку в тексте.");
        newTest.setRightAnswer("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой атрибут тега <td> указывает количество строк, занимаемых ячейкой?");
        newTest.addPossibleAnswer("val1", "cols");
        newTest.addPossibleAnswer("val2", "rows");
        newTest.addPossibleAnswer("val3", "vcells");
        newTest.addPossibleAnswer("val4", "colspan");
        newTest.addPossibleAnswer("val5", "rowspan");
        newTest.setRightAnswer("val5");
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
        newTest.setRightAnswer("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Выберите фрагмент HTML-кода, создающий ссылку со всплывающей подсказкой.");
        newTest.addPossibleAnswer("val1", "<a tip='подсказка'>текст ссылки</a>");
        newTest.addPossibleAnswer("val2", "<a balloon='подсказка'>текст ссылки</a>");
        newTest.addPossibleAnswer("val3", "<a help='подсказка'>текст ссылки</a>");
        newTest.addPossibleAnswer("val4", "<a title='подсказка'>текст ссылки</a>");
        newTest.addPossibleAnswer("val5", "<a tooltip='подсказка'>текст ссылки</a>");
        newTest.setRightAnswer("val4");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой из приведенных фрагментов кода выравнивает содержимое ячейки по правому краю?");
        newTest.addPossibleAnswer("val1", "<td float=\"right\">");
        newTest.addPossibleAnswer("val2", "<td right=\"right\">");
        newTest.addPossibleAnswer("val3", "<td align=\"right\">");
        newTest.addPossibleAnswer("val4", "<td valign=\"right\">");
        newTest.addPossibleAnswer("val5", "<td textalign=\"right\">");
        newTest.setRightAnswer("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какое свойство устанавливает расстояние от края ячейки таблицы до её содержимого?");
        newTest.addPossibleAnswer("val1", "cellmargin");
        newTest.addPossibleAnswer("val2", "cellpadding");
        newTest.addPossibleAnswer("val3", "cellspacing");
        newTest.addPossibleAnswer("val4", "cellspace");
        newTest.addPossibleAnswer("val5", "Такого атрибута не существует");
        newTest.setRightAnswer("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой html-тег создает поле ввода?");
        newTest.addPossibleAnswer("val1", "<text>");
        newTest.addPossibleAnswer("val2", "<textfield>");
        newTest.addPossibleAnswer("val3", "<select>");
        newTest.addPossibleAnswer("val4", "<input>");
        newTest.addPossibleAnswer("val5", "<textbox>");
        newTest.setRightAnswer("val4");
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
        newTest.setRightAnswer("val1");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Необходимо защитить текстовое поле формы от изменения значения пользователем.\n" +
                "Какие из представленных фрагментов кода позволят решить поставленную задачу?");
        newTest.addPossibleAnswer("val1", "<input value=\"$999\" checked/>");
        newTest.addPossibleAnswer("val2", "<input value=\"$999\" readonly/>");
        newTest.addPossibleAnswer("val3", "<input value=\"$999\" disabled/>");
        newTest.addPossibleAnswer("val4", "<input value=\"$999\" size=\"0\"/>");
        newTest.addPossibleAnswer("val5", "<input value=\"$999\" maxlength=\"0\"/>");
        newTest.setRightAnswer("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Как создать нумерованный список?");
        newTest.addPossibleAnswer("val1", "<ul>");
        newTest.addPossibleAnswer("val2", "<ist type=\"number\">");
        newTest.addPossibleAnswer("val3", "<ol>");
        newTest.addPossibleAnswer("val4", "<li>");
        newTest.addPossibleAnswer("val5", "<list type=\"ordered\">");
        newTest.addPossibleAnswer("val6", "<list>");
        newTest.setRightAnswer("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой браузер не отображает корректно прозрачность PNG-24 (альфа канал)");
        newTest.addPossibleAnswer("val1", "Internet Explorer 6");
        newTest.addPossibleAnswer("val2", "Firefox 2.0");
        newTest.addPossibleAnswer("val3", "Internet Explorer 6, Internet Explorer 7");
        newTest.addPossibleAnswer("val4", "Opera 9.5");
        newTest.setRightAnswer("val1");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой тег определяет изображение, которое будет использоваться в качестве фонового рисунка?");
        newTest.addPossibleAnswer("val1", "<body link=...>");
        newTest.addPossibleAnswer("val2", "<body background=...>");
        newTest.addPossibleAnswer("val3", "<body text...>");
        newTest.addPossibleAnswer("val4", "<body bgcolor=...>");
        newTest.setRightAnswer("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Как выглядит тэг <form> на странице?");
        newTest.addPossibleAnswer("val1", "тэг <form> представляет из себя поле для ввода текста");
        newTest.addPossibleAnswer("val2", "тэг <form> не имеет собственного графического представления - это контейнер для других элементов");
        newTest.addPossibleAnswer("val3", "тэг <form> не имеет собственного графического представления - это контейнер для текста");
        newTest.addPossibleAnswer("val4", "тэг <form> представляет из себя выдающий список");
        newTest.setRightAnswer("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Что делают эти спецсимволы &laquo; и &raquo;");
        newTest.addPossibleAnswer("val1", "Такого нет в языке html");
        newTest.addPossibleAnswer("val2", "Увеличивают первую и последнюю буквы на 0,2 em");
        newTest.addPossibleAnswer("val3", "Заключают текст во двойные угловые кавычки");
        newTest.addPossibleAnswer("val4", "Заключают текст во одинарные угловые кавычки");
        newTest.setRightAnswer("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой тег предназначен для заголовков наименьшего размера?");
        newTest.addPossibleAnswer("val1", "<h1>");
        newTest.addPossibleAnswer("val2", "<h5>");
        newTest.addPossibleAnswer("val3", "<h6>");
        newTest.addPossibleAnswer("val4", "<h7>");
        newTest.addPossibleAnswer("val5", "<hmin>");
        newTest.setRightAnswer("val3");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой тег используется для создания параграфа?");
        newTest.addPossibleAnswer("val1", "<br>");
        newTest.addPossibleAnswer("val2", "<p>");
        newTest.addPossibleAnswer("val3", "<f>");
        newTest.addPossibleAnswer("val4", "<paragraph>");
        newTest.addPossibleAnswer("val5", "<div>");
        newTest.setRightAnswer("val2");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой атрибут тега <ol> начинает нумерацию списка с определённого значения?");
        newTest.addPossibleAnswer("val1", "type");
        newTest.addPossibleAnswer("val2", "number");
        newTest.addPossibleAnswer("val3", "value");
        newTest.addPossibleAnswer("val4", "start");
        newTest.addPossibleAnswer("val5", "begin");
        newTest.setRightAnswer("val4");
        this.webDesignerTests1.add(newTest);

        newTest = new Test("Какой тег в HTML5 отображает секцию навигационных ссылок??");
        newTest.addPossibleAnswer("val1", "<navigation>");
        newTest.addPossibleAnswer("val2", "<nav>");
        newTest.addPossibleAnswer("val3", "<navlinks>");
        newTest.addPossibleAnswer("val4", "<headmenu>");
        newTest.setRightAnswer("val2");
        this.webDesignerTests1.add(newTest);
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
}
