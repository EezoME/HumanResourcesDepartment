package com.eezo.hrd.entities;

import java.util.List;
import java.util.Map;

/**
 * Test unit. Contains tests.
 */
public class TestUnit {
    private String testUnitCode;
    private String title;
    private String desc;
    private List<Test> tests;
    /**
     * Levels of competence.<br/>
     * Key - max score.<br/>
     * Value - competence description.
     */
    private Map<Integer, String> testLevels;

    public TestUnit(String testUnitCode, String title) {
        this.testUnitCode = testUnitCode;
        this.title = title;
    }

    public String getTestUnitCode() {
        return testUnitCode;
    }

    public void setTestUnitCode(String testUnitCode) {
        this.testUnitCode = testUnitCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Map<Integer, String> getTestLevels() {
        return testLevels;
    }

    public void setTestLevels(Map<Integer, String> testLevels) {
        this.testLevels = testLevels;
    }
}
