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
}
