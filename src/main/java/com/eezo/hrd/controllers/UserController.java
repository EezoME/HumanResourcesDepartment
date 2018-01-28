package com.eezo.hrd.controllers;

import com.eezo.hrd.entities.User;
import com.eezo.hrd.facades.UserFacade;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by eezo.
 */
@Named
@SessionScoped
public class UserController implements Serializable {
    @EJB
    private UserFacade userFacade;
    private User current = new User();
    private boolean entered;

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public User getCurrent() {
        if (current.getLogin().length() > 0) current = userFacade.find(current.getId());
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public void updateCurrent() {
        if (current.getId() > 0) userFacade.edit(current);
    }
}
