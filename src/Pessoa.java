import java.util.List;

public class Pessoa {
    private String nome;
    private List<TeleSena> telesenas;
    private int valorPremiado;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<TeleSena> getTelesenas() {
        return telesenas;
    }

    public void setTelesenas(List<TeleSena> telesenas) {
            this.telesenas = telesenas;
    }

    public int getValorPremiado() {
        return valorPremiado;
    }

    public void setValorPremiado(int valorPremiado) {
        this.valorPremiado = valorPremiado;
    }
}
