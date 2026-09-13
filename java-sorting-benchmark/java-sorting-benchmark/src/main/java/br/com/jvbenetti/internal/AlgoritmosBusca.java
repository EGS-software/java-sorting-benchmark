package br.com.jvbenetti.internal;

public class AlgoritmosBusca {
    public static int buscaLinear(int[] vetor, int alvo) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == alvo) {
                return i;
            }
        }
        return -1;
    }

    public static int buscaBinaria(int[] vetor, int alvo) {
        int inicio = 0;
        int fim = vetor.length - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2;
            if (vetor[meio] == alvo) return meio;
            if (vetor[meio] < alvo) inicio = meio + 1;
            else fim = meio - 1;
        }
        return -1;
    }
}