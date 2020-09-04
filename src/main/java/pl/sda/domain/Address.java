package pl.sda.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@Data
public class Address extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String street;

    @Column(length = 20, nullable = false)
    private String houseNumber;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Address(String city, String street, String houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
