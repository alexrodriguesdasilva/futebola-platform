package br.com.FutebolaPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.FutebolaPlatform.models.PlayerModel;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<PlayerModel, UUID> {
    // Pode incluir consultas customizadas aqui se necessário
}