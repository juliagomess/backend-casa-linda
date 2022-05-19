package projeto.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import projeto.backend.entity.Produto;
import projeto.backend.service.ProdutoService;

@Component
public class Teste implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private ProdutoService service;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Produto produto = new Produto();
       // produto.setNome("joao");
        //service.save(produto);
        service.deletebyId(1L);
       System.out.println(service.findAll());

    }
}
