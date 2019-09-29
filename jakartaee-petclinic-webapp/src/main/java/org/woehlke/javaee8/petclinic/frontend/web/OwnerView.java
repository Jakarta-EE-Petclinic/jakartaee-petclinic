package org.woehlke.javaee8.petclinic.frontend.web;

import org.primefaces.model.TreeNode;
import org.woehlke.javaee8.petclinic.frontend.web.common.CrudView;
import org.woehlke.javaee8.petclinic.frontend.web.common.ViewHasLanguage;
import org.woehlke.javaee8.petclinic.oodm.entities.Owner;

import java.io.Serializable;

public interface OwnerView extends CrudView<Owner>, ViewHasLanguage, OwnersPetView, OwnersPetVisitView, Serializable {

    String cancelOwnerEdit();
    String cancelOwnerNew();
    String cancelOwnerShow();

    void setRoot(TreeNode root);
    TreeNode getRoot();

}
