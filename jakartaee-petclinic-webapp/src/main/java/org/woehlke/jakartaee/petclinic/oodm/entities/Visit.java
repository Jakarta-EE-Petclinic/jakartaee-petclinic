package org.woehlke.jakartaee.petclinic.oodm.entities;

import org.hibernate.search.annotations.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;
import org.woehlke.jakartaee.petclinic.oodm.entities.listener.VisitListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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
    name = Visit.TABLENAME,
    uniqueConstraints = {
        @UniqueConstraint(
            name=Visit.TABLENAME+"_unique_uuid",
            columnNames = {
                Visit.COL_UUID
            }
        ),
        @UniqueConstraint(
            name=Visit.TABLENAME+"_unique_names",
            columnNames = {
                Visit.COL_VISIT_DATE,
                Visit.COL_DESCRIPTION,
                Visit.COL_PET_ID
            }
        )
    }
)
@NamedQueries({
    @NamedQuery(
        name = "Visit.getAll",
        query = "select s from Visit s"
    )
})
@EntityListeners(VisitListener.class)
@XmlRootElement(name="Visit")
@XmlType(
        name = "Visit",
        namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/Visit",
        propOrder = {
                "id", "uuid", "date","description"
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class Visit implements TwEntities<Visit> {

    private static final long serialVersionUID = 2357446696894656827L;

    public final static String TABLENAME = "visits";

    public final static String COL_ID = "id";

    public final static String COL_UUID = "uuid";

    public final static String COL_VISIT_DATE = "visit_date";

    public final static String COL_DESCRIPTION = "description";

    public final static String COL_PET_ID = "pet_id";

    public Visit() {
        this.uuid = UUID.randomUUID();
    }

    @Id
    @XmlElement(required=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @XmlElement(required=true)
    @Column(name = COL_UUID, nullable = false, unique = true)
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.NO,
            store = org.hibernate.search.annotations.Store.YES
    )
    private UUID uuid;

    @NotNull
    @XmlElement(required=true)
    @Column(name = COL_VISIT_DATE)
    @Temporal( TemporalType.DATE )
    private Date date;

    @NotEmpty
    @XmlElement(required=true)
    @Column(name = COL_DESCRIPTION)
    @Field(
        index = org.hibernate.search.annotations.Index.YES,
        analyze = org.hibernate.search.annotations.Analyze.YES,
        store = org.hibernate.search.annotations.Store.YES
    )
    private String description;

    @XmlTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = COL_PET_ID)
    private Pet pet;

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
                COL_VISIT_DATE, COL_DESCRIPTION, COL_PET_ID
        };
        return thisColumnNames;
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

    @Transient
    @Override
    public String getPrimaryKey() {
        String primaryKey = description;
        String localDate = LocalDate.of(date.getYear()+1900,date.getMonth()+1,date.getDate()+1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        return primaryKey + " ( "+localDate+" )";
    }

    @Transient
    @Override
    public String getPrimaryKeyWithId(){
        return this.getPrimaryKey()+"("+this.getId()+","+this.getUuid()+")";
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) return false;
        Visit visit = (Visit) o;
        return Objects.equals(getId(), visit.getId()) &&
                Objects.equals(getUuid(), visit.getUuid()) &&
                getDate().equals(visit.getDate()) &&
                getDescription().equals(visit.getDescription()) &&
                Objects.equals(getPet(), visit.getPet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getDate(), getDescription(), getPet());
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", date=" + ((date!=null)?date.toLocaleString():null) +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int compareTo(Visit o) {
        return o.getPrimaryKey().compareTo(o.getPrimaryKey());
    }
}
