package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.NavigationView;
import org.woehlke.jakartaee.petclinic.oodm.services.SearchIndexService;

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

    private static final long serialVersionUID = 8658978949128397699L;

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
