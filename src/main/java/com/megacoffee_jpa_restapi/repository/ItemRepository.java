package com.megacoffee_jpa_restapi.repository;

import com.megacoffee_jpa_restapi.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
