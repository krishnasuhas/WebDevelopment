package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue
    int VehicleId;
    String VehicleName;

    @ManyToOne(cascade = CascadeType.PERSIST) // optional
    @JoinColumn(name = "CUSTOMER_ID") // if you remove this it will create a table
    private Customer customer;

    public Vehicle() {
    }

    public Vehicle(String vehicleName) {
        VehicleName = vehicleName;
    }
}
