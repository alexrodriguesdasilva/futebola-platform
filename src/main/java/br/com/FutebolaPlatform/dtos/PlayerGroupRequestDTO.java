package br.com.FutebolaPlatform.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlayerGroupRequestDTO(
    @NotBlank(message = "O nome do grupo é obrigatório")
    String name,

    DayOfWeek dayOfWeek,

    LocalTime startTime,
    LocalTime endTime,

    Boolean active
) {}
