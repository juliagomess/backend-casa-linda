package projeto.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity

public class Produto {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long cod;
    private String nome;
    private String fornecedor;
    private String grupo;
    private String descricao;
    private float custo;

}
