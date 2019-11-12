package ua.training.services;


import ua.training.entities.Complaint;


import java.util.List;

public interface ComplaintService {
    List<Complaint> findAll();
    Complaint findById(Long id);
    Complaint save(Complaint complaint);
}