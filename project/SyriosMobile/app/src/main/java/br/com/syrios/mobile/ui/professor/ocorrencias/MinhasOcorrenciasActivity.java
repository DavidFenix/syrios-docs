package br.com.syrios.mobile.ui.professor.ocorrencias;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.api.ApiClient;
import br.com.syrios.mobile.data.api.ApiService;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.network.dto.OcorrenciaRemote;
import br.com.syrios.mobile.ui.base.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinhasOcorrenciasActivity extends BaseActivity {

    private RecyclerView recycler;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentLayout(R.layout.content_ocorrencias);
        setScreenTitle("Minhas Ocorrências");
        setupBottomMenu();

        recycler = findViewById(R.id.recyclerOcorrencias);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        session = new SessionManager(this);

        if (!session.hasValidContext()) {
            Toast.makeText(this, "Sessão inválida. Faça login novamente.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ApiService api = ApiClient.getService();

        api.getOcorrencias(
                "Bearer " + session.getToken(),
                session.getCurrentSchoolId(),
                session.getCurrentRole()
        ).enqueue(new Callback<List<OcorrenciaRemote>>() {
            @Override
            public void onResponse(Call<List<OcorrenciaRemote>> call, Response<List<OcorrenciaRemote>> response) {

                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(MinhasOcorrenciasActivity.this,
                            "Erro ao carregar ocorrências", Toast.LENGTH_SHORT).show();
                    return;
                }

                recycler.setAdapter(new OcorrenciasAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<OcorrenciaRemote>> call, Throwable t) {
                Toast.makeText(MinhasOcorrenciasActivity.this,
                        "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
