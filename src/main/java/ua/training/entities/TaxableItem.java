package ua.training.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@Entity
@Table(name = "taxable_items")
public class TaxableItem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_item")
    private long id;

    @ManyToMany
    @JoinTable(name = "persons_has_taxable_items",
            joinColumns = @JoinColumn(name = "id_item", referencedColumnName = "id_item"),
            inverseJoinColumns = @JoinColumn(name = "id_person", referencedColumnName = "id_person"))
    private Set<User> persons = new HashSet<>();

    @Column(name = "name_item")
    private String name;

    @Column(name = "price")
    private long price;


    @Override
    public String toString() {
        return "TaxableItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
