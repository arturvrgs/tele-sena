import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TeleSenaController implements Runnable{

    public final TeleSenaService teleSenaService;

    public TeleSenaController(TeleSenaService teleSenaService) {
        this.teleSenaService = teleSenaService;
    }

    public void mostrarSorteio(Map<Integer, Integer> sorteio) {

        System.out.println("================= SORTEIO ==================");
        for (int i = 1; i < sorteio.size(); i++) {

            int num = sorteio.get(i);

            System.out.print("[  " + num + (num >= 10 ? "  ]"  : "   ]") + " ");

            if(i % 5 == 0)
                System.out.println();

        }
        System.out.println("============================================");
    }

    public void mostrarCartelasCompradas(Pessoa[] jogadores) {
        for (Pessoa jogador : jogadores) {
            System.out.println("TELESENAS DE " + jogador.getNome().toUpperCase() + " " + jogador.getSobrenome().toUpperCase());
            System.out.println("============================================");
            for (TeleSena telesena : jogador.getTelesenas()) {
                System.out.println("============================================");
                System.out.println("CONJUNTO 1");
                telesena.exibirConjunto(telesena.getConjunto1());
                System.out.println("--------------------------------------------");
                System.out.println("CONJUNTO 2");
                telesena.exibirConjunto(telesena.getConjunto2());
                System.out.println("============================================");
                System.out.println();
            }
        }
    }



    @Override
    public void run() {

        teleSenaService.gerarJogadores();
        teleSenaService.comprarTeleSenas();

        Map<Integer, Integer> sorteio = teleSenaService.sortear();
        Pessoa[] jogadores = teleSenaService.getJogadores();

        mostrarSorteio(sorteio);
        mostrarCartelasCompradas(jogadores);
    }


}
