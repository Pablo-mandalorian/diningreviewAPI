package com.example.diningreview.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.diningreview.Model.User;
import com.example.diningreview.Repository.UserRepository;

@RequestMapping("/users")
@RestController
public class userController {
    private final UserRepository userRepository;

    userController(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //User entity-related scenarios
    //Create user using the displayName
    @PostMapping("/createuser")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        validateUser(user);
        User newUser = this.userRepository.save(user);
        return newUser;
    }

    //Update user
    @PutMapping("/updateUser/{displayName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody User user, @PathVariable String displayName){
        
        validateName(displayName);

        Optional<User> userToUpdateOptional = this.userRepository.findByDisplayName(displayName);
        if(!userToUpdateOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User existingUser = userToUpdateOptional.get();
        copyUserInfo(user, existingUser);
        this.userRepository.save(existingUser);
    }

    //Copy info from one user to another
    private void copyUserInfo(User updatedUser, User existingUser){
        if(ObjectUtils.isEmpty(updatedUser.getCity())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(ObjectUtils.isEmpty(updatedUser.getCity())){
            existingUser.setCity(updatedUser.getCity());
        }
        if(ObjectUtils.isEmpty(updatedUser.getState())){
            existingUser.setState(updatedUser.getState());
        }
        if(ObjectUtils.isEmpty(updatedUser.getZipCode())){
            existingUser.setZipCode(updatedUser.getZipCode());
        }
        if(ObjectUtils.isEmpty(updatedUser.getPeanutAllergies())){
            existingUser.setPeanutAllergies(updatedUser.getPeanutAllergies());
        }
        if(ObjectUtils.isEmpty(updatedUser.getEggAllergies())){
            existingUser.setEggAllergies(updatedUser.getEggAllergies());
        }
        if(ObjectUtils.isEmpty(updatedUser.getDairyAllergies())){
            existingUser.setDairyAllergies(updatedUser.getDairyAllergies());
        }
    }
 
    //Get User by Display Name

     @GetMapping("/{displayName}")
     public User getUserById(@PathVariable String displayName){
        validateName(displayName);
        Optional<User> OptionalUser = this.userRepository.findByDisplayName(displayName);
        if(!OptionalUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User userToFind = OptionalUser.get();
        userToFind.setId(null);
        return userToFind;
     }


    //Validates if the user already exist
    private void validateUser(User user){
        validateName(user.getDisplayName());
        
        Optional<User> optionalUser = this.userRepository.findByDisplayName(user.getDisplayName());
        if(optionalUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    //Validates if the displayName is empty or not
    private void validateName(String displayName){
        if(ObjectUtils.isEmpty(displayName)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
      
}
