package org.woehlke.jakartaee.petclinic.oodm.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DualListModel;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudViewFlowState;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;
import org.woehlke.jakartaee.petclinic.oodm.services.SpecialtyService;
import org.woehlke.jakartaee.petclinic.oodm.services.VetService;
import org.woehlke.jakartaee.petclinic.oodm.view.VetView;
import org.woehlke.jakartaee.petclinic.oodm.view.flow.VetViewFlow;


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
public class VetViewImpl implements VetView {

    private static Logger log = LogManager.getLogger(VetViewImpl.class.getName());

    private final static String JSF_PAGE = "vet.jsf";

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
    private FrontendMessagesView frontendMessagesView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{vetViewFlow}")
    private VetViewFlow vetViewFlow;

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
        this.vetViewFlow.setFlowStateList();
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
        for(Specialty specialty: this.entity.getSpecialties()){
            targetList.add(specialty);
        }
        for(Specialty specialty:this.specialtyService.getAll()){
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

    public org.woehlke.jakartaee.petclinic.oodm.entities.Vet getEntity() {
        return entity;
    }

    public void setEntity(org.woehlke.jakartaee.petclinic.oodm.entities.Vet entity) {
        this.entity = entity;
    }

    public org.woehlke.jakartaee.petclinic.oodm.entities.Vet getSelected() {
        return selected;
    }

    public void setSelected(org.woehlke.jakartaee.petclinic.oodm.entities.Vet selected) {
        this.selected = selected;
    }

    public FrontendMessagesView getFrontendMessagesView() {
        return frontendMessagesView;
    }

    public void setFrontendMessagesView(FrontendMessagesView frontendMessagesView) {
        this.frontendMessagesView = frontendMessagesView;
    }

    @Override
    public String showNewForm(){
        this.newEntity();
        this. initSpecialtiesPickList();
        this.vetViewFlow.isFlowStateNew();
        return JSF_PAGE;
    }

    public String saveNew() {
        this.saveNewEntity();
        this.vetViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String cancelNew(){
        this.vetViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    public void setList(List<org.woehlke.jakartaee.petclinic.oodm.entities.Vet> list) {
        this.list = list;
    }

    public List<org.woehlke.jakartaee.petclinic.oodm.entities.Vet> getList() {
        if( this.vetViewFlow.isFlowStateSearchResult()){
            performSearch();
        } else {
            loadList();
        }
        return this.list;
    }

    @Override
    public String showEditForm(){
        this.reloadEntityFromSelected();
        this.resetSpecialtiesPickList();
        this.vetViewFlow.isFlowStateEdit();
        return JSF_PAGE;
    }

    public String saveEdited() {
        this.saveEditedEntity();
        this.vetViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String cancelEdited(){
        this.vetViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String showDeleteForm(){
        this.reloadEntityFromSelected();
        this.vetViewFlow.setFlowStatDelete();
        return JSF_PAGE;
    }

    @Override
    public String performDelete(){
        this.deleteSelectedEntity();
        this.vetViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String cancelDelete(){
        this.vetViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    @Override
    public String search(){
        this.vetViewFlow.isFlowStateSearchResult();
        return JSF_PAGE;
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
            log.debug("try to save New: "+this.entity.toString());
            this.entity.removeSpecialties();
            this.entity.setUuid(UUID.randomUUID());
            this.entity = entityService.addNew(this.entity);
            this.entity = entityService.findById(this.entity.getId());
            log.debug("nr source: "+this.specialtiesPickList.getSource().size());
            log.debug("nr target: "+this.specialtiesPickList.getTarget().size());
            for (Specialty specialtyTransient : this.specialtiesPickList.getTarget()) {
                Specialty specialty = specialtyService.findSpecialtyByName(specialtyTransient.getName());
                this.entity.addSpecialty(specialty);
            }
            this.entity = entityService.update(this.entity);
            log.debug("saved New: "+this.entity.toString());
            this.selected = this.entity;
            frontendMessagesView.addInfoMessage("Added new Vetinarian", this.entity.getPrimaryKey());
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
            frontendMessagesView.addWarnMessage(e,this.entity);
        }
    }

    @Override
    public void saveEditedEntity() {
        try {
            this.entity.removeSpecialties();
            for (Specialty specialtyTransient : this.specialtiesPickList.getTarget()) {
                log.debug(" added transient via saveEditedEntity: "+specialtyTransient.toString());
                Specialty specialty = specialtyService.findSpecialtyByName(specialtyTransient.getName());
                this.entity.addSpecialty(specialty);
                //this.specialtyService.update(specialty);
            }
            this.entity = entityService.update(this.entity);
            this.selected = this.entity;
            frontendMessagesView.addInfoMessage("Updated Vetinarian", this.entity.getPrimaryKey());
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
            frontendMessagesView.addWarnMessage(e,this.entity);
        }
    }

    @Override
    public void deleteSelectedEntity(){
        try {
            if(this.selected != null) {
                String msgInfo = this.selected.getPrimaryKey();
                boolean same = (this.selected.compareTo(this.entity)==0);
                long id = this.selected.getId();
                entityService.delete(id);
                if(same){
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
        this.entity = new org.woehlke.jakartaee.petclinic.oodm.entities.Vet();
    }

    public VetViewFlow getVetViewFlow() {
        return vetViewFlow;
    }

    public void setVetViewFlow(VetViewFlow vetViewFlow) {
        this.vetViewFlow = vetViewFlow;
    }
}
