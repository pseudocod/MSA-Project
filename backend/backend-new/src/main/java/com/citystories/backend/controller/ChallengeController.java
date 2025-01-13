package com.citystories.backend.controller;

import com.citystories.backend.domain.dto.challenge.ChallengeCreateDto;
import com.citystories.backend.domain.dto.challenge.ChallengeGetDto;
import com.citystories.backend.service.ChallengeService;
import com.citystories.backend.service.impl.ChallengeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(ChallengeServiceImpl challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Restrict to admins
    public ResponseEntity<ChallengeGetDto> getChallengeById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.getChallenge(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Restrict to admins
    public ResponseEntity<ChallengeGetDto> createChallenge(@RequestBody ChallengeCreateDto challengeCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(challengeService.createChallenge(challengeCreateDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Restrict to admins
    public ResponseEntity<ChallengeGetDto> updateChallenge(
            @PathVariable Long id,
            @RequestBody ChallengeCreateDto challengeCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.updateChallenge(id, challengeCreateDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Restrict to admins
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/active")
    @PreAuthorize("hasAuthority('ROLE_USER')") // Restrict to authenticated users
    public ResponseEntity<ChallengeGetDto> getActiveChallenge() {
        ChallengeGetDto activeChallenge = challengeService.getActiveChallenge();
        return ResponseEntity.status(HttpStatus.OK).body(activeChallenge);
    }
}
