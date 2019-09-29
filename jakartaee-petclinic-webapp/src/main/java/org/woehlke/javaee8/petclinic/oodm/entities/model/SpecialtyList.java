package org.woehlke.javaee8.petclinic.oodm.entities.model;

import org.woehlke.javaee8.petclinic.oodm.entities.Specialty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@XmlRootElement(name="SpecialtyList")
@XmlType(
        name="SpecialtyList",
        namespace = "http://woehlke.org/javaee8/petclinic/oodm/entities/SpecialtyList",
        propOrder = {
                "specialtyList"
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class SpecialtyList implements Serializable {

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
