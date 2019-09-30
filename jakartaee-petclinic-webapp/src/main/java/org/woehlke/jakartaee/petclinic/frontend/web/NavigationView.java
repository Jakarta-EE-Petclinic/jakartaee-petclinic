package org.woehlke.jakartaee.petclinic.frontend.web;

import java.io.Serializable;

public interface NavigationView extends Serializable {

    long serialVersionUID = 1794528001303677239L;

    String triggerResetSearchIndexEvent();

    String invalidateSession();

    String gotoHomePage();
}
