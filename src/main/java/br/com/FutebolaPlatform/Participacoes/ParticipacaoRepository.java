package br.com.FutebolaPlatform.Participacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipacaoRepository extends JpaRepository<ParticipacaoModel, UUID> {
}
