package br.com.FutebolaPlatform.services;

import br.com.FutebolaPlatform.dtos.PlayerPaymentRequestDTO;
import br.com.FutebolaPlatform.dtos.PlayerPaymentResponseDTO;
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
        PlayerModel player = playerRepository.findById(UUID.fromString(dto.getPlayerId()))
                .orElseThrow(() -> new IllegalArgumentException("Jogador não encontrado"));

        PlayerPaymentModel payment = new PlayerPaymentModel();
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentType(dto.getPaymentType());
        payment.setPaymentCategory(dto.getPaymentCategory());  // <<< Adicionado aqui
        payment.setDescription(dto.getDescription());
        payment.setPlayerModel(player);

        PlayerPaymentModel saved = paymentRepository.save(payment);

        return toResponseDTO(saved);
    }

    public List<PlayerPaymentResponseDTO> getAll() {
        return paymentRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private PlayerPaymentResponseDTO toResponseDTO(PlayerPaymentModel payment) {
        return new PlayerPaymentResponseDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentType(),
                payment.getDescription(),
                payment.getPlayerModel().getId(),
                payment.getPlayerModel().getUser().getName(),  // <-- vírgula aqui
                payment.getPaymentCategory()
        );
    }
}
