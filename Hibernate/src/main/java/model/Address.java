package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@ToString
public class Address {
    @Column(name = "HOUSE_NUMBER")
    private int houseNum;
    private int zipCode;
    private String street;
    private String city;
    private String state;
    private String country;
}
