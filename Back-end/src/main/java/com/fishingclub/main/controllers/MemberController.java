package com.fishingclub.main.controllers;

import com.fishingclub.main.dto.MemberDTO;
import com.fishingclub.main.dto.noRelations.MemberNoRelDTO;
import com.fishingclub.main.services.MemberService;
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
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{number}")
    public ResponseEntity<Object> getMember(@PathVariable Integer number) {
        MemberDTO member = memberService.getOne(number);

        return ResponseHandler.success(
                "The member has been fetched successfully.",
                HttpStatus.OK,
                member
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "number") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "") String fullName
    ) {
        Map<String, Object> params = Utilities.params(page, size, sortBy, sortOrder);
        params.put("fullName", fullName);

        var members = memberService.getAll(params);

        return ResponseHandler.success(
                "The members have been fetched successfully",
                HttpStatus.OK,
                members
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createMember(@RequestBody @Valid MemberNoRelDTO m) {
        MemberDTO member = memberService.create(m);

        return ResponseHandler.success(
                "The member has been created successfully.",
                HttpStatus.CREATED,
                member
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateMember(@RequestBody @Valid MemberNoRelDTO m) {
        MemberDTO member = memberService.update(m);

        return ResponseHandler.success(
                "The member has been updated successfully.",
                HttpStatus.OK,
                member
        );
    }

    @DeleteMapping("/delete/{number}")
    public ResponseEntity<Object> deleteMember(@PathVariable Integer number) {
        MemberDTO member = memberService.delete(number);

        return ResponseHandler.success(
                "The member has been deleted successfully.",
                HttpStatus.OK,
                member
        );
    }
}
