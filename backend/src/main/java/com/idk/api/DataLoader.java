package com.idk.api;

import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.districtcode.domain.repository.DistrictCodeRepository;
import com.idk.api.menu.domain.entity.Menu;
import com.idk.api.menu.domain.repository.MenuRepository;
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
    private final MenuRepository menuRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
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
        if (menuRepository.findAll().isEmpty()){
            List<Menu> menuList = new ArrayList<>();
            String[] menuNames = new String[] {"한식","분식","카페/디저트","돈까스/일식","회","치킨","피자","아시안/양식",
                    "중식","족발/보쌈","야식","찜탕","도시락","패스트푸드"};

            for(String i : menuNames){
                Menu menu = Menu.builder().name(i).build();
                menuList.add(menu);
            }
            menuRepository.saveAll(menuList);
        }
        if (userRepository.findAll().isEmpty()) {
            User admin = User.builder()
                    .name("관리자")
                    .email("idontknow@idontknow.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.ADMIN)
                    .age(20)
                    .gender("F")
                    .districtCode(DistrictCode.builder().id(1).name("강남구").build())
                    .build();
            userRepository.save(admin);
            User user = User.builder()
                    .name("치킨러버")
                    .email("chikenLover@idontknow.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.USER)
                    .age(20)
                    .gender("M")
                    .districtCode(DistrictCode.builder().id(1).name("강남구").build())
                    .build();
            userRepository.save(user);
        }

    }
}
