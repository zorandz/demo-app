package com.zoran.demo.services;

// import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

// import com.recipeapp.exception.domain.*;
import org.springframework.web.multipart.MultipartFile;

import com.zoran.demo.dto.Orders;
import com.zoran.demo.dto.UsersListDTO;
import com.zoran.demo.entities.User;
import com.zoran.demo.exceptions.EmailExistException;
import com.zoran.demo.exceptions.EmailNotFoundException;
import com.zoran.demo.exceptions.NotAnImageFileException;
import com.zoran.demo.exceptions.UserNotFoundException;
import com.zoran.demo.exceptions.UsernameExistException;

public interface UserService {

    User register(String firstName, String lastName, String username, String email, String password) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    List<User> getUsers();
    
    List<UsersListDTO> getTheList();
    
    List<Orders> getOrders(String username);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException, MessagingException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;
    
}
