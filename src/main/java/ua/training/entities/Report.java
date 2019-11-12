package ua.training.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@ToString
@Getter
@Setter
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_report")
    private long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "taxpayer_code")
    private String taxpayerCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completion_time")
    private Date completionTime;

    @Column(name = "total_amount_of_property")
    private long totalAmountOfProperty;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private User person;

    @Column(name = "is_accepted")
    private boolean accepted;

    @Column(name = "should_be_changed")
    private boolean shouldBeChanged;

    @Column(name = "inspector_comment")
    private String inspectorComment;






//    public void setAcceptedFromInt(int iIsAccepted) {
//        isAccepted = (iIsAccepted != 0);
//    }
//
//    public void setShouldChangeFromInt(int iShouldChange) {
//        shouldBeChanged = (iShouldChange != 0);
//    }

}
