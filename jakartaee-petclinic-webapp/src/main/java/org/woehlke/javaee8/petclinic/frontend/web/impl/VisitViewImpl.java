package org.woehlke.javaee8.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.javaee8.petclinic.frontend.web.LanguageView;
import org.woehlke.javaee8.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.javaee8.petclinic.frontend.web.PetView;
import org.woehlke.javaee8.petclinic.frontend.web.VisitView;
import org.woehlke.javaee8.petclinic.frontend.web.common.ViewModelOperations;
import org.woehlke.javaee8.petclinic.oodm.entities.Pet;
import org.woehlke.javaee8.petclinic.oodm.entities.Visit;
import org.woehlke.javaee8.petclinic.oodm.services.VisitService;

import javax.faces.bean.ManagedBean;;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean(name="visitView")
@SessionScoped
public class VisitViewImpl implements VisitView, ViewModelOperations {

    private static Logger log = LogManager.getLogger(VisitViewImpl.class.getName());

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
    private FrontendMessagesView frontendMessagesView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{petView}")
    private PetView petView;

    @EJB
    private VisitService entityService;

    private Pet petOfTheseVisits;

    private List<Visit> list;
    private Visit entity;
    private Visit selected;

    @PostConstruct
    public void init(){
        log.trace("postConstruct");
    }

    public PetView getPetView() {
        return petView;
    }

    public void setPetView(PetView petView) {
        this.petView = petView;
    }

    public Pet getPetOfTheseVisits() {
        return petOfTheseVisits;
    }

    public void setPetOfTheseVisits(Pet petOfTheseVisits) {
        this.petOfTheseVisits = petOfTheseVisits;
    }

    public List<Visit> getList() {
        loadList();
        return list;
    }

    public void setList(List<Visit> list) {
        this.list = list;
    }

    public Visit getEntity() {
        return entity;
    }

    public void setEntity(Visit entity) {
        this.entity = entity;
    }

    public Visit getSelected() {
        return selected;
    }

    public void setSelected(Visit selected) {
        this.selected = selected;
    }

    public LanguageView getLanguageView() {
        return languageView;
    }

    public void setLanguageView(LanguageView languageView) {
        this.languageView = languageView;
    }

    public FrontendMessagesView getFrontendMessagesView() {
        return frontendMessagesView;
    }

    public void setFrontendMessagesView(FrontendMessagesView frontendMessagesView) {
        this.frontendMessagesView = frontendMessagesView;
    }

    @PreDestroy
    public void preDestroy(){
        log.trace("preDestroy");
    }


    @Override
    public void reloadEntityFromSelected() {
        if( this.selected != null ){
            this.entity = entityService.findById(this.selected.getId());
            this.selected = this.entity;
        }
    }

    @Override
    public void loadEntity(){
        if(this.entity != null) {
            this.entity = entityService.findById(this.entity.getId());
        } else {
            frontendMessagesView.addWarnMessage("cannot load Entity",this.entity);
        }
    }

    @Override
    public void loadList() {
        this.list = this.entityService.getAll();
    }

    @Override
    public void saveNewEntity() {
        try {
            this.selected = this.entityService.addNew(this.entity);
            this.entity = this.selected;
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
        }
    }

    @Override
    public void saveEditedEntity() {
        try {
            this.entity = this.entityService.update(this.entity);
            this.selected = this.entity;
            frontendMessagesView.addInfoMessage("Updated", this.entity.getPrimaryKeyWithId());
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
        }
    }

    @Override
    public void deleteSelectedEntity(){
        try {
            if(this.selected != null) {
                String msgInfo = this.selected.getPrimaryKeyWithId();
                entityService.delete(this.selected.getId());
                if(this.selected.compareTo(this.entity)==0){
                    this.entity = null;
                }
                this.selected = null;
                frontendMessagesView.addInfoMessage("Deleted", msgInfo);
            }
            loadList();
        } catch (EJBTransactionRolledbackException e) {
            frontendMessagesView.addWarnMessage("cannot delete, object still in use", this.selected);
        } catch (EJBException e){
            frontendMessagesView.addErrorMessage(e.getLocalizedMessage(),this.selected);
        }
    }

    @Override
    public void newEntity() {
        this.entity = new Visit();
    }
}
