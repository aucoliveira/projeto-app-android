package app.comabem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Listagem extends AppCompatActivity {

    private ListView listView;
    private ProdutoDAO dao;
    private List<Produto> produtos;
    private List<Produto> produtosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listView = findViewById(R.id.listaProdutos);

        dao =  new ProdutoDAO(this);
        produtos = dao.obterTodos();
        produtosFiltrados.addAll(produtos);
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this,
                R.layout.list_item_layout, // Usando o layout customizado aqui
                R.id.text1, // ID do TextView no layout customizado
                produtos);

        listView.setAdapter(adapter);
    }
}