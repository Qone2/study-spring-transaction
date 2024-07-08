package com.example.spring_transaction.user;

import org.springframework.web.bind.annotation.*;

@RestController()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void createUser() {
        userService.saveAndNotCommit();
    }

    @PatchMapping("/user/{id}")
    public void updateUser(@RequestParam String name, @PathVariable Long id) {
        User user = new User();
        user.setName(name);
        user.setId(id);
        userService.changeAndNotCommit(user);
    }
}
