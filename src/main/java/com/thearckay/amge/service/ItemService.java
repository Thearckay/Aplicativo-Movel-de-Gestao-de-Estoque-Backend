package com.thearckay.amge.service;

import com.thearckay.amge.dto.request.ItemRequest;
import com.thearckay.amge.dto.response.ActivityResponse;
import com.thearckay.amge.dto.response.DashboardResponse;
import com.thearckay.amge.entity.Item;
import com.thearckay.amge.entity.User;
import com.thearckay.amge.exceptions.RegisterItemException;
import com.thearckay.amge.repository.ItemRepository;
import com.thearckay.amge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    // todo - fazer erro personalizado e restControllerAdvice
    public DashboardResponse getDashboardData(Integer userId) {
        List<Item> meusItens = itemRepository.findByUserId(userId);

        Double totalValue = meusItens.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        List<ActivityResponse> activities = meusItens.stream()
                .limit(5)
                .map(item -> new ActivityResponse(
                        item.getName(),
                        "Status: " + item.getType(),
                        "Agora",
                        item.getType()
                )).toList();

        return new DashboardResponse(totalValue, 4.2, activities);
    }

    public Integer registerItem(ItemRequest dto, Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RegisterItemException("Usuário não encontrado", HttpStatus.BAD_REQUEST.value()));

        Item itemFromDataBase = itemRepository.findByItemCode(dto.itemCode());

        if (dto.price() < 0 || dto.quantity() < 0) {
            throw new RegisterItemException("Preço ou quantidade não podem ser negativos", HttpStatus.BAD_REQUEST.value());
        }

        if (itemFromDataBase != null){
            throw new RegisterItemException("Item já cadastrado", HttpStatus.CONFLICT.value());
        }

        Item newItem = new Item();
        newItem.setName(dto.name());
        newItem.setItemCode(dto.itemCode());
        newItem.setQuantity(dto.quantity());
        newItem.setPrice(dto.price());
        newItem.setType(dto.type() != null ? dto.type() : "ADD");
        newItem.setUser(user);

        Item saved = itemRepository.save(newItem);
        return saved.getId();
    }

    public List<Item> listAllItemsByUser(Integer userId) {
        List<Item> items = itemRepository.findByUserId(userId);
        return items;
    }

}