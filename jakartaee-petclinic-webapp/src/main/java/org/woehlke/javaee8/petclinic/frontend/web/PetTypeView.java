package org.woehlke.javaee8.petclinic.frontend.web;

import org.woehlke.javaee8.petclinic.frontend.web.common.CrudView;
import org.woehlke.javaee8.petclinic.frontend.web.common.ViewHasLanguage;
import org.woehlke.javaee8.petclinic.oodm.entities.PetType;

import java.io.Serializable;

public interface PetTypeView extends CrudView<PetType>, ViewHasLanguage, Serializable {
}
