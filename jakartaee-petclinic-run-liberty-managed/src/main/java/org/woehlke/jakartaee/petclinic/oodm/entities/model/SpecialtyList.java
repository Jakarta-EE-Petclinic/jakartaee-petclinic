package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@XmlRootElement(
    name="SpecialtyList"
)
@XmlType(
    name="SpecialtyList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/SpecialtyList",
    propOrder = {
        "specialtyList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class SpecialtyList implements Serializable {

    private static final long serialVersionUID = -4974070594228297652L;

    @XmlElement(required=true)
    private List<Specialty> specialtyList;

    public SpecialtyList() {
        specialtyList = new ArrayList<>();
    }

    public SpecialtyList(@NotNull List<Specialty> specialtyList) {
        this.specialtyList = specialtyList;
    }

    public List<Specialty> getSpecialtyList() {
        return specialtyList;
    }

    public void setSpecialtyList(List<Specialty> specialtyList) {
        this.specialtyList = specialtyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialtyList)) return false;
        SpecialtyList that = (SpecialtyList) o;
        return Objects.equals(getSpecialtyList(), that.getSpecialtyList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecialtyList());
    }
}
