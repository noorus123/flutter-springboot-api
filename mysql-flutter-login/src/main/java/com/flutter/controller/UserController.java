package com.flutter.controller;

import com.flutter.exception.ResourceNotFoundException;
import com.flutter.model.User;
import com.flutter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userData")
public class UserController {
	@Autowired
    UserRepository userRepository;

	//http://localhost:8080/userData/users
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    //http://localhost:8080/userData/user
    @RequestMapping(value="/user", method = RequestMethod.POST) 
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    //http://localhost:8080/userData/user/4
    @RequestMapping(value="/user/{phone}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "phone") String phone) {
        return userRepository.findById(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User", "phone", phone));
    }

    //http://localhost:8080/userData/user/4
    @RequestMapping(value="/user/{phone}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable(value = "phone") String phone, @RequestBody User userDetails) {
        User user = userRepository.findById(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User", "phone", phone));
        user.setUserName(userDetails.getUserName());        
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }
   
    //http://localhost:8080/userData/user/4
    @RequestMapping(value="/user/{phone}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable(value = "phone") String phone) {
        User user = userRepository.findById(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User", "phone", phone));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}
