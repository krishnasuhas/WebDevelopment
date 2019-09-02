package model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
//@DiscriminatorValue(value = "LANDS")
public class Land extends Property {
    @NonNull
    int squareFeet;
}
