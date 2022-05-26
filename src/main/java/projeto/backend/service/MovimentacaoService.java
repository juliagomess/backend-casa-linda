package projeto.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.backend.entity.Movimentacao;
import projeto.backend.entity.Produto;
import projeto.backend.repository.MovimentacoesRepository;
import projeto.backend.repository.ProdutosRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacoesRepository repository;
    private ProdutoService produtos;
    public Movimentacao realiza(long cod_produto, int tipo, int quantidade, float valor, String data){
        Movimentacao movimentacao = new Movimentacao();
        try {
            if (produtos.retorna(cod_produto)) {
                movimentacao.setCod_produto(cod_produto);
                movimentacao.setTipo(tipo);
                movimentacao.setQuantidade(quantidade);
                movimentacao.setValor_entrada(valor);
                movimentacao.setData(data);
                return repository.save(movimentacao);
            }
        }catch (NullPointerException e){
            System.out.println(e);
            return null;
        }
        return null;
    }
    public List<Movimentacao> findAll(){
        return repository.findAll();
    }
}
