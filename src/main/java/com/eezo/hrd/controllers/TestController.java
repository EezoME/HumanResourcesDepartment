package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.User;
import com.eezo.hrd.facades.UserFacade;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Named
@SessionScoped
public class TestController implements Serializable {
    @EJB
    private UserFacade userFacade;
    @Inject
    private IndexController indexController;

    public String submitTestForm() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        User user = ((User) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("current"));
        if (user == null) {
            user = new User();
            user.setLogin(params.get("test-submit-form:name"));
            user.setHowLongWorking(params.get("test-submit-form:how-long-working"));
            user.setDepartmentName(params.get("test-submit-form:department-name"));
            user.setGender(params.get("test-submit-form:gender"));
            user.setAge(Integer.parseInt(params.get("test-submit-form:age")));
            user.setEducation(params.get("test-submit-form:education"));
            user.setSpecialization(params.get("test-submit-form:specialization"));
            user.setPassedTests(new LinkedHashMap<>());
            userFacade.create(user);
        } else {
            user.setHowLongWorking(params.get("test-submit-form:how-long-working"));
            user.setDepartmentName(params.get("test-submit-form:department-name"));
            user.setGender(params.get("test-submit-form:gender"));
            user.setAge(Integer.parseInt(params.get("test-submit-form:age")));
            user.setEducation(params.get("test-submit-form:education"));
            user.setSpecialization(params.get("test-submit-form:specialization"));
            if (user.getPassedTests() == null) {
                user.setPassedTests(new LinkedHashMap<>());
            }
            userFacade.edit(user);
        }

        String test = params.get("test-submit-form:specialization");
        if (test.equals("web")) {
            return "web-design-tests.xhtml?faces-redirect=true";
        } else if (test.equals("sys")) {
            return "sys-admin-tests?faces-redirect=true";
        }
        return "";
    }

    public IndexController getIndexController() {
        return indexController;
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }
}
