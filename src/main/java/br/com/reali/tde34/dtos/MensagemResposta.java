package br.com.reali.tde34.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemResposta {
    private String mensagem;
    private boolean sucesso;
}

