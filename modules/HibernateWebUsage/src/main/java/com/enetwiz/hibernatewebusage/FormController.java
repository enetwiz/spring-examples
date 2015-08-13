package com.enetwiz.hibernatewebusage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Controller
public class FormController {

    @RequestMapping(value = "/")
    public String showForm() {
        return "form";
    }

}