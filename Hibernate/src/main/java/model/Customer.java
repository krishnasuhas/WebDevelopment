package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "CUSTOMER_DETAILS")
public class Customer {

    @Column(name = "CUSTOMER_ID")
    @EmbeddedId
    private UserId id;
    private int age;

    @Transient
    private String background;
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "houseNum", column = @Column(name = "HOME_NUMBER")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "HOME_ZIP_CODE")),
            @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
            @AttributeOverride(name = "city", column = @Column(name = "HOME_CITY")),
            @AttributeOverride(name = "state", column = @Column(name = "HOME_STATE")),
            @AttributeOverride(name = "country", column = @Column(name = "HOME_COUNTRY")),
    })
    private Address homeAddress;

    @Temporal(TemporalType.TIME)
    private Date enrolledDate;

    @Lob
    private String description;

    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "CUSTOMER_MISTAKES", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    @GenericGenerator(name = "sequence-gen", strategy = "increment")
    @CollectionId(columns = @Column(name = "Mistake_ID"), generator = "sequence-gen", type = @Type(type = "long"))
    private Collection<Mistake> listOfMistakes = new ArrayList<Mistake>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SSN_ID")
    private SSN ssn;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "CUSTOMER_FOOD", joinColumns = @JoinColumn(name = "CUSTOMER_ID"), inverseJoinColumns = @JoinColumn(name = "FOOD_ID"))
    private List<Food> foods = new ArrayList<Food>();
}
