package com.idk.api;

import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.user.domain.Role;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;


public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        User user = User
                .builder()
                .id(1L)
                .age(20)
                .name("테스터")
                .role(Role.USER)
                .email("idontknow@idontknow.com")
                .districtCode(DistrictCode.builder().id(1).name("강남구").build())
                .gender("F")
                .build();

        CustomUserDetails customUserDetails = CustomUserDetails.create(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        context.setAuthentication(token);
        return context;
    }
}