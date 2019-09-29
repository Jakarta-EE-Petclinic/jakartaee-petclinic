package org.woehlke.javaee8.petclinic.frontend.web;

import org.woehlke.javaee8.petclinic.oodm.entities.Pet;
import org.woehlke.javaee8.petclinic.oodm.entities.PetType;

import java.io.Serializable;
import java.util.List;

public interface OwnersPetView extends Serializable {

    String showOwnerPetNewForm();
    String saveOwnerPetNew();
    String cancelOwnerPetNew();

    String showOwnerPetEditForm();
    String saveOwnerPetEdit();
    String cancelOwnerPetEdit();

    Pet getPet();
    void setPet(Pet pet);
    Pet getPetSelected();
    void setPetSelected(Pet petSelected);
    long getPetTypeId();
    void setPetTypeId(long petTypeId);
    List<PetType> getAllPetTypes();
}
