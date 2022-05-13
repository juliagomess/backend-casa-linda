package produtos;

public class Produtos {
    private int cod;
    private String nome;
    private String fornecedor;
    private String grupo;
    private String descricao;
    private float custo;

    public void setCod (int newCod) {
        this.cod = newCod;    
    }
    public int getCod () {
        return this.cod; 
    }

    public void setNome (String newNome) {
        this.nome = newNome;
    }
    public String getNome () {
        return this.nome; 
    }

    public void setFornecedor (String newFornecedor) {
        this.fornecedor = newFornecedor;    
    }
    public String getNome () {
        return this.fornecedor; 
    }

    public void setGrupo (String newGrupo) {
        this.grupo = newGrupo;    
    }
    public String getGrupo () {
        return this.grupo; 
    }

    public void setDescricao (String newDescricao) {
        this.descricao= newDescricao;    
    }
    public String getDescricao () {
        return this.descricao; 
    }

    public void setCusto (float newCusto) {
        this.custo = newCusto;    
    }
    public String getCusto () {
        return this.custo; 
    }

    public void cadastroProduto (String newNome, float newCusto, String newFornecedor, String newGrupo, String newDescricao) {
        this.setNome(newNome);
        this.setCusto(newCusto);
        this.setFornecedor(newFornecedor);
        this.setGrupo(newGrupo);
        this.setDescricao(newDescricao);
    }
}