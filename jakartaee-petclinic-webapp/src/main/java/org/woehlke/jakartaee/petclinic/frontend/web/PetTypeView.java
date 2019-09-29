package org.woehlke.jakartaee.petclinic.frontend.web;

import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.ViewHasLanguage;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

import java.io.Serializable;

public interface PetTypeView extends CrudView<PetType>, ViewHasLanguage, Serializable {
}
