package com.eezo.hrd.entities;

import com.eezo.hrd.enums.TestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    private String title;
    private Map<String, String> possibleAnswers;
    private String description;
    private String rightAnswer;
    private String[] rightAnswers;
    private TestType testType;

    public Test(String title) {
        this.title = title;
        this.possibleAnswers = new HashMap<>();
        this.description = "";
        this.testType = TestType.RADIO;
    }

    public Test(String title, TestType testType) {
        this.title = title;
        this.possibleAnswers = new HashMap<>();
        this.description = "";
        this.testType = testType;
    }

    public List<String> getMapKeysAsList(){
        return new ArrayList<>(this.possibleAnswers.keySet());
    }

    public void addPossibleAnswer(String value, String content) {
        this.possibleAnswers.put(value, content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(Map<String, String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public String[] getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(String... rightAnswers) {
        this.rightAnswers = rightAnswers;
    }
}
