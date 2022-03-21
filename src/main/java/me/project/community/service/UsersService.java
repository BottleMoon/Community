package me.project.community.service;


import me.project.community.domain.User;
import me.project.community.dto.UserRequestDto;
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
        } else {
            User user = User.builder()
                    .id(userRequestDto.getId())
                    .password(encoded)
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

                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        }
        return new ApiExceptionAdvice().exceptionHandler(new ApiException(ExceptionEnum.LOGIN_FAIL_EXCEPTION));

    }

    public String getSession() {
        return userInfo.getId();
    }
}
