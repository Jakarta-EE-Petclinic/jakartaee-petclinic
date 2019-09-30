package org.woehlke.jakartaee.petclinic.frontend.web.common;

public interface HasCrudFlowState {

    boolean isFlowStateList();
    boolean isFlowStateNew();
    boolean isFlowStateEdit();
    boolean isFlowStatDelete();
    boolean isFlowStateSearchResult();

    void setFlowStateList();
    void setFlowStateNew();
    void setFlowStateEdit();
    void setFlowStatDelete();
    void setFlowStateSearchResult();
}
