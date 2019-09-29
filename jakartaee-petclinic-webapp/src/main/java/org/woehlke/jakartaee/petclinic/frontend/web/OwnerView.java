package org.woehlke.jakartaee.petclinic.frontend.web;

import org.primefaces.model.TreeNode;
import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.ViewHasLanguage;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;

import java.io.Serializable;

public interface OwnerView extends CrudView<Owner>, ViewHasLanguage, OwnersPetView, OwnersPetVisitView, Serializable {

    String cancelOwnerEdit();
    String cancelOwnerNew();
    String cancelOwnerShow();

    void setRoot(TreeNode root);
    TreeNode getRoot();

}
