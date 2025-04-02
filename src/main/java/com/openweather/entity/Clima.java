package com.openweather.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historico_consultas_clima")
public class Clima {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String cidade;
    
    @Column(nullable = false)
    private Double temperatura;
    
    @Column(nullable = false)
    private String descricaoClima;
    
    @Column(nullable = false)
    private LocalDateTime dataHoraConsulta;
} 