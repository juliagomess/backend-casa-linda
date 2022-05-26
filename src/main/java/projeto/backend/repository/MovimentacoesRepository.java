package projeto.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.backend.entity.Movimentacao;

public interface MovimentacoesRepository extends JpaRepository<Movimentacao,Long> {
}
