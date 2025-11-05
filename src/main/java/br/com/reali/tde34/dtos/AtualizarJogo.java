package br.com.reali.tde34.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarJogo {
    @NotBlank
    @NotNull
    private String titulo;

    @NotBlank
    @NotNull
    private String genero;

    @NotNull
    private LocalDate lancamento;
}

