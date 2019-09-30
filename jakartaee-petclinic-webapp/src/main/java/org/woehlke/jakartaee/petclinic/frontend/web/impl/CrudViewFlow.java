package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudViewFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasCrudFlowState;

import java.io.Serializable;

public abstract class CrudViewFlow implements HasCrudFlowState, Serializable {

    private static final long serialVersionUID = 8853867570285389553L;

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
    public void setFlowStatDelete(){
        this.flowState = CrudViewFlowState.DELETE;
    }

    @Override
    public void setFlowStateSearchResult(){
        this.flowState = CrudViewFlowState.LIST_SEARCH_RESULT;
    }
}
