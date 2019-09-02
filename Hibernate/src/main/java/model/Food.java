package model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Food {

    @Id
    @GeneratedValue
    int id;

    @NonNull
    String foodName;

    @ManyToMany(mappedBy = "foods", cascade = CascadeType.PERSIST)
    List<Customer> customers =  new ArrayList<Customer>();

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                '}';
    }
}
