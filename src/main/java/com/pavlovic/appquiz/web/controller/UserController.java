package com.pavlovic.appquiz.web.controller;

import com.pavlovic.appquiz.model.PaginationUsers;
import com.pavlovic.appquiz.model.Role;
import com.pavlovic.appquiz.model.User;
import com.pavlovic.appquiz.support.UserRegistrationDtoToUser;
import com.pavlovic.appquiz.security.TokenUtils;
import com.pavlovic.appquiz.service.UserService;
import com.pavlovic.appquiz.support.UserToUserDto;
import com.pavlovic.appquiz.web.dto.AuthUserDto;
import com.pavlovic.appquiz.web.dto.UserDto;
import com.pavlovic.appquiz.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRegistrationDtoToUser toUser;

    @Autowired
    private UserToUserDto toUserDto;

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody AuthUserDto dto) {
        // Perform the authentication
        try {
            // Reload user details so we can generate token
            String token = autenticate(dto.getUsername(), dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity create(@RequestBody @Validated UserRegistrationDto dto){

        if(!dto.getPassword().equals(dto.getRepeatedPassword())) {
            return ResponseEntity.badRequest().build();
        }

        if ( userService.findbyUsername(dto.getUsername()).isPresent() ){
            return ResponseEntity.badRequest().body("Invalid username.");
        }

        String password = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        dto.setPassword(encodedPassword);

        User user = toUser.convert(dto);

        userService.save(user);

        String token = autenticate(user.getUsername(),dto.getEmail(), password);

        return new ResponseEntity(token, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(path = "/isAdmin", method = RequestMethod.GET)
    public boolean isAdmin() {
        // Get user from authentication token.
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        boolean isAdmin = false;
        if(username != null){
            user = userService.findbyUsername(username).get();
            isAdmin = user.getRole().equals(Role.ADMIN);
        }
        return isAdmin;

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(path = "/allUsers", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {
        Page<User> users = userService.getAll(pageNo);
        List<UserDto> usersDtos = toUserDto.convert(users.getContent());
        int totalPages =  users.getTotalPages();
        PaginationUsers paginationUsers = new PaginationUsers();
        paginationUsers.setUsers(usersDtos);
        paginationUsers.setTotalPages(totalPages);
        return ResponseEntity.ok(paginationUsers);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(path = "/getUser", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam Long userId) {
        Optional<User> user = userService.findOneById(userId);
        UserDto userDto = toUserDto.convert(user.get());
        return ResponseEntity.ok(userDto);
    }



    private String autenticate(String username, String email,  String password) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        UsernamePasswordAuthenticationToken authenticationToken = null;
        if( username != null ) {
            userDetails = userDetailsService.loadUserByUsername(username);
            authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        } else if (email != null) {
            User user = userService.findByEmail(email).get();
            userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), password);
        }

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenUtils.generateToken(userDetails);
    }
}
