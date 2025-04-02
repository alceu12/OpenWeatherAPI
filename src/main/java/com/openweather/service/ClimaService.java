package com.openweather.service;

import com.openweather.dto.ClimaDTO;
import com.openweather.entity.Clima;
import com.openweather.mapper.ClimaMapper;
import com.openweather.repository.ClimaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClimaService {

    private final RestTemplate restTemplate;
    private final ClimaRepository climaRepository;
    private final ClimaMapper climaMapper;

    @Value("${openweather.api.url}")
    private String apiUrl;

    @Value("${openweather.api.key}")
    private String apiKey;

    public ClimaDTO buscarDadosClimaticos(String cidade) {
        log.info("Buscando dados climáticos para a cidade: {}", cidade);
        
        String url = String.format("%s/weather?q=%s&appid=%s&units=metric&lang=pt_br", apiUrl, cidade, apiKey);
        log.debug("URL da API OpenWeather: {}", url);

        try {
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            log.debug("Resposta da API: {}", response);

            if (response == null) {
                throw new RuntimeException("Resposta da API está vazia");
            }

            Double temperatura = response.path("main").path("temp").asDouble();
            String descricaoClima = response.path("weather").get(0).path("description").asText();

            Clima clima = climaMapper.toEntity(cidade, temperatura, descricaoClima);
            climaRepository.save(clima);
            log.info("Dados climáticos salvos com sucesso para a cidade: {}", cidade);

            return climaMapper.toDTO(clima);
        } catch (Exception e) {
            log.error("Erro ao buscar dados climáticos para a cidade {}: {}", cidade, e.getMessage());
            throw new RuntimeException("Erro ao buscar dados climáticos", e);
        }
    }

    public ClimaDTO buscarPrevisaoTempo(String cidade) {
        log.info("Buscando previsão do tempo para a cidade: {}", cidade);
        
        String url = String.format("%s/forecast?q=%s&appid=%s&units=metric&lang=pt_br", apiUrl, cidade, apiKey);
        log.debug("URL da API OpenWeather: {}", url);

        try {
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            log.debug("Resposta da API: {}", response);

            if (response == null) {
                throw new RuntimeException("Resposta da API está vazia");
            }

            JsonNode firstForecast = response.path("list").get(0);
            Double temperatura = firstForecast.path("main").path("temp").asDouble();
            String descricaoClima = firstForecast.path("weather").get(0).path("description").asText();

            Clima clima = climaMapper.toEntity(cidade, temperatura, descricaoClima);
            climaRepository.save(clima);
            log.info("Previsão do tempo salva com sucesso para a cidade: {}", cidade);

            return climaMapper.toDTO(clima);
        } catch (Exception e) {
            log.error("Erro ao buscar previsão do tempo para a cidade {}: {}", cidade, e.getMessage());
            throw new RuntimeException("Erro ao buscar previsão do tempo", e);
        }
    }

    public List<ClimaDTO> listarHistoricoConsultas() {
        log.info("Buscando histórico de consultas climáticas");
        return climaMapper.toDTOList(climaRepository.findAll());
    }
} 