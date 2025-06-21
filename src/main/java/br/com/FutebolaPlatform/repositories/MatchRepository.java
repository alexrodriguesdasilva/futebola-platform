package br.com.FutebolaPlatform.repositories;

import br.com.FutebolaPlatform.models.MatchModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchRepository extends JpaRepository<MatchModel, UUID> {
}
