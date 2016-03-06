package pl.iaeste.caseweek.api;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.iaeste.caseweek.dto.Entry;
import pl.iaeste.caseweek.dto.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

// TODO - uncomment component registration
@Component
public class DropboxApi implements Api {
    @Autowired
    private TokenProvider tokenProvider;

    // TODO
    // https://www.dropbox.com/developers/documentation/java#tutorial
    @Override
    public UserInfo getUserInfo() {
        DbxClientV2 client = getClient();
        try {
            FullAccount currentAccount = client.users().getCurrentAccount();
            return UserInfo.builder().displayName(currentAccount.getName().getDisplayName())
                    .email(currentAccount.getEmail())
                    .build();
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO
    // https://www.dropbox.com/developers/documentation/java#tutorial
    @Override
    public List<Entry> getEntries(String folder) {
        DbxClientV2 client = getClient();
        try {
            List<Metadata> metadatas = client.files().listFolder(folder).getEntries();
            return metadatas.stream().map(md ->
                    Entry.builder().name(md.getName()).path(md.getPathLower()).tag("file").build()
            ).collect(Collectors.toList());
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }

    private DbxClientV2 getClient() {
        DbxRequestConfig config = new DbxRequestConfig("", "en_US");
        return new DbxClientV2(config, tokenProvider.getToken());
    }
}
