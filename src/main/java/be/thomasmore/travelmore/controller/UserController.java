package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.validation.constraints.Null;

@ManagedBean
@SessionScoped
public class UserController {

    private User newUser = new User();
    private User gebruikteUser = new User();

    @Inject
    private UserService userService;

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public List<User> getUsers(){
        return this.userService.findAllUsers();
    }

    public void submit(){
        this.userService.insert(newUser);
    }

    public String submitRegister(){
        this.userService.insert(newUser);

        return "registerBedankt";
    }

    public User getGebruikteUser(){
        return gebruikteUser;
    }

    public String login(){
        User login = this.userService.compareLogin(gebruikteUser);

        if(login != null){

            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", login);

            return "loginBedankt";
        }else{

        }
        return "index";
    }

    public String logout(){

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String navigateToLogin(){
        return "login";
    }
}
