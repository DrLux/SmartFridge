package com.smartfridge.repo;

import com.smartfridge.model.ShopItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopItemRepository extends CrudRepository<ShopItem, Long> {
    List<ShopItem> findAll();
    void deleteById(long id);
    Boolean existsById(long id);
    ShopItem getById(long id);
}
