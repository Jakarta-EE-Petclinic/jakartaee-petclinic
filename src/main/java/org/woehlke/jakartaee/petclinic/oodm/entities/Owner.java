package org.woehlke.jakartaee.petclinic.oodm.entities;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;
import org.woehlke.jakartaee.petclinic.oodm.entities.listener.OwnerListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:08
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
    name = Owner.TABLENAME,
    uniqueConstraints = {
        @UniqueConstraint(
            name = Owner.TABLENAME + "_unique_uuid",
            columnNames = {Owner.COL_UUID}
        ),
        @UniqueConstraint(
            name = Owner.TABLENAME + "_unique_names",
            columnNames = {
                Owner.COL_FIRSTNAME,
                Owner.COL_LASTNAME,
                Owner.COL_CITY,
                Owner.COL_PHONENUMBER
            }
        )
    }
)
@NamedQueries({
    @NamedQuery(
        name = "Owner.getAll",
        query = "select o from Owner o order by o.lastName,o.firstName"
    )
})
@EntityListeners(OwnerListener.class)
@XmlRootElement(
    name = "Owner"
)
@XmlType(
    name = "Owner",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/Owner",
    propOrder = {
        "id", "uuid", "firstName", "lastName", "address", "houseNumber",
        "addressInfo", "city", "zipCode", "phoneNumber", "pets"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner implements TwEntities, Comparable<Owner> {

  public final static String TABLENAME = "owners";
  public final static String COL_ID = "id";
  public final static String COL_UUID = "uuid";
  public final static String COL_FIRSTNAME = "first_name";
  public final static String COL_LASTNAME = "lastName";
  public final static String COL_ADDRESS = "address";
  public final static String COL_HOUSENUMBER = "housenumber";
  public final static String COL_ADDRESS_INFO = "address_info";
  public final static String COL_CITY = "city";
  public final static String COL_ZIPCODE = "zipcode";
  public final static String COL_PHONENUMBER = "phonenumber";
  private static final long serialVersionUID = 7995827646591579744L;

  @Id
  @XmlElement(required = true)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @XmlElement(required = true)
  @Column(name = COL_UUID, nullable = false)
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.NO,
      store = org.hibernate.search.annotations.Store.YES
  )
  private UUID uuid;

  @XmlElement(required = true)
  @Column(name = COL_FIRSTNAME, nullable = false)
  @NotEmpty
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.YES,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String firstName;

  @XmlElement(required = true)
  @Column(name = COL_LASTNAME, nullable = false)
  @NotEmpty
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.YES,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String lastName;

  @XmlElement(required = true)
  @Column(name = COL_ADDRESS)
  @NotEmpty
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.YES,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String address;

  @XmlElement(required = true)
  @Column(name = COL_HOUSENUMBER)
  @NotEmpty
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.NO,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String houseNumber;

  @XmlElement(required = true)
  @Column(name = COL_ADDRESS_INFO)
  @NotEmpty
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.YES,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String addressInfo;

  @XmlElement(required = true)
  @Column(name = COL_CITY, nullable = false)
  @NotEmpty
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.NO,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String city;

  @XmlElement(required = true)
  @Column(name = COL_ZIPCODE)
  @NotEmpty
  @Digits(fraction = 0, integer = 5)
  @Pattern(regexp = "[0-9]{5}", message = "{invalid.zipCode}")
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.NO,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String zipCode;

  @XmlElement(required = true)
  @Column(name = COL_PHONENUMBER)
  @NotEmpty
  @Pattern(regexp = "\\+[1-9][0-9]{9,14}",
      message = "{invalid.phoneNumber}")
  private String phoneNumber;

  @IndexedEmbedded
  @XmlElementWrapper(name = "pets", nillable = false, required = true)
  @XmlElement(name = "pet")
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
  private Set<Pet> pets = new TreeSet<>();

  public void addPet(Pet pet) {
    pets.add(pet);
    pet.setOwner(this);
  }

  @Transient
  public String getTableName() {
    return TABLENAME;
  }

  @Transient
  public String[] getColumnNames() {
    String[] thisColumnNames = {
        COL_ID, COL_UUID,
        COL_FIRSTNAME, COL_LASTNAME, COL_ADDRESS,
        COL_HOUSENUMBER, COL_ADDRESS_INFO,
        COL_CITY, COL_ZIPCODE, COL_PHONENUMBER
    };
    return thisColumnNames;
  }

  @Transient
  public String getPrimaryKey() {
    return "" + this.getFullName() + " ( " + this.getCity() + " ) ";
  }

  public void setPrimaryKey(String primaryKey) {
  }

  @Transient
  public String getPrimaryKeyWithId() {
    return this.getPrimaryKey() + "(" + this.getId() + "," + this.getUuid() + ")";
  }

  @Transient
  public String getFullName() {
    return this.lastName + ", " + this.firstName;
  }

  public List<Pet> getPets() {
    List<Pet> list = new ArrayList<>();
    for (Pet pet : pets) {
      list.add(pet);
    }
    Collections.sort(list);
    return list;
  }

  @Override
  public int compareTo(Owner o) {
    return this.getPrimaryKey().compareTo(o.getPrimaryKey());
  }
}
