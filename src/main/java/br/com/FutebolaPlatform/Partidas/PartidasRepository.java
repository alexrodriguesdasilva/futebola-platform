package br.com.FutebolaPlatform.Partidas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartidasRepository extends JpaRepository<PartidasModel, UUID> {
}
