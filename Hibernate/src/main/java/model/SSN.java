package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class SSN {
    @Id
    @GeneratedValue
    int ssnId;

    @Temporal(TemporalType.DATE)
    Date issuedDate;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_ID")
    Customer customer;

    @Override
    public String toString() {
        return "SSN{" +
                "ssnId=" + ssnId +
                ", issuedDate=" + issuedDate +
                ", customer=" + customer.getId() +
                '}';
    }
}
