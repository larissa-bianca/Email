package queiroz.larissa.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        // Definição da ação do click do botão
        btnEnviar.setOnClickListener(new View.OnClickListener() { //"ouvidor de clicks" do botão, captura o click
            @Override
            public void onClick(View v) { //metodo onclick é chamado quando clica o botão
                //obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail); //obtem a caixa de texto do email
                String email = etEmail.getText().toString(); //obtem o conteudo da caixa de texto

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto); //obtem a caixa de texto do assunto
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto); //obtem a caixa de texto
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO); //criação da intent

                i.setData(Uri.parse("mailto:")); //URI "mailto" seleciona apenas apps de envio e recebimento de emails

                //dados que serão enviados para a app externa
                String[] emails = new String[]{email}; //cria lista de String
                i.putExtra(Intent.EXTRA_EMAIL, emails); //lista de Strings que sao emails de destinatarios
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); //campo de assunto do email
                i.putExtra(Intent.EXTRA_TEXT, texto); //corpo do texto do email

                try{ //executa a intent
                    startActivity(Intent.createChooser(i, "Escolha o APP")); //mostra menu para escolher uma das apps disponiveis
                }
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_SHORT).show();
                    //exibe mensagem de erro
                }
            }
        });
    }
}