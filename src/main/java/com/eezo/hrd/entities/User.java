package com.eezo.hrd.entities;

import com.eezo.hrd.enums.UserRole;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Entity(name = "J_User")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "j_login")
    private String login = "";
    @Column(name = "j_password")
    private String password = "";
    private Date lastVisit;
    private UserRole userRole = UserRole.GUEST;
    private String howLongWorking;
    private String departmentName;
    private String gender;
    private int age;
    private String education;
    private String specialization;
    @Lob
    private Map<String, Integer> passedTests;

    public double getFinalWebTestResult(int web1Size, int web2Size, int web3Size){
        double first = passedTests.get("web1") / web1Size;
        double second = passedTests.get("web2") / web2Size;
        double third = passedTests.get("web3") / web3Size;
        return first * 0.7 + second * 0.1 + third * 0.2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getHowLongWorking() {
        return howLongWorking;
    }

    public void setHowLongWorking(String howLongWorking) {
        this.howLongWorking = howLongWorking;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eezo.hrd.entities.User[ id=" + id + " ]";
    }

    public Map<String, Integer> getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(Map<String, Integer> passedTests) {
        this.passedTests = passedTests;
    }
}
