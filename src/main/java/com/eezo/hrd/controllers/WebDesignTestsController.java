package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.SpecializationTest;
import com.eezo.hrd.entities.TestUnit;
import com.eezo.hrd.entities.User;
import com.eezo.hrd.enums.UserRole;
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
    @Inject
    private UserController userController;
    @Inject
    private SpecializationTestsController specializationTestsController;
    @EJB
    private UserFacade userFacade;
    private TestUnit testUnit;
    private int testUnitIndex;
    private int counter;

    @PostConstruct
    public void init() {
        testUnitIndex = 0;
        counter = 0;
        nextTestUnit();
    }

    public void nextTestUnit() {
        for (SpecializationTest specializationTest : specializationTestsController.getSpecializationTests()) {
            if (specializationTest.getSpecializationCode().equals(indexController.getUserToSession().getSpecialization())) {
                if (testUnitIndex >= specializationTest.getTestUnits().length) {
                    testUnit = null;
                    testUnitIndex = 0;
                    return;
                }
                testUnit = specializationTest.getTestUnits()[testUnitIndex];
                testUnitIndex++;
                return;
            }
        }
    }

    public String handleResults() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.get(testUnit.getTestUnitCode() + "-test") != null) {
            int score = 0;
            for (int testNumber = 0; testNumber < testUnit.getTests().size(); testNumber++) {
                String answer = params.get("group" + testNumber);
                if (answer != null) {
                    int i = Integer.parseInt(String.valueOf(answer.charAt(3))) - 1;
                    score += testUnit.getTests().get(testNumber).getAnswersWeights()[i];
                }
            }
            User user = indexController.getUserToSession();
            user.getPassedTests().put(testUnit.getTestUnitCode(), score);
            indexController.setUserToSession(user);
            userFacade.edit(indexController.getUserToSession());
            if (user.getPassedTests().size() == specializationTestsController.getSpecializationTests().get(specializationTestsController.getSpecializationTests().size() - 1).getTestUnits().length) {
                return "test-results.xhtml?faces-redirect=true";
            }
            nextTestUnit();
        }
        return "";
    }

    public String getCompValue(String unitTestName) {
        TestUnit currentTestUnit = null;
        User user = indexController.getUserToSession();
        for (SpecializationTest specializationTest : specializationTestsController.getSpecializationTests()) {
            if (!user.getSpecialization().equals(specializationTest.getSpecializationCode())) {
                continue;
            }
            for (TestUnit testUnit : specializationTest.getTestUnits()) {
                if (testUnit.getTestUnitCode().equals(unitTestName)) {
                    currentTestUnit = testUnit;
                }
            }
        }
        if (currentTestUnit == null) return "";
        if (user.getPassedTests() == null) return "Ви ще не проходили тести";
        if (user.getPassedTests().get(unitTestName) > 0) {
            double percentage = (double) user.getPassedTests().get(unitTestName) / (double) currentTestUnit.getTests().size();
            return percentage + " / 1";
        } else {
            return "0 / 1";
        }
    }

    public String getTotalResult() {
        double result = 0.0d;
        User user = indexController.getUserToSession();
        for (SpecializationTest specializationTest : specializationTestsController.getSpecializationTests()) {
            if (!user.getSpecialization().equals(specializationTest.getSpecializationCode())) {
                continue;
            }
            result = specializationTest.getTotalResult(user.getPassedTests());
        }
        String resultAsString = "Виявлений загальний рівень компетентності - " + round(result, 2) + ".\n";
        if (result < 0.7d) {
            if (user.getUserRole().equals(UserRole.GUEST)) {
                return resultAsString + "Ваш професійний рівень не відповідає посаді, яку ви хочете займати. Ми радимо вам підвищити свій професіональний рівень.";
            } else {
                return resultAsString + "Вам потрібно підвищити свій професійний рівень для відповідності посаді, яку ви займаєте.";
            }
        } else {
            if (user.getUserRole().equals(UserRole.GUEST)) {
                return resultAsString + "Ваш професійний рівень дозволяє зайняти обрану посаду. Для подальшого працевлаштування вам необхідно прийти до відділу кадрів.";
            } else {
                return resultAsString + "Дякуємо, ваш професійний рівень відповідає посаді, яку ви займаєте.";
            }
        }
    }

    public double round(double value, int decimals) {
        value *= Math.pow(10, decimals);
        value = Math.round(value);
        return value / Math.pow(10, decimals);
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

    public SpecializationTestsController getSpecializationTestsController() {
        return specializationTestsController;
    }

    public void setSpecializationTestsController(SpecializationTestsController specializationTestsController) {
        this.specializationTestsController = specializationTestsController;
    }

    public TestUnit getTestUnit() {
        return testUnit;
    }

    public void setTestUnit(TestUnit testUnit) {
        this.testUnit = testUnit;
    }

    public int getTestUnitIndex() {
        return testUnitIndex;
    }
}
