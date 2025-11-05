import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeleSenaService {

    private int qtdTelesenas;
    private Map<Integer, Integer> sorteio;
    private Pessoa[] jogadores;

    public TeleSenaService() {

        qtdTelesenas = 300;
        sorteio = sortear();
        jogadores = new Pessoa[20];
    }

    public Map<Integer, Integer> sortear() {

        Map<Integer, Integer> numerosSorteados = new HashMap<>();

        int chave = 0;

        while (chave <= 25) {
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
            for (int j = 0; j < conjunto[i].length; j++) {
                conjunto[i][j] = numerosSorteados.get(chave);
                chave++;
            }
        }

        return conjunto;
    }

    public void gerarJogadores() {

        for (int i = 0; i < jogadores.length; i++) {

            String nome = TeleSenaService.obterNomeAleatorio();
            String sobrenome = TeleSenaService.obterSobrenomeAleatorio();

            Pessoa pessoa = new Pessoa(nome, sobrenome);

            if (jogadores[i] == null)
                jogadores[i] = pessoa;
        }
    }

    public TeleSena[] gerarTelesenas() {

        TeleSena[] telesenas = new TeleSena[qtdTelesenas];

        for (int i = 0; i < qtdTelesenas; i++) {

            TeleSena teleSena = new TeleSena();

            teleSena.setConjunto1(gerarConjunto());
            teleSena.setConjunto2(gerarConjunto());

            if (telesenas[i] == null)
                telesenas[i] = teleSena;
        }

        return telesenas;
    }

    public void comprarTeleSenas() {

        TeleSena[] telesenas = gerarTelesenas();

        for (Pessoa jogador : jogadores) {

            int qtdAleatoriaTelesena = (int) (Math.random() * 15 + 1);

            List<TeleSena> teleSenasDoJogador = new ArrayList<>();

            for (int i = 0; i < qtdAleatoriaTelesena; i++) {

                int indiceAleatorioTelesena = (int) (Math.random() * 300);

                if (qtdTelesenas > 0) {
                    teleSenasDoJogador.add(telesenas[indiceAleatorioTelesena]);

                    qtdTelesenas--;
                }
            }

            jogador.setTelesenas(teleSenasDoJogador);
        }
    }

    public List<Pessoa> verificarGanhadores() {

        List<Pessoa> ganhadores = new ArrayList<>();

        // Para cada jogador
        for (Pessoa jogador : jogadores) {

            // Para cada telesena do jogador
            for (TeleSena teleSena : jogador.getTelesenas()) {

                //Cria listas dos números dos conjuntos
                List<Integer> numerosDoConjunto1 = new ArrayList<>();
                List<Integer> numerosDoConjunto2 = new ArrayList<>();

                // Percorre matriz do conjunto1 e adiciona a lista
                for (int i = 0; i < teleSena.getConjunto1().length; i++) {
                    for (int j = 0; j < teleSena.getConjunto1().length; j++) {
                        numerosDoConjunto1.add(teleSena.getConjunto1()[i][j]);
                    }
                }

                //Verifica se o conjunto1 tem os mesmos valores do sorteio
                if(numerosDoConjunto1.containsAll(sorteio.values()))
                {
                   ganhadores.add(jogador);
                }
                else
                {
                    // Percorre matriz do conjunto2 e adiciona a lista
                    for (int i = 0; i < teleSena.getConjunto2().length; i++) {
                        for (int j = 0; j < teleSena.getConjunto2().length; j++) {
                            numerosDoConjunto2.add(teleSena.getConjunto2()[i][j]);
                        }
                    }

                    //Verifica se o conjunto2 tem os mesmos valores do sorteio
                    if(numerosDoConjunto2.containsAll(sorteio.values()))
                    {
                        ganhadores.add(jogador);
                    }
                }
            }
        }

        return ganhadores;
    }

    public static String obterNomeAleatorio() {

        int rand = (int) (Math.random() * Dados.nomes.split(";").length);
        return Dados.nomes.split(";")[rand];
    }

    public static String obterSobrenomeAleatorio() {

        int rand = (int) (Math.random() * Dados.sobrenomes.split(";").length );
        return Dados.sobrenomes.split(";")[rand];
    }

    public Pessoa[] getJogadores() {
        return jogadores;
    }

    public int getQtdTelesenas() {
        return qtdTelesenas;
    }

    public void setQtdTelesenas(int qtdTelesenas) {
        this.qtdTelesenas = qtdTelesenas;
    }

    public Map<Integer, Integer> getSorteio() {
        return sorteio;
    }

    public void setSorteio(Map<Integer, Integer> sorteio) {
        this.sorteio = sorteio;
    }
}
