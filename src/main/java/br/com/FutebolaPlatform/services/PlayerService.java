package br.com.FutebolaPlatform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import br.com.FutebolaPlatform.dtos.PlayerRequestDTO;
import br.com.FutebolaPlatform.dtos.PlayerResponseDTO;
import br.com.FutebolaPlatform.exceptions.UserAlreadyLinkedToPlayerException;
import br.com.FutebolaPlatform.exceptions.UserNotFoundException;
import br.com.FutebolaPlatform.models.PlayerModel;
import br.com.FutebolaPlatform.models.UserModel;
import br.com.FutebolaPlatform.repositories.PlayerRepository;
import br.com.FutebolaPlatform.repositories.UserRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PlayerResponseDTO> findAll() {
        return playerRepository.findAll().stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public Optional<PlayerResponseDTO> findById(UUID id) {
        return playerRepository.findById(id)
            .map(this::toResponseDTO);
    }

    public PlayerResponseDTO createPlayer(PlayerRequestDTO dto) {
        UUID userId = UUID.fromString(dto.getUserId());

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        if (user.getPlayer() != null) {
            throw new UserAlreadyLinkedToPlayerException("Usuário já vinculado a um jogador");
        }

        PlayerModel player = new PlayerModel();
        player.setUser(user);
        player.setPreferredPosition(dto.getPreferredPosition());
        player.setDominantFoot(dto.getDominantFoot());
        user.setPlayer(player);

        PlayerModel saved = playerRepository.save(player);
        return toResponseDTO(saved);
    }

    public PlayerResponseDTO updatePlayer(UUID id, PlayerRequestDTO dto) {
        return playerRepository.findById(id)
            .map(player -> {
                player.setPreferredPosition(dto.getPreferredPosition());
                player.setDominantFoot(dto.getDominantFoot());
                PlayerModel updated = playerRepository.save(player);
                return toResponseDTO(updated);
            })
            .orElseThrow(() -> new UserNotFoundException("Jogador não encontrado"));
    }

    public void deleteById(UUID id) {
        playerRepository.deleteById(id);
    }

    private PlayerResponseDTO toResponseDTO(PlayerModel player) {
        return new PlayerResponseDTO(
            player.getId(),
            player.getUser().getId(),
            player.getUser().getName(),
            player.getPreferredPosition(),
            player.getDominantFoot()
        );
    }
}
