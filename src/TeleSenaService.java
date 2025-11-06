import java.util.*;

public class TeleSenaService {

    private Map<Integer, Integer> sorteio;
    private Pessoa[] jogadores;

    public Map<Integer, Integer> getSorteio() {
        return sorteio;
    }

    public TeleSenaService() {

        sorteio = sortear();
        jogadores = new Pessoa[20];
    }

    public Map<Integer, Integer> sortear() {

        Map<Integer, Integer> numerosSorteados = new HashMap<>();

        int chave = 0;

        while (chave < 25) {
            // Sorteia número de 1 a 60
            int sorteio = (int) (Math.random() * 60 + 1);

            // Verifica se esse número já foi sorteado
            if (numerosSorteados.containsValue(sorteio))
                continue;

            chave++;

            //Adiciona número ao sorteio
            numerosSorteados.put(chave, sorteio);
        }

        return numerosSorteados;
    }

    public int[][] gerarConjunto() {

        Map<Integer, Integer> numerosSorteados = sortear();

        // Cria conjunto 5x5
        int[][] conjunto = new int[5][5];

        int chave = 1;

        // Percorre conjunto
        for (int i = 0; i < conjunto.length; i++) {
            for (int j = 0; j < conjunto[i].length; j++) {

                // Adiciona valor no conjunto
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

        TeleSena[] telesenas = new TeleSena[Dados.estoqueTelesenas];

        for (int i = 0; i < Dados.estoqueTelesenas; i++) {

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

            // Seleciona quantidade aleatória tele sena para a pessoa da iteração
            int qtdAleatoriaTelesena = (int) (Math.random() * 15 + 1);

            List<TeleSena> teleSenasDoJogador = new ArrayList<>();

            for (int i = 0; i < qtdAleatoriaTelesena; i++) {

                if (Dados.estoqueTelesenas > 0) {
                    // Seleciona telesena aleatória do estoque
                    int indiceAleatorioTelesena = (int) (Math.random() * 300);

                    // Garante que tele sena não é null
                    while (telesenas[indiceAleatorioTelesena] == null) {
                        indiceAleatorioTelesena = (int) (Math.random() * 300);
                    }

                    teleSenasDoJogador.add(telesenas[indiceAleatorioTelesena]);

                    // Incrementa o valor da telesena no valor vendido
                    Dados.valorTotalVendido += telesenas[indiceAleatorioTelesena].getPreco();

                    // Diminui estoque de tele sena
                    Dados.estoqueTelesenas--;

                    // Não permite essa tele sena ser selecionada denovo
                    telesenas[indiceAleatorioTelesena] = null;
                }
            }

            jogador.setTelesenas(teleSenasDoJogador);
        }
    }

    public List<Pessoa> verificarGanhadores() {

        List<Pessoa> ganhadores;

        while(true) {

            ganhadores = new ArrayList<>();

            // Para cada jogador
            for (Pessoa jogador : jogadores) {

                // Para cada telesena do jogador
                for (TeleSena teleSena : jogador.getTelesenas()) {

                    // Cria listas dos números dos conjuntos
                    List<Integer> numerosDoConjunto1 = new ArrayList<>();
                    List<Integer> numerosDoConjunto2 = new ArrayList<>();

                    // Percorre matriz do conjunto1 e adiciona a lista
                    for (int i = 0; i < teleSena.getConjunto1().length; i++) {
                        for (int j = 0; j < teleSena.getConjunto1().length; j++) {
                            numerosDoConjunto1.add(teleSena.getConjunto1()[i][j]);
                        }
                    }

                    // Verifica se o conjunto1 tem os mesmos valores do sorteio
                    if (sorteio.values().containsAll(numerosDoConjunto1)) {

                        teleSena.setVenceu(true);
                        teleSena.setConjuntoVencedor("Conjunto 1");

                        ganhadores.add(jogador);
                    } else {
                        // Percorre matriz do conjunto2 e adiciona a lista
                        for (int i = 0; i < teleSena.getConjunto2().length; i++) {
                            for (int j = 0; j < teleSena.getConjunto2().length; j++) {
                                numerosDoConjunto2.add(teleSena.getConjunto2()[i][j]);
                            }
                        }

                        //Verifica se o conjunto2 tem os mesmos valores do sorteio
                        if (sorteio.values().containsAll(numerosDoConjunto2)) {

                            teleSena.setVenceu(true);
                            teleSena.setConjuntoVencedor("Conjunto 2");

                            ganhadores.add(jogador);
                        }
                    }
                }
            }

            // Se há ganhadores, para o loop
            if(!ganhadores.isEmpty()) {
                break;
            }

            // Caso não há ganhadores, sorteia novo número
            aumentarSorteio();
        }

        // Percorre ganhadores
        for(Pessoa ganhador : ganhadores) {

            // Garante que se o ganhador estiver mais de uma vez na lista, ele incrementa o valor do premio ao valor premiado
            double valorPorGanhador = (double) Dados.getValorDoPremio() / ganhadores.size();
            ganhador.setValorPremiado(ganhador.getValorPremiado() + valorPorGanhador);
        }


        // Retorna ArrayList de ganhadores sem duplicatas.
        return new ArrayList<>(new HashSet<>(ganhadores));
    }

    public void aumentarSorteio() {

        // Adiciona número que ainda não existe no sorteio
        while (true) {
            int num = (int) (Math.random() * 60 + 1);

            if(!sorteio.containsValue(num)) {
                sorteio.put(sorteio.size() + 1, num);
                break;
            }
        }
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
}
