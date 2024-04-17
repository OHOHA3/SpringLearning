package ru.leontev.hibernatecource.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Passport")
//когда @id над классом, а не интом, то надо Serializable
//чтобы брать айди как норм число можно айди человека вынести в
//отдельную колонку с UNIQUE, тогда тоже один к одному
public class Passport implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "passport_number")
    private int passportNumber;

    public Passport() {

    }

    public Passport(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "person=" + person +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
