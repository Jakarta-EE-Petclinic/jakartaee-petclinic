package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(
    name="PetTypeList"
)
@XmlType(
    name="PetTypeList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/PetTypeList",
    propOrder = {
        "petTypeList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PetTypeList implements Serializable {

    @XmlElement(required=true)
    private List<PetType> petTypeList;

    public PetTypeList() {
        this.petTypeList = new ArrayList<>();
    }

    public PetTypeList(@NotNull List<PetType> petTypeList) {
        this.petTypeList = petTypeList;
    }

    public List<PetType> getPetTypeList() {
        return petTypeList;
    }

    public void setPetTypeList(List<PetType> petTypeList) {
        this.petTypeList = petTypeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetTypeList)) return false;
        PetTypeList that = (PetTypeList) o;
        return getPetTypeList().equals(that.getPetTypeList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPetTypeList());
    }
}
