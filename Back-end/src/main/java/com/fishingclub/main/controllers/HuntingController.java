package com.fishingclub.main.controllers;

import com.fishingclub.main.dto.HuntingDTO;
import com.fishingclub.main.dto.noRelations.HuntingNoRelDTO;
import com.fishingclub.main.services.HuntingService;
import com.fishingclub.main.utils.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hunting")
public class HuntingController {
    private final HuntingService huntingService;

    @Autowired
    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHunting(@PathVariable Integer id) {
        HuntingDTO hunting = huntingService.getOne(id);

        return ResponseHandler.success(
                "The hunting has been fetched successfully.",
                HttpStatus.OK,
                hunting
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createHunting(@RequestBody @Valid HuntingNoRelDTO hunt) {
        HuntingDTO hunting = huntingService.create(hunt);

        return ResponseHandler.success(
                "The hunting has been created successfully.",
                HttpStatus.CREATED,
                hunting
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteHunting(@PathVariable Integer id) {
        HuntingDTO hunting = huntingService.delete(id);

        return ResponseHandler.success(
                "The hunting has been managed successfully.",
                HttpStatus.OK,
                hunting
        );
    }
}
