package ua.training.services.impl;

import com.google.common.collect.Lists;
import ua.training.entities.TaxableItem;
import ua.training.repositories.TaxableItemRepository;
import ua.training.services.TaxableItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class TaxableItemServiceImpl implements TaxableItemService {

    @Autowired
    private TaxableItemRepository taxableItemRepository;

    @Override
    public List<TaxableItem> findAll() {
        return Lists.newArrayList(taxableItemRepository.findAll());
    }

    @Override
    public TaxableItem findById(Long id) {
        return taxableItemRepository.findById(id).get();
    }

    @Override
    public TaxableItem save(TaxableItem complaint) {
        return taxableItemRepository.save(complaint);
    }

    @Autowired
    public void setTaxableItemRepository(TaxableItemRepository taxableItemRepository) {
        this.taxableItemRepository = taxableItemRepository;
    }

}