package app.comabem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String NAME = "bancoDados.db";
    private static final int VERSION = 2;

    public Conexao(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        criarTabelaProdutos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        excluirTabelaProdutos(db);
        criarTabelaProdutos(db);
    }

    private void excluirTabelaProdutos(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS produtos");
    }
    private void criarTabelaProdutos(SQLiteDatabase db) {
        db.execSQL("create table produtos (id integer primary key autoincrement," +
                "nome varchar(50), quantidade integer, preco float, foto blob )");
    }
}
