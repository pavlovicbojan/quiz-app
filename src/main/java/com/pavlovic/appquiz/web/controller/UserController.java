package com.pavlovic.appquiz.web.controller;

import com.pavlovic.appquiz.support.UserToUserDto;
import com.pavlovic.appquiz.security.TokenUtils;
import com.pavlovic.appquiz.service.UserService;
import com.pavlovic.appquiz.web.dto.AuthUserDto;
import com.pavlovic.appquiz.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@RestController
@RequestMapping(value = "/api/users" , produces = MediaType.APPLICATION_JSON_VALUE)
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
    private UserToUserDto userToUserDto;

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody AuthUserDto dto) {
        // Perform the authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            // Reload user details so we can generate token
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public void create(@RequestBody @Validated UserRegistrationDto dto){

        if(!dto.getPassword().equals(dto.getRepeatedPassword())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // KorisnikRegistracijaDTO nasleđuje KorisnikDTO, pa možemo koristiti konverter za njega
        // ostaje da dodatno konvertujemo polje kojeg u njemu nema - password
//        UserDto korisnik = userToUserDto.convert(dto);

        // dodatak za zadatak 1
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        System.out.println(dto.getPassword());
        System.out.println(encodedPassword);
//        korisnik.setLozinka(encodedPassword);

    }


}
