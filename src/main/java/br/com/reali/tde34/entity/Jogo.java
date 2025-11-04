package br.com.reali.tde34.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {
    private String titulo;
    private String genero;
    private Date lançamento;
}
