package org.woehlke.jakartaee.petclinic.oodm.view.flow;

import org.woehlke.jakartaee.petclinic.frontend.web.owner.HasOwnerViewFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.owner.OwnerViewFlowState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("ownerViewFlow")
@SessionScoped
public class OwnerViewFlow implements HasOwnerViewFlowState, Serializable {

    private static final long serialVersionUID = 4530858836742945751L;

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
    public boolean isFlowStateDelete(){
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
    public void setFlowStateDelete(){
        this.flowState = OwnerViewFlowState.DELETE;
    }

    @Override
    public void setFlowStateSearchResult(){
        this.flowState = OwnerViewFlowState.LIST_SEARCH_RESULT;
    }


    public boolean isRenderPanelAddNewOwner(){
        return this.isFlowStateNew();
    }

    public boolean isRenderPanelEditOwner(){
        return this.isFlowStateEdit();
    }

    public boolean isRenderPanelOwner(){
        return this.isFlowStateShow();
    }

    public boolean isRenderPanelOwnerList(){
        return true;
    }

    public boolean isRenderPanelEditOwnersPet(){
        return this.isFlowStateEditPet();
    }

    public boolean isRenderPanelAddNewOwnersPet(){
        return this.isFlowStateNewPet();
    }

    public boolean isRenderPanelAddNewOwnersPetVisit(){
        return this.isFlowStateNewVisit();
    }
}
