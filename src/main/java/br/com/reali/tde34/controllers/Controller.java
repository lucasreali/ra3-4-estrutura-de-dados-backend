package br.com.reali.tde34.controllers;

import br.com.reali.tde34.dtos.CriarJogoRequest;
import br.com.reali.tde34.dtos.JogosOrdenadosResponse;
import br.com.reali.tde34.dtos.OrdenarJogosRequest;
import br.com.reali.tde34.entities.Jogo;
import br.com.reali.tde34.storage.Biblioteca;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class Controller {

    private final Biblioteca biblioteca = new Biblioteca();

    @PostMapping
    public ResponseEntity<String> criarJogo(@Valid @RequestBody CriarJogoRequest request) {
        Jogo jogo = new Jogo(request.getTitulo(), request.getGenero(), request.getLancamento());
        biblioteca.inserir(jogo);
        return ResponseEntity.ok("Jogo criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> listarJogos() {
        return ResponseEntity.ok(biblioteca.listarJogos());
    }

    @DeleteMapping("/{titulo}")
    public ResponseEntity<String> removerJogo(@PathVariable String titulo) {
        boolean removido = biblioteca.remover(titulo);

        if (removido) {
            return ResponseEntity.ok("Jogo removido com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ordenar/bubble-sort")
    public ResponseEntity<JogosOrdenadosResponse> bubbleSort(@Valid @RequestBody OrdenarJogosRequest request) {
        long inicio = System.nanoTime();
        List<Jogo> jogosOrdenados = biblioteca.bubbleSort(request.getCriterio());
        long fim = System.nanoTime();

        long tempoNano = fim - inicio;
        long tempoMicro = tempoNano / 1_000;

        JogosOrdenadosResponse response = new JogosOrdenadosResponse(
            "Bubble Sort",
            request.getCriterio(),
            tempoMicro,
            jogosOrdenados
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ordenar/insertion-sort")
    public ResponseEntity<JogosOrdenadosResponse> insertionSort(@Valid @RequestBody OrdenarJogosRequest request) {
        long inicio = System.nanoTime();
        List<Jogo> jogosOrdenados = biblioteca.insertionSort(request.getCriterio());
        long fim = System.nanoTime();

        long tempoNano = fim - inicio;
        long tempoMicro = tempoNano / 1_000;

        JogosOrdenadosResponse response = new JogosOrdenadosResponse(
            "Insertion Sort",
            request.getCriterio(),
            tempoMicro,
            jogosOrdenados
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ordenar/quick-sort")
    public ResponseEntity<JogosOrdenadosResponse> quickSort(@Valid @RequestBody OrdenarJogosRequest request) {
        long inicio = System.nanoTime();
        List<Jogo> jogosOrdenados = biblioteca.quickSort(request.getCriterio());
        long fim = System.nanoTime();

        long tempoNano = fim - inicio;
        long tempoMicro = tempoNano / 1_000;

        JogosOrdenadosResponse response = new JogosOrdenadosResponse(
            "Quick Sort",
            request.getCriterio(),
            tempoMicro,
            jogosOrdenados
        );

        return ResponseEntity.ok(response);
    }
}