package org.woehlke.jakartaee.petclinic.oodm.entities;


import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;
import org.woehlke.jakartaee.petclinic.oodm.entities.listener.VetListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Indexed
@Table(
    name = Vet.TABLENAME,
    uniqueConstraints = {
        @UniqueConstraint(
            name = Vet.TABLENAME + "_unique_uuid",
            columnNames = {
                Vet.COL_UUID
            }
        ),
        @UniqueConstraint(
            name = Vet.TABLENAME + "_unique_names",
            columnNames = {
                Vet.COL_FIRSTNAME,
                Vet.COL_LASTNAME
            }
        )
    }
)
@NamedQueries({
    @NamedQuery(
        name = "Vet.getAll",
        query = "select s from Vet s"
    )
})
@EntityListeners(VetListener.class)
@XmlRootElement(
    name = "Vet"
)
@XmlType(
    name = "Vet",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/Vet",
    propOrder = {
        "id", "uuid", "firstName", "lastName", "specialties"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class Vet implements TwEntities<Vet> {

  public final static String XML_ROOT_ELEMENT_NAME = "vet";
  public final static String TABLENAME = "vets";
  public final static String COL_ID = "id";
  public final static String COL_UUID = "uuid";
  public final static String COL_FIRSTNAME = "first_name";
  public final static String COL_LASTNAME = "lastName";
  public final static String COL_VET_SPECIALTIES = "vet_specialties";
  public final static String COL_JOIN_VET_ID = "vet_id";
  public final static String COL_JOIN_SPECIALTY_ID = "specialty_id";
  private static final long serialVersionUID = 6749793465861123385L;
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
  @NotBlank
  @XmlElement(required = true)
  @Column(name = COL_FIRSTNAME, nullable = false)
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.YES,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String firstName;
  @NotBlank
  @XmlElement(required = true)
  @Column(name = COL_LASTNAME, nullable = false)
  @Field(
      index = org.hibernate.search.annotations.Index.YES,
      analyze = org.hibernate.search.annotations.Analyze.YES,
      store = org.hibernate.search.annotations.Store.YES
  )
  private String lastName;
  @NotNull
  @XmlElementWrapper(name = "specialties", nillable = false, required = true)
  @XmlElement(name = "specialty")
  @IndexedEmbedded
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = COL_VET_SPECIALTIES,
      joinColumns = @JoinColumn(name = COL_JOIN_VET_ID),
      inverseJoinColumns = @JoinColumn(name = COL_JOIN_SPECIALTY_ID))
  private Set<Specialty> specialties;

  public Vet() {
    this.specialties = new HashSet<>();
  }

  public Vet(@NotBlank String firstName, @NotBlank String lastName) {
    this.specialties = new HashSet<>();
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Vet(@NotBlank String firstName, @NotBlank String lastName, @NotNull Set<Specialty> specialties) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.specialties = specialties;
  }

  @Transient
  protected Set<Specialty> getSpecialtiesInternal() {
    if (this.specialties == null) {
      this.specialties = new HashSet<>();
    }
    return this.specialties;
  }

  public List<Specialty> getSpecialties() {
    List<Specialty> list = new ArrayList<Specialty>();
    for (Specialty s : getSpecialtiesInternal()) {
      list.add(s);
    }
    Collections.sort(list);
    return list;
  }

  public void setSpecialties(Set<Specialty> specialties) {
    this.specialties = specialties;
  }

  @Transient
  public String getSpecialtiesAsString() {
    StringBuilder stringBuilder = new StringBuilder();
    if (getNrOfSpecialties() == 0) {
      stringBuilder.append("none");
    } else {
      for (Specialty specialty : getSpecialties()) {
        stringBuilder.append(specialty.getName());
        stringBuilder.append(" ");
      }
    }
    return stringBuilder.toString();
  }

  public int getNrOfSpecialties() {
    return getSpecialtiesInternal().size();
  }

  public void addSpecialty(Specialty specialty) {
    getSpecialtiesInternal().add(specialty);
  }

  public void removeSpecialties() {
    this.specialties = new HashSet<>();
  }

  @Transient
  @Override
  public String getTableName() {
    return TABLENAME;
  }

  @Transient
  @Override
  public String[] getColumnNames() {
    String[] thisColumnNames = {
        COL_ID, COL_UUID,
        COL_FIRSTNAME, COL_LASTNAME, COL_VET_SPECIALTIES,
        COL_JOIN_VET_ID, COL_JOIN_SPECIALTY_ID
    };
    return thisColumnNames;
  }

  @Transient
  @Override
  public String getPrimaryKey() {
    return lastName + " " + firstName;
  }

  @Transient
  @Override
  public String getPrimaryKeyWithId() {
    return this.getPrimaryKey() + "(" + this.getId() + "," + this.getUuid() + ")";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Vet)) return false;
    Vet vet = (Vet) o;
    return Objects.equals(getId(), vet.getId()) &&
        Objects.equals(getUuid(), vet.getUuid()) &&
        getFirstName().equals(vet.getFirstName()) &&
        getLastName().equals(vet.getLastName()) &&
        Objects.equals(getSpecialties(), vet.getSpecialties());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUuid(), getFirstName(), getLastName(), getSpecialties());
  }

  @Override
  public String toString() {
    return "Vet{" +
        "id=" + id +
        ", uuid=" + uuid +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", specialties=" + ((specialties != null) ? specialties.size() : null) +
        '}';
  }

  @Override
  public int compareTo(Vet o) {
    return (this.getPrimaryKey()).compareTo(o.getPrimaryKey());
  }

}
