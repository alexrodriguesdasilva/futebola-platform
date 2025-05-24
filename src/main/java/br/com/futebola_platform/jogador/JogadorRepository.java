package br.com.futebola_platform.jogador;

import br.com.futebola_platform.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
