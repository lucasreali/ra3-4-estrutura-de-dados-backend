package br.com.reali.tde34.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespostaJogo {
    private String titulo;
    private String genero;
    private LocalDate lancamento;
}