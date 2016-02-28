package pl.iaeste.caseweek.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class IndexController {
    @RequestMapping("/")
    public String indexAction(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name,
                              Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}
