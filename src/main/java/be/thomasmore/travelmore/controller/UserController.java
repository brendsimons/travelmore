package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean
@ViewScoped
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
        this.userService.compareLogin(gebruikteUser);

        return "registerBedankt";
    }

//    public void login(){
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        if(gebruikteUser.getEmail().equals("email") && gebruikteUser.getPassword().equals("password")){
//            context.getExternalContext().getSessionMap().put("email", gebruikteUser.getEmail());
//            try {
//                context.getExternalContext().redirect("registerBedankt.xhtml");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else  {
//            //Send an error message on Login Failure
//            context.addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));
//
//        }
//    }

}
