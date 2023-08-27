package app.comabem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroProdutosActivity extends AppCompatActivity {

    private Button btnListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        btnListar = findViewById(R.id.btnListar);

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroProdutosActivity.this,
                        Listagem.class);
                startActivity(intent);
            }
        });
    }

    public void inserir(View view) {
        EditText etNome = findViewById(R.id.nome);
        String nome =  etNome.getText().toString();

        EditText etQuantidade = findViewById(R.id.quantidade);
        Integer quantidade = Integer.valueOf(etQuantidade.getText().toString());

        EditText etPreco = findViewById(R.id.preco);
        Double preco = Double.valueOf(etPreco.getText().toString());

        Produto produto = new Produto(nome, quantidade, preco);
        ProdutoDAO dao;
        dao = new ProdutoDAO(this);

        Long id = dao.inserir(produto);
        Toast.makeText(this, "Produto inserido com sucesso.",
                Toast.LENGTH_SHORT).show();

    }
}