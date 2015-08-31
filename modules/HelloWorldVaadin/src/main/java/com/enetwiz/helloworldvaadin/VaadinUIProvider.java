package com.enetwiz.helloworldvaadin;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
public class VaadinUIProvider extends UIProvider {

    @Autowired
    private ApplicationContext applicationContext = null;
    
    
    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent pEvent) {
        return VaadinUI.class;
    }
    
    @Override
    public UI createInstance(UICreateEvent pEvent) {
        UI instance = new VaadinUI();
        applicationContext.getAutowireCapableBeanFactory().autowireBean( instance );
        
        return instance;
    }
    
}