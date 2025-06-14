package br.com.FutebolaPlatform.enums;

public enum MatchStatusEnum {
    SCHEDULED, // A partida está agendada, mas ainda não começou
    ONGOING, // A partida está em andamento
    FINISHED, // A partida foi finalizada
    CANCELED // A partida foi cancelada e não ocorrerá
}
