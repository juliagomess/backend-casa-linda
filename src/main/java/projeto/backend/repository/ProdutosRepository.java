package projeto.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.backend.entity.Produto;

public interface ProdutosRepository extends JpaRepository <Produto,Long>{
}
