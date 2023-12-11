package com.fishingclub.main.controllers;

import com.fishingclub.main.dto.FishDTO;
import com.fishingclub.main.dto.noRelations.FishNoRelDTO;
import com.fishingclub.main.services.FishService;
import com.fishingclub.main.utils.ResponseHandler;
import com.fishingclub.main.utils.Utilities;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fishes")
public class FishController {
    private final FishService fishService;

    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> getFish(@PathVariable String name) {
        FishDTO fish = fishService.getOne(name);

        return ResponseHandler.success(
                "The fish has been fetched successfully.",
                HttpStatus.OK,
                fish
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllFishes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder
    ) {
        Map<String, Object> params = Utilities.params(page, size, sortBy, sortOrder);

        var fishes = fishService.getAll(params);

        return ResponseHandler.success(
                "The fishes have been fetched successfully.",
                HttpStatus.OK,
                fishes
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createFish(@RequestBody @Valid FishNoRelDTO f) {
        FishDTO fish = fishService.create(f);

        return ResponseHandler.success(
                "The fish has been created successfully.",
                HttpStatus.CREATED,
                fish
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateFish(@RequestBody @Valid FishNoRelDTO f) {
        FishDTO fish = fishService.update(f);

        return ResponseHandler.success(
                "The fish has been updated successfully.",
                HttpStatus.OK,
                fish
        );
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Object> deleteFish(@PathVariable String name) {
        FishDTO fish = fishService.delete(name);

        return ResponseHandler.success(
                "The fish has been deleted successfully.",
                HttpStatus.OK,
                fish
        );
    }
}
