package app.comabem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        dao = new ProdutoDAO(this);
        produtos = dao.obterTodos();
        produtosFiltrados.addAll(produtos);
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this,
                R.layout.list_item_layout, // Usando o layout customizado aqui
                R.id.text1, // ID do TextView no layout customizado
                produtos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Listagem.this);

                builder.setTitle("Foto do Produto");
                Produto produto = produtosFiltrados.get(position); // Corrigido aqui

                // Criar uma ImageView para mostrar a foto
                ImageView imageView = new ImageView(Listagem.this);
                imageView.setImageBitmap(getBitmapFromByteArray(produto.getFoto())); // Converte o array de bytes para Bitmap
                builder.setView(imageView);

                // Botão "Fechar"
                builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                // Mostrar o AlertDialog
                AlertDialog alerta = builder.create();
                alerta.show();
            }
        });
    }

    // Método para converter um array de bytes em um Bitmap
    private Bitmap getBitmapFromByteArray(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
