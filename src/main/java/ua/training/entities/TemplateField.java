package ua.training.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "template_fields")
public class TemplateField {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_field")
    private long id;

    @Column(name = "field_name")
    private String templateName;

    @ManyToOne
    @JoinColumn(name = "id_template", referencedColumnName = "id_template")
    private ReportTemplate template;
}
