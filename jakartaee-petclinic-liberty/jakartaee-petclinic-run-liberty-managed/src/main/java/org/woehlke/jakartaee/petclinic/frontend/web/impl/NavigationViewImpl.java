package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.NavigationView;
import org.woehlke.jakartaee.petclinic.oodm.services.SearchIndexService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("navigationView")
@SessionScoped
public class NavigationViewImpl implements NavigationView {

    private static final long serialVersionUID = 8658978949128397699L;

    private static Logger log = LogManager.getLogger(NavigationViewImpl.class.getName());

    @Inject
    private LanguageView languageView;

    @Inject
    private FrontendMessagesView frontendMessagesView;

    @EJB
    private SearchIndexService searchIndexService;

    public String triggerResetSearchIndexEvent(){
        searchIndexService.resetSearchIndex();
        return "#";
    }

    public String invalidateSession(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "#";
    }

    @Override
    public String gotoHomePage() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "home.jsf?faces-redirect=true";
    }

    @PostConstruct
    public void postConstruct(){
        log.trace("postConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        log.trace("preDestroy");
    }

}
