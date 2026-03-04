package com.thearckay.amge.repository;

import com.thearckay.amge.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByUserId(Integer userId);
    Item findByItemCode(String itemCode);
    Item findByItemCodeAndUserId(String itemCode, Integer userId);
}
