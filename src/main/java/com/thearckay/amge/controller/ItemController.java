package com.thearckay.amge.controller;

import com.thearckay.amge.dto.request.ItemRequest;
import com.thearckay.amge.dto.response.ApiResponse;
import com.thearckay.amge.dto.response.DashboardResponse;
import com.thearckay.amge.entity.Item;
import com.thearckay.amge.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/itens")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard(@RequestParam Integer userId) {
        DashboardResponse dashboardData = itemService.getDashboardData(userId);

        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                "Dados do inventário carregados",
                dashboardData
        ));
    }



    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Integer>> createItem(
            @RequestBody ItemRequest itemDTO,
            @RequestParam Integer userId) {

        Integer itemId = itemService.registerItem(itemDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Item cadastrado com sucesso!",
                itemId
        ));
    }

    @GetMapping("/stock")
    public ResponseEntity<ApiResponse<List<Item>>> getFullStock(@RequestParam Integer userId) {
        List<Item> estoque = itemService.listAllItemsByUser(userId);

        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                "Estoque carregado com sucesso!",
                estoque
        ));
    }

    @PatchMapping("/stock")
    public ResponseEntity<ApiResponse> updateItem( @RequestBody ItemRequest itemUpdated,
                                                   @RequestParam String itemCode,
                                                   @RequestParam Integer userId
    ){
        return itemService.updateItem(itemUpdated, itemCode, userId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteItem(@RequestParam String itemCode, @RequestParam Integer userId){
        return itemService.deleteItem(itemCode, userId);
    }

}
