package org.woehlke.javaee8.petclinic.oodm.entities;


import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.woehlke.javaee8.petclinic.oodm.entities.common.TwEntities;
import org.woehlke.javaee8.petclinic.oodm.entities.listener.PetTypeListener;

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
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Indexed
@Table(
    name = PetType.TABLENAME,
    uniqueConstraints = {
        @UniqueConstraint(
            name=PetType.TABLENAME+"_unique_uuid",
            columnNames = { PetType.COL_UUID }
        ),
        @UniqueConstraint(
            name=PetType.TABLENAME+"_unique_names",
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
@XmlRootElement(name="petType")
@XmlType(
        name = "petType",
        namespace = "http://woehlke.org/javaee8/petclinic/oodm/entities/petType",
        propOrder = {
                "id", "uuid", "name"
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PetType implements TwEntities<PetType> {

    public final static String TABLENAME = "types";

    public final static String COL_ID = "id";

    public final static String COL_UUID = "uuid";

    public final static String COL_NAME = "name";

    public PetType() {
    }

    public PetType(@NotEmpty @NotNull String name) {
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
    @Column(name = COL_NAME, nullable = false, unique = true)
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.YES,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String name;


    @Transient
    public String getTableName(){
        return TABLENAME;
    }

    @Transient
    public String[] getColumnNames(){
        String[] thisColumnNames = {
                COL_ID, COL_UUID, COL_NAME
        };
        return thisColumnNames;
    }

    @Transient
    public String getPrimaryKey(){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetType)) return false;
        PetType petType = (PetType) o;
        return Objects.equals(getId(), petType.getId()) &&
                getUuid().equals(petType.getUuid()) &&
                getName().equals(petType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getName());
    }

    @Override
    public String toString() {
        return "PetType{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(PetType o) {
        return this.getPrimaryKey().compareTo(o.getPrimaryKey());
    }
}
