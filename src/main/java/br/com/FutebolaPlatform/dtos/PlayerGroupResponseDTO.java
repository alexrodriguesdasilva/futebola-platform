package br.com.FutebolaPlatform.dtos;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record PlayerGroupResponseDTO(
    UUID id,
    String name,
    DayOfWeek dayOfWeek,
    LocalTime startTime,
    LocalTime endTime,
    Boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
