package br.com.FutebolaPlatform.services;

import br.com.FutebolaPlatform.dtos.PlayerPaymentRequestDTO;
import br.com.FutebolaPlatform.dtos.PlayerPaymentResponseDTO;
import br.com.FutebolaPlatform.dtos.PlayerPaymentPatchDTO;
import br.com.FutebolaPlatform.enums.PaymentStatusEnum;
import br.com.FutebolaPlatform.exceptions.PlayerNotFoundException;
import br.com.FutebolaPlatform.exceptions.PlayerPaymentNotFoundException;
import br.com.FutebolaPlatform.models.PlayerModel;
import br.com.FutebolaPlatform.models.PlayerPaymentModel;
import br.com.FutebolaPlatform.repositories.PlayerPaymentRepository;
import br.com.FutebolaPlatform.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerPaymentService {

    @Autowired
    private PlayerPaymentRepository paymentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    public PlayerPaymentResponseDTO createPayment(PlayerPaymentRequestDTO dto) {
        UUID playerId;
        try {
            playerId = UUID.fromString(dto.getPlayerId());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Formato de UUID inválido para o jogador.");
        }

        PlayerModel player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Jogador não encontrado para o ID: " + playerId));

        PlayerPaymentModel payment = new PlayerPaymentModel();
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentType(dto.getPaymentType());
        payment.setPaymentCategory(dto.getPaymentCategory());
        payment.setDescription(dto.getDescription());
        payment.setPlayerModel(player);
        payment.setPaymentStatus(PaymentStatusEnum.VALID);

        PlayerPaymentModel saved = paymentRepository.save(payment);
        return toResponseDTO(saved);
    }

    public List<PlayerPaymentResponseDTO> getAll() {
        return paymentRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public PlayerPaymentResponseDTO getById(UUID id) {
        PlayerPaymentModel payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PlayerPaymentNotFoundException("Pagamento não encontrado para o ID: " + id));
        return toResponseDTO(payment);
    }

    @Transactional
    public PlayerPaymentResponseDTO patchPayment(UUID id, PlayerPaymentPatchDTO dto) {
        PlayerPaymentModel payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PlayerPaymentNotFoundException("Pagamento não encontrado para o ID: " + id));

        if (dto.getAmount() != null) {
            payment.setAmount(dto.getAmount());
        }
        if (dto.getPaymentDate() != null) {
            payment.setPaymentDate(dto.getPaymentDate());
        }
        if (dto.getPaymentType() != null) {
            payment.setPaymentType(dto.getPaymentType());
        }
        if (dto.getPaymentCategory() != null) {
            payment.setPaymentCategory(dto.getPaymentCategory());
        }
        if (dto.getDescription() != null) {
            payment.setDescription(dto.getDescription());
        }
        if (dto.getPaymentStatus() != null) {
            payment.setPaymentStatus(dto.getPaymentStatus());
        }

        PlayerPaymentModel updated = paymentRepository.save(payment);
        return toResponseDTO(updated);
    }

    private PlayerPaymentResponseDTO toResponseDTO(PlayerPaymentModel payment) {
        return new PlayerPaymentResponseDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getCreatedAt(),
                payment.getUpdatedAt(),
                payment.getPaymentType(),
                payment.getDescription(),
                payment.getPlayerModel().getId(),
                payment.getPlayerModel().getUser().getName(),
                payment.getPaymentCategory(),
                payment.getPaymentStatus()
        );
    }
}
