package me.project.community.service;


import me.project.community.domain.User;
import me.project.community.dto.UserRequestDto;
import me.project.community.dto.UserResponseDto;
import me.project.community.error.ApiException;
import me.project.community.error.ApiExceptionAdvice;
import me.project.community.error.ExceptionEnum;
import me.project.community.repository.UsersRepository;
import me.project.community.session.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfo userInfo;

    UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, UserInfo userInfo) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInfo = userInfo;
    }

    public ResponseEntity createUser(UserRequestDto userRequestDto) {
        String encoded = passwordEncoder.encode(userRequestDto.getPassword());
        if (usersRepository.findById(userRequestDto.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (userRequestDto.getId().length() < 4 || userRequestDto.getPassword().length() < 4) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            User user = User.builder()
                    .id(userRequestDto.getId())
                    .password(encoded)
                    .createdTime(LocalDateTime.now())
                    .build();
            usersRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    public ResponseEntity login(HttpServletRequest request, UserRequestDto userRequestDto) {
        Optional<User> user = usersRepository.findById(userRequestDto.getId());
        if (user.isPresent()) {
            if (passwordEncoder.matches(userRequestDto.getPassword(), user.get().getPassword())) {
                userInfo.setId(userRequestDto.getId());
                System.out.println("present");
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        }
        System.out.println("not present");
        return new ApiExceptionAdvice().exceptionHandler(new ApiException(ExceptionEnum.LOGIN_FAIL_EXCEPTION));
    }

    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public String getSession() {
        return userInfo.getId();
    }

    public UserResponseDto getUser() {
        System.out.println(userInfo.getId());
        UserResponseDto responseDto = entityToDto(usersRepository.findById(userInfo.getId()).get());
        return responseDto;
    }

    public UserResponseDto entityToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return userResponseDto;
    }
}
