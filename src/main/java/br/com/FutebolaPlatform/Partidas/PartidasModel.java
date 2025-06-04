package br.com.FutebolaPlatform.Partidas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "tb_partidas")
public class PartidasModel {

    @Id
    @GeneratedValue()
    private Long id;
    private String data;
    private String hora;
    private String
}
