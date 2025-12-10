package br.com.syrios.mobile.ui.professor.alunos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.api.ApiClient;
import br.com.syrios.mobile.data.api.ApiService;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.network.dto.AlunoDaOfertaRemote;
import br.com.syrios.mobile.ui.base.BaseActivity;
import br.com.syrios.mobile.ui.professor.ocorrencias.RegistrarOcorrenciaActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelecionarAlunosActivity extends BaseActivity {

    private RecyclerView recycler;
    private Button btnContinuar;
    private AlunosAdapter adapter;

    private long ofertaId;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_alunos);

        setContentLayout(R.layout.content_selecionar_alunos);
        setScreenTitle("Selecionar alunos");
        setupBottomMenu();

        ofertaId = getIntent().getLongExtra("oferta_id", -1);
        if (ofertaId == -1) {
            Toast.makeText(this, "Oferta inválida", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        session = new SessionManager(this);

        recycler = findViewById(R.id.recyclerAlunos);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        btnContinuar = findViewById(R.id.btnContinuar);

        carregarAlunos();
        configurarBotao();
    }


    private void carregarAlunos() {

        ApiService api = ApiClient.getService();

        api.getAlunosDaOferta(
                "Bearer " + session.getToken(),
                session.getCurrentSchoolId(),
                session.getCurrentRole(),
                ofertaId
        ).enqueue(new Callback<List<AlunoDaOfertaRemote>>() {
            @Override
            public void onResponse(Call<List<AlunoDaOfertaRemote>> call, Response<List<AlunoDaOfertaRemote>> response) {

                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(SelecionarAlunosActivity.this,
                            "Erro ao carregar alunos", Toast.LENGTH_SHORT).show();
                    return;
                }

                adapter = new AlunosAdapter(response.body());
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<AlunoDaOfertaRemote>> call, Throwable t) {
                Toast.makeText(SelecionarAlunosActivity.this,
                        "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void configurarBotao() {

        btnContinuar.setOnClickListener(v -> {
            if (adapter == null) return;

            List<Long> selecionados = adapter.getSelecionados();

            if (selecionados.isEmpty()) {
                Toast.makeText(this, "Selecione pelo menos 1 aluno", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent i = new Intent(this, RegistrarOcorrenciaActivity.class);
            i.putExtra("oferta_id", ofertaId);
            i.putExtra("alunos_ids", selecionados.toString()); // List<Long> → string " [1,2,3] "
            startActivity(i);
        });
    }
}
