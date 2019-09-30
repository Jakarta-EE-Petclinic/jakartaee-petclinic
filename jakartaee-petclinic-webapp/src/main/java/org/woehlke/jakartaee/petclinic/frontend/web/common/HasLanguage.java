package org.woehlke.jakartaee.petclinic.frontend.web.common;

import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;

import java.io.Serializable;

public interface HasLanguage extends Serializable {

    long serialVersionUID = -7665952921129558730L;

    LanguageView getLanguageView();
    void setLanguageView(LanguageView languageView);
}
