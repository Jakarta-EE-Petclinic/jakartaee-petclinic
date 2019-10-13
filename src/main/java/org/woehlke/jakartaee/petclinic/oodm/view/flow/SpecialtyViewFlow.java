package org.woehlke.jakartaee.petclinic.oodm.view.flow;

import org.woehlke.jakartaee.petclinic.frontend.web.common.HasCrudFlowState;
import org.woehlke.jakartaee.petclinic.frontend.web.impl.CrudViewFlow;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("specialtyViewFlow")
@SessionScoped
public class SpecialtyViewFlow extends CrudViewFlow implements HasCrudFlowState, Serializable {
	private static final long serialVersionUID = -621000271002202654L;
}
