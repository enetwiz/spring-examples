package com.enetwiz.helloworldvaadin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.vaadin.spring.annotation.VaadinUI;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@VaadinUI
public class MyVaadimUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        Component component = new Button("Hello World by Vaadim!");
        super.setContent( component );
    }
}