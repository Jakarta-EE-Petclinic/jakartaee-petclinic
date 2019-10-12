package org.woehlke.jakartaee.petclinic.oodm.view.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;
import org.woehlke.jakartaee.petclinic.oodm.services.PetTypeService;
import org.woehlke.jakartaee.petclinic.oodm.view.PetTypeView;
import org.woehlke.jakartaee.petclinic.oodm.view.flow.PetTypeViewFlow;

import javax.enterprise.context.SessionScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.01.14
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
@Named("petTypeView")
@SessionScoped
public class PetTypeViewImpl implements PetTypeView {

    @Inject
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;

    private static final long serialVersionUID = -528406859430949031L;

    private static Logger log = LogManager.getLogger(PetTypeViewImpl.class);

    private final static String JSF_PAGE = "petType.jsf";

    @EJB
    private PetTypeService entityService;

    @Inject
    private LanguageView languageView;

    @Inject
    private FrontendMessagesView frontendMessagesView;

    @Inject
    private PetTypeViewFlow petTypeViewFlow;

    private PetType entity;
    private PetType selected;
    private List<PetType> list;
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
        this.petTypeViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String showEditForm(){
        this.reloadEntityFromSelected();
        this.petTypeViewFlow.setFlowStateEdit();
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
        this.petTypeViewFlow.setFlowStateDelete();
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
        this.petTypeViewFlow.setFlowStateSearchResult();
        return JSF_PAGE;
    }

    @Override
    public void performSearch() {
        String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.petType.search.done";
        String summary = msg.getString(summaryKey);
        if(searchterm==null || searchterm.isEmpty()){
            this.petTypeViewFlow.setFlowStateList();
            String missingKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.missing";
            String detail = msg.getString(missingKey);
            frontendMessagesView.addInfoMessage(summary, detail);
        } else {
            try {
                this.petTypeViewFlow.setFlowStateSearchResult();
                this.list = entityService.search(searchterm);
                String foundKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.found";
                String resultsKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.results";
                String found = msg.getString(foundKey);
                String results = msg.getString(resultsKey);
                String detail = found+" "+this.list.size()+ " "+ results +" "+searchterm;
                frontendMessagesView.addInfoMessage(summary, detail);
            } catch (Exception e){
                this.petTypeViewFlow.setFlowStateList();
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
    public PetType getSelected() {
        return selected;
    }

    @Override
    public void setSelected(PetType selected) {
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

    public ResourceBundle getMsg() {
        return msg;
    }

    public void setMsg(ResourceBundle msg) {
        this.msg = msg;
    }

    public List<PetType> getList() {
        if(this.petTypeViewFlow.isFlowStateSearchResult()){
            performSearch();
        } else {
            loadList();
        }
        return list;
    }

    public void setList(List<PetType> list) {
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
            this.petTypeViewFlow.setFlowStateList();
            String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.petType.addNew.done";
            String summary = msg.getString(summaryKey);
            frontendMessagesView.addInfoMessage(summary, this.entity.getPrimaryKey());
        } catch (EJBException e){
            this.petTypeViewFlow.setFlowStateNew();
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
            this.petTypeViewFlow.setFlowStateList();
            String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.petType.edit.done";
            String summary = msg.getString(summaryKey);
            frontendMessagesView.addInfoMessage(summary, this.entity.getPrimaryKey());
        } catch (EJBException e){
            this.petTypeViewFlow.setFlowStateEdit();
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
                String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.petType.delete.done";
                String summary = msg.getString(summaryKey);
                frontendMessagesView.addInfoMessage(summary, msgInfo);
            }
            this.petTypeViewFlow.setFlowStateList();
        } catch (EJBTransactionRolledbackException e) {
            this.petTypeViewFlow.setFlowStateDelete();
            String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.petType.delete.denied";
            String summary = msg.getString(summaryKey);
            frontendMessagesView.addWarnMessage(summary, this.selected);
        } catch (EJBException e){
            this.petTypeViewFlow.setFlowStateDelete();
            frontendMessagesView.addErrorMessage(e.getLocalizedMessage(),this.selected);
        }
    }

    @Override
    public void newEntity() {
        String name = "add new name";
        this.entity = new PetType();
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
