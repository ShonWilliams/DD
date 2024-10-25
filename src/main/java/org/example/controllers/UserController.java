package org.example.controllers;

import org.example.models.Users;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //https://127.0.0.1:9000/api/users/all
    @GetMapping("/all")
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //https://127.0.0.1:9000/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> users = userService.getUserById(id);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //https://127.0.0.1:9000/api/users/signup
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        Optional<Users> userFound = userService.getUserByEmail(user.getEmail());
        if (userFound.isPresent() && userFound.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Error Login");
    }

}
