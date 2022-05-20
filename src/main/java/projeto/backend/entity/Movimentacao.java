package projeto.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class Movimentacao {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    @ForeignKey
    private Produto produto;
    private int tipo;
    private int quantidade;
    private float valor_entrada;
    private String data;

}
