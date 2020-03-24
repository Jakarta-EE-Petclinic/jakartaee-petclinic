package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import lombok.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(
    name = "SpecialtyList"
)
@XmlType(
    name = "SpecialtyList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/SpecialtyList",
    propOrder = {
        "specialtyList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class SpecialtyList implements Serializable {

  private static final long serialVersionUID = -4974070594228297652L;

  @XmlElement(required = true)
  private List<Specialty> specialtyList = new ArrayList<>();

}
