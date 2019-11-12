package ua.training.entities;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@EqualsAndHashCode
//@ToString
@Entity
@Table(name = "persons")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_person")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(name = "persons_has_roles",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();


    //todo refactor this (its just for test/dev process)
    @PrePersist
    void preInsert() {
        if (this.assignedInspector == null) {
            User inspector = new User();
            inspector.setId(1L);
            this.assignedInspector = inspector;
        }
        if (this.roles.isEmpty()) {
            Role role = new Role();
            role.setId(1L);
            role.setName("CLIENT");
            roles.add(role);
        }
    }

    //this field in table is a reference on itself
    @ManyToOne(fetch = FetchType.LAZY
            //, cascade = CascadeType.ALL
            )
    @JoinColumn(name = "id_inspector")
    private User assignedInspector;

    //mb fetch = FetchType.LAZY
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            //cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Complaint> complaints = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany
    @JoinTable(name = "persons_has_taxable_items",
            joinColumns = @JoinColumn(name = "id_person", referencedColumnName = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_item", referencedColumnName = "id_item"))
    private Set<TaxableItem> taxableItems = new HashSet<>();


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", roles=" + roles +
                ", complaints=" + complaints +
            //    ", taxableItems=" + taxableItems +
                '}';
    }

}