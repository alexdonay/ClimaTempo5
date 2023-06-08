package com.donay.climatempo5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
public class CadastroLocalidadeActivity extends AppCompatActivity implements ApiCallback {
    private ApiCallback callback;
    private static final String API_URL = "http://servicos.cptec.inpe.br/XML/listaCidades?city=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_localidade);
        TextView txNomeLocalidade = findViewById(R.id.txNomeLocalidade);
        Button button = findViewById(R.id.btnPesquisaLocalidade);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ApiDataFetcher dataFetcher = new ApiDataFetcher(API_URL + txNomeLocalidade.getText(), CadastroLocalidadeActivity.this);
                    dataFetcher.execute();
            }
        });
    }
    @Override
    public void onApiFinished(LocalidadeAdapter adapter) {
        ListView listView = findViewById(R.id.lvLocalidadesCadastro);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Localidade localidadeSelecionada = adapter.getItem(position);
                cadastrarLocalidade(localidadeSelecionada);
            }
        });
    }
    @Override
    public void onPostExecute(List<Localidade> apiData) {
        LocalidadeAdapter adapter = new LocalidadeAdapter(CadastroLocalidadeActivity.this, apiData);
        ListView listView = findViewById(R.id.lvLocalidadesCadastro);

        listView.setAdapter(adapter);
        callback.onApiFinished(adapter);
    }
    @Override
    public Context getContext() {
        return this;
    }
    private void cadastrarLocalidade(Localidade localidade) {
        // Realizar o cadastro da localidade selecionada
        // Implemente o código aqui para realizar o cadastro, como enviar os dados para um servidor ou salvar localmente.

        // Exemplo de exibição de mensagem de confirmação
        Toast.makeText(CadastroLocalidadeActivity.this, "Localidade cadastrada: " + localidade.getNome(), Toast.LENGTH_SHORT).show();
    }
}
