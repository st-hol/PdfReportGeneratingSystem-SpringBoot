package ua.training.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_complaint")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private User user;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completion_time")
    private Date completionTime;



}
