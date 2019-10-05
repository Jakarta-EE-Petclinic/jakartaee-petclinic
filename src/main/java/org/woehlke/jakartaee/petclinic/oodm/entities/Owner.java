package org.woehlke.jakartaee.petclinic.oodm.entities;

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
@Indexed
@Table(
    name = Owner.TABLENAME,
    uniqueConstraints = {
        @UniqueConstraint(
            name = Owner.TABLENAME+"_unique_uuid",
            columnNames = { Owner.COL_UUID }
        ),
        @UniqueConstraint(
            name= Owner.TABLENAME+"_unique_names",
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
    name="Owner"
)
@XmlType(
    name = "Owner",
    namespace = "http://woehlke.org/org/woehlke/jakartaee/petclinic/oodm/entities/Owner",
    propOrder = {
            "id", "uuid", "firstName", "lastName", "address", "houseNumber",
            "addressInfo","city", "zipCode", "phoneNumber","pets"
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner implements TwEntities<Owner> {

    private static final long serialVersionUID = 7995827646591579744L;

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

    public Owner() {
    }

    @Id
    @XmlElement(required=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @XmlElement(required=true)
    @Column(name = COL_UUID, nullable = false)
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.NO,
            store = org.hibernate.search.annotations.Store.YES
    )
    private UUID uuid;

    @XmlElement(required=true)
    @Column(name = COL_FIRSTNAME, nullable = false)
    @NotEmpty
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.YES,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String firstName;

    @XmlElement(required=true)
    @Column(name = COL_LASTNAME, nullable = false)
    @NotEmpty
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.YES,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String lastName;

    @XmlElement(required=true)
    @Column(name = COL_ADDRESS)
    @NotEmpty
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.YES,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String address;

    @XmlElement(required=true)
    @Column(name = COL_HOUSENUMBER)
    @NotEmpty
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.NO,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String houseNumber;

    @XmlElement(required=true)
    @Column(name = COL_ADDRESS_INFO)
    @NotEmpty
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.YES,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String addressInfo;

    @XmlElement(required=true)
    @Column(name = COL_CITY, nullable = false)
    @NotEmpty
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.NO,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String city;

    @XmlElement(required=true)
    @Column(name = COL_ZIPCODE)
    @NotEmpty
    @Digits(fraction = 0, integer = 5)
    @Pattern(regexp="[0-9]{5}",
            message = "{invalid.zipCode}")
    @Field(
            index = org.hibernate.search.annotations.Index.YES,
            analyze = org.hibernate.search.annotations.Analyze.NO,
            store = org.hibernate.search.annotations.Store.YES
    )
    private String zipCode;

    @XmlElement(required=true)
    @Column(name = COL_PHONENUMBER)
    @NotEmpty
    @Pattern(regexp="\\+[0-9]{13}",
            message = "{invalid.phoneNumber}")
    private String phoneNumber;

    @IndexedEmbedded
    @XmlElementWrapper(name = "pets", nillable = false, required = true)
    @XmlElement(name = "pet")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Pet> pets = new TreeSet<>();

    public void addPet(Pet pet){
        pets.add(pet);
        pet.setOwner(this);
    }

    @Transient
    public String getTableName(){
        return TABLENAME;
    }

    @Transient
    public String[] getColumnNames(){
        String[] thisColumnNames = {
            COL_ID, COL_UUID,
            COL_FIRSTNAME, COL_LASTNAME, COL_ADDRESS,
            COL_HOUSENUMBER, COL_ADDRESS_INFO,
            COL_CITY, COL_ZIPCODE, COL_PHONENUMBER
        };
        return thisColumnNames;
    }

    @Transient
    public String getPrimaryKey(){
        return "" + this.getFullName() + " ( "+this.getCity()+" ) ";
    }

    public void setPrimaryKey(String primaryKey){}

    @Transient
    public String getPrimaryKeyWithId(){
        return this.getPrimaryKey()+"("+this.getId()+","+this.getUuid()+")";
    }

    @Transient
    public String getFullName(){
        return this.lastName +", "+this.firstName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<Pet> getPets() {
        List<Pet> list = new ArrayList<>();
        for(Pet pet:pets){
            list.add(pet);
        }
        Collections.sort(list);
        return list;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(getId(), owner.getId()) &&
                Objects.equals(getUuid(), owner.getUuid()) &&
                getFirstName().equals(owner.getFirstName()) &&
                getLastName().equals(owner.getLastName()) &&
                getAddress().equals(owner.getAddress()) &&
                getHouseNumber().equals(owner.getHouseNumber()) &&
                getAddressInfo().equals(owner.getAddressInfo()) &&
                getCity().equals(owner.getCity()) &&
                getZipCode().equals(owner.getZipCode()) &&
                getPhoneNumber().equals(owner.getPhoneNumber()) &&
                Objects.equals(getPets(), owner.getPets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getFirstName(), getLastName(), getAddress(), getHouseNumber(), getAddressInfo(), getCity(), getZipCode(), getPhoneNumber(), getPets());
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", addressInfo='" + addressInfo + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pets=" + ((pets!=null)?pets.size():null) +
                '}';
    }

    @Override
    public int compareTo(Owner o) {
        return this.getPrimaryKey().compareTo(o.getPrimaryKey());
    }
}
