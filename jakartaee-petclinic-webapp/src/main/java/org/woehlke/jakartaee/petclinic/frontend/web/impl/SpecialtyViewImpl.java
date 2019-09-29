package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudViewFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.common.ViewModelOperations;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.services.SpecialtyService;
import org.woehlke.jakartaee.petclinic.frontend.web.SpecialtyView;

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
 * User: tw
 * Date: 04.01.14
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("deprecation")
@ManagedBean(name="specialtyView")
@SessionScoped
public class SpecialtyViewImpl implements SpecialtyView, ViewModelOperations {

    private static Logger log = LogManager.getLogger(SpecialtyViewImpl.class.getName());

    private final static String JSF_PAGE = "specialty.jsf";

    @EJB
    private SpecialtyService entityService;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
    private FrontendMessagesView frontendMessagesView;

    private Specialty entity;
    private Specialty selected;
    private List<Specialty> list;

    private String searchterm;

    private CrudViewFlowState flowState;

    @PostConstruct
    public void init(){
        log.trace("postConstruct");
        this.flowState = CrudViewFlowState.LIST;
    }

    @Override
    public void reloadEntityFromSelected(){
        if(this.selected != null){
            this.selected = entityService.findById(this.selected.getId());
            this.entity = this.selected;
        }
    }

    @Override
    public void loadList(){
        this.list = entityService.getAll();
    }

    @Override
    public void saveNewEntity(){
        try {
            this.entity = entityService.addNew(this.entity);
            this.selected = this.entity;
            frontendMessagesView.addInfoMessage("Added", this.entity.getPrimaryKey());
        } catch (EJBException e){
            frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),this.entity);
        }
    }

    @Override
    public void saveEditedEntity() {
        try {
            this.entity = this.entityService.update(this.entity);
            this.selected = this.entity;
            frontendMessagesView.addInfoMessage("Updated", this.entity.getPrimaryKey());
        } catch (EJBException e){
            frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),this.entity);
        }
    }

    @Override
    public void deleteSelectedEntity(){
        try {
            if(this.selected != null) {
                String msgInfo = this.selected.getPrimaryKey();
                entityService.delete(this.selected.getId());
                if(this.selected.compareTo(this.entity)==0){
                    this.entity = null;
                }
                this.selected = null;
                frontendMessagesView.addInfoMessage("Deleted", msgInfo);
            }
        } catch (EJBTransactionRolledbackException e) {
            frontendMessagesView.addWarnMessage("cannot delete, object still in use", this.selected);
        } catch (EJBException e){
            frontendMessagesView.addErrorMessage(e.getLocalizedMessage(),this.selected);
        }
    }

    @Override
    public boolean isFlowStateList(){
        return  this.flowState == CrudViewFlowState.LIST;
    }

    @Override
    public boolean isFlowStateNew(){
       return  this.flowState == CrudViewFlowState.NEW;
    }

    @Override
    public boolean isFlowStateEdit(){
        return  this.flowState == CrudViewFlowState.EDIT;
    }

    @Override
    public boolean isFlowStatDelete(){
        return  this.flowState == CrudViewFlowState.DELETE;
    }

    @Override
    public boolean isFlowStateSearchResult(){
        return  this.flowState == CrudViewFlowState.LIST_SEARCH_RESULT;
    }

    @Override
    public void newEntity(){
        this.entity = new Specialty();
    }

    @Override
    public String showEditForm(){
        this.reloadEntityFromSelected();
        this.flowState = CrudViewFlowState.EDIT;
        return JSF_PAGE;
    }

    @Override
    public String showNewForm(){
       this.newEntity();
        this.flowState = CrudViewFlowState.NEW;
       return JSF_PAGE;
    }

    @Override
    public String saveNew(){
        this.saveNewEntity();
        this.flowState = CrudViewFlowState.LIST;
        return JSF_PAGE;
    }

    @Override
    public String saveEdited(){
        this.saveEditedEntity();
        this.flowState = CrudViewFlowState.LIST;
        return JSF_PAGE;
    }

    @Override
    public String cancelEdited(){
        this.flowState = CrudViewFlowState.LIST;
        return JSF_PAGE;
    }

    @Override
    public String cancelNew(){
        this.flowState = CrudViewFlowState.LIST;
        return JSF_PAGE;
    }

    @Override
    public String showDeleteForm(){
        this.flowState = CrudViewFlowState.DELETE;
        return JSF_PAGE;
    }

    @Override
    public String performDelete(){
        deleteSelectedEntity();
        this.flowState = CrudViewFlowState.LIST;
        return JSF_PAGE;
    }

    @Override
    public String cancelDelete(){
        this.flowState = CrudViewFlowState.LIST;
        return JSF_PAGE;
    }

    @Override
    public String search(){
        this.flowState = CrudViewFlowState.LIST_SEARCH_RESULT;
        return JSF_PAGE;
    }


    @Override
    public void performSearch() {
        if(searchterm==null || searchterm.isEmpty()){
            this.flowState = CrudViewFlowState.LIST;
            loadList();
            frontendMessagesView.addInfoMessage("Search ", "Missing searchterm");
        } else {
            try {
                this.flowState = CrudViewFlowState.LIST_SEARCH_RESULT;
                this.list = entityService.search(searchterm);
                frontendMessagesView.addInfoMessage("Search ", "Found "+this.list.size()+ "results for searchterm "+searchterm);
            } catch (Exception e){
                this.flowState = CrudViewFlowState.LIST;
                loadList();
                frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),searchterm);
            }
        }
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

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    @Override
    public Specialty getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Specialty entity) {
        this.entity = entity;
    }

    @Override
    public Specialty getSelected() {
        return selected;
    }

    @Override
    public void setSelected(Specialty selected) {
        this.selected = selected;
    }

    @Override
    public List<Specialty> getList() {
        if(this.flowState == CrudViewFlowState.LIST_SEARCH_RESULT){
            performSearch();
        } else {
            loadList();
        }
        return list;
    }

    @Override
    public void setList(List<Specialty> list) {
        this.list = list;
    }

    @PreDestroy
    public void preDestroy(){
        log.trace("preDestroy");
    }

}
