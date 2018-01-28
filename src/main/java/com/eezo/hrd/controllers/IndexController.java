package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.User;
import com.eezo.hrd.enums.UserRole;
import com.eezo.hrd.facades.UserFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class IndexController implements Serializable {
    @EJB
    private UserFacade userFacade;
    @Inject
    private UserController userController;
    private User current = new User();
    private String redirectBack;
    private String titleSuffix = "Черноморский судостроительный завод";

    @PostConstruct
    private void setUpDefaultUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = userFacade.findAll();
        } catch (EJBException exception) {
//            logFacade.create(new Log(exception));
        }

        boolean found = false;
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getLogin().equalsIgnoreCase("eezo")) {
                    found = true;
                }
            }
        }
        if (!found) {
            User newUser = new User();
            newUser.setPassword("++++");
            newUser.setLogin("eezo");
            newUser.setUserRole(UserRole.DEVELOPER);
            userFacade.create(newUser);
            users.add(newUser);
            newUser = new User();
            newUser.setLogin("admin");
            newUser.setPassword("admin");
            newUser.setUserRole(UserRole.ADMIN);
            userFacade.create(newUser);
            users.add(newUser);
//            logFacade.create(new Log("Приложение запущено"));
        }
    }

    public static void showMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }

    public String login() {
        List<User> users = userFacade.findAll();

        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(current.getLogin())) {
                if (user.getPassword().equals(current.getPassword())) {
                    current = user;
                    current.setLastVisit(new Date());
//                    logFacade.create(new Log(current.getLogin() + " entered from " + getIpRequest()));
                    userController.setCurrent(current);
                    userController.setEntered(true);
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    session.setAttribute("current", current);
                    System.out.println(redirectBack);
                    if (redirectBack == null || redirectBack.isEmpty()) {
                        return "home.xhtml?faces-redirect=true";
                    } else {
                        String formatted = redirectBack + "?faces-redirect=true";
                        redirectBack = null;
                        return formatted;
                    }
                }
            }
        }
//        logFacade.create(new Log("Wrong authorization probe of " + current.getLogin() + " from " + getIpRequest(), LoggerLevel.WARN));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Не верный логин/пароль"));
        return "";
    }

    private String getIpRequest() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        current = new User();
        userController.setEntered(false);
        return "index.xhtml?faces-redirect=true";
    }

    public void setRedirectBack(String redirectBack) {
        this.redirectBack = redirectBack;
    }

    public String getTitleSuffix() {
        return titleSuffix;
    }

    public void setTitleSuffix(String titleSuffix) {
        this.titleSuffix = titleSuffix;
    }
}
