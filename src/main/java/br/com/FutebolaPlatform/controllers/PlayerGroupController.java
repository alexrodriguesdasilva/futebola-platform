package br.com.FutebolaPlatform.controllers;

import br.com.FutebolaPlatform.dtos.PlayerGroupPatchDTO;
import br.com.FutebolaPlatform.dtos.PlayerGroupRequestDTO;
import br.com.FutebolaPlatform.dtos.PlayerGroupResponseDTO;
import br.com.FutebolaPlatform.dtos.PlayerPaymentResponseDTO;
import br.com.FutebolaPlatform.services.PlayerGroupService;
import br.com.FutebolaPlatform.services.PlayerPaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class PlayerGroupController {

    @Autowired
    private PlayerGroupService service;

    @Autowired
    private PlayerPaymentService paymentService;

    @PostMapping
    public ResponseEntity<PlayerGroupResponseDTO> create(@RequestBody @Valid PlayerGroupRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PlayerGroupResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerGroupResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerGroupResponseDTO> updateGroupPartially(
            @PathVariable UUID id,
            @RequestBody PlayerGroupPatchDTO dto
    ) {
        return ResponseEntity.ok(service.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/payments")
    public ResponseEntity<List<PlayerPaymentResponseDTO>> getPaymentsByGroup(@PathVariable UUID id) {
        List<PlayerPaymentResponseDTO> payments = paymentService.getPaymentsByGroup(id);
        return ResponseEntity.ok(payments);
    }
}
