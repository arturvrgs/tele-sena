import java.util.HashMap;
import java.util.Map;

public class TeleSenaService {
    public Map<Integer, Integer> sortear() {

        Map<Integer, Integer> numerosSorteados = new HashMap<>();

        int chave = 0;

        while (chave < 25) {
            int sorteio = (int) (Math.random() * 60 + 1);

            if (numerosSorteados.containsValue(sorteio))
                continue;

            chave++;
            numerosSorteados.put(chave, sorteio);
        }

        return numerosSorteados;
    }

    //public static adicionarNumero() {}

    public int[][] gerarConjunto() {

        Map<Integer, Integer> numerosSorteados = sortear();

        int[][] conjunto = new int[5][5];

        int chave = 1;

        for (int i = 0; i < conjunto.length; i++) {
            for (int j = 1; j < conjunto[i].length; j++) {
                conjunto[i][j] = numerosSorteados.get(chave);
                chave++;
            }
        }

        return conjunto;
    }
}
