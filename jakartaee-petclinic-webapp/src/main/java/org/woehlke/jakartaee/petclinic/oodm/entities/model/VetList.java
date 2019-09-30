package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name="VetList")
@XmlType(
    name="VetList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/VetList",
    propOrder = {
        "vetList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class VetList implements Serializable {

    @XmlElement(required=true)
    private List<Vet> vetList;

    public VetList() {
        vetList = new ArrayList<>();
    }

    public VetList(@NotNull List<Vet> vetList) {
        this.vetList = vetList;
    }

    public List<Vet> getVetList() {
        return vetList;
    }

    public void setVetList(List<Vet> vetList) {
        this.vetList = vetList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VetList)) return false;
        VetList vetList1 = (VetList) o;
        return getVetList().equals(vetList1.getVetList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVetList());
    }
}
