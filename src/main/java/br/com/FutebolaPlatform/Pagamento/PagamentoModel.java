package br.com.FutebolaPlatform.Pagamento;

import br.com.FutebolaPlatform.Jogador.JogadorModel;
import br.com.FutebolaPlatform.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private JogadorModel jogador;

    private BigDecimal valor;
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    private String referenciaMesAno; // ex: "06/2025"
}
