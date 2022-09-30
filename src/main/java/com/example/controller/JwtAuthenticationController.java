package com.example.controller;

import com.example.configs.JwtTokenUtil;

import com.example.exception.ResponseHandler;
import com.example.model.JwtRequest;
import com.example.model.JwtResponse;
import com.example.model.UserDto;
import com.example.repo.UserRepository;
import com.example.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
       try {
           authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

           final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());


           final String token = jwtTokenUtil.generateToken(userDetails);

           //return ResponseEntity.ok(new JwtResponse(token));
           return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, new JwtResponse(token));
       }catch(Exception e){

            //return ResponseEntity.badRequest().body("invalid credentials");
           return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
       }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  ResponseEntity<?> saveUser(@RequestBody  @Valid UserDto user)  {
       UserDto use = userRepo.findByEmail(user.getEmail());
       if(use == null){
           // if(userRepo.findByEmail(user.getMobilenumber()) ==  null) {
                return ResponseEntity.ok(userDetailsService.save(user));
          //  }else return ResponseHandler.generateResponse("Mobile already exists", HttpStatus.BAD_REQUEST, null);
      } else return ResponseHandler.generateResponse("Email already exists", HttpStatus.BAD_REQUEST, null);


    }
    @GetMapping ("/userDetails")
    public Iterable<UserDto> getAllUsers() {
        return  userRepo.findAll();
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
