package br.com.FutebolaPlatform.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, UUID> {
}
