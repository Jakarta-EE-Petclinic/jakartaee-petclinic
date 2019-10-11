package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudViewFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasCrudFlowState;

import java.io.Serializable;

public abstract class CrudViewFlow implements HasCrudFlowState, Serializable {

    private static final long serialVersionUID = 8853867570285389553L;

    private CrudViewFlowState flowState;

    protected CrudViewFlowState getFlowState() {
        if(this.flowState == null){
            this.flowState = CrudViewFlowState.LIST;
        }
        return flowState;
    }

    @Override
    public boolean isFlowStateList(){
        return CrudViewFlowState.LIST == this.getFlowState();
    }

    @Override
    public boolean isFlowStateNew(){
        return CrudViewFlowState.NEW == this.getFlowState();
    }

    @Override
    public boolean isFlowStateEdit(){
        return CrudViewFlowState.EDIT == this.getFlowState();
    }

    @Override
    public boolean isFlowStateDelete(){
        return CrudViewFlowState.DELETE == this.getFlowState();
    }

    @Override
    public boolean isFlowStateSearchResult(){
        return CrudViewFlowState.LIST_SEARCH_RESULT == this.getFlowState();
    }


    @Override
    public void setFlowStateList(){
        this.flowState = CrudViewFlowState.LIST;
    }

    @Override
    public void setFlowStateNew(){
        this.flowState = CrudViewFlowState.NEW;
    }

    @Override
    public void setFlowStateEdit(){
        this.flowState = CrudViewFlowState.EDIT;
    }

    @Override
    public void setFlowStateDelete(){
        this.flowState = CrudViewFlowState.DELETE;
    }

    @Override
    public void setFlowStateSearchResult(){
        this.flowState = CrudViewFlowState.LIST_SEARCH_RESULT;
    }
}
