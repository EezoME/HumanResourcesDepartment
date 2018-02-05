package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.Test;
import com.eezo.hrd.entities.User;
import com.eezo.hrd.enums.TestType;
import com.eezo.hrd.facades.UserFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class TestController implements Serializable {
    @EJB
    private UserFacade userFacade;
    private List<Test> sysAdminTests;

    public String submitTestForm() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        User user = userFacade.find(Long.parseLong(params.get("test-submit-form:user-id")));
        if (user == null) {
            user = new User();
            user.setLogin(params.get("test-submit-form:name"));
            user.setHowLongWorking(params.get("test-submit-form:how-long-working"));
            user.setDepartmentName(params.get("test-submit-form:department-name"));
            user.setGender(params.get("test-submit-form:gender"));
            user.setAge(Integer.parseInt(params.get("test-submit-form:age")));
            user.setEducation(params.get("test-submit-form:education"));
            user.setSpecialization(params.get("test-submit-form:specialization"));
            userFacade.create(user);
        } else {
            user.setHowLongWorking(params.get("test-submit-form:how-long-working"));
            user.setDepartmentName(params.get("test-submit-form:department-name"));
            user.setGender(params.get("test-submit-form:gender"));
            user.setAge(Integer.parseInt(params.get("test-submit-form:age")));
            user.setEducation(params.get("test-submit-form:education"));
            user.setSpecialization(params.get("test-submit-form:specialization"));
            userFacade.edit(user);
        }

        String test = params.get("test-submit-form:specialization");
        if (test.equals("web-designer")) {
            return "web-design-tests.xhtml?faces-redirect=true";
        } else if (test.equals("sys-admin")) {
            return "sys-admin-tests?faces-redirect=true";
        }
        return "";
    }

    public List<Test> getSysAdminTests() {
        return sysAdminTests;
    }
}
