package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.example.models.Users;
import org.example.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    @Autowired
    private UserRepo userRepo;

    /**
     *
     * @param user
     * @return user object once a user signs up in the application
     * the user is saved in the database
     */
    public Users saveUser(Users user) {
        String pwd = user.getPassword();
        user.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt()));
        Users savedUser = userRepo.save(user);
        logger.info("User saved: {}", savedUser);
        return savedUser;
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
