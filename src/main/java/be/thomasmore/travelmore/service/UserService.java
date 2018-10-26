package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {
    @Inject
    private UserRepository userRepository;

    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public User compareLogin(User compareLogin){
        return userRepository.compareLogin(compareLogin);
    }
}
