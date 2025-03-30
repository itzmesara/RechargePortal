package com.example.mobile.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.mobile.model.History;
import com.example.mobile.model.User;
import com.example.mobile.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(User user) {
        for(History history: user.getHistories())
        {
            history.setUser(user);
        }
        userRepo.save(user);
    }

    public Optional<User> getUser(long id) {
        return userRepo.findByUser(id);
    }

    public List<User> getUsers() {
        return userRepo.findUsers();
    }

    public User updateUser(long id, User user) {
        Optional<User> userExists = userRepo.findById(id);
        if (userExists.isPresent()) {
            User userUpdate = userExists.get();
            if (user.getUsername() != null) {
                userUpdate.setUsername(user.getUsername());
            }
            if (user.getAddress() != null) {

                userUpdate.setAddress(user.getAddress());
            }
            if (user.getPassword() != null) {

                userUpdate.setPassword(user.getPassword());
            }
            if (user.getEmail() != null) {

                userUpdate.setEmail(user.getEmail());
            }
            if (user.getPhoneNumber() != 0) {

                userUpdate.setPhoneNumber(user.getPhoneNumber());
            }
            if (user.getFirstname() != null) {

                userUpdate.setFirstname(user.getFirstname());
            }
            if (user.getLastname() != null) {

                userUpdate.setLastname(user.getLastname());
            }
            return userRepo.save(userUpdate);

        } else {
            return null;
        }
    }

    public User deleteUser(long userId) {
        Optional<User> userExists = userRepo.findById(userId);
        if (userExists.isPresent()) {
            userRepo.deleteById(userId);
            return null;
        } else {
            return null;
        }
    }

    


    public Page<User> getPages(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepo.findAll(pageable);
    }
}
