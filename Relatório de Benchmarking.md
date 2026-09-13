# Relatório de Benchmarking: Algoritmos de Ordenação

## 1. Fundamentação Teórica e Complexidade

Os algoritmos de ordenação podem ser divididos em métodos elementares (iterativos) e métodos eficientes (divisão e conquista). Abaixo, um resumo de cada um dos métodos analisados:

*   **Bubble Sort (com flag):** Compara pares de elementos adjacentes e os troca de posição se estiverem na ordem errada. O uso da *flag* (sinalizador) permite interromper o algoritmo precocemente caso nenhuma troca seja feita em uma varredura completa.
*   **Selection Sort:** Busca o menor elemento do vetor e o coloca na primeira posição, repetindo o processo para as posições seguintes. Ele não se beneficia da organização prévia dos dados, pois sempre varre o restante do vetor em busca do menor valor.
*   **Insertion Sort:** Constrói o vetor ordenado um elemento de cada vez, inserindo cada novo elemento na posição correta em relação aos elementos já processados. Muito eficiente para vetores pequenos ou quase ordenados.
*   **Quick Sort:** Método de divisão e conquista que escolhe um elemento como pivô e particiona o vetor em duas metades (menores que o pivô e maiores que o pivô), chamando-se recursivamente para cada metade.

### Tabela de Complexidade Teórica (Big-O)

| Algoritmo | Melhor Caso | Caso Médio | Pior Caso | Complexidade de Espaço |
| :--- | :--- | :--- | :--- | :--- |
| **Bubble Sort (c/ flag)** | O(N) | O(N²) | O(N²) | O(1) |
| **Selection Sort** | O(N²) | O(N²) | O(N²) | O(1) |
| **Insertion Sort** | O(N) | O(N²) | O(N²) | O(1) |
| **Quick Sort** | O(N log N) | O(N log N) | O(N²) | O(log N) a O(N) na Pilha |

---

## 2. Tabela Comparativa de Resultados

Os resultados abaixo representam o tempo real de execução em milissegundos (ms) capturado no console.

| Algoritmo | Vetor (N) | Aleatório | Já Ordenado | Inverso Ordenado | C/ Duplicatas |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Bubble Sort** | 1.000 | 5 ms | 0 ms | 2 ms | 1 ms |
| **Selection Sort** | 1.000 | 1 ms | 1 ms | 0 ms | 0 ms |
| **Insertion Sort**| 1.000 | 2 ms | 0 ms | 6 ms | 0 ms |
| **Quick Sort** | 1.000 | 0 ms | 1 ms | 1 ms | 0 ms |
| | | | | | |
| **Bubble Sort** | 10.000 | 49 ms | 0 ms | 91 ms | 51 ms |
| **Selection Sort**| 10.000 | 19 ms | 18 ms | 28 ms | 19 ms |
| **Insertion Sort**| 10.000 | 4 ms | 0 ms | 9 ms | 4 ms |
| **Quick Sort** | 10.000 | 1 ms | 24 ms | 20 ms | 5 ms |
| | | | | | |
| **Bubble Sort** | 50.000 | 2723 ms | 0 ms | 2270 ms | 2379 ms |
| **Selection Sort**| 50.000 | 474 ms | 454 ms | 757 ms | 456 ms |
| **Insertion Sort**| 50.000 | 115 ms | 0 ms | 229 ms | 94 ms |
| **Quick Sort** | 50.000 | 3 ms | *Erro (Pilha)* | *Erro (Pilha)* | 122 ms |
| | | | | | |
| **Bubble Sort** | 500.000 | *Pulado* | *Pulado* | *Pulado* | *Pulado* |
| **Selection Sort**| 500.000 | *Pulado* | *Pulado* | *Pulado* | *Pulado* |
| **Insertion Sort**| 500.000 | *Pulado* | *Pulado* | *Pulado* | *Pulado* |
| **Quick Sort** | 500.000 | 30 ms | *Erro (Pilha)* | *Erro (Pilha)* | *Erro (Pilha)* |
| | | | | | |
| **Bubble Sort** | 1.000.000 | *Pulado* | *Pulado* | *Pulado* | *Pulado* |
| **Selection Sort**| 1.000.000 | *Pulado* | *Pulado* | *Pulado* | *Pulado* |
| **Insertion Sort**| 1.000.000 | *Pulado* | *Pulado* | *Pulado* | *Pulado* |
| **Quick Sort** | 1.000.000 | 61 ms | *Erro (Pilha)* | *Erro (Pilha)* | *Erro (Pilha)* |

> **Nota sobre restrição de tempo:** Para N = 500.000 e 1.000.000, os testes dos algoritmos iterativos (O(N²)) foram pulados intencionalmente na implementação do *benchmark*. Como a curva de tempo cresce de forma quadrática, a execução levaria um tempo inviável (horas) para ser concluída na máquina de testes.

---

## 3. Análise Crítica e Conclusão

**1. O Desempenho do Quick Sort vs. Métodos Elementares**
Quando observamos o cenário **Aleatório** com 50.000 elementos, os algoritmos elementares demoraram entre 115 ms (Insertion) e 2723 ms (Bubble). O Quick Sort, por outro lado, resolveu em apenas **3 ms**. Isso ocorre porque o Quick Sort possui complexidade `O(N log N)`, crescendo muito mais lentamente do que a complexidade `O(N²)` dos métodos elementares quando o volume de dados aumenta exponencialmente.

**2. O Comportamento do Bubble Sort (com flag) em Vetores Ordenados**
Nos vetores **Já Ordenados**, o Bubble Sort apresentou tempo de **0 ms** para todos os tamanhos avaliados. Isso acontece graças à variável (flag) que verifica se houve troca. Como o vetor já está ordenado, ele faz apenas uma varredura (complexidade `O(N)`) e encerra. Em contrapartida, o Selection Sort, por não ter esse mecanismo lógico de detecção, continuou apresentando tempos elevados (ex: 454 ms em 50.000 itens) porque obrigatoriamente faz todas as varreduras de comparação buscando pelo menor elemento.

**3. O Estouro de Pilha (StackOverflowError) no Quick Sort**
Os testes revelaram uma limitação grave: ao submeter vetores organizados (Já Ordenados e Inverso Ordenado) com 50.000 elementos ou mais, ocorreu o erro *StackOverflowError*. 
Isso acontece porque a implementação escolhe o último elemento como pivô. Em um vetor já ordenado, o particionamento se torna extremamente desbalanceado (uma parte fica vazia e a outra fica com N-1 elementos). Com isso, a profundidade das chamadas recursivas atinge N (em vez de `log N`), sobrecarregando a memória da pilha (*Call Stack*) do Java e resultando na quebra do programa. Esse é o Pior Caso teórico do Quick Sort `O(N²)`.

**Conclusão**
*   **Insertion Sort:** Demonstrou ser o melhor algoritmo entre os elementares para a maioria dos casos práticos, sendo extremamente eficiente e recomendável para conjuntos de dados que já estão quase totalmente ordenados.
*   **Bubble Sort e Selection Sort:** Devem ser usados apenas para fins educacionais ou vetores extremamente pequenos, dado o alto custo computacional em dados desordenados.
*   **Quick Sort:** É imbatível em tempo real para grandes volumes de dados aleatórios. Contudo, para implementações em produção, é mandatório aplicar otimizações (como a escolha do pivô via mediana de 3 ou seleção aleatória) para evitar os problemas catastróficos de estouro de pilha documentados neste benchmark.

---

## 4. Declaração de Uso de Inteligência Artificial

**Declaro para os devidos fins que utilizei Inteligência Artificial generativa neste trabalho:**
*   **Ferramenta utilizada:** Google Gemini
*   **Finalidade do uso:** Geração da base estrutural em código Java para o benchmark, elaboração dos métodos geradores de massa de dados e formatação das tabelas e texto do relatório.
*   **Etapas em que foi aplicada:** Na fase de configuração inicial do projeto e na interpretação dos resultados do console para a construção do Markdown analítico.
*   **Procedimentos de validação:** O código fornecido pela IA foi compilado nativamente via terminal local (`javac` e `java`), os algoritmos foram executados empiricamente na minha máquina e os tempos de CPU foram colhidos e transpostos manualmente para o relatório.