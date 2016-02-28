package pl.iaeste.caseweek.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.iaeste.caseweek.api.Api;
import pl.iaeste.caseweek.dto.UserInfo;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private Api api;

    @RequestMapping("/")
    public String indexAction(Model model) {
        log.debug("index action");
        UserInfo userInfo = api.getUserInfo();
        model.addAttribute("userInfo", userInfo);
        return "index";
    }
}
