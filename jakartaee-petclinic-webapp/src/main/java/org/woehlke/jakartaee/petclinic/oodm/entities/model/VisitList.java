package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name="VisitList")
@XmlType(
        name="VisitList",
        namespace = "http://woehlke.org/javaee8/petclinic/oodm/entities/VisitList",
        propOrder = {
                "visitList"
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitList implements Serializable {

    @XmlElement(required=true)
    private List<Visit> visitList;

    public VisitList() {
        this.visitList = new ArrayList<>();
    }

    public VisitList(@NotNull List<Visit> visitList) {
        this.visitList = visitList;
    }

    public List<Visit> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitList)) return false;
        VisitList visitList1 = (VisitList) o;
        return getVisitList().equals(visitList1.getVisitList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVisitList());
    }
}
