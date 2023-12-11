package com.fishingclub.main.controllers;

import com.fishingclub.main.dto.CompetitionDTO;
import com.fishingclub.main.dto.noRelations.CompetitionNoRelDTO;
import com.fishingclub.main.enums.CompetitionFilterType;
import com.fishingclub.main.services.CompetitionService;
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
@RequestMapping("/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getCompetition(@PathVariable String code) {
        CompetitionDTO competition = competitionService.getOne(code);

        return ResponseHandler.success(
                "The competition has been fetched successfully.",
                HttpStatus.OK,
                competition
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllCompetitions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "ALL") CompetitionFilterType filter
            ) {
        Map<String, Object> params = Utilities.params(page, size, sortBy, sortOrder);
        params.put("filter", filter);

        var competitions = competitionService.getAll(params);

        return ResponseHandler.success(
                "The competitions have been fetched successfully.",
                HttpStatus.OK,
                competitions
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createCompetition(@RequestBody @Valid CompetitionNoRelDTO c) {
        CompetitionDTO competition = competitionService.create(c);

        return ResponseHandler.success(
                "The competition has been created successfully.",
                HttpStatus.CREATED,
                competition
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCompetition(@RequestBody @Valid CompetitionNoRelDTO c) {
        CompetitionDTO competition = competitionService.update(c);

        return ResponseHandler.success(
                "The competition has been updated successfully.",
                HttpStatus.OK,
                competition
        );
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteCompetition(@PathVariable String code) {
        CompetitionDTO competition = competitionService.delete(code);

        return ResponseHandler.success(
                "The competition has been deleted successfully.",
                HttpStatus.OK,
                competition
        );
    }
}
