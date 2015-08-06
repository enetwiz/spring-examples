package com.enetwiz.componentscope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Component
@Scope("prototype")
public class PrototypeComponent extends DefaultComponent {
   
}