package ua.training.entities;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Getter
@Entity
@Table(name = "report_templates")
public class ReportTemplate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_template")
    private long id;

    @Column(name = "template_name")
    private String templateName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "template",
            //cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<TemplateField> fields = new HashSet<>();

}
