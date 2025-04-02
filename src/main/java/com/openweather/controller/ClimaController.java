package com.openweather.controller;

import com.openweather.dto.ClimaDTO;
import com.openweather.service.ClimaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clima")
@RequiredArgsConstructor
@Tag(name = "Clima", description = "API para consulta de dados climáticos")
public class ClimaController {

    private final ClimaService climaService;

    @GetMapping("/{cidade}")
    @Operation(summary = "Busca dados climáticos de uma cidade")
    public ResponseEntity<ClimaDTO> buscarDadosClimaticos(@PathVariable String cidade) {
        return ResponseEntity.ok(climaService.buscarDadosClimaticos(cidade));
    }

    @GetMapping("/previsao/{cidade}")
    @Operation(summary = "Busca previsão do tempo para uma cidade")
    public ResponseEntity<ClimaDTO> buscarPrevisaoTempo(@PathVariable String cidade) {
        return ResponseEntity.ok(climaService.buscarPrevisaoTempo(cidade));
    }

    @GetMapping("/historico")
    @Operation(summary = "Lista histórico de consultas climáticas")
    public ResponseEntity<List<ClimaDTO>> listarHistoricoConsultas() {
        return ResponseEntity.ok(climaService.listarHistoricoConsultas());
    }
} 