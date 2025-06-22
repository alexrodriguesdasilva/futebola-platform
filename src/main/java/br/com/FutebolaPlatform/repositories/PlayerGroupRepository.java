package br.com.FutebolaPlatform.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.FutebolaPlatform.models.PlayerGroupModel;

public interface PlayerGroupRepository extends JpaRepository<PlayerGroupModel, UUID> {
    boolean existsByName(String name);
}
