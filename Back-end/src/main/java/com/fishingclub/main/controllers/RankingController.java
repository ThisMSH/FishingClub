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

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ranking")
public class RankingController {
    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getRankingByCompetition(@PathVariable String code) {
        List<RankingDTO> rankingList = rankingService.getByCompetitionCode(code);

        return ResponseHandler.success(
                "The ranking of the competition \"" + code + "\" have been fetched successfully.",
                HttpStatus.OK,
                rankingList
        );
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

    @DeleteMapping("/delete/code-{code}/member-{id}")
    public ResponseEntity<Object> deleteRanking(@PathVariable String code, @PathVariable Integer id) {
        RankingDTO ranking = rankingService.delete(code, id);

        return ResponseHandler.success(
                "The member has been removed from the competition successfully.",
                HttpStatus.OK,
                ranking
        );
    }
}
