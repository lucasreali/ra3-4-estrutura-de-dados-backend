package br.com.reali.tde34.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {
    private String titulo;
    private String genero;
    private LocalDate lancamento;
}
