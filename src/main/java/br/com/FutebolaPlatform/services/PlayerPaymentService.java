package br.com.FutebolaPlatform.services;

import br.com.FutebolaPlatform.dtos.PlayerPaymentRequestDTO;
import br.com.FutebolaPlatform.dtos.PlayerPaymentResponseDTO;
import br.com.FutebolaPlatform.dtos.PlayerPaymentPatchDTO;
import br.com.FutebolaPlatform.enums.PaymentStatusEnum;
import br.com.FutebolaPlatform.exceptions.PlayerNotFoundException;
import br.com.FutebolaPlatform.exceptions.PlayerPaymentNotFoundException;
import br.com.FutebolaPlatform.models.PlayerModel;
import br.com.FutebolaPlatform.models.PlayerPaymentModel;
import br.com.FutebolaPlatform.models.PlayerGroupModel;
import br.com.FutebolaPlatform.repositories.PlayerPaymentRepository;
import br.com.FutebolaPlatform.repositories.PlayerRepository;
import br.com.FutebolaPlatform.repositories.PlayerGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerPaymentService {

    @Autowired
    private PlayerPaymentRepository paymentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerGroupRepository playerGroupRepository;

    @Transactional
    public PlayerPaymentResponseDTO createPayment(PlayerPaymentRequestDTO dto) {
        UUID playerId = parseUUID(dto.getPlayerId(), "Formato de UUID inválido para o jogador.");
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

        if (dto.getGroupId() != null) {
            UUID groupId = parseUUID(dto.getGroupId(), "Formato de UUID inválido para groupId.");
            PlayerGroupModel group = playerGroupRepository.findById(groupId)
                    .orElseThrow(() -> new IllegalArgumentException("Grupo não encontrado para o ID: " + groupId));
            payment.setPlayerGroupModel(group);
        }

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

        if (dto.getAmount() != null) payment.setAmount(dto.getAmount());
        if (dto.getPaymentDate() != null) payment.setPaymentDate(dto.getPaymentDate());
        if (dto.getPaymentType() != null) payment.setPaymentType(dto.getPaymentType());
        if (dto.getPaymentCategory() != null) payment.setPaymentCategory(dto.getPaymentCategory());
        if (dto.getDescription() != null) payment.setDescription(dto.getDescription());
        if (dto.getPaymentStatus() != null) payment.setPaymentStatus(dto.getPaymentStatus());

        if (dto.getGroupId() != null) {
            if (dto.getGroupId().isBlank()) {
                payment.setPlayerGroupModel(null);
            } else {
                UUID groupId = parseUUID(dto.getGroupId(), "Formato de UUID inválido para o grupo.");
                PlayerGroupModel group = playerGroupRepository.findById(groupId)
                        .orElseThrow(() -> new IllegalArgumentException("Grupo não encontrado para o ID: " + groupId));
                payment.setPlayerGroupModel(group);
            }
        }

        PlayerPaymentModel updated = paymentRepository.save(payment);
        return toResponseDTO(updated);
    }

    public PlayerPaymentResponseDTO toResponseDTO(PlayerPaymentModel payment) {
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
            payment.getPaymentStatus(),
            payment.getPlayerGroupModel() != null ? payment.getPlayerGroupModel().getId() : null,
            payment.getPlayerGroupModel() != null ? payment.getPlayerGroupModel().getName() : null
        );
    }

    public List<PlayerPaymentResponseDTO> getPaymentsByGroup(UUID groupId) {
        PlayerGroupModel group = playerGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Grupo não encontrado para o ID: " + groupId));

        return group.getPayments().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private UUID parseUUID(String raw, String errorMsg) {
        try {
            return UUID.fromString(raw);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(errorMsg);
        }
    }
}