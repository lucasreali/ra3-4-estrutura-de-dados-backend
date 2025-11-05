package br.com.reali.tde34.controllers;

import br.com.reali.tde34.dtos.*;
import br.com.reali.tde34.entities.Jogo;
import br.com.reali.tde34.storage.Biblioteca;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jogos")
public class Controller {
    private final Biblioteca biblioteca = new Biblioteca();

    @PostMapping
    public ResponseEntity<RespostaJogo> criarJogo(@Valid @RequestBody CriarJogo jogo) {
        Jogo novoJogo = new Jogo(jogo.getTitulo(), jogo.getGenero(), jogo.getLancamento());
        biblioteca.inserir(novoJogo);

        RespostaJogo resposta = new RespostaJogo(novoJogo.getTitulo(), novoJogo.getGenero(), novoJogo.getLancamento());
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<ListaJogosResposta> listarJogos() {
        ArrayList<Jogo> jogos = biblioteca.listarJogos();

        ArrayList<RespostaJogo> jogosResposta = jogos.stream()
                .map(jogo -> new RespostaJogo(jogo.getTitulo(), jogo.getGenero(), jogo.getLancamento()))
                .collect(Collectors.toCollection(ArrayList::new));

        ListaJogosResposta resposta = new ListaJogosResposta(jogosResposta, jogos.size());
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<RespostaJogo> buscarJogo(@PathVariable String titulo) {
        Jogo jogo = biblioteca.buscar(titulo);
        if (jogo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new RespostaJogo(jogo.getTitulo(), jogo.getGenero(), jogo.getLancamento()));
    }

    @PutMapping("/{titulo}")
    public ResponseEntity<RespostaJogo> atualizarJogo(
            @PathVariable String titulo,
            @Valid @RequestBody AtualizarJogo jogoAtualizado) {

        Jogo jogo = biblioteca.buscar(titulo);
        if (jogo == null) {
            return ResponseEntity.notFound().build();
        }

        int idAntigo = titulo.length();
        biblioteca.remover(idAntigo);

        Jogo novoJogo = new Jogo(
                jogoAtualizado.getTitulo(),
                jogoAtualizado.getGenero(),
                jogoAtualizado.getLancamento()
        );
        biblioteca.inserir(novoJogo);

        RespostaJogo resposta = new RespostaJogo(
                novoJogo.getTitulo(),
                novoJogo.getGenero(),
                novoJogo.getLancamento()
        );
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{titulo}")
    public ResponseEntity<MensagemResposta> deletarJogo(@PathVariable String titulo) {
        Jogo jogo = biblioteca.buscar(titulo);
        if (jogo == null) {
            MensagemResposta resposta = new MensagemResposta("Jogo não encontrado", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }

        int id = titulo.length();
        boolean removido = biblioteca.remover(id);

        if (removido) {
            MensagemResposta resposta = new MensagemResposta("Jogo removido com sucesso", true);
            return ResponseEntity.ok(resposta);
        }

        MensagemResposta resposta = new MensagemResposta("Erro ao remover jogo", false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }

    @GetMapping("/ordenar/{criterio}")
    public ResponseEntity<JogosOrdenadosResposta> listarJogosOrdenados(
            @PathVariable String criterio,
            @RequestParam(defaultValue = "bubble") String algoritmo) {

        ArrayList<Jogo> jogosOrdenados = switch (algoritmo.toLowerCase()) {
            case "insertion" -> biblioteca.insertionSort(criterio);
            case "bubble" -> biblioteca.bubbleSort(criterio);
            default -> biblioteca.bubbleSort(criterio);
        };

        ArrayList<RespostaJogo> jogosResposta = jogosOrdenados.stream()
                .map(jogo -> new RespostaJogo(jogo.getTitulo(), jogo.getGenero(), jogo.getLancamento()))
                .collect(Collectors.toCollection(ArrayList::new));

        JogosOrdenadosResposta resposta = new JogosOrdenadosResposta(
                jogosResposta,
                criterio,
                algoritmo,
                jogosOrdenados.size()
        );

        return ResponseEntity.ok(resposta);
    }
}

