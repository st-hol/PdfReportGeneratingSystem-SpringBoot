package ua.training.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "template_fields")
public class TemplateField {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_field")
    private long id;

    @Column(name = "field_name")
    private String fieldName;

    @ManyToOne
    @JoinColumn(name = "id_template", referencedColumnName = "id_template")
    private ReportTemplate template;
}
