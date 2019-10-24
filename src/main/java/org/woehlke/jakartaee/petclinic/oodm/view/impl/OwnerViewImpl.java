package org.woehlke.jakartaee.petclinic.oodm.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.woehlke.jakartaee.petclinic.application.MessageProvider;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
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
import java.util.ResourceBundle;

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

  private final static String JSF_PAGE = "owner.jsf";

  private static Logger log = LogManager.getLogger(OwnerViewImpl.class.getName());

  private MessageProvider provider;

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
  @PostConstruct
  public void init() {
    this.provider = new MessageProvider();
    this.ownerViewFlow.setFlowStateList();
    log.trace("postConstruct");
  }

  @Override
  public void reloadEntityFromSelected() {
    if (this.selected != null) {
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
      if (this.entity != null) {
        this.entity = this.entityService.addNew(this.entity);
        this.selected = this.entity;
        this.ownerViewFlow.setFlowStateShow();
        String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.owner.addNew.done";
        String summary = this.provider.getBundle().getString(summaryKey);
        frontendMessagesView.addInfoMessage(summary, this.entity);
      } else {
        this.newEntity();
        this.ownerViewFlow.setFlowStateNew();
      }
    } catch (EJBException e) {
      this.ownerViewFlow.setFlowStateNew();
      frontendMessagesView.addWarnMessage(e, this.entity);
    }
  }

  @Override
  public void saveEditedEntity() {
    try {
      if (this.entity != null) {
        this.entity = entityService.update(this.entity);
        this.selected = this.entity;
        this.ownerViewFlow.setFlowStateShow();
        String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.owner.edit.done";
        String summary = this.provider.getBundle().getString(summaryKey);
        frontendMessagesView.addInfoMessage(summary, this.entity);
      } else {
        this.ownerViewFlow.setFlowStateList();
      }
    } catch (EJBException e) {
      this.ownerViewFlow.setFlowStateEdit();
      frontendMessagesView.addWarnMessage(e, this.entity);
    }
  }

  @Override
  public void deleteSelectedEntity() {
    try {
      if (this.selected != null) {
        String msgInfo = this.selected.getPrimaryKey();
        if (this.selected.compareTo(this.entity) == 0) {
          this.entity = null;
        }
        entityService.delete(this.selected.getId());
        this.selected = null;
        this.ownerViewFlow.setFlowStateList();
        String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.owner.delete.done";
        String summary = this.provider.getBundle().getString(summaryKey);
        frontendMessagesView.addInfoMessage(summary, msgInfo);
      }
    } catch (EJBTransactionRolledbackException e) {
      this.ownerViewFlow.setFlowStateDelete();
      String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.owner.delete.denied";
      String summary = this.provider.getBundle().getString(summaryKey);
      frontendMessagesView.addWarnMessage(summary, this.selected);
    } catch (EJBException e) {
      this.ownerViewFlow.setFlowStateDelete();
      frontendMessagesView.addErrorMessage(e.getLocalizedMessage(), this.selected);
    }
  }

  @Override
  public void newEntity() {
    this.entity = new Owner();
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
  public void loadPetTypeList() {
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
  public String search() {
    performSearch();
    return JSF_PAGE;
  }

  @Override
  public void performSearch() {
    String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.owner.search.done";
    String summary = this.provider.getBundle().getString(summaryKey);
    if (searchterm == null || searchterm.isEmpty()) {
      this.ownerViewFlow.setFlowStateList();
      String missingKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.missing";
      String detail = this.provider.getBundle().getString(missingKey);
      frontendMessagesView.addInfoMessage(summary, detail);
    } else {
      try {
        this.ownerViewFlow.setFlowStateSearchResult();
        this.list = entityService.search(searchterm);
        String foundKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.found";
        String resultsKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.list.searchterm.results";
        String found = this.provider.getBundle().getString(foundKey);
        String results = this.provider.getBundle().getString(resultsKey);
        String detail = found + " " + this.list.size() + " " + results + " " + searchterm;
        frontendMessagesView.addInfoMessage(summary, detail);
      } catch (Exception e) {
        this.ownerViewFlow.setFlowStateList();
        frontendMessagesView.addWarnMessage(e.getLocalizedMessage(), searchterm);
      }
    }
  }

  @Override
  public String showNewForm() {
    this.newEntity();
    this.ownerViewFlow.setFlowStateNew();
    return JSF_PAGE;
  }

  @Override
  public String saveNew() {
    this.saveNewEntity();
    this.ownerViewFlow.setFlowStateShow();
    return JSF_PAGE;
  }

  @Override
  public String cancelNew() {
    this.ownerViewFlow.setFlowStateList();
    return JSF_PAGE;
  }

  @Override
  public String cancelOwnerPetVisitNew() {
    this.ownerViewFlow.setFlowStateShow();
    return JSF_PAGE;
  }

  @Override
  public String showSelectedEntity() {
    this.reloadEntityFromSelected();
    this.initTreeNodes();
    this.ownerViewFlow.setFlowStateShow();
    return JSF_PAGE;
  }

  @Override
  public void initTreeNodes() {
    this.treeNodeRoot = new CheckboxTreeNode(this.entity);
    for (Pet pet : this.entity.getPets()) {
      TreeNode petTreeNode = new CheckboxTreeNode(pet, treeNodeRoot);
      for (Visit visit : pet.getVisits()) {
        TreeNode petVisitsTreeNode = new CheckboxTreeNode(visit, petTreeNode);
        petTreeNode.getChildren().add(petVisitsTreeNode);
      }
      this.treeNodeRoot.getChildren().add(petTreeNode);
    }
  }

  @Override
  public String showEditForm() {
    this.reloadEntityFromSelected();
    this.ownerViewFlow.setFlowStateEdit();
    return JSF_PAGE;
  }

  @Override
  public String saveEdited() {
    this.saveEditedEntity();
    this.initTreeNodes();
    return JSF_PAGE;
  }

  @Override
  public String cancelShow() {
    this.ownerViewFlow.setFlowStateList();
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
      String selectedPrimaryKey = this.selected.getPrimaryKey() + "(" + id + "," + uuid + ")";
      entityService.delete(id);
      this.selected = null;
      this.entity = null;
      this.ownerViewFlow.setFlowStateList();
      String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.owner.delete.done";
      String summary = this.provider.getBundle().getString(summaryKey);
      frontendMessagesView.addInfoMessage(summary, selectedPrimaryKey);
    } catch (EJBException e) {
      this.ownerViewFlow.setFlowStateDelete();
      frontendMessagesView.addWarnMessage(e, this.selected);
    }
    return JSF_PAGE;
  }

  @Override
  public List<PetType> getAllPetTypes() {
    return petTypeService.getAll();
  }

  @Override
  public String showOwnerPetNewForm() {
    this.pet = new Pet();
    this.ownerViewFlow.setFlowStateNewPet();
    return JSF_PAGE;
  }

  @Override
  public String saveOwnerPetNew() {
    try {
      PetType petType = petTypeService.findById(this.petTypeId);
      this.pet.setType(petType);
      this.pet.setOwner(this.entity);
      this.pet = petService.addNew(this.pet);
      this.entity.addPet(this.pet);
      this.entity = entityService.update(this.entity);
      this.ownerViewFlow.setFlowStateShow();
      String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.owner.addNew.done";
      String summary = this.provider.getBundle().getString(summaryKey);
      frontendMessagesView.addInfoMessage(summary, this.pet);
    } catch (EJBException e) {
      this.ownerViewFlow.setFlowStateNewPet();
      frontendMessagesView.addWarnMessage(e, this.pet);
    }
    return JSF_PAGE;
  }

  @Override
  public String cancelOwnerPetNew() {
    this.ownerViewFlow.setFlowStateShow();
    return JSF_PAGE;
  }

  @Override
  public String showOwnerPetEditForm() {
    if (this.petSelected != null) {
      this.pet = petService.findById(this.petSelected.getId());
      this.ownerViewFlow.setFlowStateEditPet();
      return JSF_PAGE;
    } else {
      this.ownerViewFlow.setFlowStateShow();
      return JSF_PAGE;
    }
  }

  @Override
  public String saveOwnerPetEdit() {
    try {
      PetType petType = petTypeService.findById(this.petTypeId);
      this.pet.setType(petType);
      petService.update(this.pet);
      long ownerId = this.entity.getId();
      this.entity = this.entityService.findById(ownerId);
      this.selected = this.entity;
      this.ownerViewFlow.setFlowStateShow();
      String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.owner.edit.done";
      String summary = this.provider.getBundle().getString(summaryKey);
      frontendMessagesView.addInfoMessage(summary, this.pet);
    } catch (EJBException e) {
      this.ownerViewFlow.setFlowStateEditPet();
      frontendMessagesView.addWarnMessage(e, this.pet);
    }
    return JSF_PAGE;
  }

  @Override
  public String cancelOwnerPetEdit() {
    this.ownerViewFlow.setFlowStateShow();
    return JSF_PAGE;
  }

  @Override
  public String showOwnerPetVisitNewForm() {
    if (this.petSelected != null) {
      this.pet = petService.findById(this.petSelected.getId());
      this.petTypeId = this.pet.getType().getId();
      this.visit = new Visit();
      this.ownerViewFlow.setFlowStateNewVisit();
      return JSF_PAGE;
    } else {
      this.ownerViewFlow.setFlowStateShow();
      String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.visit.addNew";
      String summary = this.provider.getBundle().getString(summaryKey);
      String msgKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.visit.addNew.denied";
      String msg = this.provider.getBundle().getString(msgKey);
      frontendMessagesView.addWarnMessage(summary, msg);
      return JSF_PAGE;
    }
  }

  @Override
  public String saveOwnerPetVisitNew() {
    try {
      if (this.petSelected != null) {
        this.pet = petService.findById(this.petSelected.getId());
        this.visit.setPet(this.pet);
        this.pet.addVisit(this.visit);
        this.visit = this.entityService.addNewVisit(this.visit);
        log.trace("owner1: " + this.entity.toString());
        long ownerId = this.entity.getId();
        this.entity = this.entityService.findById(ownerId);
        this.selected = this.entity;
        log.trace("owner2: " + this.entity.toString());
        String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.visit.addNew.done";
        String summary = this.provider.getBundle().getString(summaryKey);
        frontendMessagesView.addInfoMessage(summary, this.visit);
        this.ownerViewFlow.setFlowStateShow();
        return JSF_PAGE;
      } else {
        this.ownerViewFlow.setFlowStateShow();
        String summaryKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.visit.addNew";
        String summary = this.provider.getBundle().getString(summaryKey);
        String msgKey = "org.woehlke.jakartaee.petclinic.frontend.web.entity.contentTitleHeadline.visit.addNew.denied";
        String msg = this.provider.getBundle().getString(msgKey);
        frontendMessagesView.addWarnMessage(summary, msg);
        return JSF_PAGE;
      }
    } catch (EJBException e) {
      this.ownerViewFlow.setFlowStateNewVisit();
      frontendMessagesView.addWarnMessage(e, this.visit);
      return JSF_PAGE;
    }
  }

  @Override
  @PreDestroy
  public void preDestroy() {
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

  @Override
  public ResourceBundle getMsg() {
    return this.provider.getBundle();
  }

  public void setMsg(ResourceBundle msg) {

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
