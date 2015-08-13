package com.enetwiz.hibernatewebusage;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Controller
public class AppController {

    @Autowired
    private ExampleEntityDao exampleEntityDao = null;
    
    
    @RequestMapping(value = "/")
    public String showForm() {    
        return "form";
    }

}