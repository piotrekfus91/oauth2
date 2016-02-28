package pl.iaeste.caseweek.api;

import pl.iaeste.caseweek.dto.Entry;
import pl.iaeste.caseweek.dto.UserInfo;

import java.util.List;

public interface Api {
    UserInfo getUserInfo();

    List<Entry> getEntries(String folder);
}
