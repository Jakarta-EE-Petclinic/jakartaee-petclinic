package org.woehlke.javaee8.petclinic.frontend.web;

import org.woehlke.javaee8.petclinic.oodm.entities.Visit;

import java.io.Serializable;

public interface OwnersPetVisitView extends Serializable {

    String showOwnerPetVisitNewForm();
    String saveOwnerPetVisitNew();
    String cancelOwnerPetVisitNew();

    Visit getVisit();
    void setVisit(Visit visit);
}
