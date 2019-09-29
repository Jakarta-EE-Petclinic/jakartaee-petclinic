package org.woehlke.jakartaee.petclinic.oodm.entities;

import org.hibernate.search.annotations.*;

import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;
import org.woehlke.jakartaee.petclinic.oodm.entities.listener.PetListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Indexed
@Table(
    name = Pet.TABLENAME,
    uniqueConstraints = {
        @UniqueConstraint(
            name=Pet.TABLENAME+"_unique_uuid",
            columnNames = { Pet.COL_UUID }
        ),
        @UniqueConstraint(
            name=Pet.TABLENAME+"_unique_names",
            columnNames = {
                Pet.COL_NAME,
                Pet.COL_BIRTH_DATE,
                Pet.COL_PETTYPE_ID,
                Pet.COL_OWNER_ID
            }
        )
    }
)
@NamedQueries({
    @NamedQuery(
        name = "Pet.getAll",
        query = "select p from Pet p order by p.name"
    )
})
@EntityListeners(PetListener.class)
@XmlRootElement(name="pet")
@XmlType(
        name="pet",
        namespace = "http://woehlke.org/javaee8/petclinic/oodm/entities/pet",
        propOrder = {
                "id", "uuid", "name", "birthDate","type","visits"
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class Pet implements TwEntities<Pet> {

    public final static String TABLENAME = "pets";

    public final static String COL_ID = "id";

    public final static String COL_UUID = "uuid";

    public final static String COL_NAME = "name";

    public final static String COL_BIRTH_DATE = "birth_date";

    public final static String COL_PETTYPE_ID = "pettype_id";

    public final static String COL_OWNER_ID = "owner_id";

    public Pet(){
    }

    @Id
    @XmlElement(required=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @XmlElement(required=true)
    @Column(name = COL_UUID, nullable = false,unique = true)
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.NO,
            store = org.hibernate.search.annotations.Store.YES
    )
    private UUID uuid;

    @NotNull
    @NotEmpty
    @XmlElement(required=true)
    @Column(name = COL_NAME, nullable = false)
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.YES,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String name;

    @NotNull
    @XmlElement(required=true)
    @Column(name = COL_BIRTH_DATE,nullable = false)
    @Temporal( TemporalType.DATE )
    private Date birthDate;

    @IndexedEmbedded
    @NotNull
    @XmlElement(required=true)
    @ManyToOne
    @JoinColumn(name = COL_PETTYPE_ID)
    private PetType type;

    @NotNull
    @XmlTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = COL_OWNER_ID)
    private Owner owner;

    @IndexedEmbedded
    @XmlElementWrapper(name = "visits", nillable = false, required = true)
    @XmlElement(name = "visit")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.EAGER)
    private Set<Visit> visits = new HashSet<>();

    @Transient
    public String getTableName(){
        return TABLENAME;
    }

    @Transient
    public String[] getColumnNames(){
        String[] thisColumnNames = {
                COL_ID, COL_UUID,
                COL_NAME, COL_BIRTH_DATE,
                COL_PETTYPE_ID, COL_OWNER_ID
        };
        return thisColumnNames;
    }

    @Transient
    public String getPrimaryKey(){
        return "" + this.getName() + " ( " + this.getType().getName() + ")";
    }

    @Transient
    public String getPrimaryKeyWithId(){
        return this.getPrimaryKey()+"("+this.getId()+","+this.getUuid()+")";
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getType() {

        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Visit> getVisits() {
        List<Visit> list = new ArrayList<>();
        for(Visit visit:visits){
            list.add(visit);
        }
        Collections.sort(list);
        return list;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(getId(), pet.getId()) &&
                Objects.equals(getUuid(), pet.getUuid()) &&
                Objects.equals(getName(), pet.getName()) &&
                Objects.equals(getBirthDate(), pet.getBirthDate()) &&
                Objects.equals(getType(), pet.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getName(), getBirthDate(), getType());
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", birthDate=" + ((birthDate !=null) ? birthDate.toLocaleString() : null) +
                ", type=" + ((type !=null)?type.getPrimaryKeyWithId():null) +
                ", owner=" + ((owner != null) ? owner.getPrimaryKeyWithId() : null) +
                ", visits=" + ((visits!=null)?visits.size():null) +
                '}';
    }

    @Override
    public int compareTo(Pet o) {
        return name.compareTo(o.getName());
    }
}
