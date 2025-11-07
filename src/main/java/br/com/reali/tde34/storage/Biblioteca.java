package br.com.reali.tde34.storage;

import br.com.reali.tde34.entities.Jogo;

import java.util.ArrayList;
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

    public ArrayList<Jogo> bubbleSort(String criterio) {

        ArrayList<Jogo> lista = new ArrayList<>(listarJogos());
        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {
            boolean houveTroca = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (comparar(lista.get(j), lista.get(j + 1), criterio) > 0) {

                    Jogo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                    houveTroca = true;
                }
            }
            if (!houveTroca) {
                break;
            }
        }
        
        return lista;
    }

    public ArrayList<Jogo> insertionSort(String criterio) {
        ArrayList<Jogo> lista = new ArrayList<>(listarJogos());

        for (int i = 1; i < lista.size(); i++) {
            Jogo chave = lista.get(i);
            int j = i - 1;

            while (j >= 0 && comparar(lista.get(j), chave, criterio) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }

            lista.set(j + 1, chave);
        }
        return lista;
    }


    public ArrayList<Jogo> quickSort(String criterio) {
        ArrayList<Jogo> lista = new ArrayList<>(listarJogos());
        quickSortRecursivo(lista, 0, lista.size() - 1, criterio);
        return lista;
    }

    private void quickSortRecursivo(ArrayList<Jogo> lista, int inicio, int fim, String criterio) {
        if (inicio < fim) {
            int indicePivo = particionar(lista, inicio, fim, criterio);
            quickSortRecursivo(lista, inicio, indicePivo - 1, criterio);
            quickSortRecursivo(lista, indicePivo + 1, fim, criterio);
        }
    }

    private int particionar(ArrayList<Jogo> lista, int inicio, int fim, String criterio) {
        Jogo pivo = lista.get(fim);
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (comparar(lista.get(j), pivo, criterio) <= 0) {
                i++;
                Jogo temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }

        Jogo temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(fim));
        lista.set(fim, temp);

        return i + 1;
    }
}
