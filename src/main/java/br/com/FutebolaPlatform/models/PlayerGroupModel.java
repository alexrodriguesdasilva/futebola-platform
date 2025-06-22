package br.com.FutebolaPlatform.models;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "TB_PLAYER_GROUP")
@Getter
@Setter
public class PlayerGroupModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    private Boolean active;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Um grupo pode ter Any pagamentos
    @OneToMany(mappedBy = "playerGroupModel", fetch = FetchType.LAZY)
    private Set<PlayerPaymentModel> payments = new HashSet<>();

    // // Um grupo de jogadores pode ter várias partidas
    // @OneToMany(mappedBy = "playerGroupModel", fetch = FetchType.LAZY)
    // private Set<MatchModel> matches = new HashSet<>();

    // // Relação entre Grupo e Jogadores
    // @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    // private Set<PlayerGroupMemberModel> players = new HashSet<>();

    // public Optional<PlayerPaymentModel> findById(UUID playerGroupId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'findById'");
    // }
}
