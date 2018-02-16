package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.User;
import com.eezo.hrd.enums.UserRole;
import com.eezo.hrd.facades.UserFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class IndexController implements Serializable {
    @EJB
    private UserFacade userFacade;
    @Inject
    private UserController userController;
    private User current = new User();
    private String titleSuffix = "Чорноморський суднобудівний завод";

    @PostConstruct
    private void setUpDefaultUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = userFacade.findAll();
        } catch (EJBException exception) {
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
            newUser.setLogin("eezo");
            newUser.setPassword("++++");
            newUser.setUserRole(UserRole.DEVELOPER);
            userFacade.create(newUser);
            users.add(newUser);
            newUser = new User();
            newUser.setLogin("admin");
            newUser.setPassword("admin");
            newUser.setUserRole(UserRole.ADMIN);
            userFacade.create(newUser);
            users.add(newUser);
        }
    }

    public static void showMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }

    public String login() {
        List<User> users = this.userFacade.findAll();

        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(current.getLogin())) {
                if (user.getPassword().equals(current.getPassword())) {
                    current = user;
                    current.setLastVisit(new Date());
                    userController.setCurrent(current);
                    userController.setEntered(true);
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    session.setAttribute("current", this.current);
                    return "index.xhtml?faces-redirect=true";
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Невірно вказаний логін/пароль."));
        return "";
    }

    public String loginAsGuest() {
        User guest = new User();
        guest.setLogin("guest" + guest.getId());
        guest.setPassword("guest");
        guest.setUserRole(UserRole.GUEST);
        userFacade.create(guest);
        current = guest;
        current.setLastVisit(new Date());
        userController.setCurrent(current);
        userController.setEntered(true);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("current", current);
        return "index.xhtml?faces-redirect=true";
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
        return "login.xhtml?faces-redirect=true";
    }

    public String getTitleSuffix() {
        return titleSuffix;
    }

    public void setTitleSuffix(String titleSuffix) {
        this.titleSuffix = titleSuffix;
    }
}
