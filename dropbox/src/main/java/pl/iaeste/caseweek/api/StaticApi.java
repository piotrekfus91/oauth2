package pl.iaeste.caseweek.api;

import org.springframework.stereotype.Component;
import pl.iaeste.caseweek.dto.UserInfo;

@Component
public class StaticApi implements Api {
    @Override
    public UserInfo getUserInfo() {
        return UserInfo.builder().displayName("Display name")
                .email("Email")
                .build();
    }
}
