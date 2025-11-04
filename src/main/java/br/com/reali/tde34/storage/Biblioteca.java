package br.com.reali.tde34.storage;

import br.com.reali.tde34.entity.Jogo;

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
        int id = geraId(jogo.getName());
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
            if (jogo.getName().equals(name)) {
                return jogo;
            }
        }

        return null;
    }

    public Jogo[] listarJogos() {
        ArrayList<Jogo> todosJogos = new ArrayList<>();
        for (ArrayList<Jogo> lista : jogos.values()) {
            todosJogos.addAll(lista);
        }
        return todosJogos.toArray(new Jogo[0]);
    }
}
