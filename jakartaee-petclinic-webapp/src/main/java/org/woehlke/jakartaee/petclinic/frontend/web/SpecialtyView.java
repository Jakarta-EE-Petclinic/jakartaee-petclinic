package org.woehlke.jakartaee.petclinic.frontend.web;

import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.ViewHasLanguage;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;

import java.io.Serializable;

public interface SpecialtyView extends CrudView<Specialty>, ViewHasLanguage, Serializable {

}
