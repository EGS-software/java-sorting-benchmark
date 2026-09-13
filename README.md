# Java Sorting Benchmark 📊

Este repositório contém um projeto acadêmico de análise e medição de desempenho (benchmarking) de algoritmos de busca e ordenação implementados em Java. O objetivo é comparar a eficiência de algoritmos de complexidade `O(N²)` versus `O(N log N)` em diferentes cenários de distribuição de dados.

## 🛠️ Algoritmos Implementados

### Algoritmos de Ordenação
*   **Bubble Sort** (com *flag* de otimização)
*   **Selection Sort**
*   **Insertion Sort**
*   **Quick Sort** (Divisão e Conquista)

### Algoritmos de Busca
*   **Busca Linear** (Sequencial)
*   **Busca Binária**

## 📦 Estrutura do Projeto

O código está organizado seguindo o padrão de pacotes do Java (`br.com.jvbenetti`):

*   `src/main/java/br/com/jvbenetti/internal/AlgoritmosOrdenacao.java`: Contém a lógica dos algoritmos de ordenação.
*   `src/main/java/br/com/jvbenetti/internal/AlgoritmosBusca.java`: Contém a lógica dos algoritmos de busca.
*   `src/main/java/br/com/jvbenetti/internal/GeradorDados.java`: Classe utilitária para gerar os vetores de teste (Aleatório, Já Ordenado, Inversamente Ordenado e com Duplicatas).
*   `src/main/java/br/com/jvbenetti/main/BenchmarkMain.java`: Classe principal que orquestra a geração de dados, executa os algoritmos, captura o tempo (via `System.currentTimeMillis()`) e imprime os resultados.

## ⚙️ Cenários de Teste

O benchmark avalia os algoritmos utilizando conjuntos de dados de variados tamanhos (N = 1.000, 10.000, 50.000, 500.000 e 1.000.000) nos seguintes estados:
1.  **Aleatório:** Elementos distribuídos randomicamente.
2.  **Já Ordenado:** Elementos em ordem crescente.
3.  **Inverso Ordenado:** Elementos em ordem estritamente decrescente (pior caso para muitos métodos).
4.  **Com Duplicatas:** Conjunto com uma alta taxa de valores repetidos.

> **Nota:** Algoritmos de complexidade `O(N²)` são intencionalmente ignorados pela classe `BenchmarkMain` para N >= 500.000 visando evitar tempos de execução impraticáveis (horas/dias).

## 🚀 Como Compilar e Executar no Terminal

Para rodar o projeto via linha de comando, certifique-se de ter o [JDK](https://www.oracle.com/java/technologies/downloads/) instalado e siga os passos abaixo:

1. Abra o terminal e navegue até o diretório raiz dos pacotes Java (`src/main/java`):
   ```bash
   cd caminho/para/o/projeto/src/main/java
---

🪡 Feito por https://github.com/jvbenetti