package com.openweather.mapper;

import com.openweather.dto.ClimaDTO;
import com.openweather.entity.Clima;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClimaMapper {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public ClimaDTO toDTO(Clima entity) {
        if (entity == null) return null;
        
        ClimaDTO dto = new ClimaDTO();
        dto.setCidade(entity.getCidade());
        dto.setTemperatura(entity.getTemperatura());
        dto.setDescricaoClima(entity.getDescricaoClima());
        dto.setDescricaoClimaTraduzida(traduzirDescricaoClima(entity.getDescricaoClima()));
        dto.setDataHoraConsulta(entity.getDataHoraConsulta().format(formatter));
        dto.setUnidadeTemperatura("Celsius");
        dto.setSensacaoTermica(determinarSensacaoTermica(entity.getTemperatura()));
        
        return dto;
    }
    
    public Clima toEntity(String cidade, Double temperatura, String descricaoClima) {
        Clima entity = new Clima();
        entity.setCidade(cidade);
        entity.setTemperatura(temperatura);
        entity.setDescricaoClima(descricaoClima);
        entity.setDataHoraConsulta(LocalDateTime.now());
        return entity;
    }
    
    public List<ClimaDTO> toDTOList(List<Clima> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    private String traduzirDescricaoClima(String descricao) {
        return switch (descricao.toLowerCase()) {
            case "clear sky" -> "Céu limpo";
            case "few clouds" -> "Poucas nuvens";
            case "scattered clouds" -> "Nuvens dispersas";
            case "broken clouds" -> "Nuvens quebradas";
            case "shower rain" -> "Chuva fraca";
            case "rain" -> "Chuva";
            case "thunderstorm" -> "Trovoada";
            case "snow" -> "Neve";
            case "mist" -> "Névoa";
            default -> descricao;
        };
    }
    
    private String determinarSensacaoTermica(Double temperatura) {
        int tempInt = temperatura.intValue();
        if (tempInt >= 35) return "Muito quente";
        if (tempInt >= 30) return "Quente";
        if (tempInt >= 25) return "Ameno";
        if (tempInt >= 20) return "Fresco";
        if (tempInt >= 15) return "Frio";
        return "Muito frio";
    }
} 