package ua.training.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "template_fields")
public class TemplateField {

    public TemplateField(String fieldName, ReportTemplate template) {
        this.fieldName = fieldName;
        this.template = template;
    }

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
