package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.frontend.web.OwnerView;
import org.woehlke.jakartaee.petclinic.frontend.web.PetView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.ViewModelOperations;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;
import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;
import org.woehlke.jakartaee.petclinic.oodm.services.PetService;
import org.woehlke.jakartaee.petclinic.oodm.services.PetTypeService;

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
@ManagedBean(name="petView")
@SessionScoped
public class PetViewImpl implements PetView, ViewModelOperations {

    private static Logger log = LogManager.getLogger(PetViewImpl.class.getName());

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
    private FrontendMessagesView frontendMessagesView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{ownerView}")
    private OwnerView ownerView;

    @EJB
    private PetService entityService;
    
    @EJB
    private PetTypeService petTypeService;

    private Owner ownerOfThesePets;

    private List<Pet> list;
    private Pet entity;
    private Pet selected;

    @PostConstruct
    public void init(){
        log.trace("postConstruct");
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

    public OwnerView getOwnerView() {
        return ownerView;
    }

    public void setOwnerView(OwnerView ownerView) {
        this.ownerView = ownerView;
    }

    public Owner getOwnerOfThesePets() {
        return ownerOfThesePets;
    }

    public void setOwnerOfThesePets(Owner ownerOfThesePets) {
        this.ownerOfThesePets = ownerOfThesePets;
    }

    public List<Pet> getList() {
        loadList();
        return list;
    }

    public void setList(List<Pet> list) {
        this.list = list;
    }

    public Pet getEntity() {
        return entity;
    }

    public void setEntity(Pet entity) {
        this.entity = entity;
    }

    public Pet getSelected() {
        return selected;
    }

    public void setSelected(Pet selected) {
        this.selected = selected;
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
    public void loadList() {
        this.list = this.entityService.getAll();
    }

    @Override
    public void saveNewEntity() {
        try {
            this.selected = this.entityService.addNew(this.entity);
            this.entity = this.selected;
            frontendMessagesView.addInfoMessage("Added", this.entity.getPrimaryKeyWithId());
        } catch (EJBException e){
            frontendMessagesView.addWarnMessage(e, this.entity);
        }
    }

    @Override
    public void saveEditedEntity() {
        try {
            this.entity = this.entityService.update(this.entity);
            this.selected = this.entity;
            frontendMessagesView.addInfoMessage("Updated", this.entity.getPrimaryKeyWithId());
        } catch (EJBException e){
            frontendMessagesView.addWarnMessage(e, this.entity);
        }
    }

    @Override
    public void deleteSelectedEntity(){
        try {
            if(this.selected != null) {
                String msgInfo = this.selected.getPrimaryKeyWithId();
                if(this.selected.compareTo(this.entity)==0){
                    this.entity = null;
                }
                entityService.delete(this.selected.getId());
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
        this.entity = new Pet();
    }


}
