package org.woehlke.javaee8.petclinic.frontend.web.common;

import org.woehlke.javaee8.petclinic.frontend.web.LanguageView;

import java.io.Serializable;

public interface ViewHasLanguage extends Serializable {

    LanguageView getLanguageView();

    void setLanguageView(LanguageView languageView);
}
