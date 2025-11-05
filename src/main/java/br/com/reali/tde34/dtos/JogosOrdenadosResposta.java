package br.com.reali.tde34.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JogosOrdenadosResposta {
    private ArrayList<RespostaJogo> jogos;
    private String criterio;
    private String algoritmo;
    private int total;
}

