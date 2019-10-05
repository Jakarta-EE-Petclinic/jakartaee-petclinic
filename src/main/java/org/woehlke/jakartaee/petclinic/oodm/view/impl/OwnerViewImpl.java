package org.woehlke.jakartaee.petclinic.oodm.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;
import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;
import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;
import org.woehlke.jakartaee.petclinic.oodm.services.OwnerService;
import org.woehlke.jakartaee.petclinic.oodm.services.PetService;
import org.woehlke.jakartaee.petclinic.oodm.services.PetTypeService;
import org.woehlke.jakartaee.petclinic.oodm.services.VisitService;
import org.woehlke.jakartaee.petclinic.oodm.view.OwnerView;
import org.woehlke.jakartaee.petclinic.oodm.view.flow.OwnerViewFlow;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.01.14
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
@Named("ownerView")
@SessionScoped
public class OwnerViewImpl implements OwnerView {

    private static final long serialVersionUID = -4809817472969005481L;

    private static Logger log = LogManager.getLogger(OwnerViewImpl.class.getName());

    private final static String JSF_PAGE = "owner.jsf";

    @EJB
    private OwnerService entityService;

    @EJB
    private PetService petService;

    @EJB
    private PetTypeService petTypeService;

    @EJB
    private VisitService visitService;

    @Inject
    private LanguageView languageView;

    @Inject
    private FrontendMessagesView frontendMessagesView;

    @Inject
    private OwnerViewFlow ownerViewFlow;

    private String searchterm;

    private List<Owner> list;
    private Owner entity;
    private Owner selected;

    private Pet pet;
    private Pet petSelected;
    private List<PetType> petTypeList;
    private long petTypeId;

    private Visit visit;

    private TreeNode treeNodeRoot;

    @Override
    public void reloadEntityFromSelected() {
        if( this.selected != null ){
            this.entity = entityService.findById(this.selected.getId());
            this.selected = this.entity;
        }
    }

    @Override
    public void loadList() {
        this.list = entityService.getAll();
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
            frontendMessagesView.addWarnMessage(e, this.entity);
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
                this.ownerViewFlow.setFlowStateList();
                frontendMessagesView.addInfoMessage("Deleted", msgInfo);
            }
        } catch (EJBTransactionRolledbackException e) {
            this.ownerViewFlow.setFlowStateDelete();
            frontendMessagesView.addWarnMessage("cannot delete, object still in use", this.selected);
        } catch (EJBException e){
            this.ownerViewFlow.setFlowStateDelete();
            frontendMessagesView.addErrorMessage(e.getLocalizedMessage(),this.selected);
        }
    }

    @Override
    public void newEntity() {
        this.entity = new Owner();
    }

    @PostConstruct
    public void init(){
        this.ownerViewFlow.setFlowStateList();
        log.trace("postConstruct and init");
    }

    @Override
    public Visit getVisit() {
        return visit;
    }

    @Override
    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    @Override
    public Pet getPet() {
        return pet;
    }

    @Override
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public long getPetTypeId() {
        return petTypeId;
    }

    @Override
    public void setPetTypeId(long petTypeId) {
        this.petTypeId = petTypeId;
    }

    @Override
    public String getSearchterm() {
        return searchterm;
    }

    @Override
    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    @Override
    public Pet getPetSelected() {
        return petSelected;
    }

    @Override
    public void setPetSelected(Pet petSelected) {
        this.petSelected = petSelected;
    }

    @Override
    public TreeNode getTreeNodeRoot() {
        this.initTreeNodes();
        return treeNodeRoot;
    }

    @Override
    public void setTreeNodeRoot(TreeNode treeNodeRoot) {
        this.treeNodeRoot = treeNodeRoot;
    }

    @Override
    public void loadPetTypeList(){
        this.petTypeList = this.petTypeService.getAll();
    }

    @Override
    public List<PetType> getPetTypeList() {
        this.loadPetTypeList();
        return petTypeList;
    }

    @Override
    public void setPetTypeList(List<PetType> petTypeList) {
        this.petTypeList = petTypeList;
    }

    @Override
    public String search(){
        performSearch();
        return JSF_PAGE;
    }

    @Override
    public void performSearch() {
        if(searchterm==null || searchterm.isEmpty()){
            this.ownerViewFlow.setFlowStateList();
        } else {
            try {
                this.list = entityService.search(searchterm);
                this.ownerViewFlow.setFlowStateSearchResult();
                frontendMessagesView.addInfoMessage("Search ", "Found "+this.list.size()+ "results for searchterm "+searchterm);
            } catch (Exception e){
                log.warn(e.getMessage());
                this.ownerViewFlow.setFlowStateList();
                frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),searchterm);
            }
        }
    }

    @Override
    public String showNewForm(){
        this.entity = new Owner();
        this.ownerViewFlow.setFlowStateNew();
        return JSF_PAGE;
    }

    @Override
    public String saveNew(){
        try {
            this.entity = entityService.addNew(this.entity);
            this.selected=this.entity;
            this.ownerViewFlow.setFlowStateShow();
            frontendMessagesView.addInfoMessage("addded owner",this.entity);
        } catch (EJBException e){
            this.ownerViewFlow.setFlowStateNew();
            frontendMessagesView.addWarnMessage(e,this.entity);
        }
        return JSF_PAGE;
    }

    @Override
    public String cancelNew() {
        this.ownerViewFlow.setFlowStateList();
        return JSF_PAGE;
    }

    @Override
    public String cancelOwnerPetVisitNew(){
        this.ownerViewFlow.setFlowStateShow();
        return JSF_PAGE;
    }

    @Override
    public String showSelectedEntity(){
        long id = this.selected.getId();
        this.entity = entityService.findById(id);
        this.selected = this.entity;
        initTreeNodes();
        this.ownerViewFlow.setFlowStateEdit();
        return JSF_PAGE;
    }

    @Override
    public void initTreeNodes(){
        this.treeNodeRoot = new CheckboxTreeNode(this.entity);
        for(Pet pet:this.entity.getPets()){
            TreeNode petTreeNode = new CheckboxTreeNode(pet, treeNodeRoot);
            for(Visit visit:pet.getVisits()){
                TreeNode petVisitsTreeNode = new CheckboxTreeNode(visit, petTreeNode);
                petTreeNode.getChildren().add(petVisitsTreeNode);
            }
            this.treeNodeRoot.getChildren().add(petTreeNode);
        }
    }

    @Override
    public String showEditForm(){
        long id = this.selected.getId();
        this.entity = entityService.findById(id);
        this.selected = this.entity;
        this.ownerViewFlow.setFlowStateEdit();
        return JSF_PAGE;
    }

    @Override
    public String saveEdited() {
        try {
            this.entity= entityService.update(this.entity);
            this.selected=this.entity;
            this.ownerViewFlow.setFlowStateShow();
            frontendMessagesView.addInfoMessage("updated edited owner",this.entity);
        } catch (EJBException e){
            this.ownerViewFlow.setFlowStateEdit();
            frontendMessagesView.addWarnMessage(e,this.entity);
        }
        initTreeNodes();
        return JSF_PAGE;
    }

    @Override
    public String cancelEdited() {
        this.ownerViewFlow.setFlowStateShow();
        return JSF_PAGE;
    }

    @Override
    public String showDeleteForm() {
        this.ownerViewFlow.setFlowStateDelete();
        return JSF_PAGE;
    }

    @Override
    public String cancelDelete() {
        this.ownerViewFlow.setFlowStateShow();
        return JSF_PAGE;
    }

    @Override
    public String performDelete() {
        try {
            long id = this.selected.getId();
            String uuid = this.selected.getUuid().toString();
            String selectedPrimaryKey = this.selected.getPrimaryKey() + "("+id+","+uuid+")";
            entityService.delete(id);
            this.selected = null;
            this.entity = null;
            this.ownerViewFlow.setFlowStateList();
            frontendMessagesView.addInfoMessage("deleted selected owner", selectedPrimaryKey);
        } catch (EJBException e){
            this.ownerViewFlow.setFlowStateDelete();
            frontendMessagesView.addWarnMessage(e,this.selected);
        }
        return JSF_PAGE;
    }

    @Override
    public List<PetType> getAllPetTypes(){
        return petTypeService.getAll();
    }

    @Override
    public String showOwnerPetNewForm(){
        this.pet = new Pet();
        this.ownerViewFlow.setFlowStateNewPet();
        return JSF_PAGE;
    }

    @Override
    public String saveOwnerPetNew(){
        try {
            PetType petType = petTypeService.findById(this.petTypeId);
            this.pet.setType(petType);
            this.pet.setOwner(this.entity);
            this.pet = petService.addNew(this.pet);
            this.entity.addPet(this.pet);
            this.entity = entityService.update(this.entity);
            this.ownerViewFlow.setFlowStateShow();
            frontendMessagesView.addInfoMessage("saved new pet",this.pet.toString());
        } catch (EJBException e) {
            this.ownerViewFlow.setFlowStateNewPet();
            frontendMessagesView.addWarnMessage(e,this.pet);
        }
        return JSF_PAGE;
    }

    @Override
    public String cancelOwnerPetNew() {
        this.ownerViewFlow.setFlowStateShow();
        return JSF_PAGE;
    }

    @Override
    public String showOwnerPetEditForm(){
        if(this.petSelected != null){
            this.pet = petService.findById(this.petSelected.getId());
            this.ownerViewFlow.setFlowStateEditPet();
            return JSF_PAGE;
        } else {
            this.ownerViewFlow.setFlowStateShow();
            return JSF_PAGE;
        }
    }

    @Override
    public String saveOwnerPetEdit(){
        try {
            PetType petType = petTypeService.findById(this.petTypeId);
            this.pet.setType(petType);
            petService.update(this.pet);
            long ownerId = this.entity.getId();
            this.entity = this.entityService.findById(ownerId);
            this.selected = this.entity;
            this.ownerViewFlow.setFlowStateShow();
            frontendMessagesView.addInfoMessage("updated edited pet",this.pet);
        } catch (EJBException e){
            this.ownerViewFlow.setFlowStateEditPet();
            frontendMessagesView.addWarnMessage(e,this.pet);
        }
        return JSF_PAGE;
    }

    @Override
    public String cancelOwnerPetEdit() {
        this.ownerViewFlow.setFlowStateShow();
        return JSF_PAGE;
    }

    @Override
    public String showOwnerPetVisitNewForm(){
        if(this.petSelected != null) {
            this.pet = petService.findById(this.petSelected.getId());
            this.petTypeId = this.pet.getType().getId();
            this.visit = new Visit();
            this.ownerViewFlow.setFlowStateNewVisit();
            return JSF_PAGE;
        } else {
            this.ownerViewFlow.setFlowStateShow();
            frontendMessagesView.addWarnMessage("Add New Visit","You must select a Pet first.");
            return JSF_PAGE;
        }
    }

    @Override
    public String saveOwnerPetVisitNew(){
        try {
            if(this.petSelected != null) {
                this.pet = petService.findById(this.petSelected.getId());
                this.visit.setPet(this.pet);
                this.pet.addVisit(this.visit);
                this.visit = this.entityService.addNewVisit(this.visit);
                log.trace("owner1: " + this.entity.toString());
                long ownerId = this.entity.getId();
                this.entity = this.entityService.findById(ownerId);
                this.selected = this.entity;
                log.trace("owner2: "+this.entity.toString());
                frontendMessagesView.addInfoMessage("added new visit",this.visit);
                this.ownerViewFlow.setFlowStateShow();
                return JSF_PAGE;
            } else {
                this.ownerViewFlow.setFlowStateShow();
                frontendMessagesView.addWarnMessage("Add New Visit","You must select a Pet first.");
                return JSF_PAGE;
            }
        } catch (EJBException e){
            this.ownerViewFlow.setFlowStateNewVisit();
            frontendMessagesView.addWarnMessage(e,this.visit);
            return JSF_PAGE;
        }
    }

    @PreDestroy
    public void preDestroy(){
        log.trace("preDestroy");
    }

    @Override
    public List<Owner> getList() {
        loadList();
        return list;
    }

    @Override
    public void setList(List<Owner> list) {
        this.list = list;
    }

    @Override
    public Owner getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Owner entity) {
        this.entity = entity;
    }

    @Override
    public Owner getSelected() {
        return selected;
    }

    @Override
    public void setSelected(Owner selected) {
        this.selected = selected;
    }

    @Override
    public LanguageView getLanguageView() {
        return languageView;
    }

    @Override
    public void setLanguageView(LanguageView languageView) {
        this.languageView = languageView;
    }

    public FrontendMessagesView getFrontendMessagesView() {
        return frontendMessagesView;
    }

    public void setFrontendMessagesView(FrontendMessagesView frontendMessagesView) {
        this.frontendMessagesView = frontendMessagesView;
    }

    public OwnerViewFlow getOwnerViewFlow() {
        return ownerViewFlow;
    }

    public void setOwnerViewFlow(OwnerViewFlow ownerViewFlow) {
        this.ownerViewFlow = ownerViewFlow;
    }
}
