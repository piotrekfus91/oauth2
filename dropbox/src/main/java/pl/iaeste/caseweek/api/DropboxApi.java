package pl.iaeste.caseweek.api;

import org.springframework.beans.factory.annotation.Autowired;
import pl.iaeste.caseweek.dto.Entry;
import pl.iaeste.caseweek.dto.UserInfo;

import java.util.List;

// TODO - uncomment component registration
//@Component
public class DropboxApi implements Api {
    @Autowired
    private TokenProvider tokenProvider;

    // TODO
    // https://www.dropbox.com/developers/documentation/java#tutorial
    @Override
    public UserInfo getUserInfo() {
        return null;
    }

    // TODO
    // https://www.dropbox.com/developers/documentation/java#tutorial
    @Override
    public List<Entry> getEntries(String folder) {
        return null;
    }
}
