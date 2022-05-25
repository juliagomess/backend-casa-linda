package projeto.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import projeto.backend.entity.Movimentacao;
import projeto.backend.entity.Produto;
import projeto.backend.service.MovimentacaoService;
import projeto.backend.service.ProdutoService;

@Component
public class Teste implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private MovimentacaoService service;
    @Autowired
    private ProdutoService produto;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
      // produto.cadastra("gabriel","joao","julia","gabriel gay", (float) 1.10);
        service.realiza(5L,1,50, 32.5f,"24/05/2022");
       System.out.println(service.findAll());
    }
}
