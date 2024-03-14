package com.markj.onlinerecipes.controller;


import com.markj.onlinerecipes.config.JwtProvider;
import com.markj.onlinerecipes.model.User;
import com.markj.onlinerecipes.repository.UserRepository;
import com.markj.onlinerecipes.request.LoginRequest;
import com.markj.onlinerecipes.response.AuthResponse;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.markj.onlinerecipes.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customerUserDetails;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{

        String email=user.getEmail();
        String password=user.getPassword();
        String fullName=user.getFullname();

        User isExistEmail = userRepository.findByEmail(email);
        if(isExistEmail!=null){
            throw new Exception("Email is already used with another account");
        }

        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullname(fullName);

        User savedUser=userRepository.save(createdUser);

        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(email,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);

        AuthResponse res=new AuthResponse();

        res.setJwt(token);
        res.setMessage("signup success");

        return res;
    }


    @PostMapping("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest){
        String username= loginRequest.getEmail();
        String password= loginRequest.getPassword();

        Authentication authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);

        AuthResponse res=new AuthResponse();

        res.setJwt(token);
        res.setMessage("signin success");

        return res;
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails=customerUserDetails.loadUserByUsername(username);

        if(userDetails==null) {
            throw new BadCredentialsException("user not found");

        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
