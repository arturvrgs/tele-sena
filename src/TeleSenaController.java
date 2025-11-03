import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TeleSenaController implements Runnable{

    private final TeleSenaService teleSenaService;

    private int qtdTelesenas;
    private Map<Integer, Integer> sorteio;
    private Pessoa[] jogadores;

    public TeleSenaController(TeleSenaService teleSenaService) {

        this.teleSenaService = teleSenaService;

        qtdTelesenas = 300;
        sorteio = teleSenaService.sortear();
        jogadores = new Pessoa[20];
    }

    public void gerarJogadores() {

        for (int i = 0; i < jogadores.length; i++) {

            Pessoa pessoa = new Pessoa("aNÓnIMOs");

            if(jogadores[i] == null)
                jogadores[i] = pessoa;
        }
    }

    public TeleSena[] gerarTelesenas() {

        TeleSena[] telesenas = new TeleSena[qtdTelesenas];

        for (int i = 0; i < qtdTelesenas; i++) {

            TeleSena teleSena = new TeleSena();

            teleSena.setConjunto1(teleSenaService.gerarConjunto());
            teleSena.setConjunto2(teleSenaService.gerarConjunto());

            if(telesenas[i] == null)
                telesenas[i] = teleSena;
        }

        return telesenas;
    }

    public void comprarTeleSenas() {

        TeleSena[] telesenas = gerarTelesenas();

        for(Pessoa jogador : jogadores) {

            int qtdAleatoriaTelesena = (int) (Math.random() * 15 + 1);

            List<TeleSena> teleSenasDoJogador = new ArrayList<>();

            for (int i = 0; i < qtdAleatoriaTelesena; i++) {

                int indiceAleatorioTelesena = (int) (Math.random() * 300);

                teleSenasDoJogador.add(telesenas[indiceAleatorioTelesena]);
            }

            jogador.setTelesenas(teleSenasDoJogador);
        }
    }

    @Override
    public void run() {

        gerarJogadores();
        comprarTeleSenas();

        for (Pessoa jogador : jogadores) {
            System.out.println("Jogador: " + jogador.getNome());

            for (TeleSena teleSena : jogador.getTelesenas()) {
                System.out.println("Conjunto 1: " + Arrays.deepToString(teleSena.getConjunto1()));
                System.out.println("Conjunto 2: " + Arrays.deepToString(teleSena.getConjunto2()));
            }
        }

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
