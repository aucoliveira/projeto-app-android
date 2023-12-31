package app.comabem;

import java.text.DecimalFormat;

public class Produto {

    private Long id;
    private String nome;
    private int quantidade;
    private Double preco;
    private byte[] foto;

    public Produto() {
    }

    public Produto(String nome, int quantidade, Double preco, byte[] foto) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return "Nome: "+ nome+", quantidade: "+quantidade+", preço: "
                +decimalFormat.format(preco)+".";
    }

}
