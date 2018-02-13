package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.SpecializationTest;
import com.eezo.hrd.entities.TestUnit;
import com.eezo.hrd.entities.User;
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
    private SpecializationTestsController specializationTestsController;
    @EJB
    private UserFacade userFacade;
    private TestUnit testUnit;
    private int testUnitIndex = 0;
    private int counter = 0;

    @PostConstruct
    public void init() {
    }

    public void nextTestUnit() {
        for (SpecializationTest specializationTest : specializationTestsController.getSpecializationTests()) {
            if (specializationTest.getSpecializationCode().equals(indexController.getCurrent().getSpecialization())) {
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
            indexController.getCurrent().getPassedTests().put(testUnit.getTestUnitCode(), score);
            userFacade.edit(indexController.getCurrent());
        }
        return "";
    }

    public String getCompValue(String unitTestName){
        TestUnit currentTestUnit = null;
        for (SpecializationTest specializationTest : specializationTestsController.getSpecializationTests()){
            if (!indexController.getCurrent().getSpecialization().equals(specializationTest.getSpecializationCode())) {
                continue;
            }
            for (TestUnit testUnit : specializationTest.getTestUnits()){
                if (testUnit.getTestUnitCode().equals(unitTestName)){
                    currentTestUnit = testUnit;
                }
            }
        }
        if (currentTestUnit == null) return "";
        double percentage = indexController.getCurrent().getPassedTests().get(unitTestName) / currentTestUnit.getTests().size();
        return percentage + " / 1";
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
}
