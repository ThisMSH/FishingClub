package com.fishingclub.main.controllers;

import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.dto.noRelations.RankingNoRelDTO;
import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.services.RankingService;
import com.fishingclub.main.utils.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ranking")
public class RankingController {
    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @PostMapping("/rank")
    public ResponseEntity<Object> getRanking(@RequestBody @Valid RankingKey key) {
        RankingDTO ranking = rankingService.getOne(key);

        return ResponseHandler.success(
                "The ranking has been fetched successfully.",
                HttpStatus.OK,
                ranking
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Object> assignRanking(@RequestBody @Valid RankingNoRelDTO r) {
        RankingDTO ranking = rankingService.create(r);

        return ResponseHandler.success(
                "The member has been assigned to the competition successfully.",
                HttpStatus.CREATED,
                ranking
        );
    }

    @PostMapping("/set-ranks/{code}")
    public ResponseEntity<Object> setRankings(@PathVariable String code) {
        rankingService.update(code);

        return ResponseHandler.success(
                "The ranks have been set successfully.",
                HttpStatus.OK,
                null
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRanking(@RequestBody @Valid RankingKey key) {
        RankingDTO ranking = rankingService.delete(key);

        return ResponseHandler.success(
                "The member has been removed from the competition successfully.",
                HttpStatus.OK,
                ranking
        );
    }
}
