package br.com.reali.tde34.storage;

import br.com.reali.tde34.entity.Jogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Biblioteca {
    private final HashMap<Integer, ArrayList<Jogo>> jogos;

    public Biblioteca() {
        this.jogos = new HashMap<>();
    }

    private int geraId(String nome) {
        return nome.length();
    }

    public void inserir(Jogo jogo) {
        int id = geraId(jogo.getTitulo());
        if (!jogos.containsKey(id)) {
            jogos.put(id, new ArrayList<>());
        }
        jogos.get(id).add(jogo);
    }

    public boolean remover(int id) {
        return jogos.remove(id) != null;
    }

    public Jogo buscar(String name) {
        int id = geraId(name);
        ArrayList<Jogo> lista = jogos.get(id);

        if (lista == null) {
            return null;
        }

        for (Jogo jogo : lista) {
            if (jogo.getTitulo().equals(name)) {
                return jogo;
            }
        }

        return null;
    }

    public ArrayList<Jogo> listarJogos() {
        ArrayList<Jogo> todosJogos = new ArrayList<>();
        for (ArrayList<Jogo> lista : jogos.values()) {
            todosJogos.addAll(lista);
        }
        return todosJogos;
    }

    private int comparar(Jogo jogo1, Jogo jogo2, String criterio) {
        return switch (criterio.toLowerCase()) {
            case "titulo" -> jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo());
            case "genero" -> jogo1.getGenero().compareToIgnoreCase(jogo2.getGenero());
            case "ano" -> Integer.compare(
                    jogo1.getLancamento().getYear(),
                    jogo2.getLancamento().getYear()
            );
            default -> 0;
        };
    }

    public ArrayList<Jogo> boubleSort() {

    }

    public ArrayList<Jogo> insertionSort() {

    }

    public ArrayList<Jogo> quickSort() {

    }
}
