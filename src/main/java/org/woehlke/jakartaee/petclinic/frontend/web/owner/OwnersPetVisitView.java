package org.woehlke.jakartaee.petclinic.frontend.web.owner;

import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;

import java.io.Serializable;

public interface OwnersPetVisitView extends Serializable {

  long serialVersionUID = 2400107254778567823L;

  String showOwnerPetVisitNewForm();

  String saveOwnerPetVisitNew();

  String cancelOwnerPetVisitNew();

  Visit getVisit();

  void setVisit(Visit visit);
}
