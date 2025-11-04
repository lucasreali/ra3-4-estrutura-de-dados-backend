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




    /**
     * criterio "titulo", "genero" ou "ano" - define o campo para ordenação
     * titulo e genero - ordem alfabética
     * ano - ordem crescente
     * @return Lista ordenada de jogos
     */
    public ArrayList<Jogo> bubbleSort(String criterio) {
        // 1. Converter a tabela hash para uma lista
        ArrayList<Jogo> lista = listarJogos();
        int n = lista.size();

        // 2. Implementar o BubbleSort
        // Loop externo: percorre toda a lista
        for (int i = 0; i < n - 1; i++) {
            boolean houveTroca = false;

            // Loop interno: compara elementos que estão lado a lado
            for (int j = 0; j < n - 1 - i; j++) {
                // Compara o elemento atual com o próximo
                if (comparar(lista.get(j), lista.get(j + 1), criterio) > 0) {
                    // Troca os elementos se estiverem fora de ordem
                    Jogo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                    houveTroca = true;
                }
            }
            // Otimização: se não houve troca, a lista já está ordenada
            if (!houveTroca) {
                break;
            }
        }
        
        return lista;
    }

    public ArrayList<Jogo> insertionSort() {

    }

    public ArrayList<Jogo> quickSort() {

    }
}



