package br.com.FutebolaPlatform.repositories;

import br.com.FutebolaPlatform.models.PlayerPaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerPaymentRepository extends JpaRepository<PlayerPaymentModel, UUID> {
}
