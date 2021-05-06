package io.glide.service.impl;

import io.glide.boot.api.dto.AddressDto;
import io.glide.boot.api.dto.UserDto;
import io.glide.boot.api.dto.UserInfos;
import io.glide.boot.api.dto.UserRegistrationDto;
import io.glide.boot.domain.Address;
import io.glide.boot.domain.User;
import io.glide.boot.repository.AddressRepository;
import io.glide.boot.repository.UserRepository;
import io.glide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public User registerUser(final UserRegistrationDto userRegistrationDto) {
        User userCreate = new User();
        userCreate.setFirstName(userRegistrationDto.getFirstName());
        userCreate.setLastName(userRegistrationDto.getLastName());

        AddressDto principalAddressDto = userRegistrationDto.getPrincipalAddress();
        AddressDto secondaryDto = userRegistrationDto.getSecondaryAddress();

        Set<Address> setAddress = new HashSet<>();
        setAddress.add(convertDtoToEntity(principalAddressDto));
        if (secondaryDto != null) {
            setAddress.add(convertDtoToEntity(secondaryDto));
        }

        userCreate.setAddresses(setAddress);
        userCreate.setLastName(userRegistrationDto.getLastName());


        List<Address> addressEntitys = setAddress.stream().collect(Collectors.toList());

        for (Address ad : addressEntitys) {
            addressRepository.save(ad);

        }

        userRepository.save(userCreate);

        return userCreate;
    }

    private Address convertDtoToEntity(AddressDto dto) {
        Address principal = new Address();
        principal.setCity(dto.getCity());
        principal.setCountry(dto.getCountry());
        principal.setPostalCode(dto.getPostalCode());
        principal.setStreetName(dto.getStreetName());
        principal.setStreetNumber(dto.getStreetNumber());
        return principal;
    }

    private AddressDto convertAddressEnitityToDto(Address dto) {
        AddressDto principal = new AddressDto();
        principal.setCity(dto.getCity());
        principal.setCountry(dto.getCountry());
        principal.setPostalCode(dto.getPostalCode());
        principal.setStreetName(dto.getStreetName());
        principal.setStreetNumber(dto.getStreetNumber());
        return principal;
    }

    @Override
    public UserDto convertUserEntityToDto(User enity) {
        UserDto dto = new UserDto();
        UserInfos userInfo = new UserInfos();

        List<AddressDto> addressDtos = new ArrayList<>();

        userInfo.setAdresses(addressDtos);
        userInfo.setFirstName(enity.getFirstName());
        userInfo.setLastName(enity.getLastName());
        dto.setUserInfos(userInfo);
        dto.setId(enity.getId());

        return dto;
    }

    @Override
    public Mono<UserDto> getById(final long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        return Mono.just(convertUserEntityToDto(user));
    }
}
