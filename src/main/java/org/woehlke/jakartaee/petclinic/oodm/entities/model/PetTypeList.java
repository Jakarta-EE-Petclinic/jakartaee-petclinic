package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import lombok.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

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
    name = "PetTypeList"
)
@XmlType(
    name = "PetTypeList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/PetTypeList",
    propOrder = {
        "petTypeList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PetTypeList implements Serializable {

  private static final long serialVersionUID = -5455359835551484530L;

  @XmlElement(required = true)
  private List<PetType> petTypeList = new ArrayList<>();

}
