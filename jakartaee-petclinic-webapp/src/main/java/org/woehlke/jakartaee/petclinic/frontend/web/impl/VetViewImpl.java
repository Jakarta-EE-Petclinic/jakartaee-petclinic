package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DualListModel;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudViewFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.common.ViewModelOperations;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;
import org.woehlke.jakartaee.petclinic.oodm.services.SpecialtyService;
import org.woehlke.jakartaee.petclinic.oodm.services.VetService;
import org.woehlke.jakartaee.petclinic.frontend.web.VetView;


import javax.faces.bean.ManagedBean;;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("deprecation")
@ManagedBean(name="vetView")
@SessionScoped
public class VetViewImpl implements VetView, ViewModelOperations {

    private static Logger log = LogManager.getLogger(VetViewImpl.class.getName());

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
    private FrontendMessagesView frontendMessagesView;

    @EJB
    private SpecialtyService specialtyService;

    @EJB
    private VetService entityService;

    private Vet entity;
    private Vet selected;
    private List<Vet> list;

    private String searchterm;

    private DualListModel<Specialty> specialtiesPickList;

    @PostConstruct
    public void init() {
        log.trace("postConstruct");
        this.flowState = CrudViewFlowState.LIST;
        loadList();
        initSpecialtiesPickList();
    }

    private void initSpecialtiesPickList(){
        List<Specialty> srcList = specialtyService.getAll();
        List<Specialty> targetList = new ArrayList<>();
        this.specialtiesPickList = new DualListModel<Specialty>(srcList, targetList);
    }

    private void resetSpecialtiesPickList(){
        List<Specialty> srcList = new ArrayList<>();
        List<Specialty> targetList = new ArrayList<>();
        for(Specialty specialty: entity.getSpecialties()){
            targetList.add(specialty);
        }
        for(Specialty specialty:specialtyService.getAll()){
            if(!targetList.contains(specialty)){
                srcList.add(specialty);
            }
        }
        this.specialtiesPickList = new DualListModel<>(srcList, targetList);
    }

    public DualListModel<Specialty> getSpecialtiesPickList() {
        return specialtiesPickList;
    }

    public void setSpecialtiesPickList(DualListModel<Specialty> specialtiesPickList) {
        this.specialtiesPickList = specialtiesPickList;
    }

    @Override
    public Specialty findSpecialtyByName(String name) {
        return specialtyService.findSpecialtyByName(name);
    }

    public Vet getEntity() {
        return entity;
    }

    public void setEntity(Vet entity) {
        this.entity = entity;
    }

    public Vet getSelected() {
        return selected;
    }

    public void setSelected(Vet selected) {
        this.selected = selected;
    }

    public FrontendMessagesView getFrontendMessagesView() {
        return frontendMessagesView;
    }

    public void setFrontendMessagesView(FrontendMessagesView frontendMessagesView) {
        this.frontendMessagesView = frontendMessagesView;
    }

    public String showNewForm() {
        this.entity = new Vet();
        initSpecialtiesPickList();
        return "vetNew.jsf";
    }

    public String saveNew() {
        try {
            log.debug("try to save New: "+this.entity.toString());
            this.entity.removeSpecialties();
            this.entity = entityService.addNew(this.entity);
            log.debug("nr source: "+this.specialtiesPickList.getSource().size());
            log.debug("nr target: "+this.specialtiesPickList.getTarget().size());
            for (Specialty specialtyTransient : this.specialtiesPickList.getTarget()) {
                log.debug("saveNew: "+specialtyTransient.toString());
                Specialty specialty = specialtyService.findSpecialtyByName(specialtyTransient.getName());
                this.entity.addSpecialty(specialty);
                this.specialtyService.update(specialty);
            }
            this.entity = entityService.update(this.entity);
            this.selected = this.entity;
        } catch (EJBException e){
            log.warn("saveNew:");
            log.warn(this.entity.toString());
            log.warn(e.getMessage());
        }
        loadList();
        return "vetList.jsf";
    }

    @Override
    public String cancelNew() {
        loadList();
        return "vetList.jsf";
    }

    public void setList(List<Vet> list) {
        this.list = list;
    }

    public List<Vet> getList() {
        loadList();
        return this.list;
    }

    public String showEditForm() {
        this.entity = entityService.findById(this.selected.getId());
        resetSpecialtiesPickList();
        return "vetEdit.jsf";
    }

    public String saveEdited() {
        try {
            this.entity.removeSpecialties();
            for (Specialty specialtyTransient : this.specialtiesPickList.getTarget()) {
                log.debug("saveEdited: "+specialtyTransient.toString());
                Specialty specialty = specialtyService.findSpecialtyByName(specialtyTransient.getName());
                this.entity.addSpecialty(specialty);
                //this.specialtyService.update(specialty);
            }
            this.entity = entityService.update(this.entity);
            this.selected = this.entity;
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
        }
        loadList();
        return "vetList.jsf";
    }

    @Override
    public String cancelEdited() {
        return null;
    }

    @Override
    public String showDeleteForm() {
        return null;
    }

    @Override
    public String performDelete() {
        deleteSelectedEntity();
        loadList();
        return "vetList.jsf";
    }

    @Override
    public String cancelDelete() {
        loadList();
        return "vetList.jsf";
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public String search() {
        performSearch();
        return "vetList.jsf";
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

    @Override
    public LanguageView getLanguageView() {
        return this.languageView;
    }

    @Override
    public void setLanguageView(LanguageView languageView) {
        this.languageView = languageView;
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
        this.entity = new Vet();
    }

    private CrudViewFlowState flowState;


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

}
