package uz.online.springsecurityfour.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.online.springsecurityfour.domein.User;
import uz.online.springsecurityfour.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity save(@RequestBody User user) {
        if (!checkPasswordLength(user.getPassword())) {
            return new ResponseEntity("parol uzinligi 4 tadan kam", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserName(user.getUserName())) {
            return new ResponseEntity("bu user oldin royhatdan otgan", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.create(user));
    }

    private Boolean checkPasswordLength(String password) {
        return password.length() >= 4;

    }
}
