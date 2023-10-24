package uz.online.springsecurityfour.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.online.springsecurityfour.domein.LoginYM;
import uz.online.springsecurityfour.domein.User;
import uz.online.springsecurityfour.repository.UserRepository;
import uz.online.springsecurityfour.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserJwtController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public UserJwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginYM loginYM) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginYM.getUserName(), loginYM.getPassword()));
        User user = userRepository.findByUserName(loginYM.getUserName());
        if (user == null) {
            throw new UsernameNotFoundException("bu user mavjud emaas");
        }
        String token = jwtTokenProvider.createToken(user.getUserName(), user.getRoles());
        Map<Object,Object> map = new HashMap<>();
        map.put("userName",user.getUserName());
        map.put("token", token);
        return ResponseEntity.ok(map);
    }

}
