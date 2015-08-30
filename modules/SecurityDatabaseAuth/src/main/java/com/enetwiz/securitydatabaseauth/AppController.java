package com.enetwiz.securitydatabaseauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Controller
public class AppController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index() {
        
        return "index";
    }
    
    @RequestMapping(value="/admin", method=RequestMethod.GET)
    public String admin() {
        
        return "admin";
    }
    
}