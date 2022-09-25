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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final DistrictCodeRepository districtCodeRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    @Value("${FROM_ADDRESS}")
    private static String fromAddress;

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
        User findUser = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        if(!passwordEncoder.matches(request.getPassword(), findUser.getPassword())) throw new InvalidPasswordException();
        return UserResponse.Login.build(findUser, tokenProvider.generateAccessToken(findUser));
    }

    @Transactional
    public boolean resetPassword(UserRequest.RePassword request){
        User findUser = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        String newPassword = UUID.randomUUID().toString().replace("-", "");
        newPassword = newPassword.substring(0, 10);
        findUser.updatePassword(passwordEncoder.encode(newPassword));
        userRepository.save(findUser);
        sendMain(findUser.getEmail(), newPassword);
        return true;
    }

    public void sendMain(String email, String password){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(fromAddress);
        message.setSubject("[아~ 모르겠다] 임시 비밀번호 재발급 안내");
        message.setText("안녕하세요. '아~ 모르겠다' 임시비밀번호 안내 관련 이메일 입니다.\n"+" 임시 비밀번호는 " + password + "입니다.");
        mailSender.send(message);
    }
}
