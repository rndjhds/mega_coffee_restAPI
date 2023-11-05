package com.megacoffee_jpa_restapi.controller;


import com.megacoffee_jpa_restapi.entity.item.Item;
import com.megacoffee_jpa_restapi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @PostMapping("/api/item/join")
    public Item item(@RequestBody Item item) {
        Item save = itemRepository.save(item);
        return save;
    }

}
