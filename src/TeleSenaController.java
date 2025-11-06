import java.time.Year;
import java.util.*;

public class TeleSenaController implements Runnable{

    public final TeleSenaService teleSenaService;

    // Seta velocidade padrão de visualização do jogo
    public int velocidade = 1;

    public TeleSenaController(TeleSenaService teleSenaService) {
        this.teleSenaService = teleSenaService;
    }

    public void mostrarSorteio(Map<Integer, Integer> sorteio) {

        System.out.println("================= SORTEIO INICIAL ==================");
        for (int i = 1; i <= sorteio.size(); i++) {

            int num = sorteio.get(i);

            // Se o número tiver menos de 2 digitos ele da dois espaços
            System.out.print("[  " + num + (num >= 10 ? "  ]"  : "   ]") + " ");

            // Pula linha a cada 5 iterações
            if(i % 5 == 0)
                System.out.println();

        }
        System.out.println("===================================================");
    }

    // Mostra todas as cartelas de todos os jogadores e avisa se a cartela ganhou
    public void mostrarCartelasCompradas(Pessoa[] jogadores) {
        try {
            for (Pessoa jogador : jogadores) {

                System.out.println();
                System.out.println("Nome: " + jogador.getNomeCompleto());
                Thread.sleep(800/velocidade);
                System.out.println("Quantidade de telesenas: " + jogador.getTelesenas().size());
                Thread.sleep(1000/velocidade);
                System.out.println("Telesenas: ");
                Thread.sleep(500/velocidade);

                for (TeleSena telesena : jogador.getTelesenas()) {
                    System.out.println();

                    int tempo = 700/velocidade;

                    if(telesena.isVenceu()) {

                        tempo *= 2;

                        System.out.println("ACERTOU! Ganhou pelo " + telesena.getConjuntoVencedor());
                    }

                    System.out.println("===========================================================================");
                    telesena.exibirConjuntos(telesena.getConjunto1(), telesena.getConjunto2());
                    System.out.println("===========================================================================");
                    Thread.sleep(tempo);
                }
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public void bemVindo() {
        try {
            System.out.println("=======================================================");
            System.out.println("             BEM VINDO A TELESENA " + Year.now() + "!");
            System.out.println("=======================================================");
            System.out.println();
            Thread.sleep(2000/velocidade);
            System.out.println("Sorteando...");
            Thread.sleep(2000/velocidade);
            System.out.println("Vendendo cartelas...");
            Thread.sleep(2000/velocidade);
            System.out.println("=======================================================");
            System.out.println();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void informacoesFinais(List<Pessoa> ganhadores) {

        System.out.println("=======================================================");
        System.out.println("INFORMAÇÕES FINAIS: ");
        System.out.println("=======================================================");
        System.out.println("Sorteio final: ");

        int i = 0;
        for (int numero : teleSenaService.getSorteio().values()) {

            if(i % 5 == 0) {
                System.out.println();
            }

            System.out.print("[ " + numero + (numero >= 10 ? " ]"  : "  ]") + " ");
            i++;
        }
        System.out.println();
        System.out.print("Quantidade de tele senas vendidas: ");
        System.out.println(300 - Dados.estoqueTelesenas);

        System.out.print("Quantidade de ganhadores: ");
        System.out.println(ganhadores.size());

        System.out.println("Ganhadores: ");
        for (Pessoa ganhador : ganhadores) {
            System.out.println("Nome: "+ganhador.getNomeCompleto());
            System.out.println("Valor premiado: R$ "+ganhador.getValorPremiado());
        }

        System.out.print("Valor total vendido: ");
        System.out.println("R$ "+Dados.valorTotalVendido);
        System.out.println("Lucro obtido: R$ " + (Dados.valorTotalVendido - Dados.getValorDoPremio()));
        System.out.println("=======================================================");
    }

    public void escolherVelocidade() {
        Scanner scan = new Scanner(System.in);
        System.out.println("=======================================================");
        System.out.println("Selecione a velocidade que deseja visualizar o jogo: ");
        System.out.println("(1): Normal");
        System.out.println("(2): Rápido");
        System.out.println("(3): Muito Rápido");
        System.out.println("=======================================================");

        String escolha = scan.nextLine();

        switch (escolha) {
            case "1" -> velocidade = 1;
            case "2" -> velocidade = 2;
            case "3" -> velocidade = 4;
            case "I" -> velocidade = 10;
            default -> velocidade = 1;
        }
    }



    @Override
    public void run() {

        teleSenaService.gerarJogadores();
        teleSenaService.comprarTeleSenas();

        Map<Integer, Integer> sorteio = teleSenaService.sortear();
        Pessoa[] jogadores = teleSenaService.getJogadores();

        try {
            escolherVelocidade();

            bemVindo();
            Thread.sleep(1000/velocidade);

            List<Pessoa> ganhadores = teleSenaService.verificarGanhadores();

            mostrarSorteio(sorteio);
            Thread.sleep(1000/velocidade);

            mostrarCartelasCompradas(jogadores);

            informacoesFinais(ganhadores);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
