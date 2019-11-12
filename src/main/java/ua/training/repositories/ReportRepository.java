package ua.training.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.training.entities.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.training.entities.Role;
import ua.training.entities.User;

import java.util.List;


@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    Page<Report> findAllByPerson(User user, Pageable pageable);

    List<Report> findAllByPerson(User user);

    @Query("select r from Report r inner join User u on r.person = u and u.assignedInspector=:user")
    List<Report> findAllReportsOfPersonsByAssignedInspector(@Param("user") User user);

    @Query("select r from Report r inner join User u on r.person = u and u.assignedInspector=:user")
    Page<Report> findAllReportsOfPersonsByAssignedInspector(@Param("user") User user, Pageable pageable);
}
