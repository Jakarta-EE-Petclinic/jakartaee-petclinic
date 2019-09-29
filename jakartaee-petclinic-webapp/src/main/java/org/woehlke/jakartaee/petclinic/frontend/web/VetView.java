package org.woehlke.jakartaee.petclinic.frontend.web;

import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudView;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;

public interface VetView extends CrudView<Vet> {

    Specialty findSpecialtyByName(String name);

    boolean isFlowStateList();
    boolean isFlowStateNew();
    boolean isFlowStateEdit();
    boolean isFlowStatDelete();
    boolean isFlowStateSearchResult();
}
