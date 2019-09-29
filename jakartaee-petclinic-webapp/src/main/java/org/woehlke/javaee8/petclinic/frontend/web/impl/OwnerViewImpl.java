package org.woehlke.javaee8.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.woehlke.javaee8.petclinic.frontend.web.LanguageView;
import org.woehlke.javaee8.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.javaee8.petclinic.oodm.entities.Owner;
import org.woehlke.javaee8.petclinic.oodm.entities.Pet;
import org.woehlke.javaee8.petclinic.oodm.entities.PetType;
import org.woehlke.javaee8.petclinic.oodm.entities.Visit;
import org.woehlke.javaee8.petclinic.oodm.services.OwnerService;
import org.woehlke.javaee8.petclinic.oodm.services.PetService;
import org.woehlke.javaee8.petclinic.oodm.services.PetTypeService;
import org.woehlke.javaee8.petclinic.oodm.services.VisitService;
import org.woehlke.javaee8.petclinic.frontend.web.OwnerView;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.01.14
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("deprecation")
@ManagedBean(name="ownerView")
@SessionScoped
public class OwnerViewImpl implements OwnerView {

    private static Logger log = LogManager.getLogger(OwnerViewImpl.class.getName());

    @EJB
    private PetService petService;

    @EJB
    private PetTypeService petTypeService;

    @EJB
    private VisitService visitService;

    @EJB
    private OwnerService entityService;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{languageView}")
    private LanguageView languageView;

    @SuppressWarnings("deprecation")
    @ManagedProperty(value = "#{frontendMessagesView}")
    private FrontendMessagesView frontendMessagesView;

    private String searchterm;

    private List<Owner> list;
    private Owner entity;
    private Owner selected;

    private Pet pet;
    private Pet petSelected;
    private long petTypeId;

    private Visit visit;

    private TreeNode root;

    @PostConstruct
    public void init(){
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
    public TreeNode getRoot() {
        initTreeNodes();
        return root;
    }

    @Override
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    @Override
    public String search(){
        performSearch();
        return "ownerList.jsf";
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
                log.warn(e.getMessage());
                frontendMessagesView.addWarnMessage(e.getLocalizedMessage(),searchterm);
                this.list = entityService.getAll();
            }
        }
    }

    @Override
    public String showEntityList() {
        this.list = entityService.getAll();
        return "ownerList.jsf";
    }

    @Override
    public String showNewForm(){
        this.entity = new Owner();
        return "ownerNew.jsf";
    }

    @Override
    public String saveNew(){
        try {
            this.entity = entityService.addNew(this.entity);
            this.selected=this.entity;
            frontendMessagesView.addInfoMessage("addded owner",this.entity);
            return "owner.jsf?faces-redirect=true";
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
            frontendMessagesView.addWarnMessage(e,this.entity);
            this.list = entityService.getAll();
            return "ownerList.jsf?faces-redirect=true";
        }
    }

    @Override
    public String cancel(){
        this.list = entityService.getAll();
        return "ownerList.jsf?faces-redirect=true";
    }

    @Override
    public String cancelOwnerShow(){
        return "ownerList.jsf?faces-redirect=true";
    }

    @Override
    public String cancelOwnerNew(){
        return "ownerList.jsf?faces-redirect=true";
    }

    @Override
    public String cancelOwnerEdit(){
        return "owner.jsf?faces-redirect=true";
    }

    @Override
    public String cancelOwnerPetVisitNew(){
        return "ownerList.jsf?faces-redirect=true";
    }

    @Override
    public String showSelectedEntity(){
        long id = this.selected.getId();
        this.entity = entityService.findById(id);
        initTreeNodes();
        return "owner.jsf";
    }

    private void initTreeNodes(){
        this.root = new CheckboxTreeNode(this.entity);
        for(Pet pet:this.entity.getPets()){
            TreeNode petTreeNode = new CheckboxTreeNode(pet, root);
            for(Visit visit:pet.getVisits()){
                TreeNode petVisitsTreeNode = new CheckboxTreeNode(visit, petTreeNode);
                petTreeNode.getChildren().add(petVisitsTreeNode);
            }
            this.root.getChildren().add(petTreeNode);
        }
    }

    @Override
    public String showEditForm(){
        return "ownerEdit.jsf";
    }

    @Override
    public String saveEdited() {
        try {
            this.entity= entityService.update(this.entity);
            this.selected=this.entity;
            frontendMessagesView.addInfoMessage("updated edited owner",this.entity);
        } catch (EJBException e){
            log.warn(e.getMessage()+this.entity.toString());
            frontendMessagesView.addWarnMessage(e,this.entity);
        }
        initTreeNodes();
        return "owner.jsf";
    }

    private void fetchList(){
        this.list = entityService.getAll();
        if(this.list.size()>0){
            int indexFirst = 0;
            this.selected = this.list.get(indexFirst);
            this.entity = this.selected;
        }
    }

    @Override
    public String deleteSelected() {
        try {
            long id = this.selected.getId();
            String uuid = this.selected.getUuid().toString();
            String selectedPrimaryKey = this.selected.getPrimaryKey() + "("+id+","+uuid+")";
            entityService.delete(id);
            this.selected = null;
            this.entity = null;
            frontendMessagesView.addInfoMessage("deleted selected owner", selectedPrimaryKey);
        } catch (EJBException e){
            frontendMessagesView.addWarnMessage(e,this.selected);
        }
        fetchList();
        return "ownerList.jsf";
    }

    @Override
    public List<PetType> getAllPetTypes(){
        return petTypeService.getAll();
    }

    @Override
    public String showOwnerPetNewForm(){
        this.pet = new Pet();
        return "ownerPetNew.jsf";
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
            frontendMessagesView.addInfoMessage("saved new pet",this.pet.toString());
        } catch (EJBException e) {
            frontendMessagesView.addWarnMessage(e,this.pet);
        }
        return "owner.jsf?faces-redirect=true";
    }

    @Override
    public String cancelOwnerPetNew() {
        return "owner.jsf?faces-redirect=true";
    }

    @Override
    public String showOwnerPetEditForm(){
        if(this.petSelected != null){
            this.pet = petService.findById(this.petSelected.getId());
            return "petEdit.jsf";
        } else {
            return "ownerList.jsf";
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
            frontendMessagesView.addInfoMessage("updated edited pet",this.pet);
        } catch (EJBException e){
            frontendMessagesView.addWarnMessage(e,this.pet);
        }
        return "owner.jsf";
    }

    @Override
    public String cancelOwnerPetEdit() {
        return "owner.jsf";
    }

    @Override
    public String showOwnerPetVisitNewForm(){
        this.pet = petService.findById(this.pet.getId());
        this.petTypeId = this.pet.getType().getId();
        this.visit = new Visit();
        return "ownerPetVisitNew.jsf";
    }

    @Override
    public String saveOwnerPetVisitNew(){
        try {
            if(this.petSelected != null) {
                this.pet = petService.findById(this.petSelected.getId());
                this.visit.setPet(this.pet);
                this.pet.addVisit(this.visit);
                this.visit = this.entityService.addNewVisit(this.visit);
                this.pet = this.petService.update(this.pet);
                log.trace("owner1: " + this.entity.toString());
                long ownerId = this.entity.getId();
                this.entity = this.entityService.findById(ownerId);
                frontendMessagesView.addInfoMessage("added new visit",this.visit);
            }
        } catch (EJBException e){
            frontendMessagesView.addWarnMessage(e,this.visit);
        }
        log.trace("owner2: "+this.entity.toString());
        return "owner.jsf";
    }

    @PreDestroy
    public void preDestroy(){
        log.trace("preDestroy");
    }

    @Override
    public List<Owner> getList() {
        fetchList();
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

}
