package vttp2022.day22.giphy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.day22.giphy.service.GiphyService;

@Controller
@RequestMapping(path="")
public class GiphyController {
    private Logger logger = LoggerFactory.getLogger(GiphyController.class);
    
    @Autowired
    private GiphyService giphySvc;

    @GetMapping(path="")
    public String getHomepage(){
        return "index";
    }

    @GetMapping(path="/search")
    public String getForm(@RequestParam String searchval, @RequestParam(defaultValue = "10") String limit, 
        @RequestParam(defaultValue = "pg") String rating, Model m) 
    {
        logger.info("searchval - " + searchval + " limit - " + limit + " rating - " + rating);
        List<String> gifList = giphySvc.retrieveGifLinks(searchval, limit, rating);
        m.addAttribute("gif", gifList);
        return "result";
    }
}
