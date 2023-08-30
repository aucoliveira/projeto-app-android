package app.comabem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class CadastroProdutosActivity extends AppCompatActivity {

    private static final int CODIGO_REQUISICAO_CAMERA = 1;
    private ImageView imageViewFoto;
    private Button btnListar;
    private byte[] fotoByteArray;

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

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.CAMERA
            },0);
        }

    }

    public void inserir(View view) {
        EditText etNome = findViewById(R.id.nome);
        String nome =  etNome.getText().toString();

        EditText etQuantidade = findViewById(R.id.quantidade);
        Integer quantidade = Integer.valueOf(etQuantidade.getText().toString());

        EditText etPreco = findViewById(R.id.preco);
        Double preco = Double.valueOf(etPreco.getText().toString());

        byte[] fotoConvertida = fotoByteArray;

        Produto produto = new Produto(nome, quantidade, preco, fotoByteArray);
        ProdutoDAO dao;
        dao = new ProdutoDAO(this);

        Long id = dao.inserir(produto);
        Toast.makeText(this, "Produto inserido com sucesso. "+id,
                Toast.LENGTH_SHORT).show();
        //System.out.print(produto);
    }

    public void tirarFoto(View view) {
        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CODIGO_REQUISICAO_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODIGO_REQUISICAO_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            // Converte a imagem em um array de bytes
            fotoByteArray = getBytesFromBitmap(imagem);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}