package ua.training.repositories;

import ua.training.entities.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Long> {


}
