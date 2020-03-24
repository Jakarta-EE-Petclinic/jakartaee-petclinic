package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import lombok.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;

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
    name = "PetList"
)
@XmlType(
    name = "PetList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/PetList",
    propOrder = {
        "petList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PetList implements Serializable {

  private static final long serialVersionUID = -5829352711555277375L;

  @XmlElement(required = true)
  private List<Pet> petList= new ArrayList<>();

}
