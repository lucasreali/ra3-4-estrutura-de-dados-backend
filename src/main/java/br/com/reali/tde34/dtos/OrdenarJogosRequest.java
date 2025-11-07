package br.com.reali.tde34.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenarJogosRequest {

    @NotBlank(message = "O critério de ordenação é obrigatório")
    @Pattern(regexp = "^(titulo|genero|ano)$",
             message = "Critério deve ser: titulo, genero ou ano")
    private String criterio;
}
