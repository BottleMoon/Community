package me.project.community.controller;

import me.project.community.dto.UserRequestDto;
import me.project.community.dto.UserResponseDto;
import me.project.community.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/mypage")
    public UserResponseDto getUser() {
        return usersService.getUser();
    }

    @GetMapping("session")
    public String getSession(HttpServletResponse response) {
        return usersService.getSession();
    }

    @PostMapping("/login")
    public ResponseEntity login(HttpServletRequest request, @RequestBody UserRequestDto userRequestDto) {
        return usersService.login(request, userRequestDto);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        return usersService.logout(request);
    }

}
