package pl.iaeste.caseweek.api;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.iaeste.caseweek.dto.Entry;
import pl.iaeste.caseweek.dto.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

// TODO - uncomment component registration
//@Component
public class DropboxApi implements Api {
    @Autowired
    private ObjectFactory<RestTemplate> restTemplateObjectFactory;

    // https://www.dropbox.com/developers-v1/core/docs#account-info
    @Override
    public UserInfo getUserInfo() {
        return null;
    }

    // https://www.dropbox.com/developers/documentation/http/documentation#files-list_folder
    // TODO
    @Override
    public List<Entry> getEntries(String folder) {
        return null;
    }

    private String getToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        return (String) session.getAttribute("token");
    }
}
