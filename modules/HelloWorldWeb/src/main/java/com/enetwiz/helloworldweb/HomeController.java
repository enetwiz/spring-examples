package com.enetwiz.helloworldweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String showHello() {
        return "hello";
    }

}