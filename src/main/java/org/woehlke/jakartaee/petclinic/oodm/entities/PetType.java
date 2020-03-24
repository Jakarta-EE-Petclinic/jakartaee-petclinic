package org.woehlke.jakartaee.petclinic.oodm.entities;


import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;
import org.woehlke.jakartaee.petclinic.oodm.entities.listener.PetTypeListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.*;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@Table(
    name = PetType.TABLENAME,
    uniqueConstraints = {
        @UniqueConstraint(
            name = PetType.TABLENAME + "_unique_uuid",
            columnNames = {PetType.COL_UUID}
        ),
        @UniqueConstraint(
            name = PetType.TABLENAME + "_unique_names",
            columnNames = {
                PetType.COL_NAME
            }
        )
    }
)
@NamedQueries({
    @NamedQuery(
        name = "PetType.getAll",
        query = "select pt from PetType pt order by pt.name"
    )
})
@EntityListeners(PetTypeListener.class)
@XmlRootElement(
    name = "PetType"
)
@XmlType(
    name = "PetType",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/PetType",
    propOrder = {
        "id", "uuid", "name"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PetType implements TwEntities, Comparable<PetType> {

  public final static String TABLENAME = "types";
  public final static String COL_ID = "id";
  public final static String COL_UUID = "uuid";
  public final static String COL_NAME = "name";
  private static final long serialVersionUID = -2213412509142145275L;

  @Id
  @XmlElement(required = true)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @XmlElement(required = true)
  @Column(name = COL_UUID, nullable = false, unique = true)
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.NO,
      store = org.hibernate.search.annotations.Store.YES
  )
  private UUID uuid;

  @NotEmpty
  @XmlElement(required = true)
  @Column(name = COL_NAME, nullable = false, unique = true)
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.YES,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String name;

  public PetType(@NotBlank String name){
    this.name=name;
  }

  @Transient
  public String getTableName() {
    return TABLENAME;
  }

  @Transient
  public String[] getColumnNames() {
    String[] thisColumnNames = {
        COL_ID, COL_UUID, COL_NAME
    };
    return thisColumnNames;
  }

  @Transient
  public String getPrimaryKey() {
    return this.getName();
  }

  @Transient
  public String getPrimaryKeyWithId() {
    return this.getPrimaryKey() + "(" + this.getId() + "," + this.getUuid() + ")";
  }

  @Override
  public int compareTo(PetType o) {
    return this.getPrimaryKey().compareTo(o.getPrimaryKey());
  }
}
