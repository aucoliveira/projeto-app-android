package app.comabem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ProdutoDAO(Context context) {
        conexao = new Conexao(context);
        banco =  conexao.getWritableDatabase();
    }

    public Long inserir(Produto produto) {
        ContentValues values =  new ContentValues();

        values.put("nome", produto.getNome());
        values.put("quantidade", produto.getQuantidade());
        values.put("preco", produto.getPreco());
        values.put("foto", produto.getFoto());

        return banco.insert("produtos", null, values);
    }


    public List<Produto> obterTodos(){
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor =  banco.query("produtos", new String[]{
                "id","nome", "quantidade", "preco", "foto"},
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Produto produto = new Produto();

            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            produto.setQuantidade(cursor.getInt(2));
            produto.setPreco(cursor.getDouble(3));
            produto.setFoto(cursor.getBlob(4));
            produtos.add(produto);
        }
        return produtos;
    }


}
