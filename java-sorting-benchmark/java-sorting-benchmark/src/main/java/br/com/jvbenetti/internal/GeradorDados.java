package br.com.jvbenetti.internal;

import java.util.Random;
import java.util.Arrays;

public class GeradorDados {
    private static final Random random = new Random();

    public static int[] gerarAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(tamanho * 10);
        }
        return vetor;
    }

    public static int[] gerarOrdenado(int tamanho) {
        int[] vetor = gerarAleatorio(tamanho);
        Arrays.sort(vetor);
        return vetor;
    }

    public static int[] gerarInversamenteOrdenado(int tamanho) {
        int[] vetor = gerarOrdenado(tamanho);
        int[] invertido = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            invertido[i] = vetor[tamanho - 1 - i];
        }
        return invertido;
    }

    public static int[] gerarComDuplicatas(int tamanho) {
        int[] vetor = new int[tamanho];
        // Preenche com apenas 5 valores diferentes para forçar muita repetição
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(5); 
        }
        return vetor;
    }
}
