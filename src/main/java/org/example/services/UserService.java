package org.example.services;

import org.example.models.Users;
import org.example.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    /**
     *
     * @param user
     * @return user object once a user signs up in the application
     * the user is saved in the database
     */
    public Users saveUser(Users user) {
//        String pwd = user.getPassword();
//        user.setPassword(BCrypt);
        return userRepo.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<Users> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
