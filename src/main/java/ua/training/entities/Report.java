package ua.training.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "completed_reports")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person", nullable = false)
    private User person;

    @Lob
    @Column(name = "report_pdf", columnDefinition = "LONGBLOB")
    private byte[] reportPdf;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_type", referencedColumnName = "id_template")
    private ReportTemplate reportType;

    @OneToMany(mappedBy = "report")
    private Set<ReportParam> reportParams;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completion_time")
    private Date completionTime;

}
