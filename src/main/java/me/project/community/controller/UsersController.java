package me.project.community.controller;

import me.project.community.dto.UserRequestDto;
import me.project.community.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UsersService usersService;

    UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //Create
    @PostMapping("/")
    public void createUser(@RequestBody UserRequestDto userRequestDto) {
        usersService.createUser(userRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity login(HttpServletRequest request, @RequestBody UserRequestDto userRequestDto) {
        return usersService.login(request, userRequestDto);
    }

    @GetMapping("session")
    public String getSession() {
        return usersService.getSession();
    }
}
