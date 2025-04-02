package com.openweather.dto;

import lombok.Data;

@Data
public class ClimaDTO {
    private String cidade;
    private Double temperatura;
    private String descricaoClima;
    private String descricaoClimaTraduzida;
    private String dataHoraConsulta;
    private String unidadeTemperatura;
    private String sensacaoTermica;
} 