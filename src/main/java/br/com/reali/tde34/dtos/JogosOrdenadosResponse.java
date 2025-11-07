package br.com.reali.tde34.dtos;

import br.com.reali.tde34.entities.Jogo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JogosOrdenadosResponse {
    private String algoritmo;
    private String criterio;
    private List<Jogo> jogos;
    private long tempoExecucaoMs;
}
