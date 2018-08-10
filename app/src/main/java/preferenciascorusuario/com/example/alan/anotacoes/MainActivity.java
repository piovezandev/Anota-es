package preferenciascorusuario.com.example.alan.anotacoes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText texto;
    private ImageView botaoSalvar;
    private static final String NOME_ARQUIVO ="arquivo.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  texto =  findViewById(R.id.editText);
  botaoSalvar = findViewById(R.id.salvarId);

botaoSalvar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String textoDigitado = texto.getText().toString();
        gravarArquivo(textoDigitado);
        Toast.makeText(MainActivity.this, "Anotação Salva!",Toast.LENGTH_SHORT).show();

    }
});

//Recuperar o que foi gravado
        if (lerArquivo()!=null){
            texto.setText(lerArquivo());
        }


 }

    private void gravarArquivo(String texto){
      try {
          OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
          outputStreamWriter.write(texto);
          outputStreamWriter.close();

   }    catch (IOException e){
          Log.v("MainActivity",e.toString());
   }
    }

    private String lerArquivo(){
        String resultado = "";
        try {
//Abrir arquivo
            InputStream arquivo = openFileInput(NOME_ARQUIVO);
            if (arquivo != null){
//ler arquivo
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                //gerar Buffer arquivo lido
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //recuperar texto arquivo

                String linhaArquivo = "";
                while ( (linhaArquivo = bufferedReader.readLine()) != null){
                    resultado+=linhaArquivo;
                    }
                    arquivo.close();

            }

        }catch (IOException e){
            Log.v("MainActivity",e.toString());
        }

        return resultado;
    }
}
