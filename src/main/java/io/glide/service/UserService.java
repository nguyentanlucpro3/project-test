package io.glide.service;

import io.glide.boot.api.dto.UserDto;
import io.glide.boot.api.dto.UserRegistrationDto;
import io.glide.boot.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {

    /**
     * Register a new user.
     *
     * @param userRegistrationDto dto for user registration.
     * @return user entity.
     */
    User registerUser(@NotNull @Valid UserRegistrationDto userRegistrationDto);

    /**
     * Get user by its id.
     *
     * @param id user id.
     * @return user entity.
     */
    Mono<UserDto> getById(long id);


    UserDto convertUserEntityToDto(User enity);
}
