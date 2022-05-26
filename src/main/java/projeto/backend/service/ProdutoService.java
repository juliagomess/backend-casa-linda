package projeto.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.backend.entity.Produto;
import projeto.backend.repository.ProdutosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutosRepository repository;

    public Produto cadastra(String nome, String fornecedor, String grupo, String descricao, float custo){
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setFornecedor(fornecedor);
        produto.setGrupo(grupo);
        produto.setDescricao(descricao);
        produto.setCusto(custo);
        return repository.save(produto);
    }
    public void deletebyId(Long id){
        Optional<Produto> read = read(id);
        if(read.isPresent()){
            repository.delete(read.get());
        }

    }
    public Optional<Produto> read(Long id){
        return repository.findById(id);
    }
    public List<Produto> findAll(){
        return repository.findAll();
    }

    public boolean retorna(Long id){
        Produto p = new Produto();
        p = repository.getById(id);
        System.out.println(p);
        return false;
    }
}
