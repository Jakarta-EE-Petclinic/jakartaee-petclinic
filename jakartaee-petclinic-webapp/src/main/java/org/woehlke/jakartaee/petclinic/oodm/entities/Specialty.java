package org.woehlke.jakartaee.petclinic.oodm.entities;


import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Indexed
@Table(
    name = Specialty.TABLENAME,
    uniqueConstraints = {
        @UniqueConstraint(
            name=Specialty.TABLENAME+"_unique_uuid",
            columnNames = { Specialty.COL_UUID }
        ),
        @UniqueConstraint(
            name=Specialty.TABLENAME+"_unique_names",
            columnNames = {
                Specialty.COL_NAME
            }
        )
    }
)
@NamedQueries({
    @NamedQuery(
        name = "Specialty.getAll",
        query = "select s from Specialty s order by s.name"
    )
})
@XmlRootElement(name="specialty")
@XmlType(
        name = "specialty",
        namespace = "http://woehlke.org/javaee8/petclinic/oodm/entities/specialty",
        propOrder = {
                "id", "uuid", "name"
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class Specialty implements TwEntities<Specialty> {

    public final static String TABLENAME = "specialties";

    public final static String COL_ID = "id";

    public final static String COL_UUID = "uuid";

    public final static String COL_NAME = "name";

    public Specialty(){
    }

    public Specialty(@NotEmpty @NotNull String name) {
        this.name = name;
    }

    @Id
    @XmlElement(required=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @XmlElement(required=true)
    @Column(name = COL_UUID,nullable = false, unique = true)
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.NO,
            store = org.hibernate.search.annotations.Store.YES
    )
    private UUID uuid;

    @NotEmpty
    @XmlElement(required=true)
    @Column(name = COL_NAME, unique = true, nullable = false)
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.YES,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String name;

    @Override
    public String getTableName() {
        return TABLENAME;
    }

    @Override
    public String[] getColumnNames() {
        String[] thisColumnNames = {
                COL_ID, COL_UUID, COL_NAME
        };
        return thisColumnNames;
    }

    @Override
    public String getPrimaryKey() {
        return this.getName();
    }

    @Transient
    public String getPrimaryKeyWithId(){
        return this.getPrimaryKey()+"("+this.getId()+","+this.getUuid()+")";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
	public int compareTo(Specialty other) {
		return this.getPrimaryKey().compareTo(other.getPrimaryKey());
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specialty)) return false;
        Specialty specialty = (Specialty) o;
        return Objects.equals(getId(), specialty.getId()) &&
                getUuid().equals(specialty.getUuid()) &&
                getName().equals(specialty.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getName());
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
