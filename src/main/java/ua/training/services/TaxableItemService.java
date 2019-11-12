package ua.training.services;


import ua.training.entities.TaxableItem;

import java.util.List;

public interface TaxableItemService {
    List<TaxableItem> findAll();
    TaxableItem findById(Long id);
    TaxableItem save(TaxableItem taxableItem);
}