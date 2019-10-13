package org.woehlke.jakartaee.petclinic.oodm.view;

import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasLanguage;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasSearch;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasTreeNode;
import org.woehlke.jakartaee.petclinic.frontend.web.owner.HasOwnerModelOperations;
import org.woehlke.jakartaee.petclinic.frontend.web.owner.HasOwnersPetModelOperations;
import org.woehlke.jakartaee.petclinic.frontend.web.owner.OwnersPetView;
import org.woehlke.jakartaee.petclinic.frontend.web.owner.OwnersPetVisitView;
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
