package com.enetwiz.vaadinmvp;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;

import com.vaadin.ui.UI;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.spring.annotation.VaadinUI;
import ru.xpoft.vaadin.DiscoveryNavigator;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@VaadinUI
@Component
@Scope("prototype")
public class MyVaadimUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        DiscoveryNavigator discoveryNavigator = new DiscoveryNavigator(this, this);
        super.setNavigator( discoveryNavigator );
        
        discoveryNavigator.navigateTo( UI.getCurrent().getPage().getUriFragment() );
        
    }
}