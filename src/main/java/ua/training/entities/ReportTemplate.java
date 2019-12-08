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
@Table(name = "report_templates")
public class ReportTemplate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_template")
    private long id;

    @Column(name = "template_name")
    private String templateName;

    @Lob
    @Column(name = "report_pdf", columnDefinition = "LONGBLOB")
    private byte[] reportPdf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "template",
            //cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<TemplateField> fields = new HashSet<>();

    @OneToMany(mappedBy = "reportType")
    private Set<Report> reports;
}
