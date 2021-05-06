package io.glide.boot.api.impl;

import io.glide.boot.api.UserApi;
import io.glide.boot.api.dto.UserDto;
import io.glide.boot.api.dto.UserRegistrationDto;
import io.glide.boot.domain.User;
import io.glide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserApiImpl implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    public UserApiImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto register(final UserRegistrationDto userRegistrationDto) {
        User userCreated = userService.registerUser(userRegistrationDto);

        return userService.convertUserEntityToDto(userCreated);
    }

    @Override
    public Mono<UserDto> findUserById(long id) {
        return userService.getById(id);
    }

}
