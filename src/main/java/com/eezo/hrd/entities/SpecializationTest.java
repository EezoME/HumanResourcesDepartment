package com.eezo.hrd.entities;

import java.util.Map;

/**
 * Specialization test. Contains test units.
 */
public class SpecializationTest {
    private String specializationCode;
    private String specialization;
    private TestUnit[] testUnits;
    private double[] unitsWeights;

    public SpecializationTest(String specializationCode, String specialization) {
        this.specializationCode = specializationCode;
        this.specialization = specialization;
    }

    public double getTotalResult(Map<String, Integer> passedResults) {
        double result = 0.0d;
        int i = 0;
        for (String testName : passedResults.keySet()) {
            if (passedResults.get(testName) > 0) {
                result += (double) passedResults.get(testName) / (double) testUnits[i].getTests().size() * unitsWeights[i++];
            }
        }
        return result / (double) passedResults.size();
    }

    public String getSpecializationCode() {
        return specializationCode;
    }

    public void setSpecializationCode(String specializationCode) {
        this.specializationCode = specializationCode;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public TestUnit[] getTestUnits() {
        return testUnits;
    }

    public void setTestUnits(TestUnit[] testUnits) {
        this.testUnits = testUnits;
    }

    public double[] getUnitsWeights() {
        return unitsWeights;
    }

    public void setUnitsWeights(double[] unitsWeights) {
        this.unitsWeights = unitsWeights;
    }

    @Override
    public String toString() {
        return specialization;
    }
}
