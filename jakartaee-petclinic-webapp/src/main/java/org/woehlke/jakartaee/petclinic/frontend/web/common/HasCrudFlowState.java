package org.woehlke.jakartaee.petclinic.frontend.web.common;

import java.io.Serializable;

public interface HasCrudFlowState extends Serializable {

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
