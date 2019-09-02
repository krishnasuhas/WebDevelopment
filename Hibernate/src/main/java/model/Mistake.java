package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
public class Mistake {
    @Column(name = "MISTAKE_NAME")
    String mistakeName;
}
