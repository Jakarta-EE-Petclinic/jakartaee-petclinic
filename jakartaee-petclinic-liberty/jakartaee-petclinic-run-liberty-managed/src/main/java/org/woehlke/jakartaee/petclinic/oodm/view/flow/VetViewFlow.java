package org.woehlke.jakartaee.petclinic.oodm.view.flow;


import org.woehlke.jakartaee.petclinic.frontend.web.common.HasCrudFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.impl.CrudViewFlow;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("vetViewFlow")
@SessionScoped
public class VetViewFlow extends CrudViewFlow implements HasCrudFlowState, Serializable {
    private static final long serialVersionUID = -4032116498859585466L;
}
