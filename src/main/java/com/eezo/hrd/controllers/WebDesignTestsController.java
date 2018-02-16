package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.SpecializationTest;
import com.eezo.hrd.entities.TestUnit;
import com.eezo.hrd.facades.UserFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@ManagedBean
@SessionScoped
public class WebDesignTestsController implements Serializable {
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
            if (specializationTest.getSpecializationCode().equals(userController.getCurrent().getSpecialization())) {
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
            userController.getCurrent().getPassedTests().put(testUnit.getTestUnitCode(), score);
            userFacade.edit(userController.getCurrent());
        }
        return "";
    }

    public String getCompValue(String unitTestName){
        TestUnit currentTestUnit = null;
        for (SpecializationTest specializationTest : specializationTestsController.getSpecializationTests()){
            if (!userController.getCurrent().getSpecialization().equals(specializationTest.getSpecializationCode())) {
                continue;
            }
            for (TestUnit testUnit : specializationTest.getTestUnits()){
                if (testUnit.getTestUnitCode().equals(unitTestName)){
                    currentTestUnit = testUnit;
                }
            }
        }
        if (currentTestUnit == null) return "";
        if (userController.getCurrent().getPassedTests() ==null) return "Ви ще не проходили тести";
        double percentage = userController.getCurrent().getPassedTests().get(unitTestName) / currentTestUnit.getTests().size();
        return percentage + " / 1";
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
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
