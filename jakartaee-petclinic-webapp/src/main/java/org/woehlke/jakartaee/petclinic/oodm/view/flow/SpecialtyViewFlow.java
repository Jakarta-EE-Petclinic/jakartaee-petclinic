package org.woehlke.jakartaee.petclinic.oodm.view.flow;

import org.woehlke.jakartaee.petclinic.frontend.web.common.HasCrudFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.impl.CrudViewFlow;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SuppressWarnings("deprecation")
@ManagedBean(name="specialtyViewFlow")
@SessionScoped
public class SpecialtyViewFlow extends CrudViewFlow implements HasCrudFlowState, Serializable {
}
