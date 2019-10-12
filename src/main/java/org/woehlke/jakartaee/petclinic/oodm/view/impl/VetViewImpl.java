package org.woehlke.jakartaee.petclinic.oodm.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DualListModel;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.application.MessageProvider;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;
import org.woehlke.jakartaee.petclinic.oodm.services.SpecialtyService;
import org.woehlke.jakartaee.petclinic.oodm.services.VetService;
import org.woehlke.jakartaee.petclinic.oodm.view.VetView;
import org.woehlke.jakartaee.petclinic.oodm.view.flow.VetViewFlow;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
@Named("vetView")
@SessionScoped
public class VetViewImpl implements VetView {

    private MessageProvider provider;

    private static final long serialVersionUID = 2838339162976374606L;

    private static Logger log = LogManager.getLogger(VetViewImpl.class.getName());

    private final static String JSF_PAGE = "vet.jsf";

    @Inject
    private LanguageView languageView;

    @Inject
    private FrontendMessagesView frontendMessagesView;

    @Inject
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
        this.provider = new MessageProvider();
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

    public ResourceBundle getMsg() {
        return this.provider.getBundle();
    }

    public void setMsg(ResourceBundle msg) {
    }

    @Override
    public String showNewForm(){
        this.newEntity();
        this. initSpecialtiesPickList();
        this.vetViewFlow.setFlowStateNew();
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
        this.vetViewFlow.setFlowStateEdit();
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
        this.vetViewFlow.setFlowStateDelete();
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
        this.vetViewFlow.setFlowStateSearchResult();
        return JSF_PAGE;
    }

    @Override
    public void performSearch() {
        String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.veterinarian.search.done";
        String summary = this.provider.getBundle().getString(summaryKey);
        if(searchterm==null || searchterm.isEmpty()){
            this.vetViewFlow.setFlowStateList();
            String missingKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.missing";
            String detail = this.provider.getBundle().getString(missingKey);
            frontendMessagesView.addInfoMessage(summary, detail);
        } else {
            try {
                this.vetViewFlow.setFlowStateSearchResult();
                this.list = entityService.search(searchterm);
                String foundKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.found";
                String resultsKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.results";
                String found = this.provider.getBundle().getString(foundKey);
                String results = this.provider.getBundle().getString(resultsKey);
                String detail = found+" "+this.list.size()+ " "+ results +" "+searchterm;
                frontendMessagesView.addInfoMessage(summary, detail);
            } catch (Exception e){
                this.vetViewFlow.setFlowStateList();
                frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),searchterm);
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
            String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.veterinarian.addNew.done";
            String summary = this.provider.getBundle().getString(summaryKey);
            frontendMessagesView.addInfoMessage(summary, this.entity);
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
                log.debug(" added transient via saveEditedEntity: "+specialtyTransient.getPrimaryKeyWithId());
                Specialty specialty = specialtyService.findSpecialtyByName(specialtyTransient.getName());
                this.entity.addSpecialty(specialty);
            }
            this.entity = entityService.update(this.entity);
            this.selected = this.entity;
            String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.veterinarian.edit.done";
            String summary = this.provider.getBundle().getString(summaryKey);
            frontendMessagesView.addInfoMessage(summary, this.entity);
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
                String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.veterinarian.delete.done";
                String summary = this.provider.getBundle().getString(summaryKey);
                frontendMessagesView.addInfoMessage(summary, msgInfo);
            }
            loadList();
        } catch (EJBTransactionRolledbackException e) {
            String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.veterinarian.delete.denied";
            String summary = this.provider.getBundle().getString(summaryKey);
            frontendMessagesView.addWarnMessage(summary, this.selected);
        } catch (EJBException e){
            frontendMessagesView.addErrorMessage(e.getLocalizedMessage(),this.selected);
        }
    }

    @Override
    public void newEntity() {
        String firstName="add new first Name";
        String lastName="add new last Name";
        this.entity = new org.woehlke.jakartaee.petclinic.oodm.entities.Vet();
    }

    public VetViewFlow getVetViewFlow() {
        return vetViewFlow;
    }

    public void setVetViewFlow(VetViewFlow vetViewFlow) {
        this.vetViewFlow = vetViewFlow;
    }
}
