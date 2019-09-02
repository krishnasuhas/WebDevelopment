package model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "PROPERTY_VALUE")
//@DiscriminatorValue(value = "PROPERTIES")

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "by.Place", query = "from Property where Place = :place")
@NamedNativeQuery(name = "nativeBy.Place", query = "select * from PROPERTY WHERE PLACE = ?", resultClass = Property.class)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Property {
    @Id
    @GeneratedValue
    int propertyId;

    @NonNull
    int cost;

    @NonNull
    String Place;
}
