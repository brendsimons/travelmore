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
    private String errorMessage = null;

    @Inject
    private UserService userService;

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public String getErrorMessage() {return errorMessage;}

    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}

    public List<User> getUsers(){
        return this.userService.findAllUsers();
    }

    public void submit(){
        this.userService.insert(newUser);
    }

    public String submitRegister(){
        if(!this.userService.compareRegister(newUser)) {
            this.userService.insert(newUser);
            return "registerBedankt";
        }else{
            this.errorMessage = "Het email adres is al in gebruik.";
            return "register";
        }
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
            this.errorMessage = "Foutief wachtwoord of email adres ingegeven.";
            return "login";
        }

    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }


    public boolean isLoggedIn(){
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().containsKey("user");
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

    public String navigateToLogin(){
        return "login";
    }

}
