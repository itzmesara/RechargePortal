package com.example.mobile.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mobile.model.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT a FROM User a")
    List<User> findUsers();

    @Query("SELECT a FROM User a WHERE a.id= :id ")
    Optional<User> findByUser(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO User ( username, password, email, firstname, lastname, phone_number, address) VALUES ( :username, :password, :email, :firstname, :lastname, :phone_number, :address)", nativeQuery = true)
    void addUser(@Param("username") String username, @Param("password") String password, @Param("email") String email,
            @Param("firstname") String firstname, @Param("lastname") String lastname,
            @Param("phone_number") long phoneNumber, @Param("address") String address);
}
