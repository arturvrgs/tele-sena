import java.util.Arrays;
import java.util.List;

public class TeleSena {

    private final double preco = 10;
    private int[][] conjunto1;
    private int[][] conjunto2;


    public double getPreco() {
        return preco;
    }

    public void setConjunto1(int[][] conjunto1) {
        this.conjunto1 = conjunto1;
    }

    public void setConjunto2(int[][] conjunto2) {
        this.conjunto2 = conjunto2;
    }

    public int[][] getConjunto1() {
        return conjunto1;
    }

    public int[][] getConjunto2() {
        return conjunto2;
    }

    public void exibirConjuntos(int[][] conjunto1, int[][] conjunto2) {
        for (int i = 0; i < conjunto1.length; i++) {

            for (int j = 0; j < conjunto1[i].length; j++) {
                int numC1 = conjunto1[i][j];
                System.out.print("[ " + numC1 + (numC1 >= 10 ? " ]"  : "  ]") + " ");
            }

            System.out.print("      ");

            for (int j = 0; j < conjunto2[i].length; j++) {
                int numC2 = conjunto2[i][j];
                System.out.print("[ " + numC2 + (numC2 >= 10 ? " ]"  : "  ]") + " ");
            }

            System.out.println();
        }
    }
}
