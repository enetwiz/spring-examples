package com.enetwiz.helloworldvaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Theme("mytheme")
public class VaadinUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = VaadinUI.class, widgetset = "com.enetwiz.helloworldvaadin.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
    
    @Override
    protected void init(VaadinRequest pRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin( true );
        setContent( layout );
        
        Button button = new Button("Hello world and click me!");
        button.addClickListener(new Button.ClickListener() {
            
            @Override
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label("Thank you for clicking"));
            }
            
        });
        layout.addComponent(button);
    }
    
}