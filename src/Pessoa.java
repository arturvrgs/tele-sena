import java.util.List;

public class Pessoa {
    private String nome;
    private String sobrenome;
    private List<TeleSena> telesenas;
    private int valorPremiado;

    public Pessoa(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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
