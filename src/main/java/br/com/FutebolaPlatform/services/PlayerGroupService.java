package br.com.FutebolaPlatform.services;

import br.com.FutebolaPlatform.dtos.*;
import br.com.FutebolaPlatform.exceptions.PlayerGroupNotFoundException;
import br.com.FutebolaPlatform.models.PlayerGroupModel;
import br.com.FutebolaPlatform.repositories.PlayerGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerGroupService {

    @Autowired
    private PlayerGroupRepository repository;

    @Transactional
    public PlayerGroupResponseDTO create(PlayerGroupRequestDTO dto) {
        if (repository.existsByName(dto.name())) {
            throw new IllegalArgumentException("Já existe um grupo com este nome.");
        }

        PlayerGroupModel group = new PlayerGroupModel();
        group.setName(dto.name());
        group.setDayOfWeek(dto.dayOfWeek());
        group.setStartTime(dto.startTime());
        group.setEndTime(dto.endTime());
        group.setActive(dto.active() != null ? dto.active() : true);

        return toDTO(repository.save(group));
    }

    public List<PlayerGroupResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public PlayerGroupResponseDTO getById(UUID id) {
        return toDTO(findByIdOrThrow(id));
    }

    public void delete(UUID id) {
        repository.delete(findByIdOrThrow(id));
    }

    public PlayerGroupResponseDTO patch(UUID id, PlayerGroupPatchDTO dto) {
        PlayerGroupModel group = findByIdOrThrow(id);

        if (dto.getName() != null && !dto.getName().equals(group.getName())) {
            if (repository.existsByName(dto.getName())) {
                throw new IllegalArgumentException("Já existe um grupo com este nome.");
            }
            group.setName(dto.getName());
        }

        if (dto.getDayOfWeek() != null) group.setDayOfWeek(dto.getDayOfWeek());
        if (dto.getStartTime() != null) group.setStartTime(dto.getStartTime());
        if (dto.getEndTime() != null) group.setEndTime(dto.getEndTime());
        if (dto.getActive() != null) group.setActive(dto.getActive());

        PlayerGroupModel updated = repository.save(group);
        return toDTO(updated);
    }

    private PlayerGroupModel findByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PlayerGroupNotFoundException("Grupo não encontrado para o ID: " + id));
    }

    private PlayerGroupResponseDTO toDTO(PlayerGroupModel model) {
        return new PlayerGroupResponseDTO(
                model.getId(),
                model.getName(),
                model.getDayOfWeek(),
                model.getStartTime(),
                model.getEndTime(),
                model.getActive(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }
}
