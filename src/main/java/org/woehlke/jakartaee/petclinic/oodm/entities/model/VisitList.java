package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import lombok.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(
    name = "VisitList"
)
@XmlType(
    name = "VisitList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/VisitList",
    propOrder = {
        "visitList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitList implements Serializable {

  private static final long serialVersionUID = -7588305041391798453L;

  @XmlElement(required = true)
  private List<Visit> visitList = new ArrayList<>();

}
