package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import lombok.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;

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
    name = "OwnerList"
)
@XmlType(
    name = "OwnerList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/OwnerList",
    propOrder = {
        "ownerList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class OwnerList implements Serializable {

  private static final long serialVersionUID = 7608980315748812643L;

  @XmlElement(required = true)
  private List<Owner> ownerList = new ArrayList<>();

}
