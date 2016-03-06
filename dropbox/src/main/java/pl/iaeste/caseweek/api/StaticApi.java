package pl.iaeste.caseweek.api;

import org.springframework.stereotype.Component;
import pl.iaeste.caseweek.dto.Entry;
import pl.iaeste.caseweek.dto.UserInfo;

import java.util.ArrayList;
import java.util.List;

// TODO - comment component registration
@Component
public class StaticApi implements Api {
    @Override
    public List<Entry> getEntries(String folder) {
        List<Entry> entries = new ArrayList<>();
        entries.add(Entry.builder().tag("folder").name("Photos").path("/photos").build());
        entries.add(Entry.builder().tag("folder").name("Videos").path("/videos").build());
        entries.add(Entry.builder().tag("file").name("music.mp3").path("/music.mp3").build());
        return entries;
    }

    @Override
    public UserInfo getUserInfo() {
        return UserInfo.builder().displayName("Display name")
                .email("Email")
                .build();
    }
}
