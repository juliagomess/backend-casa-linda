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
    public Produto save(Produto produto){
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
}
