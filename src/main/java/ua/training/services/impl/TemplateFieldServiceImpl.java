package ua.training.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.repositories.TemplateFieldRepository;


@Service
public class TemplateFieldServiceImpl {

    @Autowired
    private TemplateFieldRepository templateFieldRepository;

}