package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(
    name="PetList"
)
@XmlType(
    name="PetList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/PetList",
    propOrder = {
        "petList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PetList implements Serializable {

    @XmlElement(required=true)
    private List<Pet> petList;

    public PetList() {
        this.petList = new ArrayList<>();
    }

    public PetList(@NotNull List<Pet> list) {
        this.petList = list;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetList)) return false;
        PetList petList1 = (PetList) o;
        return getPetList().equals(petList1.getPetList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPetList());
    }
}
