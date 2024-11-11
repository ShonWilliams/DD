package org.example.controllers;

import org.example.models.Users;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //https://127.0.0.1:9000/api/users/all
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //https://127.0.0.1:9000/api/users/1
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> users = userService.getUserById(id);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //https://127.0.0.1:9000/api/users/signup
    @CrossOrigin(origins = "*")
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Users user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.status(200).body("Signup successful");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error Signing Up");
        }
    }

    //https://127.0.0.1:9000/api/users/login
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        Optional<Users> userFound = userService.getUserByEmail(user.getEmail());

        if (!userFound.isPresent()) {
            return ResponseEntity.status(401).body("Error Login");  // User not found
        }
        String storedHashedPassword = userFound.get().getPassword();
        if (BCrypt.checkpw(user.getPassword(), storedHashedPassword)) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Error Login");
    }

}
