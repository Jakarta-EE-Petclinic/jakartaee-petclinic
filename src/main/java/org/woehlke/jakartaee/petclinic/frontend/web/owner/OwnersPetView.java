package org.woehlke.jakartaee.petclinic.frontend.web.owner;

import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

import java.io.Serializable;
import java.util.List;

public interface OwnersPetView extends Serializable {

	long serialVersionUID = 889970231263134104L;

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

	List<PetType> getPetTypeList();

	void setPetTypeList(List<PetType> petTypeList);

	List<PetType> getAllPetTypes();
}
