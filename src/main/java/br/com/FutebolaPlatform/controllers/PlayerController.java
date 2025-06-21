package br.com.FutebolaPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

import br.com.FutebolaPlatform.dtos.PlayerRequestDTO;
import br.com.FutebolaPlatform.dtos.PlayerResponseDTO;
import br.com.FutebolaPlatform.exceptions.UserNotFoundException;
import br.com.FutebolaPlatform.services.PlayerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<PlayerResponseDTO> getAllPlayers() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable UUID id) {
        return playerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDTO> createPlayer(@RequestBody @Valid PlayerRequestDTO dto) {
        PlayerResponseDTO savedPlayer = playerService.createPlayer(dto);
        return ResponseEntity.ok(savedPlayer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable UUID id, @RequestBody @Valid PlayerRequestDTO dto) {
        try {
            PlayerResponseDTO updatedPlayer = playerService.updatePlayer(id, dto);
            return ResponseEntity.ok(updatedPlayer);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
        if (playerService.findById(id).isPresent()) {
            playerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
