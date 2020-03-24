package org.woehlke.jakartaee.petclinic.oodm.entities.model;

import lombok.*;
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
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "VetList")
@XmlType(
    name = "VetList",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/model/VetList",
    propOrder = {
        "vetList"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class VetList implements Serializable {

  private static final long serialVersionUID = 6396791677094922721L;

  @XmlElement(required = true)
  private List<Vet> vetList = new ArrayList<>();

}
