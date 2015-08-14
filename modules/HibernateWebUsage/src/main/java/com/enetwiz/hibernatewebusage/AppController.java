package com.enetwiz.hibernatewebusage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Controller
public class AppController {

    @Autowired
    private ExampleEntityDao exampleEntityDao = null;
    
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String form( Model model ) {
        model.addAttribute("entity", new ExampleEntity());
        
        return "form";
    }
    
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute ExampleEntity pExampleEntity) {
        exampleEntityDao.saveOrUpdate( pExampleEntity );
        
        return "redirect:/list";
    }
    
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject( "entities", exampleEntityDao.list() );
        
        return modelAndView;
    }
    
}