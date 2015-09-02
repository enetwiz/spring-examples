package com.enetwiz.vaadinmvp;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Scope("prototype")
@Component
public class HomepagePresenter implements ViewProvider {

    @Override
    public String getViewName(String viewAndParameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public View getView(String viewName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}