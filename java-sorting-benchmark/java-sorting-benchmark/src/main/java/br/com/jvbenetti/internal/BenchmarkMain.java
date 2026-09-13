package main.java.br.com.jvbenetti.internal;

public class BenchmarkMain {

    public static void main(String[] args) {
        int[] tamanhos = {1000, 10000, 50000, 500000, 1000000};
        String[] casos = {"Aleatório", "Já Ordenado", "Inverso Ordenado", "C/ Duplicatas"};

        System.out.println("Iniciando Benchmarking...\n");

        for (int n : tamanhos) {
            System.out.println("==================================================");
            System.out.println("Testando para N = " + n + " elementos");
            System.out.println("==================================================");

            int[][] conjuntos = {
                GeradorDados.gerarAleatorio(n),
                GeradorDados.gerarOrdenado(n),
                GeradorDados.gerarInversamenteOrdenado(n),
                GeradorDados.gerarComDuplicatas(n)
            };

            for (int c = 0; c < casos.length; c++) {
                System.out.println("\n--- Caso: " + casos[c] + " ---");

                testarAlgoritmo("Bubble Sort", conjuntos[c].clone(), n);
                testarAlgoritmo("Selection Sort", conjuntos[c].clone(), n);
                testarAlgoritmo("Insertion Sort", conjuntos[c].clone(), n);
                
                // Evitando StackOverflowError no QuickSort com vetores grandes e já ordenados/inversos
                try {
                    testarAlgoritmo("Quick Sort", conjuntos[c].clone(), n);
                } catch (StackOverflowError e) {
                    System.out.println("Quick Sort: StackOverflowError (Estouro de pilha)");
                }
            }
            System.out.println();
        }
    }

    private static void testarAlgoritmo(String nome, int[] vetor, int tamanho) {
        // Algoritmos O(N^2) demoram muito para N >= 500.000. Pula para não travar o PC.
        if (tamanho >= 500000 && !nome.equals("Quick Sort")) {
            System.out.println(nome + ": Pulado (Demoraria horas/dias para concluir)");
            return;
        }

        long inicioTempo = System.currentTimeMillis();

        switch (nome) {
            case "Bubble Sort": AlgoritmosOrdenacao.bubbleSort(vetor); break;
            case "Selection Sort": AlgoritmosOrdenacao.selectionSort(vetor); break;
            case "Insertion Sort": AlgoritmosOrdenacao.insertionSort(vetor); break;
            case "Quick Sort": AlgoritmosOrdenacao.quickSort(vetor, 0, vetor.length - 1); break;
        }

        long fimTempo = System.currentTimeMillis();
        long tempoTotal = fimTempo - inicioTempo;

        System.out.println(nome + ": " + tempoTotal + " ms");
    }
}