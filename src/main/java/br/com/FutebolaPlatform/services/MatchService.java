package br.com.FutebolaPlatform.services;

import br.com.FutebolaPlatform.dtos.MatchRequestDTO;
import br.com.FutebolaPlatform.dtos.MatchResponseDTO;
import br.com.FutebolaPlatform.enums.MatchStatusEnum;
import br.com.FutebolaPlatform.exceptions.MatchAlreadyFinishedException;
import br.com.FutebolaPlatform.dtos.MatchPatchDTO;
import br.com.FutebolaPlatform.models.MatchModel;
import br.com.FutebolaPlatform.repositories.MatchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MatchService {

    @Autowired
    private MatchRepository repository;

    @Transactional
    public MatchResponseDTO createMatch(MatchRequestDTO dto) {
        MatchModel match = new MatchModel();
        match.setMatchDate(dto.getMatchDate());
        match.setLocation(dto.getLocation());
        match.setTeamA(dto.getTeamA());
        match.setTeamB(dto.getTeamB());

        // status será definido automaticamente como SCHEDULED via @PrePersist
        MatchModel saved = repository.save(match);
        return toDTO(saved);
    }

    public MatchResponseDTO getById(UUID id) {
        MatchModel match = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partida não encontrada para o ID: " + id));
        return toDTO(match);
    }

    public List<MatchResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public MatchResponseDTO patch(UUID id, MatchPatchDTO dto) {
        MatchModel match = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partida não encontrada"));

        // Regra: se status já for FINISHED, bloqueia alterações
        if (match.getStatus() == MatchStatusEnum.FINISHED) {
            throw new MatchAlreadyFinishedException("Partida finalizada não pode ser alterada.");
        }

        // Permitir alterações se não finalizada
        if (dto.getMatchDate() != null) match.setMatchDate(dto.getMatchDate());
        if (dto.getLocation() != null) match.setLocation(dto.getLocation());
        if (dto.getTeamA() != null) match.setTeamA(dto.getTeamA());
        if (dto.getTeamB() != null) match.setTeamB(dto.getTeamB());
        if (dto.getScoreTeamA() != null) match.setScoreTeamA(dto.getScoreTeamA());
        if (dto.getScoreTeamB() != null) match.setScoreTeamB(dto.getScoreTeamB());

        // Permitir alteração de status somente se não finalizado ainda
        if (dto.getStatus() != null) {
            match.setStatus(dto.getStatus());
        }

        MatchModel updated = repository.save(match);
        return toDTO(updated);
    }


    @Transactional
    public void delete(UUID id) {
        MatchModel match = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partida não encontrada para o ID: " + id));
        repository.delete(match);
    }

    private MatchResponseDTO toDTO(MatchModel match) {
        return new MatchResponseDTO(
                match.getId(),
                match.getMatchDate(),
                match.getLocation(),
                match.getTeamA(),
                match.getTeamB(),
                match.getScoreTeamA(),
                match.getScoreTeamB(),
                match.getStatus(),
                match.getCreatedAt(),
                match.getUpdatedAt()
        );
    }
}
