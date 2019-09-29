package org.woehlke.jakartaee.petclinic.frontend.web;

import java.io.Serializable;

public interface NavigationView extends Serializable {

    String triggerResetSearchIndexEvent();

    String invalidateSession();

    String gotoHomePage();
}
