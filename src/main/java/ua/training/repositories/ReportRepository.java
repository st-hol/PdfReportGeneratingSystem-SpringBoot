package ua.training.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.training.entities.Report;
import ua.training.entities.User;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    Page<Report> findAllByPerson(User user, Pageable pageable);
}
