package org.woehlke.javaee8.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.javaee8.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.javaee8.petclinic.frontend.web.LanguageView;
import org.woehlke.javaee8.petclinic.frontend.web.NavigationView;
import org.woehlke.javaee8.petclinic.oodm.services.SearchIndexService;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SuppressWarnings("deprecation")
@ManagedBean(name="navigationView")
@SessionScoped
public class NavigationViewImpl implements NavigationView {

    private static Logger log = LogManager.getLogger(NavigationViewImpl.class.getName());

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
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
