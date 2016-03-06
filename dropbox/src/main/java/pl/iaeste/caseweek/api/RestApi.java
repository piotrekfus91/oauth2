package pl.iaeste.caseweek.api;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.iaeste.caseweek.dto.Entry;
import pl.iaeste.caseweek.dto.EntryRequest;
import pl.iaeste.caseweek.dto.EntryResponse;
import pl.iaeste.caseweek.dto.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class RestApi implements Api {
    @Autowired
    private ObjectFactory<RestTemplate> restTemplateObjectFactory;

    @Override
    public UserInfo getUserInfo() {
        String token = getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<UserInfo> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = restTemplateObjectFactory.getObject();
        return restTemplate
                .exchange("https://api.dropboxapi.com/1/account/info", HttpMethod.GET, entity, UserInfo.class).getBody();
    }

    @Override
    public List<Entry> getEntries(String folder) {
        String token = getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        EntryRequest entryRequest = EntryRequest.builder().path(folder).build();
        HttpEntity<EntryRequest> entity = new HttpEntity<>(entryRequest, headers);

        RestTemplate restTemplate = restTemplateObjectFactory.getObject();
        return restTemplate
                .exchange("https://api.dropboxapi.com/2/files/list_folder", HttpMethod.POST, entity, EntryResponse.class)
                .getBody().getEntries();
    }

    private String getToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        return (String) session.getAttribute("token");
    }
}
