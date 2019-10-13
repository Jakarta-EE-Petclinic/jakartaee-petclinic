package org.woehlke.jakartaee.petclinic.oodm.view;

import org.woehlke.jakartaee.petclinic.frontend.web.common.*;
import org.woehlke.jakartaee.petclinic.frontend.web.owner.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;

import java.io.Serializable;

public interface OwnerView extends CrudView<Owner>,
		OwnersPetView,
		OwnersPetVisitView,
		HasOwnerModelOperations,
		HasOwnersPetModelOperations,
		HasLanguage,
		HasSearch,
		HasTreeNode,
		Serializable {

	long serialVersionUID = 3691413509555926089L;

	String showSelectedEntity();

	String cancelShow();
}
