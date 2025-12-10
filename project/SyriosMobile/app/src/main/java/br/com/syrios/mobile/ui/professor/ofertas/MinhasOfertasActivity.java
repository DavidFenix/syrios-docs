package br.com.syrios.mobile.ui.professor.ofertas;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.syrios.mobile.R;
import br.com.syrios.mobile.data.api.ApiClient;
import br.com.syrios.mobile.data.api.ApiService;
import br.com.syrios.mobile.data.session.SessionManager;
import br.com.syrios.mobile.network.dto.OfertaRemote;
import br.com.syrios.mobile.ui.base.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinhasOfertasActivity extends BaseActivity {

    private RecyclerView recycler;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentLayout(R.layout.content_ofertas);
        setScreenTitle("Minhas Ofertas");
        setupBottomMenu();

        recycler = findViewById(R.id.recyclerOfertas);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        session = new SessionManager(this);

        // Garante que há contexto válido; se não, volta ao login
        if (!session.hasValidContext()) {
            Toast.makeText(this, "Sessão inválida. Faça login novamente.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ApiService api = ApiClient.getService();

        api.getOfertas(
                "Bearer " + session.getToken(),
                session.getCurrentSchoolId(),
                session.getCurrentRole()
        ).enqueue(new Callback<List<OfertaRemote>>() {
            @Override
            public void onResponse(Call<List<OfertaRemote>> call, Response<List<OfertaRemote>> response) {

                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(MinhasOfertasActivity.this,
                            "Erro ao carregar ofertas", Toast.LENGTH_SHORT).show();
                    return;
                }

                recycler.setAdapter(new OfertasAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<OfertaRemote>> call, Throwable t) {
                Toast.makeText(MinhasOfertasActivity.this,
                        "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
