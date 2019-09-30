package org.woehlke.jakartaee.petclinic.oodm.view.flow;

import org.woehlke.jakartaee.petclinic.frontend.web.owner.HasOwnerViewFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.owner.OwnerViewFlowState;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SuppressWarnings("deprecation")
@ManagedBean(name="ownerViewFlow")
@SessionScoped
public class OwnerViewFlow implements HasOwnerViewFlowState, Serializable {

    private OwnerViewFlowState flowState;

    @Override
    public boolean isFlowStateShow(){
        return this.flowState == OwnerViewFlowState.SHOW;
    }

    @Override
    public boolean isFlowStateList(){
        return this.flowState == OwnerViewFlowState.LIST;
    }

    @Override
    public boolean isFlowStateNew(){
        return  this.flowState == OwnerViewFlowState.NEW;
    }

    @Override
    public boolean isFlowStateEdit(){
        return  this.flowState == OwnerViewFlowState.EDIT;
    }

    @Override
    public boolean isFlowStatDelete(){
        return  this.flowState == OwnerViewFlowState.DELETE;
    }

    @Override
    public boolean isFlowStateSearchResult(){
        return  this.flowState == OwnerViewFlowState.LIST_SEARCH_RESULT;
    }

    @Override
    public boolean isFlowStateNewPet(){
        return  this.flowState == OwnerViewFlowState.NEW_PET;
    }

    @Override
    public boolean isFlowStateEditPet(){
        return  this.flowState == OwnerViewFlowState.EDIT_PET;
    }

    @Override
    public boolean isFlowStateNewVisit(){ return this.flowState == OwnerViewFlowState.NEW_VISIT; }

    @Override
    public void setFlowStateNewVisit(){ this.flowState = OwnerViewFlowState.NEW_VISIT; }

    @Override
    public void setFlowStateShow(){
        this.flowState = OwnerViewFlowState.SHOW;
    }

    @Override
    public void setFlowStateNewPet() {
        this.flowState = OwnerViewFlowState.NEW_PET;
    }

    @Override
    public void setFlowStateEditPet() {
        this.flowState = OwnerViewFlowState.EDIT_PET;
    }

    @Override
    public void setFlowStateList(){
        this.flowState = OwnerViewFlowState.LIST;
    }

    @Override
    public void setFlowStateNew(){
        this.flowState = OwnerViewFlowState.NEW;
    }

    @Override
    public void setFlowStateEdit(){
        this.flowState = OwnerViewFlowState.EDIT;
    }

    @Override
    public void setFlowStatDelete(){
        this.flowState = OwnerViewFlowState.DELETE;
    }

    @Override
    public void setFlowStateSearchResult(){
        this.flowState = OwnerViewFlowState.LIST_SEARCH_RESULT;
    }

    public boolean renderPanelAddNewOwner(){
        return this.isFlowStateNew();
    }

    public boolean renderPanelEditOwner(){
        return this.isFlowStateEdit();
    }

    public boolean renderPanelOwner(){
        return this.isFlowStateShow();
    }

    public boolean renderPanelPetsAndVisitsTable(){
        return this.renderPanelOwner();
    }

    public boolean renderPanelPetsAndVisitsTree(){
        return this.renderPanelOwner();
    }

    public boolean renderPanelOwnerList(){
        return true;
    }
}
