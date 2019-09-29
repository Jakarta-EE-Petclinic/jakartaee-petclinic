package org.woehlke.javaee8.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.javaee8.petclinic.frontend.web.LanguageView;
import org.woehlke.javaee8.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.javaee8.petclinic.frontend.web.common.ViewModelOperations;
import org.woehlke.javaee8.petclinic.oodm.entities.Specialty;
import org.woehlke.javaee8.petclinic.oodm.services.SpecialtyService;
import org.woehlke.javaee8.petclinic.frontend.web.SpecialtyView;

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

    @PostConstruct
    public void init(){
        log.trace("postConstruct");
    }

    public void reloadEntityFromSelected(){
        if(this.selected != null){
            this.selected = entityService.findById(this.selected.getId());
            this.entity = this.selected;
        }
    }

    public void loadEntity(){
        if(this.entity != null) {
            this.entity = entityService.findById(this.entity.getId());
        } else {
            frontendMessagesView.addWarnMessage("cannot load Entity",this.entity);
        }
    }

    public void loadList(){
        this.list = entityService.getAll();
    }

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
            loadList();
        } catch (EJBTransactionRolledbackException e) {
            frontendMessagesView.addWarnMessage("cannot delete, object still in use", this.selected);
        } catch (EJBException e){
            frontendMessagesView.addErrorMessage(e.getLocalizedMessage(),this.selected);
        }
    }


    @Override
    public void newEntity(){
        this.entity = new Specialty();
    }

    @Override
    public String showEditForm(){
        this.reloadEntityFromSelected();
        return "specialtyEdit.jsf";
    }

    @Override
    public String showEntityList() {
        this.loadList();
        return "specialtyList.jsf";
    }

    public String showNewForm(){
       this.newEntity();
       return "specialtyNew.jsf";
    }

    public String deleteSelected() {
        deleteSelectedEntity();
        return "specialtyList.jsf";
    }

    public String saveNew(){
        this.saveNewEntity();
        this.loadList();
        return "specialtyList.jsf";
    }

    public String saveEdited(){
        this.saveEditedEntity();
        this.loadList();
        return "specialtyList.jsf";
    }

    public String cancel(){
        this.loadList();
        return "specialtyList.jsf";
    }

    public String showSelectedEntity(){
        return showEditForm();
    }

    public String search(){
        performSearch();
        return "specialtyList.jsf";
    }


    @Override
    public void performSearch() {
        if(searchterm==null || searchterm.isEmpty()){
            this.list = entityService.getAll();
        } else {
            try {
                this.list = entityService.search(searchterm);
                frontendMessagesView.addInfoMessage("Search ", "Found "+this.list.size()+ "results for searchterm "+searchterm);
            } catch (Exception e){
                log.debug(e.getMessage());
                frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),searchterm);
                this.list = entityService.getAll();
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
        loadList();
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
