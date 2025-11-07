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

    @PostMapping("/ordenar/bubble-sort")
    public ResponseEntity<JogosOrdenadosResponse> bubbleSort(@Valid @RequestBody OrdenarJogosRequest request) {
        long inicio = System.currentTimeMillis();
        List<Jogo> jogosOrdenados = biblioteca.bubbleSort(request.getCriterio());
        long fim = System.currentTimeMillis();

        JogosOrdenadosResponse response = new JogosOrdenadosResponse(
            "Bubble Sort",
            request.getCriterio(),
            jogosOrdenados,
            fim - inicio
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ordenar/insertion-sort")
    public ResponseEntity<JogosOrdenadosResponse> insertionSort(@Valid @RequestBody OrdenarJogosRequest request) {
        long inicio = System.currentTimeMillis();
        List<Jogo> jogosOrdenados = biblioteca.insertionSort(request.getCriterio());
        long fim = System.currentTimeMillis();

        JogosOrdenadosResponse response = new JogosOrdenadosResponse(
            "Insertion Sort",
            request.getCriterio(),
            jogosOrdenados,
            fim - inicio
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ordenar/quick-sort")
    public ResponseEntity<JogosOrdenadosResponse> quickSort(@Valid @RequestBody OrdenarJogosRequest request) {
        long inicio = System.currentTimeMillis();
        List<Jogo> jogosOrdenados = biblioteca.quickSort(request.getCriterio());
        long fim = System.currentTimeMillis();

        JogosOrdenadosResponse response = new JogosOrdenadosResponse(
            "Quick Sort",
            request.getCriterio(),
            jogosOrdenados,
            fim - inicio
        );

        return ResponseEntity.ok(response);
    }
}