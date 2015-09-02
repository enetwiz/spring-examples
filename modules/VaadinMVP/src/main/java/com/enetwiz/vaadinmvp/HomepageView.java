package com.enetwiz.vaadinmvp;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Scope("prototype")
@Component
@VaadinView(HomepageView.NAME)
public class HomepageView extends VerticalLayout implements View {

    public static final String NAME = "homepage";
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent pEvent) {
        super.addComponent( new Label("Welcome on the home page") );
    }
    
}