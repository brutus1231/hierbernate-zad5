package pl.sda.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@Getter @Setter @ToString
public class Address extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String street;

    @Column(length = 20, nullable = false)
    private String houseNumber;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;
}
