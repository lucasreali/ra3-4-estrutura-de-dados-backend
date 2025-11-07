# API de Ordenação de Jogos

Esta API fornece endpoints para organizar jogos usando diferentes algoritmos de ordenação.

## Endpoints Disponíveis

### 1. Criar Jogo
**POST** `/api/jogos`

Cria um novo jogo na biblioteca.

**Body:**
```json
{
  "titulo": "The Legend of Zelda",
  "genero": "Aventura",
  "lancamento": "1986-02-21"
}
```

**Validações:**
- `titulo`: Obrigatório, não pode estar em branco
- `genero`: Obrigatório, não pode estar em branco  
- `lancamento`: Obrigatório, deve ser uma data no passado

### 2. Listar Jogos
**GET** `/api/jogos`

Retorna todos os jogos cadastrados na biblioteca.

### 3. Bubble Sort
**POST** `/api/jogos/ordenar/bubble-sort`

Ordena os jogos usando o algoritmo Bubble Sort.

**Body:**
```json
{
  "criterio": "titulo"
}
```

### 4. Insertion Sort
**POST** `/api/jogos/ordenar/insertion-sort`

Ordena os jogos usando o algoritmo Insertion Sort.

**Body:**
```json
{
  "criterio": "genero"
}
```

### 5. Quick Sort
**POST** `/api/jogos/ordenar/quick-sort`

Ordena os jogos usando o algoritmo Quick Sort.

**Body:**
```json
{
  "criterio": "ano"
}
```

## Critérios de Ordenação

Os seguintes critérios são aceitos:
- `titulo`: Ordena por título (alfabética)
- `genero`: Ordena por gênero (alfabética)  
- `ano`: Ordena por ano de lançamento (numérica)

## Resposta de Ordenação

Todos os endpoints de ordenação retornam:

```json
{
  "algoritmo": "Quick Sort",
  "criterio": "titulo", 
  "jogos": [
    {
      "titulo": "Super Mario Bros",
      "genero": "Plataforma",
      "lancamento": "1985-09-13"
    }
  ],
  "tempoExecucaoMs": 2
}
```

## Validações

- O campo `criterio` é obrigatório e deve ser exatamente: "titulo", "genero" ou "ano"
- Campos em branco ou nulos retornarão erro 400 Bad Request
- Datas futuras para lançamento não são aceitas
