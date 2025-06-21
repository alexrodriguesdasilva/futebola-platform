package br.com.FutebolaPlatform.controllers;

import br.com.FutebolaPlatform.dtos.PlayerPaymentRequestDTO;
import br.com.FutebolaPlatform.dtos.PlayerPaymentResponseDTO;
import br.com.FutebolaPlatform.services.PlayerPaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PlayerPaymentController {

    @Autowired
    private PlayerPaymentService playerPaymentService;

    @PostMapping
    public ResponseEntity<PlayerPaymentResponseDTO> createPayment(@RequestBody @Valid PlayerPaymentRequestDTO dto) {
        return ResponseEntity.ok(playerPaymentService.createPayment(dto));
    }

    @GetMapping
    public ResponseEntity<List<PlayerPaymentResponseDTO>> getAllPayments() {
        return ResponseEntity.ok(playerPaymentService.getAll());
    }
}
