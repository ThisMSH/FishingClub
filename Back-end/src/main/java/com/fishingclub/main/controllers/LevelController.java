package com.fishingclub.main.controllers;

import com.fishingclub.main.dto.LevelDTO;
import com.fishingclub.main.dto.noRelations.LevelNoRelDTO;
import com.fishingclub.main.services.LevelService;
import com.fishingclub.main.utils.ResponseHandler;
import com.fishingclub.main.utils.Utilities;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/levels")
public class LevelController {
    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getLevel(@PathVariable Integer code) {
        LevelDTO level = levelService.getOne(code);

        return ResponseHandler.success(
                "The level has been fetched successfully.",
                HttpStatus.OK,
                level
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllLevels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder
    ) {
        Map<String, Object> params = Utilities.params(page, size, sortBy, sortOrder);

        var levels = levelService.getAll(params);

        return ResponseHandler.success(
                "The levels have been fetched successfully.",
                HttpStatus.OK,
                levels
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createLevel(@RequestBody @Valid LevelNoRelDTO l) {
        LevelDTO level = levelService.create(l);

        return ResponseHandler.success(
                "The level has been created successfully.",
                HttpStatus.CREATED,
                level
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateLevel(@RequestBody @Valid LevelNoRelDTO l) {
        LevelDTO level = levelService.update(l);

        return ResponseHandler.success(
                "The level has been updated successfully.",
                HttpStatus.OK,
                level
        );
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteLevel(@PathVariable Integer code) {
        LevelDTO level = levelService.delete(code);

        return ResponseHandler.success(
                "The level has been deleted successfully.",
                HttpStatus.OK,
                level
        );
    }
}
