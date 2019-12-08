package ua.training.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.training.entities.Report;
import ua.training.entities.ReportParam;

import java.util.List;

@Repository
public interface ReportParamRepository extends CrudRepository<ReportParam, Long> {

    @Query("select rp from ReportParam rp where rp.report = :report")
    List<ReportParam> findParamsByReport(@Param("report") Report report);

}
