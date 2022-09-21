package com.idk.api;

import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.districtcode.domain.repository.DistrictCodeRepository;
import com.idk.api.districtcode.exception.DistrictCodeNotFoundException;
import com.idk.api.menucode.domain.entity.MenuCode;
import com.idk.api.menucode.domain.repository.MenuCodeRepository;
import com.idk.api.user.domain.Role;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final DistrictCodeRepository districtCodeRepository;
    private final MenuCodeRepository menuCodeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (districtCodeRepository.findAll().isEmpty()){
            List<DistrictCode> districtCodeList = new ArrayList<>();
            String[] districtNames = new String[] {"강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구",
                    "노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"};

            for(String i : districtNames){
                DistrictCode districtCode = DistrictCode.builder().name(i).build();
                districtCodeList.add(districtCode);
            }
            districtCodeRepository.saveAll(districtCodeList);
        }
        if (menuCodeRepository.findAll().isEmpty()){
            List<MenuCode> menuCodeList = new ArrayList<>();
            String[] menuCodeNames = new String[] {"한식","분식","카페/디저트","돈까스/일식","회","치킨","피자","아시안/양식",
                    "중식","족발/보쌈","야식","찜탕","도시락","패스트푸드"};

            for(String i : menuCodeNames){
                MenuCode menu = MenuCode.builder().name(i).build();
                menuCodeList.add(menu);
            }
            menuCodeRepository.saveAll(menuCodeList);
        }
        if (userRepository.findAll().isEmpty()) {
            List<User> userList = new ArrayList<>();
            DistrictCode districtCode = districtCodeRepository.findById(1).orElseThrow(DistrictCodeNotFoundException::new);
            User admin = User.builder()
                    .name("관리자")
                    .email("idontknow@idontknow.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.ADMIN)
                    .age(20)
                    .gender("F")
                    .districtCode(districtCode)
                    .build();
            userList.add(admin);
            User user = User.builder()
                    .name("치킨러버")
                    .email("chickenLover@idontknow.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.USER)
                    .age(20)
                    .gender("M")
                    .districtCode(districtCode)
                    .build();
            userList.add(user);
            userRepository.saveAll(userList);
        }
    }
}
