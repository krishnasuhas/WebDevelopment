package model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
//@DiscriminatorValue(value = "BUILDINGS")
public class Building extends Property {
    @NonNull
    int noOfRooms;
}
