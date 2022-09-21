package com.idk.api.user.service;

import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.districtcode.domain.repository.DistrictCodeRepository;
import com.idk.api.districtcode.exception.DistrictCodeNotFoundException;
import com.idk.api.user.domain.Role;
import com.idk.api.user.dto.UserRequest;
import com.idk.api.user.dto.UserResponse;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.domain.repository.UserRepository;
import com.idk.api.user.exception.InvalidPasswordException;
import com.idk.api.user.exception.UserNotFoundException;
import com.idk.api.user.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.zip.DataFormatException;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final DistrictCodeRepository districtCodeRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse.OnlyId join(UserRequest.Join request){
        DistrictCode districtCode = districtCodeRepository.findById(request.getDistrictId()).orElseThrow(DistrictCodeNotFoundException::new);

        User newUser = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .districtCode(districtCode)
                .gender(request.getGender())
                .age(request.getAge())
                .role(Role.USER)
                .build();
        userRepository.save(newUser);
        return UserResponse.OnlyId.build(newUser);
    }

    public boolean checkEmailDuplication(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean checkNameDuplication(String name){
        return userRepository.existsByName(name);
    }

    public UserResponse.Login login(UserRequest.Login request){
        User findUser = userRepository.findByEmail(request.getEmail());
        if(findUser == null)    throw new UserNotFoundException();
        if(!passwordEncoder.matches(request.getPassword(), findUser.getPassword())) throw new InvalidPasswordException();
        return UserResponse.Login.build(findUser, tokenProvider.generateAccessToken(findUser));
    }
}
