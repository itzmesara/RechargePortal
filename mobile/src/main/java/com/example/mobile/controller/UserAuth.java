package com.example.mobile.controller;

import java.util.List;
import java.util.Optional;
// import java.util.logging.Handler;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobile.model.User;
import com.example.mobile.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/users")
public class UserAuth {

    UserService userService;

    public UserAuth(UserService userService) {
        this.userService = userService;
    }

   

    // 201 Created – When the user is successfully created.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addUser(@RequestBody User model) {

        try {
            userService.registerUser(model);
            return new ResponseEntity<>("User Added Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // 200 OK – When the user is found.
    // 404 Not Found – When the user is not found.
    @GetMapping("/{id}")
    
    public ResponseEntity<Object> getUser(@PathVariable long id) {
        try {

            Optional<User> userExists = userService.getUser(id);
            return new ResponseEntity<>(userExists, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 200 OK – When the users are retrieved successfully.
    @GetMapping("/paginate")
    
    public ResponseEntity<Page<User>> Paging(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(userService.getPages(page, size), HttpStatus.OK);
    }

    // 200 OK – When the users are retrieved successfully.
    @GetMapping()
   
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    // 200 OK – When the user is updated successfully.
    // 404 Not Found – When the user is not found.
    @PutMapping("/{id}")
    
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
        }
    }

    // 204 No Content – When the user is deleted successfully.
    // 404 Not Found – When the user is not found.
    @DeleteMapping("/{id}")
    
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted Successfully", HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
