package org.woehlke.javaee8.petclinic.frontend.web;

import org.woehlke.javaee8.petclinic.frontend.web.common.CrudView;
import org.woehlke.javaee8.petclinic.frontend.web.common.ViewHasLanguage;
import org.woehlke.javaee8.petclinic.oodm.entities.Specialty;

import java.io.Serializable;

public interface SpecialtyView extends CrudView<Specialty>, ViewHasLanguage, Serializable {

}
