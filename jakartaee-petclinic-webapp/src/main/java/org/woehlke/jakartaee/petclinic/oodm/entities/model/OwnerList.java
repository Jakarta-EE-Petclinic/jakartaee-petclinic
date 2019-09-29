package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name="OwnerList")
@XmlType(
        name="OwnerList",
        namespace = "http://woehlke.org/javaee8/petclinic/oodm/entities/OwnerList",
        propOrder = {
            "ownerList"
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class OwnerList implements Serializable {

    @XmlElement(required=true)
    private List<Owner> ownerList;

    public OwnerList() {
        this.ownerList = new ArrayList<>();
    }

    public OwnerList(@NotNull List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerList)) return false;
        OwnerList ownerList1 = (OwnerList) o;
        return getOwnerList().equals(ownerList1.getOwnerList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerList());
    }
}
