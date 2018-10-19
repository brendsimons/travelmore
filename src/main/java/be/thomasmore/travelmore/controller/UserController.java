package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserController {

    private User newUser = new User();

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

    public String submit(){
        this.userService.insert(newUser);

        return "registerBedankt";
    }
}
