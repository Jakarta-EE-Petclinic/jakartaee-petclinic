package org.woehlke.jakartaee.petclinic.oodm.view.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;
import org.woehlke.jakartaee.petclinic.oodm.services.PetTypeService;
import org.woehlke.jakartaee.petclinic.oodm.view.PetTypeView;
import org.woehlke.jakartaee.petclinic.oodm.view.flow.PetTypeViewFlow;

import javax.faces.bean.ManagedBean;;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.01.14
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("deprecation")
@ManagedBean(name="petTypeView")
@SessionScoped
public class PetTypeViewImpl implements PetTypeView {

    private static Logger log = LogManager.getLogger(PetTypeViewImpl.class);

    private final static String JSF_PAGE = "petType.jsf";

    @EJB
    private PetTypeService entityService;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
    private FrontendMessagesView frontendMessagesView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{petTypeViewFlow}")
    private PetTypeViewFlow petTypeViewFlow;

    private org.woehlke.jakartaee.petclinic.oodm.entities.PetType entity;
    private org.woehlke.jakartaee.petclinic.oodm.entities.PetType selected;
    private List<org.woehlke.jakartaee.petclinic.oodm.entities.PetType> list;
    private String searchterm;

    @PostConstruct
    public void init(){
        this.petTypeViewFlow.setFlowStateList();
        log.trace("postConstruct");
    }

    @Override
    public String showNewForm(){
        this.newEntity();
        this.petTypeViewFlow.setFlowStateNew();
        return JSF_PAGE;
    }


    @Override
    public String saveNew(){
        this.saveNewEntity();
        this.petTypeViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String cancelNew() {
        loadList();
        return JSF_PAGE;
    }

    @Override
    public String showEditForm(){
        this.reloadEntityFromSelected();
        this.petTypeViewFlow.isFlowStateEdit();
        return JSF_PAGE;
    }

    @Override
    public String saveEdited(){
        this.saveEditedEntity();
        this.petTypeViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String cancelEdited() {
        this.petTypeViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String showDeleteForm() {
        this.petTypeViewFlow.isFlowStatDelete();
        return JSF_PAGE;
    }

    @Override
    public String performDelete() {
        deleteSelectedEntity();
        this.petTypeViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String cancelDelete() {
        this.petTypeViewFlow.setFlowStateList();
        return JSF_PAGE;
    }


    @Override
    public String search(){
        this.petTypeViewFlow.isFlowStateSearchResult();
        return JSF_PAGE;
    }

    @Override
    public void performSearch() {
        if(searchterm==null || searchterm.isEmpty()){
            this.petTypeViewFlow.setFlowStateList();
            loadList();
            frontendMessagesView.addInfoMessage("Search ", "Missing searchterm");
        } else {
            try {
                this.petTypeViewFlow.setFlowStateSearchResult();
                this.list = entityService.search(searchterm);
                frontendMessagesView.addInfoMessage("Search ", "Found "+this.list.size()+ "results for searchterm "+searchterm);
            } catch (Exception e){
                this.petTypeViewFlow.setFlowStateList();
                loadList();
                frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),searchterm);
            }
        }
    }

    @Override
    public PetType getEntity() {
        if(entity == null){
            newEntity();
        }
        return entity;
    }

    @Override
    public void setEntity(PetType entity) {
        this.entity = entity;
    }

    public FrontendMessagesView getFrontendMessagesView() {
        return frontendMessagesView;
    }

    public void setFrontendMessagesView(FrontendMessagesView frontendMessagesView) {
        this.frontendMessagesView = frontendMessagesView;
    }

    @Override
    public org.woehlke.jakartaee.petclinic.oodm.entities.PetType getSelected() {
        return selected;
    }

    @Override
    public void setSelected(org.woehlke.jakartaee.petclinic.oodm.entities.PetType selected) {
        this.selected = selected;
        if( this.selected != null ){
            this.entity = entityService.findById(this.selected.getId());
        }
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public LanguageView getLanguageView() {
        return languageView;
    }

    public void setLanguageView(LanguageView languageView) {
        this.languageView = languageView;
    }

    public List<org.woehlke.jakartaee.petclinic.oodm.entities.PetType> getList() {
        if(this.petTypeViewFlow.isFlowStateSearchResult()){
            performSearch();
        } else {
            loadList();
        }
        return list;
    }

    public void setList(List<org.woehlke.jakartaee.petclinic.oodm.entities.PetType> list) {
        this.list = list;
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
            log.debug((this.entity!=null)?this.entity.toString():"null");
            log.debug((this.selected!=null)?this.selected.toString():"null");
            this.selected = this.entityService.addNew(this.entity);
            this.entity = this.selected;
            log.debug((this.entity!=null)?this.entity.toString():"null");
            log.debug((this.selected!=null)?this.selected.toString():"null");
            frontendMessagesView.addInfoMessage("Added", this.entity.getPrimaryKey());
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
            frontendMessagesView.addWarnMessage(e, this.entity);
        }
    }

    @Override
    public void saveEditedEntity() {
        try {
            log.debug((this.entity!=null)?this.entity.toString():"null");
            log.debug((this.selected!=null)?this.selected.toString():"null");
            this.entity = this.entityService.update(this.entity);
            log.debug((this.entity!=null)?this.entity.toString():"null");
            log.debug((this.selected!=null)?this.selected.toString():"null");
            frontendMessagesView.addInfoMessage("Updated", this.entity.getPrimaryKey());
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
        }
    }

    @Override
    public void deleteSelectedEntity(){
        try {
            if(this.selected != null) {
                String msgInfo = this.selected.getPrimaryKey();
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
        this.entity = new org.woehlke.jakartaee.petclinic.oodm.entities.PetType();
    }

    @PreDestroy
    public void preDestroy(){
        log.trace("preDestroy");
    }

    public PetTypeViewFlow getPetTypeViewFlow() {
        return petTypeViewFlow;
    }

    public void setPetTypeViewFlow(PetTypeViewFlow petTypeViewFlow) {
        this.petTypeViewFlow = petTypeViewFlow;
    }
}
