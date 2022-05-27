package Controller;
import com.example.projeto.Produtos;
public class CadastraProdutos {
    Produtos p = new Produtos();

    public void checaCodigo()
    {
        p.setCod(5);
        System.out.printf("Codigo é"+ p.getCod());
    }
}
