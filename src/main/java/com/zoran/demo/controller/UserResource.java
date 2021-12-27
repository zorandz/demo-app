package com.zoran.demo.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zoran.demo.constant.FileConstant;
import com.zoran.demo.constant.SecurityConstant;
import com.zoran.demo.domain.HttpResponse;
import com.zoran.demo.domain.UserPrincipal;
import com.zoran.demo.dto.Orders;
import com.zoran.demo.dto.UsersListDTO;
import com.zoran.demo.entities.User;
import com.zoran.demo.exceptions.EmailExistException;
import com.zoran.demo.exceptions.EmailNotFoundException;
import com.zoran.demo.exceptions.ExceptionHandling;
import com.zoran.demo.exceptions.NotAnImageFileException;
import com.zoran.demo.exceptions.UserNotFoundException;
import com.zoran.demo.exceptions.UsernameExistException;
import com.zoran.demo.services.UserService;
import com.zoran.demo.utility.JWTTokenProvider;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/user")
public class UserResource extends ExceptionHandling {
	  public static final String EMAIL_SENT = "An email with a new password was sent to: ";
	    public static final String USER_DELETED_SUCCESSFULLY = "User successfully deleted.";
	    private AuthenticationManager authenticationManager;
        private JWTTokenProvider jwtTokenProvider;
        private UserService userService;
        


	
	  @Autowired
	    public UserResource(AuthenticationManager authenticationManager, UserService userService, JWTTokenProvider jwtTokenProvider) {
	        this.authenticationManager = authenticationManager;
	        this.userService = userService;
	        this.jwtTokenProvider = jwtTokenProvider;
	    }
	  
	   
	@PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
    }
	

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException {
		User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword());
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	@PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String isActive,
                                           @RequestParam("isNotLocked") String isNotLocked,
                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) 
                                        		   throws UserNotFoundException, UsernameExistException, EmailExistException, 
                                        		   IOException, NotAnImageFileException, MessagingException {
        User newUser = userService.addNewUser(firstName, lastName, username,email, role, Boolean.parseBoolean(isNotLocked), 
        										Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
	
	@PostMapping("/update")
    public ResponseEntity<User> update(@RequestParam("currentUsername") String currentUsername,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("username") String username,
                                       @RequestParam("email") String email,
                                       @RequestParam("role") String role,
                                       @RequestParam("isActive") String isActive,
                                       @RequestParam("isNotLocked") String isNotLocked,
                                       @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) 
                                    		   throws UserNotFoundException, UsernameExistException, EmailExistException, 
                                    		   IOException, NotAnImageFileException {
        User updatedUser = userService.updateUser(currentUsername, firstName, lastName, username,email, 
        						role, Boolean.parseBoolean(isNotLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
	
	  @GetMapping("/find/{username}")
	    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
	        User user = userService.findUserByUsername(username);
	        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	  

	

	  @GetMapping("/list")
	    public ResponseEntity<List<UsersListDTO>> getAllUsers() {
		    
		  List<UsersListDTO> users = this.userService.getTheList();

		  return new ResponseEntity<List<UsersListDTO>>(users, HttpStatus.OK);

	}
	  @GetMapping("/resetpassword/{email}")
	    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) 
	    		throws MessagingException, EmailNotFoundException {
	        userService.resetPassword(email);
	        return response(HttpStatus.OK, EMAIL_SENT + email);
	    }
	  
	  private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
	        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
	                message), httpStatus);
	    }
	  
	  @DeleteMapping("/delete/{username}")
	  @PreAuthorize("hasAnyAuthority('user:delete')")
	  public ResponseEntity<HttpResponse> deleteUser(@PathVariable("username") String username) 
	    		throws IOException {
	        userService.deleteUser(username);
	        return response(HttpStatus.OK, USER_DELETED_SUCCESSFULLY);
	  }
	
	
	  @PostMapping("/updateProfileImage")
	    public ResponseEntity<User> updateProfileImage(
	    		@RequestParam("username") String username, 
	    		@RequestParam(value = "profileImage") MultipartFile profileImage) 
	    				throws UserNotFoundException, UsernameExistException, 
	    						EmailExistException, IOException, NotAnImageFileException {
	        User user = userService.updateProfileImage(username, profileImage);
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    }
	  
	   @GetMapping(path = "/image/{username}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE) //{Ako hoću +1, GIF}
	    public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
	        return Files.readAllBytes(Paths.get(FileConstant.USER_FOLDER + username + FileConstant.FORWARD_SLASH + fileName));
	    }
	   
	   @GetMapping(path = "/image/profile/{username}", produces = MediaType.IMAGE_JPEG_VALUE)
	    public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
	        URL url = new URL(FileConstant.TEMP_PROFILE_IMAGE_BASE_URL + username);
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        try (InputStream inputStream = url.openStream()) {
	            int bytesRead;
	            byte[] chunk = new byte[1024];
	            while((bytesRead = inputStream.read(chunk)) > 0) {
	                byteArrayOutputStream.write(chunk, 0, bytesRead);
	            }
	        }
	        return byteArrayOutputStream.toByteArray();
	    }
	   
	@GetMapping(path = "/orders/{username}")
	public ResponseEntity<List<Orders>> getOrders(@PathVariable("username") String username) {
		
		List<Orders> orders = userService.getOrders(username);
		
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	   
	private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
	
	private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }
    
}
