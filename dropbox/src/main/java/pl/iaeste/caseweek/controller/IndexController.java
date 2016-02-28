package pl.iaeste.caseweek.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.iaeste.caseweek.api.Api;
import pl.iaeste.caseweek.dto.Entry;
import pl.iaeste.caseweek.dto.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private Api api;

    @RequestMapping("/")
    public String indexAction(Model model,
                              @RequestParam(name = "folder", required = false, defaultValue = "") String folder) {
        log.debug("index action");
        UserInfo userInfo = api.getUserInfo();
        List<Entry> rootEntries = api.getEntries(folder);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("folders", extractFolders(rootEntries));
        model.addAttribute("files", extractFiles(rootEntries));
        return "index";
    }

    private List<Entry> getEntries(List<Entry> entries, String tag) {
        return entries.stream().filter(entry -> tag.equals(entry.getTag())).collect(Collectors.toList());
    }

    private List<Entry> extractFolders(List<Entry> rootEntries) {
        return getEntries(rootEntries, "folder");
    }

    private List<Entry> extractFiles(List<Entry> entries) {
        return getEntries(entries, "file");
    }
}
