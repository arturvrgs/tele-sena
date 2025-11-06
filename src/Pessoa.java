import java.util.List;

public class Pessoa {
    private String nome;
    private String sobrenome;
    private String nomeCompleto;
    private List<TeleSena> telesenas;
    private double valorPremiado;

    public Pessoa(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomeCompleto = nome + " " + sobrenome;
    }

    public String getNomeCompleto() {return nomeCompleto;}

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

    public double getValorPremiado() {
        return valorPremiado;
    }

    public void setValorPremiado(double valorPremiado) {
        this.valorPremiado = valorPremiado;
    }
}
