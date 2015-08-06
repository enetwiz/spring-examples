package com.enetwiz.componentscope;

import org.springframework.stereotype.Component;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Component
public class DefaultComponent {
    
    private String label = null;
    
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String pLabel) {
        label = pLabel;
    }
    
}