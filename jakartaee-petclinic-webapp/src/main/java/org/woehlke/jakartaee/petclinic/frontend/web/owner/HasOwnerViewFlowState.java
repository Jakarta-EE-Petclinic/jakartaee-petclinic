package org.woehlke.jakartaee.petclinic.frontend.web.owner;

import org.woehlke.jakartaee.petclinic.frontend.web.common.HasCrudFlowState;

import java.io.Serializable;

public interface HasOwnerViewFlowState extends HasCrudFlowState, Serializable {

    long serialVersionUID = 6654391340545426308L;

    boolean isFlowStateShow();
    void setFlowStateShow();

    boolean isFlowStateNewPet();
    void setFlowStateNewPet();

    boolean isFlowStateEditPet();
    void setFlowStateEditPet();

    boolean isFlowStateNewVisit();
    void setFlowStateNewVisit();

   boolean isRenderPanelAddNewOwner();
   boolean isRenderPanelEditOwner();
   boolean isRenderPanelOwner();
   boolean isRenderPanelOwnerList();
}
